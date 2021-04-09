package simulator.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class PhysicsSimulator {
	private ArrayList<Body> bodyList;
	private double 			actualTime;
	private double 			dt;
	private ForceLaws       fl;
	
	public PhysicsSimulator(ForceLaws fl, double dt) {
		this.bodyList   = new ArrayList<Body>();
		this.actualTime = 0.0;
		this.dt         = dt;
		this.fl 		= fl;
	}
	
	public void addBody(Body b) throws IllegalArgumentException {
		for(Body by: bodyList) {
			if(by.getID() == b.getID()){
				throw new IllegalArgumentException();
			}
		}
		this.bodyList.add(b);
		
	}
	
	public void advance() {
		// Reseteamos todas las fuerzas de la lista de Body
		for(Body b: bodyList) {
			b.resetForce();
		}
		
		// Llamamos a la función apply
		this.fl.apply(this.bodyList);
		
		// Llamamos al método move de cada Body
		for(Body b: bodyList) {
			b.move(this.dt);
		}
		
		// Incrementamos el tiempo actual
		this.actualTime += this.dt;
		
	}
	
	public JSONObject getState() {
		JSONObject jo = new JSONObject();
		JSONArray  ja = new JSONArray();
		
		jo.put("time", this.actualTime);
		for(Body b: this.bodyList) {
			ja.put(b.getState());
		}
		
		return jo.put("bodies", ja);
		
	}
	
	public String toString() {
		return getState().toString();
		
	}
}
