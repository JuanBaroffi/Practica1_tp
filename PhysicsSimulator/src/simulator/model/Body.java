package simulator.model;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.Vector2D;

public class Body {
	
	protected String id;
	protected Vector2D v; //vector velocidad
	protected Vector2D f; //vector fuerza
	protected Vector2D p; //vector posicion
	protected double m;   //masa
	
	//protected Vector2D a; //aceleración
	
	public Body(String id, Vector2D v, Vector2D p, double m) {
		this.id = id;
		this.v = v;
		this.p = p;
		//System.out.println("posicion cuerpo " + id + ": " + this.p.getX() + ", " + this.p.getY());
		f = new Vector2D();
		this.m = m;
		//a = new Vector2D();
	}
	
	public String getId() {
		return id;
	}
	
	public Vector2D getVelocity() {
		return v;
	}
	
	public Vector2D getForce() {
		return f;
	}
	
	/*public Vector2D getAceleration() {
		return a;
	}*/
	
	public Vector2D getPosition() {
		return p;
	}
	
	public double getMass() {
		return m;
	}
	
	void addForce(Vector2D f) {
		this.f.plus(f);
	}
	
	void resetForce() {
		f = new Vector2D();
	}
	
	void move(double t) {
		Vector2D a;
		//calcular aceleracion
		if(m ==0) 
			a = new Vector2D();
		else 
			//calcular con segunda ley Newton a = f/m
			a = f.scale(1/m); //multiplicamos por el inverso de m
	
		//movemos
		p = p.plus(v.scale(t)).plus(a.scale(t*t/2)); //también se puede usar función pow //p = p + v*t + 1/2*a*t^2
		v = v.plus(a.scale(t)); // v= v + a*t
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
