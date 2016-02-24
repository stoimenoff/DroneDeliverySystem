package location;

public class Location2D implements Location {
	
	private long x;
	private long y;
	
	public Location2D() {
		this(42,42);
	}
	
	public Location2D(long x, long y) {
		this.setX(x);
		this.setY(y);
	}
	
	@Override
	public double getDistance(Location other) {
		if (other instanceof Location2D) {
			Location2D other2D = (Location2D) other;
			long deltaX = this.x - other2D.getX();
			long deltaY = this.y - other2D.getY();
			return Math.sqrt(deltaX*deltaX + deltaY*deltaY);
		} else {
			throw new IllegalArgumentException("Different location implementations...");
		}
	}

	public long getX() {
		return x;
	}

	public void setX(long x) {
		this.x = x;
	}

	public long getY() {
		return y;
	}

	public void setY(long y) {
		this.y = y;
	}

}
