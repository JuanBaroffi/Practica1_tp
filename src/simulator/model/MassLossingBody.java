package simulator.model;

import simulator.misc.Vector2D;

public class MassLossingBody extends Body {
	private double lossFactor;
	private double lossFrequency;
	private double c;

	public MassLossingBody(String id, Vector2D v, Vector2D p, double m, double lFactor, double lFrequency) {
		super(id,v,p,m);
		this.lossFactor = lFactor;
		this.lossFrequency = lFrequency;
		this.c = 0.0;
	}

	public double getLossFactor() {
		return this.lossFactor;
	}

	public double getLossFrequency() {
		return lossFrequency;
	}

	public void move(double t) {
		Vector2D a1;
		
		// calcule of acceleration
		
		if(m == 0) {
			a = new Vector2D();
		}else { 
			a = f.scale(1/m);
		}
		
		// change the position 

		p = p.plus(v.scale(t));
		a1 	   = a.scale(1/2);
		p = p.plus(a1.scale(t*t));
		
		// change the velocity
		
		v = v.plus(a.scale(t));
		
		this.c += t;
		
		if(this.c >= this.lossFrequency) {
			m *= 1 - this.lossFactor;
			this.c = 0.0;
		}
	}

}
