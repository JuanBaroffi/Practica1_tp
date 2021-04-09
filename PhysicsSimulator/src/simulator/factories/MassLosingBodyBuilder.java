package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.Body;
import simulator.model.MassLossingBody;

public class MassLosingBodyBuilder extends Builder<Body> {

	private String id;
	private double m;
	private Vector2D p;
	private Vector2D v;
	private double freq;
	private double factor;
	
	@Override
	public Body createInstance(JSONObject info) {
		try {
			if(info.getString("type").equals("mlb")) {
				
				JSONObject o = info.getJSONObject("data");
				id = o.getString("id");
				
				JSONArray jv = o.getJSONArray("v");
				if(jv.length()==2) {
					v = new Vector2D(jv.getDouble(0), jv.getDouble(1));
				}
				else return null;

				JSONArray jp = o.getJSONArray("p");
				if(jp.length()==2) {
					p = new Vector2D(jp.getDouble(0), jp.getDouble(1));	
				}
				else return null;
				
				m = o.getDouble("m");
				
				freq = o.getDouble("freq");
				factor = o.getDouble("factor");
					
				return new MassLossingBody(id, v, p, m, factor, freq);
			}
			
		}catch (IllegalArgumentException e) {
			return null;
			//System.out.println("Illegal Argument Exception");
		}
		return null;
	}

	@Override
	public JSONObject getBuilderInfo() {
		JSONObject o = new JSONObject();
		o.put("type", "mlb");
		JSONObject ja = new JSONObject();
		ja.put("id", "b1");
		
		JSONArray p = new JSONArray();
		p.put(-3.5E10);
		p.put(0.0E00);
		ja.put("p", p);
		
		JSONArray v = new JSONArray();
		v.put(0.0E00);
		v.put(1.4E03);
		ja.put("v", v);
		
		ja.put("m", 3.0E28);
		ja.put("freq", 1E3);
		ja.put("factor", 1E-3);

		o.put("data", ja);
		
		o.put("desc", "hola");
		return o;
	}

}
