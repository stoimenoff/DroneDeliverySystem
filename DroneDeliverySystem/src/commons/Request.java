package commons;

import java.util.Map;
import java.util.Map.Entry;

import location.Location;

public class Request { 
	
	private final int id;
	private final RequestType type;
	private final Location target;
	
	private Map<Product, Integer> contents;
	
	public Request(int id, RequestType t, Location tar, Map<Product, Integer> conts) {
		this.id = id;
		type = t;
		target = tar;
		contents = conts;
	}
	
	public int getWeight() {
		int weight = 0;
		int productWeight = 0;
		int quantity = 0;
		for(Entry<Product, Integer> productQuantityPair : contents.entrySet()) {
			productWeight = productQuantityPair.getKey().getWeightPerQuantity();
			quantity = productQuantityPair.getValue();
			weight += productWeight * quantity;
		}
		return weight;
	}
	
	//getters
	public int getId() {
		return id;
	}

	public RequestType getType() {
		return type;
	}

	public Location getTarget() {
		return target;
	}

	public Map<Product, Integer> getContents() {
		return contents;
	}
}
