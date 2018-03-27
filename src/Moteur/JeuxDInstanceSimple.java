package Moteur;

import java.util.ArrayList;
import java.util.Arrays;

import weka.core.Attribute;
import weka.core.Instances;
import weka.core.Instance;

public class JeuxDInstanceSimple {

	private static final long serialVersionUID = 1L;

	private Instances instances;
	private ArrayList<MyAttribute> attributes;
	private FonctionMaths fonctionMaths;

	private String trueValue;
	private String falseValue;
	private Attribute decisionField;

	private ArrayList<Instance> instance_true;
	private ArrayList<Instance> instance_false;
	
	public JeuxDInstanceSimple() {
		this.fonctionMaths = FonctionMaths.getInstance();
		this.attributes = new ArrayList<MyAttribute>();
		this.instance_false = new ArrayList<Instance>();
		this.instance_true = new ArrayList<Instance>();
	}

	public ArrayList<ConditionRegle> apprendre() {

		ArrayList<Instance> neg = this.instance_false;
		ArrayList<Instance> pos = this.instance_true;		
		ArrayList<ConditionRegle> regles = new ArrayList<ConditionRegle>();
		ArrayList<Literal> lits = (ArrayList<Literal>) this.getLiterals().clone();
		int prev_size_pos = pos.size();
//		System.out.println("\n---- POSITIFS ----");
//		afficheInstances(pos);
		
		while ( ! pos.isEmpty() ) {
			
			ConditionRegle condition_regle = new ConditionRegle();
			ArrayList<Instance> neg2 = (ArrayList<Instance>) neg.clone();
			ArrayList<Instance> pos2 = (ArrayList<Instance>) pos.clone();
//			System.out.println("\n###\nTurn pos\n###");
			int prev_size_neg2 = neg2.size();
			
			while ( ! neg2.isEmpty() ) {
				Literal lit = literalWithMaxGain( lits, pos2, neg2 );
//				System.out.println("\n--- Turn neg2 "+ lit +" --- ");
//				System.out.println("\n---- NEGATIFS ----");
//				afficheInstances(neg2);
//				System.out.println("\n---- POSITIFS ----");
//				afficheInstances(pos2);
				
				// Remove literal with the same attribute
				ArrayList<Literal> litToRemove = new ArrayList<Literal>();
				for ( Literal l : lits ) {
					if ( l.getAttribute().name().equals( lit.getAttribute().name()) ) {
						litToRemove.add(l);
					}
				}
				lits.removeAll(litToRemove);
				
				// Remove instances that don't satisfy lit
				ArrayList<Instance> toRemove = new ArrayList<Instance>();
				for ( Instance inst : neg2 ) {
					if ( ! lit.satisfy(inst) ) {
						toRemove.add(inst);
					}
				}
				neg2.removeAll(toRemove);
				toRemove.clear();
				for ( Instance inst : pos2 ) {
					if ( ! lit.satisfy(inst) ) {
						toRemove.add(inst);
					}
				}
				pos2.removeAll(toRemove);

//				System.out.println("neg2: "+neg2);
//				System.out.println("pos2: "+pos2);
				if ( prev_size_neg2 == neg2.size() ) {
					break;
				} else {
					prev_size_neg2 = neg2.size();
					condition_regle.getLitteraux().add(lit);
				}
				
			}
			condition_regle.setThenField( this.decisionField.name() );
			condition_regle.setThenValue( this.trueValue );
		
//			System.out.println("Regle: " + condition_regle);
			
			regles.add(condition_regle);
			
//			System.out.println("\nPOS:");
//			afficheInstances(pos);
			ArrayList<Instance> toRemove = new ArrayList<Instance>();
			for ( Instance inst : pos ) {
				if ( condition_regle.satisfy(inst) ) {
					toRemove.add(inst);
				}
			}
			pos.removeAll(toRemove);

			if ( prev_size_pos == pos.size() ) {
				break;
			}
			prev_size_pos = pos.size();

		}
		
		int nb_regles = regles.size();
		for ( int i=0; i<nb_regles; i++ ) {
			System.out.println("Regle n°"+(i+1)+":\n\t"+regles.get(i));
		}
		
		return regles;		
	}

	public void setValues(Instances data) {
		this.instances = data;
		int nb_attr = data.numAttributes();
		
		// We save attributes and decision field
		for (int i=0; i< nb_attr; i++) {
			if ( i == (nb_attr-1) ) {
				this.decisionField = data.attribute(i);
				this.trueValue = data.attribute(i).value(0);
				this.falseValue = data.attribute(i).value(1);
			} else {
				// We don't save the decision field in the attributes
				MyAttribute attr = new MyAttribute();
				attr.setAttr(data.attribute(i));
				this.attributes.add( attr );
			}
		}
		
		// We see which instances are true, which are false
		for ( Instance inst : this.instances ) {
			if ( inst.stringValue(decisionField) == this.trueValue ) {
				this.instance_true.add(inst);
			} else {
				this.instance_false.add(inst);
			}
		}
		
		int
			numInstance = this.instances.numInstances(),
			numAttr = this.attributes.size();
			;
		// Foreach attributes we look how many true or false instance it has
		for ( int i_attr = 0; i_attr<numAttr; i_attr++  ) {
			MyAttribute attr = this.attributes.get(i_attr);
			
			int nbValues = attr.getAttr().numValues();
			for ( int i=0; i<nbValues; i++ ) {
				Literal literal = new Literal( attr.getAttr(), attr.getAttr().value(i) );
				attr.getLiterraux().add( i, literal );
			}
			// We add the "?" value
			attr.getLiterraux().add( nbValues, new Literal( attr.getAttr(), "?" ) );
			
			// For each instance
			for ( int i_inst=0; i_inst<numInstance; i_inst++ ) {
				Instance currentInst = this.instances.get(i_inst);
				// We get the index of the value of the attribute of the current instance
				int index_valueOfAttr = -1;
				if ( currentInst.stringValue( attr.getAttr() ).equals("?") ) {
					index_valueOfAttr = attr.getAttr().numValues();
				} else {
					for (int i_voa=0; i_voa<nbValues; i_voa++) {
						if ( attr.getAttr().value( i_voa ) == currentInst.stringValue( attr.getAttr() ) ) {
							index_valueOfAttr = i_voa;
						}
					}
				}
				
				// If the current instance leads to a "true"
				if ( currentInst.stringValue( this.decisionField ) == this.trueValue ) {
					attr.incrementTrueLiteral( index_valueOfAttr );
				} else {
					attr.incrementFalseLiteral( index_valueOfAttr );
				}
				
			}
			
		}
		
	}

	private ArrayList<Literal> getLiterals() {
		ArrayList<Literal> lits = new ArrayList<Literal>();

		for ( MyAttribute attr : this.attributes ) {
			for ( Literal lit : attr.getLiterraux() ) {
				lits.add(lit);
			}
		}
		
		return lits;
	}


	private Literal literalWithMaxGain(ArrayList<Literal> literals, ArrayList<Instance> pos2, ArrayList<Instance> neg2) {

		if ( literals.isEmpty() ) {
			return null;
		}

		Literal max = literals.get(0);
		double maxGain = this.fonctionMaths.gain(max, pos2, neg2);
		
		// Foreach literal we compute the "gain"
		int nb_lits = literals.size();
		for ( int i=1; i<nb_lits; i++ ) {
			Literal lit = literals.get(i);
			double gainLit = this.fonctionMaths.gain(lit, pos2, neg2);
			
			if (  gainLit > maxGain ) {
				max = lit;
				maxGain = gainLit;
			}
			
		}

		return max;
	}

	private void afficheInstances( ArrayList<Instance> instances) {
		for (Instance i : instances) {
			System.out.println(i);
		}
	}
	
}
