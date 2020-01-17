import java.text.DecimalFormat;
import java.util.ArrayList;

public class Satellite {

	// Local Variables
	String satName;
	double lowerAzimuth;
	double elevation;
	double upperAzimuth;
	boolean isAuthorized = false;
	boolean isEphemerisCurrent = false;
	
	// TRANSEC Keys
	ArrayList<Character> keys = new ArrayList<Character>();
	
	// Decimal Format for Values
	DecimalFormat decimalFormat = new DecimalFormat("##0.0");
	
	// Satellite Constructor
	public Satellite(String satName) {
		this.satName = satName;
		lowerAzimuth = generateRandomValue(125.0, 175.0);
		elevation = generateRandomValue(20.0, 45.0);
		upperAzimuth = elevation + lowerAzimuth;
		
		// Initializes TRANSEC keys
		for (int i = 0; i < 10; i++) {
			keys.add('-');
		}
	}
	
	// Generates Random Value 
	private double generateRandomValue(double min, double max) {
		return Math.random() * (max - min) + min;
	}
	
	// Returns Name
	public String getName() {
		return satName;
	}
	
	// Returns Lower Azimuth
	public String getLowerAzimuth() {
		return decimalFormat.format(lowerAzimuth);
	}
	
	// Returns Elevation
	public String getElevation() {
		return decimalFormat.format(elevation);
	}
	
	// Returns Upper Azimuth
	public String getUpperAzimuth() {
		return decimalFormat.format(upperAzimuth);
	}
	
	// Sets Authorization
	public void setAuthorization(boolean isAuthorized) {
		this.isAuthorized = isAuthorized;
	}
	
	// Returns Authorization
	public boolean getAuthorization() {
		return isAuthorized;
	}
	
	// Sets Currentness of Ephemeris
	public void setEphemerisCurrentness(boolean isEphemerisCurrent) {
		this.isEphemerisCurrent = isEphemerisCurrent;
	}
	
	// Returns Currentness of Ephemeris
	public boolean getEphemerisCurrentness() {
		return isEphemerisCurrent;
	}
	
	// Sets Keys
	public void setKeys(ArrayList<Character> keys) {
		this.keys = keys;
	}
	
	// Returns Keys
	public ArrayList<Character> getKeys() {
		return keys;
	}
}
