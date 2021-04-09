package simulator.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

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
			// System.out.println(b.getId());
		}
		for(Body b: bs) {
			b.resetForce();
			law.apply(bs);
			b.move(dt);
			T += dt;
		}
	}
	
	public void addBody(Body b) {
		for(Body a: bs) {
			//System.out.println(b.getId());
			//System.out.println(a.getId());
			if(b.getId().equals(a.getId())) {
				System.out.println("Dos cuerpos iguales no pueden compartir condiciones iniciales");
				throw new IllegalArgumentException();
			}
				
		}
		bs.add(b);
	}
	
	public JSONObject getState() {
		JSONObject o= new JSONObject();
		o.put("time", T);
		
		JSONArray b =  new JSONArray();
		for(Body body: bs) {
			b.put(body.toString());
		}
		
		o.put("bodies", b);
		
		return o;
	}
	
	public String toString() {
		return getState().toString();
	}
}
