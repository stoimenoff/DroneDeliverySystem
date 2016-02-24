package commons;

import java.util.Map;
import java.util.Map.Entry;

public class Request {
	
	private static int IDGenerator = 0; 
	
	private final int id;
	private final RequestType type;
	private final long timestamp;
	
	private Map<Product, Integer> contents;
	
	public Request(RequestType t, long time, Map<Product, Integer> conts) {
		id = IDGenerator += 1;
		type = t;
		timestamp = time;
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

	public long getTimestamp() {
		return timestamp;
	}

	public RequestType getType() {
		return type;
	}

	public Map<Product, Integer> getContents() {
		return contents;
	}
}
