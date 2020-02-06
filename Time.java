import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Time {

	// Instance Variables
	private String day = "";
	private String hour = "";
	private String minute = "";
	private String second = "";
	private String month = "";
	private String year = "";
	private String time;
	private SimpleDateFormat timeFormat = new SimpleDateFormat("dd-HHmmss'Z'-MMMyy");
	private Calendar calendar;
	private boolean newTime;
	
	// Constructor
	public Time(String timeSource, String timeInput, boolean newTime, Calendar calendar) {

			// Returns Formatted Zulu Time + Date
			if (timeSource.equals("KYBD")) {
				if (newTime && !timeInput.equals("")) {
					newTime = false;
					for (int i = 0; i < timeInput.length(); i++) {
						if (i == 0 || i == 1) {
							day += timeInput.charAt(i);
						}
						if (i == 3 || i == 4) {
							hour += timeInput.charAt(i);
						}
						if (i == 5 || i == 6) {
							minute += timeInput.charAt(i);
						}
						if (i == 7 || i == 8) {
							second += timeInput.charAt(i);
						}
						if (i == 11 || i == 12 || i == 13) {
							month += timeInput.charAt(i);
						}
						if (i == 14 || i == 15) {
							year += timeInput.charAt(i);
						}
					}
					calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
					calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour));
					calendar.set(Calendar.MINUTE, Integer.parseInt(minute));
					calendar.set(Calendar.SECOND, Integer.parseInt(second));
					if (month.equals("JAN")) {
						calendar.set(Calendar.MONTH, Calendar.JANUARY);
					} else if (month.equals("FEB")) {
						calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
					} else if (month.equals("MAR")) {
						calendar.set(Calendar.MONTH, Calendar.MARCH);
					} else if (month.equals("APR")) {
						calendar.set(Calendar.MONTH, Calendar.APRIL);
					} else if (month.equals("MAY")) {
						calendar.set(Calendar.MONTH, Calendar.MAY);
					} else if (month.equals("JUN")) {
						calendar.set(Calendar.MONTH, Calendar.JUNE);
					} else if (month.equals("JUL")) {
						calendar.set(Calendar.MONTH, Calendar.JULY);
					} else if (month.equals("AUG")) {
						calendar.set(Calendar.MONTH, Calendar.AUGUST);
					} else if (month.equals("SEP")) {
						calendar.set(Calendar.MONTH, Calendar.SEPTEMBER);
					} else if (month.equals("OCT")) {
						calendar.set(Calendar.MONTH, Calendar.OCTOBER);
					} else if (month.equals("NOV")) {
						calendar.set(Calendar.MONTH, Calendar.NOVEMBER);
					} else if (month.equals("DEC")) {
						calendar.set(Calendar.MONTH, Calendar.DECEMBER);
					}
					year = "20"+year;
					calendar.set(Calendar.YEAR, Integer.parseInt(year));
				} else {
					calendar.add(Calendar.SECOND, 1);
				}
				time = timeFormat.format(calendar.getTime()).toUpperCase();
			} else {
				timeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
				time = timeFormat.format(new Date()).toUpperCase();
			}
			this.newTime = newTime;
			this.calendar = calendar;
	}
	
	// Returns Time
	public String getTime() {
		return time;
	}
	
	// Returns Boolean Indicating Whether a New Time Has Been Set
	public boolean isNewTime() {
		return newTime;
	}
	
	// Returns Calendar Instance
	public Calendar getCalendar() {
		return this.calendar;
	}
	
	// Returns Expected Time for Keys
	public String getExpectedTime() {
		Calendar tempCalendar = Calendar.getInstance();
		tempCalendar.setTime(this.calendar.getTime());
		tempCalendar.add(Calendar.SECOND, 15);
		return timeFormat.format(tempCalendar.getTime()).toUpperCase();
	}
}
