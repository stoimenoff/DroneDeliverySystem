package commons;

import java.util.Map;

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
