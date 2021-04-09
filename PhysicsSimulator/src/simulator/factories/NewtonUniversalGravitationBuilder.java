package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.ForceLaws;
import simulator.model.NewtonUniversalGravitation;

public class NewtonUniversalGravitationBuilder extends Builder<ForceLaws> {

	double g;
	
	@Override
	public ForceLaws createInstance(JSONObject info) { 
		
		try {
			if(info.getString("type") =="nlug") {
				g = info.getJSONObject("data").getDouble("G");
				if(g ==6.67E-11)
					return new NewtonUniversalGravitation();
			}
			
		}catch (IllegalArgumentException e) {
			return null;
		}

		return null;
	}

	@Override
	public JSONObject getBuilderInfo() {
		JSONObject o = new JSONObject();
		o.put("type", "nlug");
		JSONObject ja = new JSONObject();
		ja.put("G", 6.67E-11);
		
		o.put("data", ja);
		
		o.put("desc", "Newton’s law of universal gravitation");
		
		return o;
	}

}
