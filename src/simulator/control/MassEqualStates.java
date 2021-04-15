package simulator.control;

import org.json.JSONArray;
import org.json.JSONObject;

public class MassEqualStates implements StateComparator {

	public MassEqualStates() {
		
	}

	@Override
	public boolean equal(JSONObject s1, JSONObject s2) throws NotEqualStatesException {
		boolean equal = true;
		
		System.out.println("Ha entrado1");
		
		if(s1.getDouble("time") != s2.getDouble("time")) {
			equal = false;
			throw new NotEqualStatesException("Tiempos no iguales");
		}else {
			JSONArray state1 = s1.getJSONArray("bodies");
			JSONArray state2 = s2.getJSONArray("bodies");
			
			int i = 0;
			while(equal  && i < state1.length()) {
				JSONObject body1 = state1.getJSONObject(i);
				JSONObject body2 = state2.getJSONObject(i);
				if(!body1.getString("id").equals(body2.getString("id"))) {
					equal = false;
					throw new NotEqualStatesException("Id no coincide en paso " + i);
				}
					
				if(body1.getDouble("m") != body2.getDouble("m")) {
					equal = false;
					throw new NotEqualStatesException("m no coincide en paso " + i);
				}
					
				i++;
			}
		}
			
		
		/*
		
		if(s1.getDouble("time") == s2.getDouble("time")) {
			JSONArray state1 = s1.getJSONArray("bodies");
			JSONArray state2 = s2.getJSONArray("bodies");
			
			int i=0;
			while(equal ==true && i<state1.length()) {
				JSONObject body1 = state1.getJSONObject(i);
				JSONObject body2 = state2.getJSONObject(i);
				if(body1.getString("id") != body2.getString("id")) {
					equal = false;
					throw new NotEqualStatesException("Id no coincide en paso " + i);
				}
					
				if(body1.getDouble("m") != body2.getDouble("m")) {
					equal = false;
					throw new NotEqualStatesException("m no coincide en paso " + i);
				}
					
				i++;
			}
		}
		else throw new NotEqualStatesException("Tiempos no iguales");
		return equal;*/
		return equal;
		
	}
}
