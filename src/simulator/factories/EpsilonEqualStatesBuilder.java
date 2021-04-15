package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.control.EpsilonEqualState;
import simulator.control.StateComparator;

public class EpsilonEqualStatesBuilder extends Builder<StateComparator>{

	double eps;
	
	public EpsilonEqualStatesBuilder(){
		super();
	}
	@Override
	public StateComparator createInstance(JSONObject info) {
		try {
			if(info.getString("type")=="epseq") {
				eps = info.getJSONObject("data").getDouble("eps");
				//System.out.println("eps: " + eps);
				return new EpsilonEqualState(eps);
			}
		}catch(IllegalArgumentException e) {
			return null;
		}
		return null;
	}
	
	@Override
	public JSONObject getBuilderInfo() {
		
		JSONObject o = new JSONObject();
		o.put("type", "epseq");
		JSONObject ja = new JSONObject();
		ja.put("eps", 0.1);
		
		o.put("data", ja);
		
		o.put("desc", "Espsilon-equal states comparator");
		
		return o;
	}
	
}
