package simulator.control;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.Vector2D;

public class EpsilonEqualState implements StateComparator{

	private double eps;
	
	public EpsilonEqualState(double eps) {
		this.eps = eps;
	}

	@Override
	public boolean equal(JSONObject s1, JSONObject s2) throws NotEqualStatesException {
		boolean equal = true;
		
		if(s1.getDouble("time") == s2.getDouble("time")) {
			JSONArray state1 = s1.getJSONArray("bodies");
			JSONArray state2 = s2.getJSONArray("bodies");
			
			int i=0;
			while(equal ==true && i<state1.length()) {
				JSONObject body1 = state1.getJSONObject(i);
				JSONObject body2 = state2.getJSONObject(i);
				if(body1.getString("Id") != body2.getString("Id")) {
					equal = false;
					throw new NotEqualStatesException("Id no coincide en paso " + i);
				}
					
				if(Math.abs(body1.getDouble("m")-body2.getDouble("m")) >eps) {
					equal = false;
					throw new NotEqualStatesException("m no coincide en paso " + i);
				}
				
				double p1, p2, v1, v2, f1, f2;
				JSONArray p = new JSONArray();
				JSONArray v = new JSONArray();
				JSONArray f = new JSONArray();
				p = body1.getJSONArray("p");
				v = body1.getJSONArray("v");
				f = body1.getJSONArray("f");
				
				p1 = p.getDouble(0);
				p2 = p.getDouble(1);
				v1 = v.getDouble(0);
				v2 = v.getDouble(1);
				f1 = f.getDouble(0);
				f2 = f.getDouble(1);
				
				Vector2D pos = new Vector2D(p1, p2);
				Vector2D vel = new Vector2D(v1, v2);
				Vector2D force = new Vector2D(f1, f2);
				
				//////////////////////////////////////
				
				double p11, p22, v11, v22, f11, f22;
				JSONArray pp = new JSONArray();
				JSONArray vv = new JSONArray();
				JSONArray ff = new JSONArray();
				pp = body2.getJSONArray("p");
				vv = body2.getJSONArray("v");
				ff = body2.getJSONArray("f");
				
				p11 = pp.getDouble(0);
				p22 = pp.getDouble(1);
				v11 = vv.getDouble(0);
				v22 = vv.getDouble(1);
				f11 = ff.getDouble(0);
				f22 = ff.getDouble(1);
				
				Vector2D pos1 = new Vector2D(p11, p22);
				Vector2D vel1 = new Vector2D(v11, v22);
				Vector2D force1 = new Vector2D(f11, f22);
				
				if(pos.distanceTo(pos1)>eps) {
					equal = false;
					throw new NotEqualStatesException(" Distancia entre v1 y v2 es mayor que epsilon en paso " + i);
				}
				if(vel.distanceTo(vel1)>eps) {
					equal = false;
					throw new NotEqualStatesException("Velocidad entre v1 y v2 es mayor que epsilon en paso " + i);
				}
				if(force.distanceTo(force1)>eps) {
					equal = false;
					throw new NotEqualStatesException("Fuerza entre v1 y v2 es mayor que epsilon en paso " + i);
				}
				i++;
			}
			
		}
		else throw new NotEqualStatesException("Tiempos no iguales");
		return equal;
	}
}
