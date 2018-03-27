package Solveur;

import java.util.ArrayList;

import weka.core.Attribute;
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
	
	public Instances parse( String filename ) {
		BufferedReader reader;
		ArffReader arff;
		Instances data = null;
		try {
			reader = new BufferedReader(new FileReader( filename ));
			arff = new ArffReader(reader, 1000);
			data = arff.getStructure();
			data.setClassIndex(data.numAttributes() - 1);
			Instance inst;

			while ((inst = arff.readInstance(data)) != null) {
				data.add(inst);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return data;
		/*
		ArrayList<String[]> list = new ArrayList<String[]>();
			

	// Affiche le contenu du fichier. Cette fonction va disparaître prochainement, mais ça fait une base.
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
		*/
	}


}
