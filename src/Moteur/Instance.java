package Moteur;

import java.util.ArrayList;

public class Instance extends ArrayList<Attribut> {

	private static final long serialVersionUID = 1L;

	public Instance(ArrayList<Attribut> attributs) {
		this.addAll(attributs);
	}

	public String toString() {
		String res = "";
		
		for (Attribut attr: this) {
			res += "\n\t"+attr;
		}
		
		return res;
	}
	
}
