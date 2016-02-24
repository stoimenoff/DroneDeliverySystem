package drone;

import java.util.ArrayList;
import java.util.List;

import commons.Request;
import warehouse.WarehouseManager;

public class DroneManager {
	// Singleton pattern
	private static final DroneManager INSTANCE = new DroneManager();
	private static final int INITIAL_NUMBER_OF_DRONES = 5;

	private List<Drone> drones;

	private DroneManager() {
		 drones = new ArrayList<Drone>();
		 for (int i = 0; i < INITIAL_NUMBER_OF_DRONES; i++) {
			drones.add(new Drone());
		}
	}

	public static DroneManager getInstance() {
		return INSTANCE;
	}
	
	public void submitRequest(Request request) {
		WarehouseManager.getInstance().getProducts(request.getContents());
		
	}
	
}
