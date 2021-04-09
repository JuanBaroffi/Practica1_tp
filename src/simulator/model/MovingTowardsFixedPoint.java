package simulator.model;

import java.util.List;

import simulator.misc.Vector2D;

public class MovingTowardsFixedPoint implements ForceLaws{
	private double   g;
	private Vector2D c;
	
	public MovingTowardsFixedPoint(Vector2D o, double g) {
		this.g = g;
		this.c = o;
	}
	
	@Override
	public void apply(List<Body> bs) {
		Vector2D a;
		for(Body bi: bs) {
			
			// Comprueba si se dirige a 0,0 o a otro punto de centro
			 
			if(this.c.getX() == 0 && this.c.getY() == 0) {		// Si se va al centro
				// F = (-g * v.di) * m
				a = bi.getPosicion().direction().scale(this.g);			
				bi.addForce(a.scale(bi.getMass()));
			}else {												// Si se va a otro punto
				// F = (-g * (v.c - v.di)) * m
				a = this.c.minus(bi.getPosicion().direction()).scale(this.g);
				bi.addForce(a.scale(bi.getMass()));
			}
		}
		
	}

	public String toString() {
		return "Moving towards " + this.c + " with constant acceleration " + this.g;
	}
}
