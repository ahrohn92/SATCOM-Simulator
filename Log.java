public class Log {
	
	// Local Variables
	private String timeStamp;
	private String message;
	private Status status;
	
	// Log Constructor
	public Log(String timeStamp, String message, Status status) {
		this.timeStamp = timeStamp;
		this.message = message;
		this.status = status;
	}
	
	// Getter Method for Time Stamp
	public String getTimeStamp() {
		return timeStamp;
	}
	
	// Getter Method for Message
	public String getMessage() {
		return message;
	}
	
	// Getter Method for Status
	public Status getStatus() {
		return status;
	}
}
