package drone;

import java.util.Calendar;
import java.util.Date;

import location.Location;
import warehouse.WarehouseManager;

public class Drone {

	private static int IDGenerator = 0;

	private final int id;
	private final int bateryUnits;
	private final int chargingRate;
	private final int weightCapacity;
	private int currentBatery;
	private Location currentLocation;
	private Date timeWhenAvailable;

	public Drone(int bateryUnits, int chargingRate, int weightCapacity, Location currentLocation) {
		this.id = IDGenerator += 1;
		this.bateryUnits = bateryUnits;
		this.chargingRate = chargingRate;
		this.weightCapacity = weightCapacity;
		timeWhenAvailable = Calendar.getInstance().getTime();
		currentBatery = bateryUnits;
	}

	public Date makeDelivery(Location warehouseLocation, Location deliveryLocation, Date startTime) {
		long estimatedDeliveryTime = Math.max(timeWhenAvailable.getTime(), startTime.getTime());
		double distance = getDistance(warehouseLocation, deliveryLocation);
		
		if (distance > currentBatery) {
			estimatedDeliveryTime += ((distance - currentBatery) * chargingRate * 60000);
			currentBatery = 0;
		} else {
			currentBatery -= distance;
		}
		currentLocation = WarehouseManager.getInstance().getNearestWarehouseLocation(deliveryLocation);

		estimatedDeliveryTime += (distance * 60000);
		timeWhenAvailable.setTime(estimatedDeliveryTime);

		return new Date(estimatedDeliveryTime);
	}

	public Date estimateDeliveryTime(Location warehouseLocation, Location deliveryLocation, Date startTime) {
		long estimatedDeliveryTime = Math.max(timeWhenAvailable.getTime(), startTime.getTime());
		double distance = getDistance(warehouseLocation, deliveryLocation);

		if (distance > currentBatery) {
			estimatedDeliveryTime += ((distance - currentBatery) * chargingRate * 60000);
		}

		estimatedDeliveryTime += (distance * 60000);

		return new Date(estimatedDeliveryTime);
	}

	/**
	 * Calculate drone path distance.
	 * <p>
	 * Get warehouse and delivery location and calculate the distance: current
	 * to warehouse to delivery to returning warehouse.
	 * <p>
	 */

	private double getDistance(Location warehouseLocation, Location deliveryLocation) {
		double distance = currentLocation.getDistance(warehouseLocation);
		distance += warehouseLocation.getDistance(deliveryLocation);
		return distance;
	}

	public int getId() {
		return id;
	}

	public int getCurrentBatery() {
		return currentBatery;
	}

	public Location getCurrentLocation() {
		return currentLocation;
	}

	public Date getTimeWhenAvailable() {
		return timeWhenAvailable;
	}

	public int getBateryUnits() {
		return bateryUnits;
	}

	public int getChargingRate() {
		return chargingRate;
	}

	public int getWeightCapacity() {
		return weightCapacity;
	}

}
