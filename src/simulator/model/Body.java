package simulator.model;

import org.json.JSONObject;

import simulator.misc.Vector2D;

public class Body {
	protected String   id;
	protected Vector2D v;
	protected Vector2D f;
	protected Vector2D p;
	protected Vector2D a;
	protected double   m;
	
	
	public Body(String id, Vector2D v, Vector2D p, double m) {
		this.id = id;
		this.v  = v;
		this.p  = p;
		this.f  = new Vector2D();
		this.m  = m;
	}
	
	public String getID() {
		return this.id;
	}
	
	public Vector2D getVelocity() {
		return this.v;
	}
	
	public Vector2D getForce() {
		return this.f;
	}
	
	public Vector2D getPosicion() { 
		return this.p;
	}
	
	public double getMass() {
		return this.m;
	}
	
	
	
	public void addForce(Vector2D f) {
		this.f = this.f.plus(f);
	}
	
	public void resetForce() {
		this.f = new Vector2D();
	}
	
	public void resetAcceleration() {
		this.a = new Vector2D();
	}
	
	public void resetVelocity() {
		this.v = new Vector2D();
	}
	
	public void move(double t) {
		Vector2D a1;
		
		// calcule of acceleration
		
		if(this.m == 0) {
			this.a = new Vector2D();
		}else { 
			this.a = this.f.scale(1/this.m);
		}
		
		// change the position 

		this.p = this.p.plus(this.v.scale(t));
		a1 	   = this.a.scale(1/2);
		this.p = this.p.plus(a1.scale(t*t));
		
		// change the velocity
		
		this.v = this.v.plus(this.a.scale(t));
	}
	
	public JSONObject getState() {
		JSONObject jo = new JSONObject();
		
		jo.put("id", id);
		jo.put("m" ,  m);
		
	
		jo.put("p", this.p.asJSONArray());
	
		jo.put("v", this.v.asJSONArray());
		
		jo.put("f", this.f.asJSONArray());
		
		return jo;
	}
	
	public String toString() {
		return getState().toString();
	}
}
