package simulator.model;

import java.util.ArrayList;
import java.util.List;

import simulator.misc.Vector2D;

public class NewtonUniversalGravitation implements ForceLaws {

	//private List<Body> bs;
	private double G;
	private double fuerza;
	private Vector2D F; //vector resultante (vector Fuerza)
	private Vector2D direction;
	
	public NewtonUniversalGravitation() {
		//this.a = a;
		//this.b = b;
		G = 6.67E-11;	
	}
	
	
	//usar función minus!! es más legible
	Vector2D calcularFuerza(Body a, Body b) {
		//direction = new Vector2D(a.getPosition().getX()-b.getPosition().getX(),a.getPosition().getY()-b.getPosition().getY());
		direction = new Vector2D(b.getPosition().getX()-a.getPosition().getX(),b.getPosition().getY()-a.getPosition().getY());
		fuerza = G*(a.getMass()*b.getMass())/Math.pow((Math.abs(a.getPosition().distanceTo(b.getPosition()))), 2);
		F = direction.scale(fuerza);
		//System.out.println("F: " + F);
		return F;
	}	
	
	@Override
	public void apply(List<Body> bs) {
		//para cada objeto de la lista, calcular la fuerza total que se aplica a él por el resto
		//de elementos de la lista
		for(Body a: bs){
			List<Body> aux = new ArrayList<Body>(bs);
			aux.remove(a);
			Vector2D force = new Vector2D();
			System.out.println("inicio");
			for(Body elemento: aux) {
				System.out.println("fuerza antes: " + force);
				Vector2D fuerzaIntermedia = calcularFuerza(a, elemento);
				//force.plus(calcularFuerza(a, elemento));
				System.out.println("fuerza intermedia: " +fuerzaIntermedia);
				force.plus(fuerzaIntermedia);
				System.out.println("fuerza despues: " +force);
			}	
			//actualizamos la fuerza del objeto, que más tarde actualizará su aceleración en move
			//a.resetForce(); // no es necesario. Ya se hace en la simulacion
			a.addForce(force);
			//System.out.println("calculo: " +force);
		//	System.out.println("id: " + a.getId() + " force: " + a.getForce());
		}
		
		for(Body a: bs) {
			//System.out.println("posicion: " + a.getPosition());
			//System.out.println("fuerza" + a.getForce());
		}
	
		
	}

}
