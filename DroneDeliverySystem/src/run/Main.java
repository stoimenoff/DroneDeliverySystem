package run;

import java.io.File;

import commons.Request;
import drone.DroneManager;
import logger.Logger;
import request.RequestManager;
import ui.Parser;
import ui.RequestFileReader;

public class Main {

	public static void main(String[] args) {

		for (int i = 0; i < 50; i++) {
			DroneManager.getInstance().addDrone(2000, 5, 500);
		}
		for (int i = 0; i < 30; i++) {
			DroneManager.getInstance().addDrone(1200, 3, 200);
		}

		/*
		 * String sup =
		 * "supply 5 2016-10-25 12:32 tomato 5 100 potato 6 50 cheese 2 4";
		 * String del = "delivery 4 2016-10-25 12:31 420,369 tomato 5 potato 20"
		 * ; Request supply = Parser.parse(sup);
		 * RequestManager.getInstance().submitRequest(supply); Request delivery
		 * = Parser.parse(del);
		 * RequestManager.getInstance().submitRequest(delivery);
		 * System.out.println(Logger.getInstance().getRequestLogs(supply));
		 * System.out.println(Logger.getInstance().getRequestLogs(delivery));
		 */
		File f = new File("test");
		for (String requestString : RequestFileReader.readFile(f)) {
			Request req = Parser.parse(requestString);
			RequestManager.getInstance().submitRequest(req);
		}
		System.out.println(Logger.getInstance());
	}

}