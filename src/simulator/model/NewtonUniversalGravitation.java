package simulator.model;

import java.util.List;

import simulator.misc.Vector2D;

public class NewtonUniversalGravitation implements ForceLaws {
    private double g;
    // arrives the constant gravitation 
    
    public NewtonUniversalGravitation() {
        this.g = 6.67E-11;
    }
    
    public NewtonUniversalGravitation(double g) {
		this.g = g;
	}
    
    @Override
    public void apply(List<Body> bs) {
        double   fij;
        double 	 mag;
        Vector2D dij;
        
        
        /*
             private Vector2D force(Body a, Body b) {
    Vector2D delta = b.getPosition().minus(a.getPosition());
    double dist = delta.magnitude();
    double magnitude = dist>0 ? (_G * a.getMass() * b.getMass()) / (dist * dist) : 0.0;
    return delta.direction().scale(magnitude);
   }



public vector2D getForce(Body b){
Vector2D f = c.minus(b.getPosition()).direction().scale(g*b.getMass());
return f;
}
         */
        for(Body bi: bs) {
            for(Body bj: bs) {
                
                if(!bi.getId().equals(bj.getId())) { 
               
                	if(bi.getMass() > 0) {
                		mag  = bj.getPosition().distanceTo(bi.getPosition());
                        fij  =  mag > 0 ? (this.g * bi.getMass() * bj.getMass())   / (Math.pow(mag, 2)) : 0.0 ;
                        dij  = bj.getPosition().minus(bi.getPosition());
                        bi.addForce(dij.direction().scale(fij));
                		/*
                		dij  = bj.getPosition().minus(bi.getPosition());
                		mag = dij.magnitude();
                        fij  =  mag > 0 ? (this.g * bi.getMass() * bj.getMass())   / (Math.pow(mag, 2)) : 0.0 ;
                        bi.addForce(dij.direction().scale(fij));
                       */
                    }else {
                        bi.resetAcceleration();
                        bi.resetVelocity();
                    }
                    
                }
            }
        }
    }
    
    public String toString() {
        return Double.toString(this.g);
    }

}