package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.Body;
import simulator.model.NewtonUniversalGravitation;

public class BasicBodyBuilder extends Builder<Body> {

	private String id;
	private double m;
	private Vector2D p;
	private Vector2D v;
	
	public BasicBodyBuilder(){
		super();
	}
	
	@Override
	public Body createInstance(JSONObject info) {

		try {
			if(info.getString("type").equals("basic")) {
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
					//System.out.println("posicion cuerpo " + id + ": " + jp.getDouble(0) + ", " + jp.getDouble(1));
				}
				else return null;
				
				m = o.getDouble("m");
				
				return new Body(id, v, p, m);
			}
			
		}catch(IllegalArgumentException e) {
			return null;
		}
		return null;
	}

	@Override
	public JSONObject getBuilderInfo() {
		
		JSONObject o = new JSONObject();
		o.put("type", "basic");
		JSONObject ja = new JSONObject();
		ja.put("id", "b2");
		
		JSONArray p = new JSONArray();
		p.put(0.0E00);
		p.put(0.0E00);
		ja.put("p", p);
		
		JSONArray v = new JSONArray();
		v.put(0.05E04);
		v.put(0.0E00);
		ja.put("v", v);
		
		ja.put("m", 5.97E24);
		
		o.put("data", ja);
		
		o.put("desc", "hola");
		return o;
	}

}
