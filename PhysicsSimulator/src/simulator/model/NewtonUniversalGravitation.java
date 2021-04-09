package simulator.model;

import java.util.List;

import simulator.misc.Vector2D;

public class NewtonUniversalGravitation implements ForceLaws {
    private double g;
    // arrives the constant gravitation 
    public NewtonUniversalGravitation() {
        this.g = 6.67E-11;
    }
    /*
     fi,j= G * mi* mj/ |v.pj − v.pi|^2
     1 - Primero se resta pj y pi
     2 - Se calcula la magnitud
     3 - Se comprueba si es distinto de 0
     -- Básicamente hay que hacer distanceTo(Vector2D)
     4 - Se eleva al cuadrado
     */
    
    @Override
    public void apply(List<Body> bs) {
        double   fij;
        Vector2D dij;
        
        for(Body bi: bs) {
            for(Body bj: bs) {
                
                if(bi.getId() != bj.getId()) { // Se tendría que usar el comparador | Duda
                    if(bi.getMass() > 0) {
                        fij  = ( (this.g * bi.getMass() * bj.getMass())   / (Math.pow(bj.getPosition().distanceTo(bi.getPosition()), 2))  );
                        dij  = bj.getPosition().minus(bi.getPosition()).direction();
                        
                        bi.addForce(dij.scale(fij));
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
