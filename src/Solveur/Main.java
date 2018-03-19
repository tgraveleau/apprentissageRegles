package Solveur;
import java.util.Arrays;

import Moteur.JeuxDInstanceSimple;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ParseurArff ra = new ParseurArff();
		JeuxDInstanceSimple vals = new JeuxDInstanceSimple("yes", "no");
		vals.setValues( ra.parse("./docs/weather.nominal.arff") );
		
		vals.treatData();
		

	}

}
