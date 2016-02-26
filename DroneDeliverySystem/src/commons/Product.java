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
	
	@Override
	public boolean equals(Object obj) {
		Product other = null;
		if (obj instanceof  Product) {
			other = (Product) obj;
		} else {
			return false;
		}
		return name.equals(other.getName());
	}
	
}
