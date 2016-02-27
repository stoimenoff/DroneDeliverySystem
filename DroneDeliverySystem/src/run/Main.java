package run;

import commons.Request;
import request.RequestManager;
import ui.Parser;

public class Main {

	public static void main(String[] args) {
		String sup = "supply 5 2016-10-25 12:32 tomato 5 100 potato 6 50 cheese 2 4";
		String del = "delivery 4 2016-10-25 12:31 420,369 tomato 5 potato 20";
		Request supply = Parser.parse(sup);
		System.out.println(supply);
		RequestManager.getInstance().submitRequest(supply);
		Request delivery = Parser.parse(del);
		System.out.println(delivery);
		
	}

}
