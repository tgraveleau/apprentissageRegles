package Moteur;

import java.util.ArrayList;

import weka.core.Attribute;

public class MyAttribute {

	private Attribute attr;
	private ArrayList<Literal> literraux;
	
	public MyAttribute() {
		this.literraux = new ArrayList<Literal>();
	}

	
	
	@Override
	public String toString() {
		return "MyAttribute [attr=" + attr + ", literraux=" + literraux
				+ "]";
	}


	public void incrementTrueLiteral(int i) {
		this.literraux.get(i).setNb_true( this.literraux.get(i).getNb_true() +1 );
	}

	public void incrementFalseLiteral(int i) {
		this.literraux.get(i).setNb_false( this.literraux.get(i).getNb_false() +1 );
	}
	
	public Attribute getAttr() {
		return attr;
	}

	public void setAttr(Attribute attr) {
		this.attr = attr;
	}

	public ArrayList<Literal> getLiterraux() {
		return literraux;
	}

	public void setLiterraux(ArrayList<Literal> literraux) {
		this.literraux = literraux;
	}

	
}
