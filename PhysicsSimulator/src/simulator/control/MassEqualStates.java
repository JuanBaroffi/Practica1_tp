package simulator.control;

import org.json.JSONArray;
import org.json.JSONObject;

public class MassEqualStates implements StateComparator {
	
	public MassEqualStates() {}

	@Override
	public boolean equal(JSONObject s1, JSONObject s2) {
		boolean   a = true;

		if(s1.getDouble("time") != s2.getDouble("time")) 
			return false;
		
		JSONArray ja1 = s1.getJSONArray("bodies");
		JSONArray ja2 = s2.getJSONArray("bodies");
		for(int i = 0; i < ja1.length() && a;i++) {
			if((ja1.getJSONObject(i).getString("id") != ja2.getJSONObject(i).getString("id")) || (ja1.getJSONObject(i).getDouble("m") != ja2.getJSONObject(i).getDouble("m"))  || (ja1.getJSONObject(i).getString("id") == null)) {
				a = false;
			}
		}
		
		
		return a;
	}

}
