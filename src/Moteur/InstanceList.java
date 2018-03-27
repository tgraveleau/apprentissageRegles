package Moteur;

import java.util.ArrayList;
import weka.core.Instance;

public class InstanceList extends ArrayList<Instance> {

	public boolean add(Instance instance) {
		
		if ( this.size() == 0 ) {
			this.add(0, instance);
		} else {
			
			for ( Instance i : this ) {
				
			}
			
		}
		
		return false;
	}
	
}
