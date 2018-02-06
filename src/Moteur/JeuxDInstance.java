package Moteur;

import java.util.ArrayList;

public class JeuxDInstance extends ArrayList<Instance> {

	private static final long serialVersionUID = 1L;

	private Field decisionField;
	private Value trueValue;
	private Value falseValue;
	
	public JeuxDInstance(Field df, Value tv, Value fv) {
		this.decisionField = df;
		this.trueValue = tv;
		this.falseValue = fv;
	}

	public Field getDecisionField() {
		return decisionField;
	}

	public Value getTrueValue() {
		return trueValue;
	}

	public Value getFalseValue() {
		return falseValue;
	}
	
	public void addValueFromList( ArrayList<String[]> list ) {
		
	}
	
}
