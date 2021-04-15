package simulator.model;

import simulator.misc.Vector2D;

public class MassLossingBody extends Body {
 
	private double lossFactor;    // número entre 0 y 1 que representa el factor de pérdida de masa
	private double lossFrequency; // indica el intervalo de tiempo (en segundos) después del cual el objeto pierde masa
	private double c;             // contador de tiempo

	
	public MassLossingBody(String id, Vector2D v, Vector2D p, double m, double lossFactor, double lossFrequency) {
		super(id, v, p, m);
		this.lossFactor = lossFactor;
		this.lossFrequency = lossFrequency;
		c = 0.0;
	}
	
	void move(double t) {
		Vector2D a;
		//calcular aceleracion
		if(m ==0) 
			a = new Vector2D();
		else 
			//calcular con segunda ley Newton a = f/m
			a = f.scale(1.0/m); //multiplicamos por el inverso de m
	
		//movemos
		p = p.plus(v.scale(t)).plus(a.scale(t*t/2)); //también se puede usar función pow //p = p + v*t + 1/2*a*t^2
		v = v.plus(a.scale(t)); // v= v + a*t
		
		//comprueba si han pasado lossFrequency segundos desde la última vez que se redujo la masa del objeto
		if(c >= lossFrequency) {
			m = m * (1-lossFactor);
			c = 0.0;
		}
		c += t;
	}
}
