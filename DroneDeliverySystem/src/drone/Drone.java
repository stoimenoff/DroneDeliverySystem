package drone;

import java.util.Calendar;
import java.util.Date;

import location.Location;

public class Drone {

	private final int bateryUnits;
	private final int chargingRate;
	private final int weightCapacity;
	private int currentBatery;
	private Location currentLocation;
	private Date timeWhenAvailable;

	public Drone(int bateryUnits, int chargingRate, int weightCapacity, Location currentLocation,
			Calendar availableNow) {
		this.bateryUnits = bateryUnits;
		this.chargingRate = chargingRate;
		this.weightCapacity = weightCapacity;
		timeWhenAvailable = Calendar.getInstance().getTime();
		currentBatery = bateryUnits;
	}

	public void makeDelivery(Location warehouseLocation, Location deliveryLocation, Date startTime) {

	}

	public Date estimateDeliveryTime() {
		return null;
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
