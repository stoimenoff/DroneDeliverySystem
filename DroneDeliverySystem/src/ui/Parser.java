package ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import commons.Product;
import commons.Request;
import commons.RequestType;
import location.Location;
import location.Location2D;
import warehouse.WarehouseManager;

public final class Parser {

	/*
	 * supply <id> <timestamp YYYY-MM-DD HH:MM> <product name 1> <product
	 * weight> <quantity> <product name 2> <product weight> <quantity> e.g.
	 * "supply 5 2016-10-25 12:32 tomato 5 100 potatoes 6 50 cheese 2 4
	 * 
	 * 
	 * 
	 * delivery <id> <timestamp YYYY-MM-DD HH:MM> <location - "INT,INT"> <product name 1> <product
	 * weight> <quantity> <product name 2> <product weight> <quantity> e.g.
	 */

	private Parser() {
	}

	public static Request parse(String request) {

		ArrayList<String> reqString = new ArrayList<>();
		Collections.addAll(reqString, request.split(" "));

		String typeStr = reqString.remove(0);
		RequestType type;
		if (typeStr.equals("supply")) {
			type = RequestType.Supply;
		} else {
			type = RequestType.Delivery;
		}
		int id = Integer.getInteger(reqString.remove(0));
		//remove date and time
		reqString.remove(0);
		reqString.remove(0);
		
		Location loc = WarehouseManager.getInstance().getDefaultWarehouseLocation();
		
		if (type.equals(RequestType.Delivery)) {
			ArrayList<String> coords = new ArrayList<>();
			Collections.addAll(coords, reqString.remove(0).split(","));
			loc = new Location2D(Integer.getInteger(coords.remove(0)), Integer.getInteger(coords.remove(0))); 
		}
		
		Map<Product, Integer> contents = new HashMap<>();
		while (!reqString.isEmpty()) {
			Product product = new Product(reqString.remove(0), Integer.getInteger(reqString.remove(0)));
			contents.put(product, Integer.getInteger(reqString.remove(0)));
		}
		
		return new Request(id, type, loc, contents);
	}

}
