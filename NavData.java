import java.text.DecimalFormat;

public class NavData {

	private String latitude;
	private String longitude;
	private double altitude;
	private static double speed = 0.0;  // static for fixed terminals
	private static double climbRate = 0.0;  // static for fixed terminals
	private double heading;
	private double pitch;
	private double roll;

	DecimalFormat decimalFormat = new DecimalFormat("##0.0");
	
	// Navigation Data Constructor
	public NavData(String latitude, String longitude, double altitude, double heading, double pitch, double roll) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.altitude = altitude;
		this.heading = heading;
		this.pitch = pitch;
		this.roll = roll;
	}
	
	// Returns Latitude
	public String getLatitude() {
		return latitude;
	}
	
	// Returns Longitude
	public String getLongitude() {
		return longitude;
	}

	// Returns Altitude
	public String getAltitude() {
		return decimalFormat.format(altitude);
	}
	
	// Returns Speed
	public String getSpeed() {
		return decimalFormat.format(speed);
	}
	
	// Returns Climb Rate
	public String getClimbRate() {
		return decimalFormat.format(climbRate);
	}

	// Returns Heading
	public String getHeading() {
		return decimalFormat.format(heading);
	}
	
	// Returns Pitch
	public String getPitch() {
		return decimalFormat.format(pitch);
	}
	
	// Returns Roll
	public String getRoll() {
		return decimalFormat.format(roll);
	}	
}
