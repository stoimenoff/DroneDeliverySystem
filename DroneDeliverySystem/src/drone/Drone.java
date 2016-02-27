package drone;

import java.util.Calendar;
import java.util.Date;

import commons.Request;
import location.Location;
import warehouse.WarehouseManager;

public class Drone {

	private static int IDGenerator = 0;

	private final int id;
	private final double batteryUnits;
	private final int chargingRate;
	private final int weightCapacity;
	private double currentBattery;
	private Location currentLocation;
	private Date timeWhenAvailable;

	public Drone(double batteryUnits, int chargingRate, int weightCapacity, Location currentLocation) {
		IDGenerator += 1;
		this.id = IDGenerator;
		this.batteryUnits = batteryUnits;
		this.chargingRate = chargingRate;
		this.weightCapacity = weightCapacity;
		timeWhenAvailable = Calendar.getInstance().getTime();
		currentBattery = batteryUnits;
		this.currentLocation = currentLocation;
	}

	public Date makeDelivery(Location warehouseLocation, Request request, Location deliveryLocation, Date startTime) {
		long estimatedDeliveryTime = 0;
		int deltaBattery = 0;
		if (timeWhenAvailable.getTime() > startTime.getTime()) {
			estimatedDeliveryTime = timeWhenAvailable.getTime();
		} else {
			estimatedDeliveryTime = startTime.getTime();
			deltaBattery = (int) (startTime.getTime() - timeWhenAvailable.getTime()) * chargingRate * 60000;
		}
		estimatedDeliveryTime += request.getNumberOfProducts() * 60000;

		double deliveryDistance = getDistance(warehouseLocation, deliveryLocation);
		Location nearestWarehouse = WarehouseManager.getInstance().getNearestWarehouseLocation(deliveryLocation);
		double returnDistance = deliveryLocation.getDistance(nearestWarehouse);
		
		if (deliveryDistance + returnDistance > batteryUnits || request.getWeight() > weightCapacity) {
			return null;
		}
		
		double batteryLeft = deliveryDistance + returnDistance - currentBattery - deltaBattery;
		if (batteryLeft < 0) {
			estimatedDeliveryTime += (-batteryLeft * chargingRate * 60000);
			currentBattery = 0;
		} else {
			currentBattery = (int) batteryLeft;
		}
		currentLocation = nearestWarehouse;
		
		estimatedDeliveryTime += (deliveryDistance * 60000);

		return new Date(estimatedDeliveryTime);
	}

	public Date estimateDeliveryTime(Location warehouseLocation, Request request, Location deliveryLocation, Date startTime) {

		long estimatedDeliveryTime = 0;
		int deltaBattery = 0;
		if (timeWhenAvailable.getTime() > startTime.getTime()) {
			estimatedDeliveryTime = timeWhenAvailable.getTime();
		} else {
			estimatedDeliveryTime = startTime.getTime();
			deltaBattery = (int) (startTime.getTime() - timeWhenAvailable.getTime()) * chargingRate * 60000;
		}
		estimatedDeliveryTime += request.getNumberOfProducts() * 60000;
		
		double deliveryDistance = getDistance(warehouseLocation, deliveryLocation);
		Location nearestWarehouse = WarehouseManager.getInstance().getNearestWarehouseLocation(deliveryLocation);
		double returnDistance = deliveryLocation.getDistance(nearestWarehouse);
		
		if (deliveryDistance + returnDistance > batteryUnits || request.getWeight() > weightCapacity) {
			return null;
		}
		
		double batteryLeft = deliveryDistance + returnDistance - currentBattery - deltaBattery;
		if (batteryLeft < 0) {
			estimatedDeliveryTime += (-batteryLeft * chargingRate * 60000);
		}

		estimatedDeliveryTime += (deliveryDistance * 60000);

		return new Date(estimatedDeliveryTime);
	}

	/**
	 * Calculate drone path distance.
	 * <p>
	 * Get warehouse and delivery location and calculate the distance: current
	 * to warehouse to delivery.
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

	public double getCurrentBattery() {
		return currentBattery;
	}

	public Location getCurrentLocation() {
		return currentLocation;
	}

	public Date getTimeWhenAvailable() {
		return timeWhenAvailable;
	}

	public double getBatteryUnits() {
		return batteryUnits;
	}

	public int getChargingRate() {
		return chargingRate;
	}

	public int getWeightCapacity() {
		return weightCapacity;
	}

}
