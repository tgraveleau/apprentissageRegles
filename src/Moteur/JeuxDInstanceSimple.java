package Moteur;

import java.util.ArrayList;
import java.util.Arrays;

import weka.core.Attribute;
import weka.core.Instances;
import weka.core.Instance;

public class JeuxDInstanceSimple {

	private static final long serialVersionUID = 1L;

	private Instances instances;
	private ArrayList<Attribute> attributes;
	private FonctionMaths fonctionMaths;

	// Counter of true and false value for each attributes
	private ArrayList<int[]> counters_true;
	private ArrayList<int[]> counters_false;
	
	private String trueValue;
	private String falseValue;
	private Attribute decisionField;

	private int nb_false;
	private int nb_true;
	
	public JeuxDInstanceSimple() {
		this.fonctionMaths = FonctionMaths.getInstance();
		this.attributes = new ArrayList<Attribute>();
		this.counters_false = new ArrayList<int[]>();
		this.counters_true = new ArrayList<int[]>();
		this.nb_true = 0;
		this.nb_false = 0;
	}

	public void treatData() {
		// TODO Auto-generated method stub
		System.out.println(this.instances);
		System.out.println("\nTRUE: "+this.nb_true+", FALSE: "+this.nb_false+"\n");
		
		int nbAttr = this.attributes.size();
		for ( int i_attr = 0; i_attr<nbAttr; i_attr++ ) {
			Attribute attr = this.attributes.get(i_attr);
			System.out.println("###\n"+attr+"\n###\n");
			int numVal = attr.numValues();
			for ( int i=0; i<numVal; i++) {
				System.out.println(attr.value(i));
				System.out.println("\tTRUE: "+ this.counters_true.get( i_attr )[i] );
				System.out.println("\tFALSE: "+ this.counters_false.get( i_attr )[i] );
				System.out.println("\tGAIN : "+ this.fonctionMaths.gain(this.counters_false.get( i_attr )[i],this.counters_true.get( i_attr )[i],this.nb_false, this.nb_true ));
			}
		}
		
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
				this.attributes.add( data.attribute(i) );
			}
		}
		
		// We count how many true and false values there is
		for ( Instance inst : this.instances ) {
			if ( inst.stringValue(decisionField) == this.trueValue ) {
				this.nb_true++;
			} else {
				this.nb_false++;
			}
		}
		
		int numInstance = this.instances.numInstances();
		// Foreach attributes we look how many true or false instance it has
		for ( Attribute attr : this.attributes ) {
//			System.out.println( attr );
			
			int nbValues = attr.numValues();
			// We declare tables with as many row as possible values for the attribute to count true and false instance
			int[] counter_true = new int[ nbValues ];
			int[] counter_false = new int[ nbValues ];
			for ( int i=0; i<nbValues; i++ ) {
				counter_true[i] = 0;
				counter_false[i] = 0;
			}
			
			// For each instance
			for ( int i_inst=0; i_inst<numInstance; i_inst++ ) {
				Instance currentInst = this.instances.get(i_inst);
				
				// We get the index of the value of the attribute of the current instance
				int index_valueOfAttr = -1;
				for (int i_voa=0; i_voa<nbValues; i_voa++) {
					if ( attr.value( i_voa ) == currentInst.stringValue( attr ) ) {
						index_valueOfAttr = i_voa;
					}
				}
//				System.out.println("\t"+currentInst.stringValue( attr ) +", i: "+index_valueOfAttr);
				
				// If the current instance leads to a "true"
				if ( currentInst.stringValue( this.decisionField ) == this.trueValue ) {
					counter_true[ index_valueOfAttr ]++;
//					System.out.println( "\t"+currentInst.stringValue( attr ) +", true: "+ counter_true[ index_valueOfAttr ]);
				} else {
					counter_false[ index_valueOfAttr ]++;
//					System.out.println( "\t"+currentInst.stringValue( attr ) +", false: "+ counter_false[ index_valueOfAttr ]);
				}
				
			}
			
			// At the end of the loop, we add the counters to the ArrayLists
			this.counters_true.add(counter_true);
			this.counters_false.add(counter_false);
		}
		
	}
	
	
	
}
