package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.ForceLaws;
import simulator.model.MovingTowardsFixedPoint;

public class MovingTowardsFixedPointBuilder extends Builder<ForceLaws>{

	Vector2D c;
	double g;
	
	@Override
	public ForceLaws createInstance(JSONObject info) {
		try {
			if(info.getString("type")=="mtfp") {
				JSONObject o= info.getJSONObject("data");
				c = new Vector2D(o.getJSONArray("c").getDouble(0), o.getJSONArray("c").getDouble(1));
				g = o.getDouble("g");
				if(g ==9.81 && c.getX()==0 && c.getY()==0)
					return new MovingTowardsFixedPoint();
			}
		}
		catch(IllegalArgumentException e) {
			return null;
		}
		return null;
	}

	@Override
	public JSONObject getBuilderInfo() {
		JSONObject o = new JSONObject();
		o.put("type", "mtfp");
		JSONObject ja = new JSONObject();
		JSONArray a = new JSONArray();
		a.put(0);
		a.put(0);
		ja.put("c", a);
		
		ja.put("g", 9.81);
	
		o.put("data", ja);
		
		o.put("desc", "Moving towards a fixed point");
		
		return o;
	}

}
