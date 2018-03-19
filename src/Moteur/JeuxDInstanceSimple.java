package Moteur;

import java.util.ArrayList;
import java.util.Arrays;

public class JeuxDInstanceSimple extends ArrayList<String[]> {

	private static final long serialVersionUID = 1L;

	private String trueValue;
	private String falseValue;
	
	public JeuxDInstanceSimple(String string, String string2) {
		this.trueValue = string;
		this.falseValue = string2;
	}

	public String getTrueValue() {
		return trueValue;
	}

	public String getFalseValue() {
		return falseValue;
	}

	public void treatData() {
		// TODO Auto-generated method stub
		int nb_true = 0, nb_false = 0;
		for (String[] instance : this) {
			// If the last value of the instance (decision field) is true
			if ( instance[ instance.length -1 ].equals( trueValue ) ) {
				nb_true++;
			} else {
				nb_false++;
			}
			System.out.println("Valeur : " + Arrays.toString(instance));
        }
		System.out.println("NB val: "+ this.size() +", NB val true: "+ nb_true +", NB val false: "+ nb_false);
	}

	public void setValues(ArrayList<String[]> values) {
		for (String[] instance : values) {
			this.add(instance);
		}
	}
	
	
	
}
