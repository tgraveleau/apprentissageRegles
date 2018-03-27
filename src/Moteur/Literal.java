package Moteur;

import weka.core.Attribute;
import weka.core.Instance;

public class Literal {

	private Attribute attribute;
	private String attributeValue;
	private int nb_true;
	private int nb_false;
	
	public Literal( Attribute attr, String value ) {
		this.attribute = attr;
		this.attributeValue = value;
	}
	
	@Override
	public String toString() {
		return attribute.name() + " = "
				+ attributeValue;
	}
	
	public boolean satisfy( Instance inst ) {
		return inst.stringValue(this.attribute).equals( this.attributeValue );
	}
	
	public Attribute getAttribute() {
		return attribute;
	}
	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}
	public String getAttributeValue() {
		return attributeValue;
	}
	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}
	public int getNb_true() {
		return nb_true;
	}
	public void setNb_true(int nb_true) {
		this.nb_true = nb_true;
	}
	public int getNb_false() {
		return nb_false;
	}
	public void setNb_false(int nb_false) {
		this.nb_false = nb_false;
	}

	
}
