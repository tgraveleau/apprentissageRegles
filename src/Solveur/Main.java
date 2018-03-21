package Solveur;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader.ArffReader;

import Moteur.JeuxDInstanceSimple;

public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
				 
		ParseurArff ra = new ParseurArff();
		Instances data = ra.parse("./docs/weather.nominal.arff");
		JeuxDInstanceSimple vals = new JeuxDInstanceSimple();
		
		vals.setValues( data );
		vals.treatData();
		

	}

}
