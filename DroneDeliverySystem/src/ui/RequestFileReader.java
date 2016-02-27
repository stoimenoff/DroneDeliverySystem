package ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class RequestFileReader {
	
	private RequestFileReader() {
		
	}
	
	public static List<String> readFile(File file) {
		List<String> requests = new ArrayList<String>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
		    while ((line = br.readLine()) != null) {
		       requests.add(line);
		    }
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return requests;
	}
	
}
