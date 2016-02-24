package warehouse;

import java.util.Map;
import java.util.Map.Entry;

import commons.Product;
import location.Location;

public class WarehouseManager {
	// Singleton pattern
	private static final WarehouseManager INSTANCE = new WarehouseManager();

	private Warehouse warehouse;

	private WarehouseManager() {
		warehouse = new Warehouse();
	}

	public static WarehouseManager getInstance() {
		return INSTANCE;
	}
	
	public boolean areProductsAvailable(Map<Product, Integer> wantedProducts) {
		Map<Product, Integer> allAvailables = warehouse.getProducts();
		
		for(Entry<Product, Integer> wanted : wantedProducts.entrySet()) {
			Product wantedProduct = wanted.getKey();
			Integer wantedQuantity = wanted.getValue();
			if (!allAvailables.containsKey(wantedProduct)) {
				return false;
			}
			if (allAvailables.get(wantedProduct) < wantedQuantity) {
				return false;
			}
		}
		return true;
	}
	
	public void supplyProducts(Map<Product, Integer> supply) {
		for(Entry<Product, Integer> suppliedProduct : supply.entrySet()) {
			Product product = suppliedProduct.getKey();
			Integer quantity = suppliedProduct.getValue();
			warehouse.supply(product, quantity);
		}
	}
	
	public void getProducts(Map<Product, Integer> supply) {
		for(Entry<Product, Integer> suppliedProduct : supply.entrySet()) {
			Product product = suppliedProduct.getKey();
			Integer quantity = suppliedProduct.getValue();
			warehouse.get(product, quantity);
		}
	}
	
	public Location getNearestWarehouseLocation(Location l) {
		return warehouse.getLocation();
	}
			
}
