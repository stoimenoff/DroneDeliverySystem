package request;

import java.util.ArrayList;
import java.util.List;

import commons.Request;
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
		//try to deliver waiting
		int size = waitingForSupply.size();
		for (int i = 0; i < size; i++) {
			deliver(waitingForSupply.remove(0));
		}
		System.out.println("Supply request is completed.");
	}

	private void deliver(Request request) {
		if (WarehouseManager.getInstance().areProductsAvailable(request.getContents())) {
			//TODO send request to drone manager
		} else {
			waitingForSupply.add(request);
		}
		System.out.println("Delivery request is completed.");
	}

}
