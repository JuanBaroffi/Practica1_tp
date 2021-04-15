package simulator.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.Vector2D;

public class PhysicsSimulator {
	
	private List<Body>bs;
	private double T;
	private double dt; //tiempo real paso por paso
	private ForceLaws law;
	
	public PhysicsSimulator(double dt, ForceLaws law){
		this.dt = dt;
		T = 0.0;
		try {
			this.law = law;
		}
		catch(IllegalArgumentException e) {
			System.out.println("Illegal Argument Exception. Try to put a real law in the constructor");
		}
		bs = new ArrayList<Body>();
	}
	
	public void advance() {
		for(Body b: bs) {
			b.resetForce();
			law.apply(bs);
			b.move(dt);
			
		}
		T += dt;
	}
	
	public void addBody(Body b) {
		for(Body a: bs) {
			if(b.getId().equals(a.getId())) {
				System.out.println("Dos cuerpos iguales no pueden compartir condiciones iniciales");
				throw new IllegalArgumentException();
			}
				
		}
		bs.add(b);
	}
	
	public JSONObject getState() {
		
		JSONObject jo = new JSONObject();
		JSONArray  ja = new JSONArray();
		
		jo.put("time", T);
		for(Body b: bs) {
			ja.put(b.getState());
		}
		
		return jo.put("bodies", ja);
		
	}
	
	public String toString() {
		return getState().toString();
	}
}
