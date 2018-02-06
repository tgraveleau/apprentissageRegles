package Moteur;

public class Attribut {

	private Field field;
	private Value value;

	public Attribut(Field field, Value value) {
		this.field = field;
		this.value = value;
	}

	public String toString() {
		return this.field + ": " + this.value;
	}

	public Field getField() {
		return field;
	}

	public Value getValue() {
		return value;
	}
	
}
