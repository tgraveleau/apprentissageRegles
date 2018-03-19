package Solveur;
import java.util.ArrayList;
import java.util.Arrays;

import UI.MainPanel;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ParseurArff ra = new ParseurArff();
		ArrayList<String[]> vals = ra.parse("./docs/weather.nominal.arff");
		for (String[] arr : vals) {
           System.out.println("Valeur : " + Arrays.toString(arr));
        }

		MainPanel window = new MainPanel();

	}

}
