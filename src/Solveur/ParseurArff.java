package Solveur;

import java.util.ArrayList;
import weka.core.Instances;
import weka.core.converters.ArffLoader.ArffReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ParseurArff {
	
	public ArrayList<String[]> parse( String filename ) {
		ArrayList<String[]> list = new ArrayList<String[]>();
		
		//@TODO
		/**
		 * Chaque donnée du fichier va être ajouté à la liste de cette façon:
		 * 
		 * list.add( [ field, vlaue ]);
		 * 
		 * car on attend un tableau de string qui correspond au couple (field, value)
		 */

	

	/**Affiche le contenu du fichier. Cette fonction va disparaître prochainement, mais ça fait une base.
	*/
	ArffReader arff;
	try {
		BufferedReader reader =	new BufferedReader(new FileReader("./docs/weather.nominal.arff"));
		arff = new ArffReader(reader);
		Instances data = arff.getData();
		data.setClassIndex(data.numAttributes()-1);
		
		for (int i = 0; i <= data.numInstances() - 1; i++) {
		    Instance instance = data.get(i);
		    list.add([instance.attribute(i%5).name(),instance.stringValue(i)]);	

		}
		
	} catch (FileNotFoundException e){
		e.printStackTrace();
	}
	
	catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

		
		return list;
	}
	/**Fonction présente pour le test*/
	public static void main(String[] args) {
		ParseurArff ra = new ParseurArff();
	}

}
