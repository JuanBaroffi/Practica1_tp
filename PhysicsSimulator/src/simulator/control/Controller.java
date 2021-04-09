package simulator.control;


import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import simulator.factories.Factory;
import simulator.model.Body;
import simulator.model.PhysicsSimulator;

public class Controller{
	
	PhysicsSimulator sim;
	Factory<Body> f;
	
	public Controller(PhysicsSimulator sim, Factory<Body> f) {
		this.sim = sim;
		this.f = f;
	}

	public void loadBodies(InputStream in) {
		JSONObject jsonInput = new JSONObject(new JSONTokener(in));
		//System.out.println("longitud: " + jsonInput.length());
		JSONArray ja = jsonInput.getJSONArray("bodies");
		
		for(int i=0; i<ja.length(); i++) {
			Body b;
			b = f.createInstance(ja.getJSONObject(i));
			//System.out.println(b.getId());
			try {
			sim.addBody(b);
			}catch (IllegalArgumentException e) {
				System.out.println("[ERROR]: no deben existir dos cuerpos con el mismo id");
			}
		}
	}
	
	public void run(int n, OutputStream out, InputStream expOut, StateComparator cmp) throws NotEqualStatesException {
		boolean hasOutput=false;
		JSONObject jsonOutput = null;
		/*if(n<1) {
			sim.getState();
		}*/
		if(expOut!=null) {
			jsonOutput = new JSONObject(new JSONTokener(expOut));	
			hasOutput = true;
		}
		
		if(out !=null) {
			PrintStream p = new PrintStream(out);
			p.println("{");
			p.println("\"states\": [");
			for(int i=0; i<n; i++) {
				// run the sumulation n steps , etc.
				p.println(sim.getState());
				sim.advance();
				if(i != n-1) {
					p.print(',');
				}
				if(hasOutput) {
					if(!cmp.equal(jsonOutput.getJSONArray("states").getJSONObject(i), sim.getState())) {
						System.out.println("mal");
						throw new NotEqualStatesException();
					}
				}
			}
			p.println("]");
			p.println("}");
		}
		
		else {
			System.out.println("{");
			System.out.println("\"states\": [");

			for(int i=0; i<n; i++) {
				// run the sumulation n steps , etc.
				System.out.println(sim.getState());
				sim.advance();
				
				if(hasOutput) {
					if(!cmp.equal(jsonOutput.getJSONArray("states").getJSONObject(i), sim.getState())) {
						throw new NotEqualStatesException();
					}
				}
			}
			System.out.println("]");
			System.out.println("}");
		}
	}
	
}
