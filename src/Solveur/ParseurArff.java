package Solveur;

import java.util.ArrayList;

import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader.ArffReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ParseurArff {
	
	public static ParseurArff instance;
	
	public static ParseurArff getInstance() {
		if ( ParseurArff.instance == null ) {
			ParseurArff.instance = new ParseurArff();
		}
		return ParseurArff.instance;
	}
	
	public ArrayList<String[]> parse( String filename ) {
		ArrayList<String[]> list = new ArrayList<String[]>();
		
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
		BufferedReader reader =	new BufferedReader(new FileReader(filename));
		arff = new ArffReader(reader);
		Instances data = arff.getData();
		data.setClassIndex(data.numAttributes()-1);
		
		for (int i = 0; i <= data.numInstances()- 1; i++) {
		    String[] vals = new String[5];
		    Instance instance = data.get(i);
		    vals[0]=instance.stringValue(0);
		    vals[1]=instance.stringValue(1);
		    vals[2]=instance.stringValue(2);
		    vals[3]=instance.stringValue(3);
		    vals[4]=instance.stringValue(4);
		    list.add(vals);	

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
	

}
