package simulator.model;

import org.json.JSONObject;

import simulator.misc.Vector2D;

public class Body {
	
	protected String   id;
	protected Vector2D v; // vector velocidad
	protected Vector2D f; // vector fuerza
	protected Vector2D p; // vector posicion
	protected double   m; // masa
	
	protected Vector2D a; // aceleración
	
	public Body(String id, Vector2D v, Vector2D p, double m) {
		this.id = id;
		this.v  = v;
		this.p  = p;
		this.f  = new Vector2D();
		this.m  = m;
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
	
	public Vector2D getAceleration() {
		return a;
	}
	
	public Vector2D getPosition() {
		return p;
	}
	
	public double getMass() {
		return m;
	}
	
	void addForce(Vector2D a) {
		this.f = this.f.plus(a);
		
	}
	
	void resetForce() {
		f = new Vector2D();
	}
	
	void move(double t) {
		Vector2D a;
		//System.out.println(this.getId());
		if(m ==0) 
			a = new Vector2D();
		else 					    // calcular con segunda ley Newton a = f/m
			a = f.scale(1.0/m); 	// multiplicamos por el inverso de m	
	
		// movemos
		p = p.plus(v.scale(t)).plus(a.scale(Math.pow(t, 2)/2)); // también se puede usar función pow //p = p + v*t + 1/2*a*t^2
		v = v.plus(a.scale(t));                      // v= v + a*t
		
	}
	
	
	/*void move(double t) {
		Vector2D a;

		// calcular aceleracion
		
		if(m ==0) 
			a = new Vector2D();
		else 					    // calcular con segunda ley Newton a = f/m
			a = f.scale(1.0/m); 	// multiplicamos por el inverso de m
	
		
		
		 método move
-----------

  _f.scale(1.0 / _mass)
  _p.plus(_v.scale(t).plus(acc.scale(0.5 * t * t)))
  _v.plus(acc.scale(t))
  
		// change the position 

				this.p = this.p.plus(this.v.scale(t)).direction();
				a1 	   = a.scale(1/2);
				this.p = this.p.plus(a1.scale(t*t)).direction();
				
				// change the velocity
				
				this.v = this.v.plus(a.scale(t));
				
	
		// movemos
		//v = v.plus(a.scale(t));                      // v= v + a*t
		p = p.plus(v.scale(t)).plus(a.scale(Math.pow(t, 2)/2)); // también se puede usar función pow //p = p + v*t + 1/2*a*t^2
		v = v.plus(a.scale(t));                      // v= v + a*t
		
		//System.out.println(this.getId());
		//System.out.println("fuerza " + id + " "+ f);
		System.out.println("t " + t);
		System.out.println("a " + a);
		System.out.println("a*t " +a.scale(t));
		System.out.println("v " +v);
		System.out.println("v+(a*t) " +v.plus(a.scale(t)));
		
	}*/
	
	
	public JSONObject getState() {
		JSONObject jo = new JSONObject();
		
		jo.put("id", id);
		jo.put("m" ,  m);
		
	
		jo.put("p", this.p.asJSONArray());
	
		jo.put("v", this.v.asJSONArray());
		//System.out.println("velocidad " + v);
		jo.put("f", this.f.asJSONArray());
		
		return jo;
	}
	
	public String toString() {
		return getState().toString();
	}

	public void resetAcceleration() {
		a = new Vector2D();
	}
	
	public void resetVelocity() {
		v = new Vector2D();
	}
	
}
