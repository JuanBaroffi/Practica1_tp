package simulator.factories;

import org.json.JSONObject;

import simulator.control.MassEqualStates;
import simulator.control.StateComparator;

public class MassEqualStatesBuilder extends Builder<StateComparator>{

	@Override
	public StateComparator createInstance(JSONObject info) {
		try {
			if(info.getString("type")=="masseq") {
				return new MassEqualStates();
			}
			
		}catch(IllegalArgumentException e) {
			return null;
		}
		return null;
	}

	@Override
	public JSONObject getBuilderInfo() {
		JSONObject o = new JSONObject();
		o.put("type", "masseq");
		JSONObject ja = new JSONObject();
		
		o.put("data", ja);
		
		o.put("desc", "Mass equal states comparator");
		
		return o;
	}

}
