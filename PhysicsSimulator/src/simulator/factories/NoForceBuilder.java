package simulator.factories;

import org.json.JSONObject;

import simulator.model.ForceLaws;
import simulator.model.NoForce;

public class NoForceBuilder extends Builder<ForceLaws> {

	@Override
	public ForceLaws createInstance(JSONObject info) {
		try {
			if(info.getString("type")=="ng") {
				return new NoForce();
			}
		}catch(IllegalArgumentException e) {
			return null;
		}
		return null;
	}

	@Override
	public JSONObject getBuilderInfo() {
		JSONObject o = new JSONObject();
		o.put("type", "ng");
		JSONObject ja = new JSONObject();
		
		o.put("data", ja);
		
		o.put("desc", "No force");
		
		return o;
	}

}
