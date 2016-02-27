package request;

import java.util.ArrayList;
import java.util.List;

import commons.Request;
import drone.DroneManager;
import logger.Logger;
import warehouse.WarehouseManager;

public class RequestManager {

	// Singleton pattern
	private static final RequestManager INSTANCE = new RequestManager();

	private List<Request> waitingForSupply;

	private RequestManager() {
		waitingForSupply = new ArrayList<Request>();
	}

	public static RequestManager getInstance() {
		return INSTANCE;
	}

	public void submitRequest(Request request) {
		Logger.getInstance().log("Request manager accepted the request.", request);
		switch (request.getType()) {
		case Supply:
			supply(request);
			break;
		case Delivery:
			deliver(request);
			break;
		}
	}

	private void supply(Request request) {
		WarehouseManager.getInstance().supplyProducts(request.getContents());
		Logger.getInstance().log("Send supplies to Warehouse manager.", request);
		//try to deliver waiting
		int size = waitingForSupply.size();
		Request waiting;
		for (int i = 0; i < size; i++) {
			waiting = waitingForSupply.remove(0);
			Logger.getInstance().log("Supply came, trying to deliver the request.", waiting);
			deliver(waiting);
		}
	}

	private void deliver(Request request) {
		if (WarehouseManager.getInstance().areProductsAvailable(request.getContents())) {
			Logger.getInstance().log("Sending the request to Drone manager.", request);
			DroneManager.getInstance().submitRequest(request);
		} else {
			Logger.getInstance().log("Request waiting for supply.", request);
			waitingForSupply.add(request);
		}
	}

}
