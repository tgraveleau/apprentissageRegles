package Controller;

import Moteur.JeuxDInstance;
import Solveur.ParseurArff;

public class MainController {
	
	public static MainController instance;
	
	private ParseurArff parseur;
	private JeuxDInstance jeuxDinstance;

	private  MainController() {
		this.parseur = ParseurArff.getInstance();
	}

	public void fileSelected( String path ) {

		System.out.println("MainController - File selected");
		jeuxDinstance.addValueFromList( parseur.parse( path ) );
		
	}
	
	static public MainController getInstance() {
		if ( instance == null ) {
			instance = new MainController();
		}
		return instance;
	}

}
