package Moteur;

public class FonctionMaths {

	public static FonctionMaths instance;
	
	private FonctionMaths() {
	}
	
	
	public double gain(int n, int p, int N, int P) {
		return log2( (double) (n/(n+p)) ) - log2( (double) (N/(N+P)) );
	}
	
	
	
	private double log2(double d) {
		return Math.log(d) / Math.log(2);
	}


	public static FonctionMaths getInstance() {
		if ( instance == null ) {
			instance = new FonctionMaths();
		}
		return instance;
	}
	
}
