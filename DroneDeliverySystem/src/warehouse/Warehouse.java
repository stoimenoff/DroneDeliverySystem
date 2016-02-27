package warehouse;

import java.util.HashMap;
import java.util.Map;

import commons.Product;
import location.Location;
import location.Location2D;

public class Warehouse {
	
	private static int IDGenerator = 0; 
	
	private final int id;
	private final Location location;
	private Map<Product, Integer> products;
	private Map<String, Product> names;
	
	public Warehouse(Location location) {
		this.id = IDGenerator += 1;
		this.location = location;
		this.products = new HashMap<>();
	}
	
	public Warehouse() {
		this(new Location2D());
	}
	
	public void supply(Product product, Integer quantity) {
		Integer currentAvailable = 0;
		if (products.containsKey(product)) {
			currentAvailable = products.get(product);
		}
		products.put(product, currentAvailable + quantity);
		names.put(product.getName(), product);
	}

	public void get(Product product, Integer quantity) {
		Integer currentAvailable = 0;
		if (!products.containsKey(product)) {
			throw new IllegalArgumentException("No such product!");
		}
		currentAvailable = products.get(product);
		if (currentAvailable < quantity) {
			throw new IllegalArgumentException("Not enough quantity!");
		}
		products.put(product, currentAvailable - quantity);
	}

	public int getId() {
		return id;
	}
	
	public Map<Product, Integer> getProducts() {
		return products;
	}

	public Location getLocation() {
		return location;
	}
	
	public Product getProductInfo(String name) {
		if (names.containsKey(name)) {
			return names.get(name);
		}
		return null;
	}
	
}
