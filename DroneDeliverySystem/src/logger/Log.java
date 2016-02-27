package logger;

import commons.Request;

import java.util.Date;

public class Log {
	
	private final String message;
	private final Request request;
	private final Date time;
	
	public Log(String logMessage, Request logRequest, Date logTime) {
		this.message = logMessage;
		this.request = logRequest;
		this.time = logTime;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("[Log]" + "\n");
		result.append("Request ID: " + request.getId() + "\n");
		result.append("Message: " + message + "\n");
		result.append("Time: " + time.toString() + "\n");
		return result.toString();
	}
	
	public String fullLog() {
		StringBuilder result = new StringBuilder();
		result.append("[Log]" + "\n");
		result.append("Request: \n" + request.toString());
		result.append("Message: " + message + "\n");
		result.append("Time: " + time.toString() + "\n");
		return result.toString();
	}
	
	//getters
	public String getMessage() {
		return message;
	}

	public Request getRequest() {
		return request;
	}

	public Date getTime() {
		return time;
	}
	
}
