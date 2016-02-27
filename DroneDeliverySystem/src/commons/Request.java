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
	
	public int getNumberOfProducts() {
		return this.contents.size();
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("|Request|\n");
		result.append("ID: " + id + "\n");
		result.append("Type: " + type + "\n");
		if (type.equals(RequestType.Delivery)) {
			result.append("Delivery location: " + target.toString() + "\n");
		}
		result.append("Products: \n");
		String name;
		int quantity;
		for (Entry<Product, Integer> productQuantityPair : contents.entrySet()) {
			name = productQuantityPair.getKey().getName();
			quantity = productQuantityPair.getValue();
			result.append("\t" + name + ": " + quantity + "\n");
		}
		return result.toString();
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
