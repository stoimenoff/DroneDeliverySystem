package commons;

public class Product {
	
	private final String name;
	private final int weightPerQuantity;
	
	public Product(String name, int weightPerQuantity) {
		this.name = name;
		this.weightPerQuantity = weightPerQuantity;
	}
	
	public int getWeightPerQuantity() {
		return weightPerQuantity;
	}

	public String getName() {
		return name;
	}
	
}
