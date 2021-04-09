package simulator.model;

import java.util.List;

import simulator.misc.Vector2D;

public class MovingTowardsFixedPoint implements ForceLaws{

	private double g;
	
	public MovingTowardsFixedPoint(){
		g = 9.81;
	}
	
	@Override
	public void apply(List<Body> bs) {
		//para cada objeto de la lista, calcular la fuerza total que se aplica a él por el resto
		//de elementos de la lista
		for(Body a: bs){
			Vector2D aceleration;
			//la direccion se calcula con la posicion del objeto y el punto (0,0)
			Vector2D direction = new Vector2D(-a.getPosition().getX(), -a.getPosition().getY());
			aceleration = direction.scale(-g);
			Vector2D force = new Vector2D();
			force = aceleration.scale(a.getMass());
			//actualizamos la fuerza del objeto, que más tarde actualizará su aceleración en move
			//a.resetForce();
			a.addForce(force);
	
			//con minus
			/*
			 * aceleration = new Vector2D()
			 * aceleration = aceleration.minus(a)
			 * 
			 * */
		}
		
	}

}
