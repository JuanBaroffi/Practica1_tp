package simulator.control;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.Vector2D;

public class EpsilonEqualStates implements StateComparator {
	private double eps;
	
	public EpsilonEqualStates(double eps) {
		this.eps = eps;
	}

	@Override
	public boolean equal(JSONObject s1, JSONObject s2) {
		boolean   a = true;

		if(s1.getDouble("time") != s2.getDouble("time")) 
			return false;
		
		JSONArray ja1 = s1.getJSONArray("bodies");
		JSONArray ja2 = s2.getJSONArray("bodies");
		
		if(ja1.length() != ja2.length())
			return false;
		
		for(int i = 0; i < ja1.length() && a; i++) {
			Vector2D v1;
			Vector2D v2;
			
			if((ja1.getJSONObject(i).getString("id") != ja2.getJSONObject(i).getString("id")) || (Math.abs(ja1.getJSONObject(i).getDouble("m") -  ja2.getJSONObject(i).getDouble("m")) > this.eps)) {
				a = false;
			}
			
			if(a) {
				// Comprobamos posición
				v1 =  vector(ja1.getJSONObject(i).getJSONArray("p"));
				v2 =  vector(ja2.getJSONObject(i).getJSONArray("p"));
				
				if(v1.distanceTo(v2) > this.eps)
					a = false;
				
				if(a) {
					// Comprobamos velocidad
					
					v1 =  vector(ja1.getJSONObject(i).getJSONArray("v"));
					v2 =  vector(ja2.getJSONObject(i).getJSONArray("v"));
					
					if(v1.distanceTo(v2) > this.eps)
						a = false;
					
					if(a) {
						// Comprobamos fuerza
						v1 =  vector(ja1.getJSONObject(i).getJSONArray("f"));
						v2 =  vector(ja2.getJSONObject(i).getJSONArray("f"));
						
						if(v1.distanceTo(v2) > this.eps)
							a = false;
					}
				}
			}
			
		}
		
		return a;
	
	}
	
	
	public Vector2D vector(JSONArray a) {
		Double [] array = new Double[2];
		for(int i = 0; i < a.length();i++) {
			array[i] = a.getDouble(i);
		}
		return new Vector2D(array[0],array[1]);
		
	}

}
