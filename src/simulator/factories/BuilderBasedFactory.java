package simulator.factories;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class BuilderBasedFactory<T>  implements Factory<T>{

	List<Builder<T>> builders;
	
	public BuilderBasedFactory(List<Builder<T>>builders) {
		this.builders = builders;
	}
	
	@Override
	public T createInstance(JSONObject info) {
		for(Builder<T> b: builders) {
			T bb = b.createInstance(info);
			if(bb!=null)
				return bb;
		}
		return null;
	}

	@Override
	public List<JSONObject> getInfo() {
		List<JSONObject> list = new ArrayList<JSONObject>();
		
		for(Builder<T>b: builders) {
			list.add(b.getBuilderInfo());
		}
		return list;
	}

}
