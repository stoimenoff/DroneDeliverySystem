package logger;

import commons.Request;

import java.util.Map;

import commons.Product;
import drone.Drone;

public class Log {

	// private Map<Product, Integer> contents = new
	// Map<>(request.getContents());

	public Log(Request request) {
		Map<Product, Integer> contents = request.getContents();
		contents.toString();
		System.out.println(
				"Request logged in: " + "ID " + request.getId() + " product and quantity \n" + contents.toString() + " ");
	}

	public Log(Drone drone) {

	}

}
