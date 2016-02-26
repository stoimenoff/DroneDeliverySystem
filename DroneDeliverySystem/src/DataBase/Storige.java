package DataBase;

import java.sql.DriverAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Storige {

	public String request;

	/*
	 * supply <id> <timestamp YYYY-MM-DD HH:MM> <product name 1> <product
	 * weight> <quantity> <product name 2> <product weight> <quantity> e.g.
	 * "supply 5 2016-10-25 12:32 tomato 5 100 potatoes 6 50 cheese 2 4
	 */

	Storige(String request) {
		this.request = request;
	}

	public String getRequest() {
		return request;
	}

	public void parssser() {

		ArrayList<String> lists = new ArrayList<>();


			Collections.addAll(lists,getRequest().split(" "));
			

		System.out.println(lists);
	}

	public static void main(String[] args) {

		Storige a = new Storige(
				"supply 5 2016-10-25 12:32 tomato 5 100 potatoes 6 50 cheese 24");
		a.parssser();

	}

}
