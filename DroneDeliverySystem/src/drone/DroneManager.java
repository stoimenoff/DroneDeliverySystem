package drone;

import java.util.ArrayList;
import java.util.List;

import commons.Request;
import location.Location;
import warehouse.WarehouseManager;

public class DroneManager {
	// Singleton pattern
	private static final DroneManager INSTANCE = new DroneManager();
	private static final int INITIAL_NUMBER_OF_DRONES = 5;

	private List<Drone> drones;

	private DroneManager() {
		Location initialLocation = WarehouseManager.getInstance().getDroneSpawnLocation(); 
		drones = new ArrayList<Drone>();
		for (int i = 0; i < INITIAL_NUMBER_OF_DRONES; i++) {
			drones.add(new Drone(2000, 5, 500, initialLocation));
		}
	}

	public static DroneManager getInstance() {
		return INSTANCE;
	}
	
	public void submitRequest(Request request) {
		WarehouseManager.getInstance().getProducts(request.getContents());
		
	}
	
}
