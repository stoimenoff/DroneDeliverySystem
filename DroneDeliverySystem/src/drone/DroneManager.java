package drone;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import commons.Request;
import location.Location;
import logger.Logger;
import warehouse.WarehouseManager;

public class DroneManager {
	// Singleton pattern
	private static final DroneManager INSTANCE = new DroneManager();

	private List<Drone> drones;
	private Location initialDroneLocation = WarehouseManager.getInstance().getDefaultWarehouseLocation();

	private DroneManager() {
		drones = new ArrayList<Drone>();
	}

	public static DroneManager getInstance() {
		return INSTANCE;
	}

	public void submitRequest(Request request) {
		Logger.getInstance().log("Drone manager accepted the request.", request);
		WarehouseManager.getInstance().getProducts(request.getContents());

		Location warehouseLocation = WarehouseManager.getInstance().getDefaultWarehouseLocation();
		Location deliveryLocation = request.getTarget();
		Date startTime = new Date(System.currentTimeMillis());

		Drone fastestDrone = null;
		Date fastestTime = null;
		Date currentTime = null;
		for (Drone currentDrone : drones) {
			currentTime = currentDrone.estimateDeliveryTime(warehouseLocation, request, deliveryLocation, startTime);
			if (currentTime != null) {
				if (fastestTime == null || fastestTime.compareTo(currentTime) == 1) {
					fastestTime = currentTime;
					fastestDrone = currentDrone;
				}
			}

		}

		if (fastestDrone != null) {
			Logger.getInstance().log("Drone manager sending drone.", request, startTime);
			Date deliveryTime = fastestDrone.makeDelivery(warehouseLocation, request, deliveryLocation, startTime);
			Logger.getInstance().log("Drone delivered the request.", request, deliveryTime);
		} else {
			Logger.getInstance().log("Drone manager could not send drone.", request, startTime);
		}

	}

	public void addDrone(double batteryUnits, int chargingRate, int weightCapacity) {
		drones.add(new Drone(batteryUnits, chargingRate, weightCapacity, initialDroneLocation));
	}

}
