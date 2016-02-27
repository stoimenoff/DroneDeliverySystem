package logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import commons.Request;

public class Logger {
	//Singleton
	private static final Logger INSTANCE = new Logger();

	private List<Log> logs;

	private Logger() {
		logs = new ArrayList<Log>();
	}

	public static Logger getInstance() {
		return INSTANCE;
	}
	
	public void log(String message, Request request, Date time) {
		logs.add(new Log(message, request, time));
	}
	
	public void log(String message, Request request) {
		Date time = new Date(System.currentTimeMillis());
		logs.add(new Log(message, request, time));
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("Logger: \n");
		for(Log log : logs) {
			result.append(log.toString() + "\n");
		}
		return result.toString();
	}
	
	public String fullLogger() {
		StringBuilder result = new StringBuilder();
		result.append("Logger: \n");
		for(Log log : logs) {
			result.append(log.fullLog() + "\n");
		}
		return result.toString();
	}
	
	public String getRequestLogs(Request request) {
		StringBuilder result = new StringBuilder();
		result.append("Logs for request: " + request.getId() + "\n");
		for(Log log : logs) {
			if(log.getRequest().getId() == request.getId()) {
				result.append(log.getMessage() + "  " + log.getTime() + "\n");
			}
		}
		return result.toString();
	}
	
}
