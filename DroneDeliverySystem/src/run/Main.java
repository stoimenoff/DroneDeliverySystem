package run;

import java.util.HashMap;
import java.util.Map;

import commons.Product;
import commons.Request;
import commons.RequestType;
import drone.DroneManager;
import location.Location;
import location.Location2D;
import request.RequestManager;

public class Main {

	public static void main(String[] args) {
		
		for (int i = 0; i < 50; i++) {
			DroneManager.getInstance().addDrone(2000, 5, 500);
		}
		for (int i = 0; i < 30; i++) {
			DroneManager.getInstance().addDrone(1200, 3, 200);
		}
		
		Product morkov = new Product("Morkov", 1);
		Product keyboard = new Product("Keyboard", 2);
		
		Location loc = new Location2D(100, 100);
		
		Map<Product, Integer> contents = new HashMap<>();
		
		contents.put(morkov, 50);
		contents.put(keyboard, 10);
		
		Map<Product, Integer> order1 = new HashMap<>();
		
		contents.put(morkov, 2);
		contents.put(keyboard, 1);
		
		Request supply = new Request(1, RequestType.Supply, loc, contents);
		RequestManager.getInstance().submitRequest(supply);
		
		Request delivery = new Request(2, RequestType.Delivery, loc, contents);
		RequestManager.getInstance().submitRequest(delivery);
		
	}

}
