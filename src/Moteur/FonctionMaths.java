package Moteur;

import java.util.ArrayList;

import weka.core.Instance;

public class FonctionMaths {

	public static FonctionMaths instance;
	
	private FonctionMaths() {
	}
	
	
	private double gain(int n, int p, int N, int P) {
		return log2( ((double)n/((double)n+(double)p)) ) - log2(((double)N/((double)N+(double)P)) );
		
//		if ( p+n == 0 || P+N == 0)
//			return -9999;
//		else
//			return (double) p*(Math.log(p/(p+n))-Math.log(P/(P+N)));
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


	public double gain(Literal lit, ArrayList<Instance> pos2, ArrayList<Instance> neg2) {
		
		int nb_true =0, nb_false =0, index_attr =-1;
		
		for ( Instance inst : pos2 ) {
			if ( lit.satisfy(inst) ) {
				nb_true++;
			}
		}

		for ( Instance inst : neg2 ) {
			if ( lit.satisfy(inst) ) {
				nb_false++;
			}
		}

		return this.gain(nb_false, nb_true, neg2.size(), pos2.size());
	}
	
}
