import java.text.DecimalFormat;

public class Ephemeris {

	// NEED TO GET GOOD RANGES ON THESE VALUES
	// THE TWO IN MSL SHOULD BE EASY TO GET DATA ON
	// NEED TO UPDATE ONCE EVERY 5 SECONDS???
	private double range;
	private double rangeRate;
	private double offsetOne, offsetTwo, offsetThree, offsetFour;
	private double SNRL;
	private double SNRHC;
	private double SNRHF;
	private double ESNR;
	
	// Decimal Format for Values
	DecimalFormat decimalFormat = new DecimalFormat("##0.0");
	
	// Ephemeris Constructor
	public Ephemeris() {
		range = generateRandomValue(35000.0, 40000.0);
		rangeRate = generateRandomValue(5.0, 6.0);
		offsetOne = generateRandomValue(0.1, 0.4);
		offsetTwo = generateRandomValue(0.1, 0.8);
		offsetThree = generateRandomValue(1.0, 6.0);
		offsetFour = generateRandomValue(0.1, 0.4);
		SNRL = generateRandomValue(59.0, 61.0);
		SNRHC = generateRandomValue(53.0, 56.0);
		SNRHF = generateRandomValue(50.0, 54.0);
		ESNR = generateRandomValue(15.0, 16.5);
	}
	
	// Generates Random Value 
	private double generateRandomValue(double min, double max) {
		return Math.random() * (max - min) + min;
	}
	
	// Returns Range
	public String getRange() {
		return decimalFormat.format(range);
	}
	
	// Returns Range Rate
	public String getRangeRate() {
		return decimalFormat.format(rangeRate);
	}
	
	// Returns Offset One
	public String getOffsetOne() {
		return "-"+decimalFormat.format(offsetOne);
	}
	
	// Returns Offset Two
	public String getOffsetTwo() {
		return "-"+decimalFormat.format(offsetTwo);
	}
	
	// Returns Offset Three
	public String getOffsetThree() {
		return decimalFormat.format(offsetThree);
	}
	
	// Returns Offset Four
	public String getOffsetFour() {
		return decimalFormat.format(offsetFour);
	}
	
	// Returns SNRL
	public String getSNRL() {
		return decimalFormat.format(SNRL);
	}
	
	// Returns SNRHC
	public String getSNRHC() {
		return decimalFormat.format(SNRHC);
	}
	
	// Returns SNRHF
	public String getSNRHF() {
		return decimalFormat.format(SNRHF);
	}
	
	// Returns ESRN
	public String getESNR() {
		return decimalFormat.format(ESNR);
	}
}

