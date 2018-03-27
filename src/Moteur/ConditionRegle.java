package Moteur;

import java.util.ArrayList;

import weka.core.Instance;

public class ConditionRegle {

	private ArrayList<Literal> litteraux;
	private String thenField;
	private String thenValue;
	
	public ConditionRegle() {
		this.litteraux = new ArrayList<Literal>();
	}

	@Override
	public String toString() {
		String res = "SI ";
		
		int nb_lits = litteraux.size();
		for (int i=0; i<nb_lits; i++) {
			if ( i != 0 ) {
				res += " ET ";
			}
			res += this.litteraux.get(i).toString();
		}

		res += " ALORS "+ this.thenField + " = "+ this.thenValue ;
		
		return res;
	}

	public ArrayList<Literal> getLitteraux() {
		return litteraux;
	}

	public void setLitteraux(ArrayList<Literal> litteraux) {
		this.litteraux = litteraux;
	}

	public String getThenField() {
		return thenField;
	}

	public void setThenField(String thenField) {
		this.thenField = thenField;
	}

	public String getThenValue() {
		return thenValue;
	}

	public void setThenValue(String thenValue) {
		this.thenValue = thenValue;
	}

	public boolean satisfy(Instance inst) {
		
		if ( ! inst.stringValue( inst.numAttributes() -1 ).equals(this.thenValue) ) {
			return false;
		}
		
		for ( Literal lit : this.litteraux ) {
//			System.out.println(lit);
			// If only one do not satisfy, we return false
			if ( ! lit.satisfy(inst) ) {
				return false;
			}
		}
		return true;
	}
	
	
	
}
