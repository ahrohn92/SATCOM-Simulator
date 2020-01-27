import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

public class Screens {

	// CAN ADD FUNCTIONS WHERE WE A SCREEN IS SELECTED THAT SCREEN IS CREATED WITH NEWEST VALUES FOR VARIABLES

	// Local Variables
	private HashMap<Integer, ArrayList<String>> screenIndex = new HashMap<Integer, ArrayList<String>>();
	private HashMap<Integer, ArrayList<Integer>> associatedScreensIndex = new HashMap<Integer, ArrayList<Integer>>();
	HashMap<String, Satellite> sats;
	private ArrayList<String> screenAreas;
	private ArrayList<Integer> associatedScreens;
	private String newStatusArea;
	private String newMainArea;
	private String newPromptArea;
	private String newOptionsArea;
	private int indexNumber;
	private ArrayList<Log> log;
	private String[] tempNavData;
	private String lineTwo = "Line Two Begins Here";
	String timeSource;

	// Sat Variables
	private String satName1;
	private String satName2;
	private String upperAzimuth;
	private String lowerAzimuth;
	private String elevation;
	
	Time time;
	
	/*
	 * INDEX
	 * 
	 * 
	 * 1500s = BIT
	 */
	
	// Constructs Screens
	public Screens(ArrayList<Log> log, Satellite currentSat, NavData navData, String[] tempNavData, String printMode, String satStatus, 
			String commonGroupBIT, String EHFGroupBIT, HashMap<Integer, Net> nets, int selectedNet, String tsmStatus, String timeSource,
			String startupMode, String otarAuto, HashMap<String, Satellite> sats, String time, String beam, String[] acqIndicators, String GSData, 
			String[] ephemeris, String[] logonParams, String[] operatorInputs, boolean loggedOnSat, char[] SRC, String mode) {
		this.log = log;
		this.tempNavData = tempNavData;
		this.sats = sats;
		this.timeSource = timeSource;
		
		if (satStatus == null) {
			satStatus = "";
		}
		
		// Filter Satellite Information
		if (currentSat == null) {
			satName1 = "     ";
			satName2 = "-    ";
			upperAzimuth = "     ";
			lowerAzimuth = "     ";
			elevation = "    ";
		} else {
			satName1 = currentSat.getName();
			satName2 = currentSat.getName();
			upperAzimuth = currentSat.getUpperAzimuth();
			lowerAzimuth = currentSat.getLowerAzimuth();
			elevation = currentSat.getElevation();
		}
		
		// MAIN SCREEN
		indexNumber = 0;
		newStatusArea =
				"DTG:      "+time+"     ** UNCLASSIFIED **                               \r\n" + 
				"                                                                                \r\n" + 
				"ALARM:                                                                     	 \r\n" + 
				"                                                                                \r\n" + 
				"ADVISORY: "+fetchLogEntry(1)+"                                                  \r\n" +
				"                                                                                \r\n";
		newMainArea =
				"MAIN----------------------------------------------------------------------------\r\n" + 
				"                                                                          V14.00\r\n" + 
				"                                                                    "+GSData+" \r\n" + 
				"                                                                                \r\n" + 
				"                        OPERATOR 1                                              \r\n" + 
				"                        MODE             NORMAL POWER ON                        \r\n" + 
				"                        STARTUP MODE     "+startupMode+"                                  \r\n" + 
				"                        DATABASE SOURCE  IMG2019v23    29-200000Z-APR98         \r\n" + 
				"                        TIME SOURCE      "+timeSource+"                                    \r\n" +
				"                        CONSTELLATION    MIL                                    \r\n" + 
				"                        TSM STATUS       "+tsmStatus+"         \r\n" + 
				"                        BIT STATUS       OK                                     \r\n" + 
				"                        LATITUDE          "+String.format("%1$6s", navData.getLatitude())+"                                \r\n" + 
				"                        LONGITUDE        "+String.format("%1$7s", navData.getLongitude())+"                                \r\n" + 
				"                                                                                \r\n" + 
				"                                       EHF                                      \r\n" + 
				"                                                                                \r\n" +
				"                                                                                \r\n" + 
				"         "+String.format("%1$-11s", acqIndicators[0])+"             "+satName1+"                                 \r\n" +
				"         "+String.format("%1$-16s", acqIndicators[1])+"        "+satStatus+"                                      \r\n" + 
				"         "+String.format("%1$-12s", acqIndicators[2])+"            "+beam+"                                             \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" +
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n"; 
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     SELECT OPTION                                                              \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		newOptionsArea =
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦    MESSAGE    ¦  ¦   TERMINAL    ¦  ¦ EHF SERVICES  ¦  ¦ EHF ACQ/LOGON ¦    \r\n" + 
				"  ¦  PROCESSING   ¦  ¦    CONTROL    ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦ BUILT-IN TEST ¦  ¦     DAMA      ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		screenAreas = new ArrayList<String>();
		screenAreas.add(newStatusArea);
		screenAreas.add(newMainArea);
		screenAreas.add(newPromptArea);
		screenAreas.add(newOptionsArea);
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(1);
		associatedScreens.add(2);
		associatedScreens.add(3);
		associatedScreens.add(4);
		associatedScreens.add(5);
		associatedScreens.add(6);
		screenIndex.put(indexNumber, screenAreas);
		associatedScreensIndex.put(indexNumber, associatedScreens);
			
		// MESSAGE PROCESSING SCREEN
		// LAST SCREEN = MAIN SCREEN
		indexNumber = 1;
		newOptionsArea = 
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦ RED RECEIVED  ¦  ¦  RED STORED   ¦  ¦    NEW RED    ¦  ¦BLACK RECEIVED ¦    \r\n" + 
				"  ¦   MESSAGES    ¦  ¦   MESSAGES    ¦  ¦    MESSAGE    ¦  ¦   MESSAGES    ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦ BLACK STORED  ¦  ¦   NEW BLACK   ¦  ¦AUTO BROADCAST ¦  ¦               ¦    \r\n" + 
				"  ¦   MESSAGES    ¦  ¦    MESSAGE    ¦  ¦     QUEUE     ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(7);
		associatedScreens.add(8);
		associatedScreens.add(9);
		associatedScreens.add(10);
		associatedScreens.add(11);
		associatedScreens.add(12);
		associatedScreens.add(13);
		screenIndex.put(indexNumber, newScreenTemplate(0, true, true, true, false));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// TERMINAL CONTROL SCREEN
		// LAST SCREEN = MAIN SCREEN
		indexNumber = 2;
		newMainArea = 
				"MAIN:TERMINAL-------------------------------------------------------------------\r\n" + 
				"                                                                                \r\n" + 
				"DTG               ADVISORY/ALARM                                                \r\n" + 
				"                                                                                \r\n" + 
				""+fetchLogEntry(1)+"                                                            \r\n" + 
				""+fetchLogEntry(2)+"                                          		             \r\n" + 
				""+fetchLogEntry(3)+"                                                            \r\n" + 
				""+fetchLogEntry(4)+"                         						             \r\n" + 
				""+fetchLogEntry(5)+"                                                            \r\n" +
				""+fetchLogEntry(6)+"                                                            \r\n" + 
				""+fetchLogEntry(7)+"                             					             \r\n" + 
				""+fetchLogEntry(8)+"                                                            \r\n" + 
				""+fetchLogEntry(9)+"                                                            \r\n" + 
				""+fetchLogEntry(10)+"                                                           \r\n" + 
				""+fetchLogEntry(11)+"                                                           \r\n" + 
				""+fetchLogEntry(12)+"                                                           \r\n" + 
				""+fetchLogEntry(13)+"                                                           \r\n" +
				""+fetchLogEntry(14)+"                                                           \r\n" + 
				""+fetchLogEntry(15)+"                                                           \r\n" + 
				""+fetchLogEntry(16)+"                                           	             \r\n" + 
				""+fetchLogEntry(17)+"                                                           \r\n" + 
				""+fetchLogEntry(18)+"                                                           \r\n" + 
				""+fetchLogEntry(19)+"                                                           \r\n" +
				""+fetchLogEntry(20)+"                                                           \r\n" + 
				""+fetchLogEntry(21)+"                                                           \r\n" + 
				""+fetchLogEntry(22)+"                                                           \r\n" + 
				""+fetchLogEntry(23)+"                                                           \r\n" + 
				""+fetchLogEntry(24)+"                                                           \r\n" + 
				""+fetchLogEntry(25)+"                                                           \r\n" + 
				""+fetchLogEntry(26)+"                                                           \r\n" + 
				"                                                                                \r\n"; 
		newOptionsArea =
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦ EXIT DISPLAY  ¦  ¦  PORT USAGE   ¦  ¦  CRYPTO KEYS  ¦  ¦SPOT BEAM POINT¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦NAV DATA UPDATE¦  ¦  KB LOCKOUT   ¦  ¦  POWER DOWN   ¦  ¦DATA RECORDING ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(0);
		associatedScreens.add(14);
		associatedScreens.add(15);
		associatedScreens.add(16);
		associatedScreens.add(17);
		associatedScreens.add(18);
		associatedScreens.add(19);
		associatedScreens.add(20);
		screenIndex.put(indexNumber, newScreenTemplate(0, true, false, true, false));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// EHF SERVICES SCREEN
		// LAST SCREEN = MAIN SCREEN
		indexNumber = 3;
		newStatusArea = 
				"DTG:      "+time+"        ** SECRET **                                  \r\n" + 
				"                                                                                \r\n" + 
				"ALARM:                                                                     	 \r\n" + 
				"                                                                                \r\n" + 
				"ADVISORY: "+fetchLogEntry(1)+" \r\n" +
				"                                                                                \r\n";
		newMainArea = 
				"MAIN:EHF------------------------------------------------------------------------\r\n" + 
				"           UL SLOTS: PRIMARY    1 N01  2 -    3 -    4 -     CONSTELLATION  MIL \r\n" + 
				"                     SECONDARY  1 -    2 N02  3 -    4 -     TERMINAL ID   XXXX \r\n" + 
				"                                                                                \r\n" + 
				"KEYS                        TX RX DL                                            \r\n" + 
				"   CON NAME       TX   RX   C3 C3 RT ST                   TX   RX   C3 C3 RT ST \r\n" + 
				" 1 MIL NAME        -    -   -  -  -  "+nets.get(1).getNetStatus()+" 19 MIL NAME        -    -   -  -  -  "+nets.get(19).getNetStatus()+" \r\n" + 
				" 2 MIL NAME        -    -   -  -  -  "+nets.get(2).getNetStatus()+" 20 MIL NAME        -    -   -  -  -  "+nets.get(20).getNetStatus()+" \r\n" + 
				" 3 MIL NAME        -    -   -  -  -  "+nets.get(3).getNetStatus()+" 21 MIL NAME        -    -   -  -  -  "+nets.get(21).getNetStatus()+" \r\n" +
				" 4 MIL NAME        -    -   -  -  -  "+nets.get(4).getNetStatus()+" 22 MIL NAME        -    -   -  -  -  "+nets.get(22).getNetStatus()+" \r\n" + 
				" 5 MIL NAME        -    -   -  -  -  "+nets.get(5).getNetStatus()+" 23 MIL NAME        -    -   -  -  -  "+nets.get(23).getNetStatus()+" \r\n" + 
				" 6 MIL NAME        -    -   -  -  -  "+nets.get(6).getNetStatus()+" 24 MIL NAME        -    -   -  -  -  "+nets.get(24).getNetStatus()+" \r\n" + 
				" 7 MIL NAME        -    -   -  -  -  "+nets.get(7).getNetStatus()+" 25 MIL NAME        -    -   -  -  -  "+nets.get(25).getNetStatus()+" \r\n" + 
				" 8 MIL NAME        -    -   -  -  -  "+nets.get(8).getNetStatus()+" 26 MIL NAME        -    -   -  -  -  "+nets.get(26).getNetStatus()+" \r\n" + 
				" 9 MIL NAME        -    -   -  -  -  "+nets.get(9).getNetStatus()+" 27 MIL NAME        -    -   -  -  -  "+nets.get(27).getNetStatus()+" \r\n" + 
				"10 MIL NAME        -    -   -  -  -  "+nets.get(10).getNetStatus()+" 28 MIL NAME        -    -   -  -  -  "+nets.get(28).getNetStatus()+" \r\n" + 
				"11 MIL NAME        -    -   -  -  -  "+nets.get(11).getNetStatus()+" 29 MIL NAME        -    -   -  -  -  "+nets.get(29).getNetStatus()+" \r\n" +
				"12 MIL NAME        -    -   -  -  -  "+nets.get(12).getNetStatus()+" 30 MIL NAME        -    -   -  -  -  "+nets.get(30).getNetStatus()+" \r\n" + 
				"13 MIL NAME        -    -   -  -  -  "+nets.get(13).getNetStatus()+" 31 MIL NAME        -    -   -  -  -  "+nets.get(31).getNetStatus()+" \r\n" + 
				"14 MIL NAME        -    -   -  -  -  "+nets.get(14).getNetStatus()+" 32 MIL NAME        -    -   -  -  -  "+nets.get(32).getNetStatus()+" \r\n" + 
				"15 MIL NAME        -    -   -  -  -  "+nets.get(15).getNetStatus()+" 33 MIL NAME        -    -   -  -  -  "+nets.get(33).getNetStatus()+" \r\n" + 
				"16 MIL NAME        -    -   -  -  -  "+nets.get(16).getNetStatus()+" 34 MIL NAME        -    -   -  -  -  "+nets.get(34).getNetStatus()+" \r\n" + 
				"17 MIL NAME        -    -   -  -  -  "+nets.get(17).getNetStatus()+" 35 MIL NAME        -    -   -  -  -  "+nets.get(35).getNetStatus()+" \r\n" +
				"18 MIL NAME       B01   -   -  -  -  "+nets.get(18).getNetStatus()+" 36 MIL NAME        -    -   -  -  -  "+nets.get(36).getNetStatus()+" \r\n" + 
				"                                                                                \r\n" + 
				"PTP CALLS                                                                       \r\n" + 
				"   DEST    TX   ST     DEST    TX   ST      DEST    TX   ST      DEST    TX   ST\r\n" + 
				" 1 XXXX XX  -   IA   5 XXXX XX  -   IA    9 XXXX XX  -   IA   13 XXXX XX  -   IA\r\n" + 
				" 2 XXXX 0   -   IA   6 XXXX XX  -   IA   10 XXXX XX  -   IA   14 XXXX XX  -   IA\r\n" + 
				" 3 XXXX 0   -   IA   7 XXXX XX  -   IA   11 XXXX XX  -   IA   15 XXXX XX  -   IA\r\n" + 
				" 4 XXXX 0   -   IA   8 XXXX XX  -   IA   12 XXXX XX  -   IA   16 XXXX XX  -   IA\r\n";
		newOptionsArea = 
				"----------------------------------** SECRET **----------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦ EXIT DISPLAY  ¦  ¦     NETS      ¦  ¦   PTP CALLS   ¦  ¦ TX/RX SUMMARY ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦RX-ONLY SUMMARY¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(0);
		associatedScreens.add(21);
		associatedScreens.add(22);
		associatedScreens.add(23);
		associatedScreens.add(24);
		screenIndex.put(indexNumber, newScreenTemplate(0, false, false, true, false));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// EHF ACQ/LOGON SCREEN
		// LAST SCREEN = MAIN SCREEN
		indexNumber = 4;
		newMainArea =
				"MAIN:EHF ACQ/TRACK--------------------------------------------------------------\r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"          CURRENT CONSTELLATION      MIL                                        \r\n" + 
				"                                                                                \r\n" + 
				"          CURRENT SAT                "+satName2+"                             \r\n" + 
				"          BEAM                       "+beam+"                                         \r\n" +
				"                                                                                \r\n" + 
				"          STATUS                     "+satStatus+"                                  \r\n" + 
				"                                                                                \r\n" + 
				"       LOGON PARAMETERS                                                         \r\n" + 
				"                                     TERMINAL    REQUESTED                      \r\n" + 
				"          C2 ROBUSTNESS              "+String.format("%1$-5s", logonParams[3])+"       "+String.format("%1$-5s", logonParams[0])+"                          \r\n" + 
				"          C2 CYCLE PERIOD            "+String.format("%1$-4s", logonParams[4])+"        "+String.format("%1$-4s", logonParams[1])+"                            \r\n" + 
				"          C3 MODE                    "+String.format("%1$-9s", logonParams[5])+"   "+String.format("%1$-5s", logonParams[2])+"                           \r\n" +
				"                                                                                \r\n" + 
				"       SWITCHES                                                                 \r\n" + 
				"                                                                                \r\n" + 
				"          SAT TRANSFER IF BLOCKED    OFF                                        \r\n" + 
				"          ACQ ON DETECTION           OFF                                        \r\n" + 
				"          STRESS MODE                OFF                                        \r\n" +
				"          REPEAT JAM MODE            OFF                                        \r\n" + 
				"          RECEIVE ONLY MODE          OFF                                        \r\n" + 
				"          SCINTILLATION MODE    AUTO OFF                                        \r\n" + 
				"          ACQ MODE               LDR/MDR                                        \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		newOptionsArea =
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦ EXIT DISPLAY  ¦  ¦RESTART DL ACQ ¦  ¦ START UL ACQ  ¦  ¦    LOG OFF    ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦  LOGON PARAM  ¦  ¦   EHF SATS    ¦  ¦ POINTING DATA ¦  ¦ MORE OPTIONS  ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(0);
		associatedScreens.add(25);
		associatedScreens.add(26);
		associatedScreens.add(27);
		associatedScreens.add(28);
		associatedScreens.add(29);
		associatedScreens.add(30);
		associatedScreens.add(31);
		screenIndex.put(indexNumber, newScreenTemplate(0, true, false, true, false));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// BUILT-IN TEST SCREEN
		// LAST SCREEN = MAIN SCREEN
		indexNumber = 5;
		newMainArea =
				"MAIN:BIT------------------------------------------------------------------------\r\n" + 
				"  "+printMode+"                                                               \r\n" + 
				"       RECOMMENDED TEST                      FIRST FAULT                        \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"       LRU      STATUS                       LRU      STATUS                    \r\n" + 
				"                                                                                \r\n" + 
				"  COMMON GROUP  "+commonGroupBIT+"                                                                \r\n" +
				"       TAC      OK                           BBP      OK                        \r\n" + 
				"       TFS      OK                           TSM      OK                        \r\n" + 
				"       LCDI51   OK                                                              \r\n" + 
				"       KB1      OK                                                              \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"  EHF GROUP     "+EHFGroupBIT+"                                                                \r\n" +
				"       EHFMOD   OK                           RSU      OK                        \r\n" + 
				"       HVPS     OK                           HPA      OK                        \r\n" + 
				"       CONSCAN  OK                           ACCEL    -                         \r\n" + 
				"       IDA      -                            RFE      OK                        \r\n" + 
				"       APCU     OK                           ARU      -                         \r\n" + 
				"       KGV-11   OK                           INT KIV7 OK                        \r\n" +
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		newOptionsArea = 
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦ EXIT DISPLAY  ¦  ¦   I/O PORTS   ¦  ¦DISRUPTIVE TEST¦  ¦   FAULT LOG   ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦C2/C3 LOOPBACK ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(0);
		associatedScreens.add(32);
		associatedScreens.add(33);
		associatedScreens.add(34);
		associatedScreens.add(35);
		screenIndex.put(indexNumber, newScreenTemplate(0, true, false, true, false));
		associatedScreensIndex.put(indexNumber, associatedScreens);

		// DAMA SCREEN
		// LAST SCREEN = MAIN SCREEN
		indexNumber = 6;
		newMainArea =
				"MAIN:DAMA:EHF SUMMARY-----------------------------------------------------------\r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                             PERMANENT ADDR 123 \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                       NET ADDRESS           5                                  \r\n" + 
				"                       SAT NUM               1                                  \r\n" +
				"                       TERMINAL TYPE         MNC                                \r\n" + 
				"                       NET STATUS            INACTIVE                           \r\n" + 
				"                       SUPRESS REQUEST       OFF                                \r\n" + 
				"                       TX PORT               OPR                                \r\n" + 
				"                       EAM PORT              OPR   -    -                       \r\n" + 
				"                       NORMAL MSG PORT       OPR   -    -                       \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" +
				"                                                                                \r\n" + 
				"                       CHANNEL MONITOR       ON                                 \r\n" + 
				"                       LOGINS                                                   \r\n" + 
				"                       NET MEMBERS                                              \r\n" + 
				"                       MESSAGE REQ/ASC                                          \r\n" + 
				"                       SRI IN PROGRESS                                          \r\n" +
				"                       AUTO SRI MODE                                            \r\n" + 
				"                       UL SLOT               S2                                 \r\n" + 
				"                       REQUESTED DL RATE     HF04                               \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		newOptionsArea =
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦ EXIT DISPLAY  ¦  ¦ GLOBAL CONFIG ¦  ¦   JOIN NET    ¦  ¦NET PARAMETERS ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦   EXIT NET    ¦  ¦  NET MEMBERS  ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦    SUMMARY    ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";				
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(0);
		associatedScreens.add(36);
		associatedScreens.add(37);
		associatedScreens.add(38);
		associatedScreens.add(39);
		associatedScreens.add(40);
		screenIndex.put(indexNumber, newScreenTemplate(0, true, false, true, false));
		associatedScreensIndex.put(indexNumber, associatedScreens);
        
		// CRYPTO KEYS SCREEN
		// LAST SCREEN = TERMINAL CONTROL SCREEN (INDEX = 2)
		indexNumber = 15;
		newMainArea =
				"MAIN:TERMINAL:KGV-11 KEYS-------------------------------------------------------\r\n" + 
				"                                                                                \r\n" + 
				"CURRENT CONSTELLATION  MIL             AUTO FUTURE ALL         COMPROMISE COT   \r\n" + 
				"                                 OTAR  "+String.format("%1$-3s", otarAuto)+"  -      -           -                \r\n" + 
				"CONSTELLATIONS                                                                  \r\n" + 
				"         CANISTER ID      GROUP            UNIVERSAL  LINKING                   \r\n" + 
				" LABEL   CURRENT FUTURE   ID     CHAINING  A     B    CURRENT FUTURE COMPROMISE \r\n" + 
				" MIL     434  Y  434  N   -      -         -     -    -       -      -          \r\n" + 
				" UFO     -    N  -    N   -      -         -     -    -       -      -          \r\n" +
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" +
				"                                                                                \r\n" + 
				"SATELLITES                        TRANSEC KEYS                                  \r\n" + 
				" LABEL S BEAMS                    CURRENT         FUTURE          COMPROMISE    \r\n" + 
				"         EC SA SB SC AH AL CI RB  DL T1 T2 ST CV  DL T1 T2 ST CV  DL T1 T2 ST CV\r\n" + 
				" SAT01 N T1 T1 T1 T1 T1 ST T1 ST  "+printTRANSECKeyStatus("SAT01")+"-  -  -  -  - \r\n" + 
				" SAT02 Y Y  Y  Y  Y  Y  Y  Y  Y   "+printTRANSECKeyStatus("SAT02")+"-  -  -  -  - \r\n" +
				" SAT03 N T1 T1 T1 T1 T1 ST T1 ST  "+printTRANSECKeyStatus("SAT03")+"-  -  -  -  - \r\n" + 
				" SAT04 N T1 T1 T1 T1 T1 ST T1 ST  "+printTRANSECKeyStatus("SAT04")+"-  -  -  -  - \r\n" + 
				" SAT05 N T1 T1 T1 T1 T1 ST T1 ST  "+printTRANSECKeyStatus("SAT05")+"-  -  -  -  - \r\n" + 
				" SAT06 N T1 T1 T1 T1 T1 ST T1 ST  "+printTRANSECKeyStatus("SAT06")+"-  -  -  -  - \r\n" + 
				" SAT07 N T1 T1 T1 T1 T1 ST T1 ST  "+printTRANSECKeyStatus("SAT07")+"-  -  -  -  - \r\n" + 
				" SAT08 Y Y  Y  Y  Y  Y  Y  Y  Y   "+printTRANSECKeyStatus("SAT08")+"-  -  -  -  - \r\n" + 
				" SAT09 N T1 T1 T1 T1 T1 ST T1 ST  "+printTRANSECKeyStatus("SAT09")+"-  -  -  -  - \r\n" + 
				" SAT10 N T1 T1 T1 T1 T1 ST T1 ST  "+printTRANSECKeyStatus("SAT10")+"-  -  -  -  - \r\n";
		newOptionsArea =
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦ EXIT DISPLAY  ¦  ¦    COMSEC     ¦  ¦     OTAR      ¦  ¦UPDATE CON KEYS¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦UPDATE SAT KEYS¦  ¦      TSM      ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";		
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(2);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(81);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(82);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(83);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(84);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(85);  // NOT REAL VALUE JUST FOR TEST
		screenIndex.put(indexNumber, newScreenTemplate(2, true, false, true, false));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// NAV DATA UPDATE SCREENS
		// LAST SCREEN = TERMINAL CONTROL SCREEN (INDEX = 2)
		indexNumber = 17;
		newMainArea =
				"MAIN:TERMINAL:NAV DATA----------------------------------------------------------\r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                   PARAMETER      CURRENT    SRC    OPERATOR                    \r\n" + 
				"                                                                                \r\n" + 
				"                   LATITUDE         "+String.format("%1$6s", navData.getLatitude())+"    "+SRC[0]+"       "+getTempNavData(0)+"                    \r\n" +
				"                   LONGITUDE       "+String.format("%1$7s", navData.getLongitude())+"    "+SRC[1]+"      "+getTempNavData(1)+"                         \r\n" + 
				"                   ALTITUDE      "+String.format("%1$7s", navData.getAltitude())+"      "+SRC[2]+"    "+getTempNavData(2)+"                                 \r\n" + 
				"                   GND SPEED       "+String.format("%1$5s", navData.getSpeed())+"      "+SRC[3]+"                                            \r\n" + 
				"                   CLIMB           "+String.format("%1$5s", navData.getClimbRate())+"      "+SRC[4]+"                                            \r\n" + 
				"                   HEADING         "+String.format("%1$5s", navData.getHeading())+"      "+SRC[5]+"      "+getTempNavData(3)+"                                 \r\n" + 
				"                   PITCH           "+String.format("%1$5s", navData.getPitch())+"      "+SRC[6]+"      "+getTempNavData(4)+"                                 \r\n" + 
				"                   ROLL           "+String.format("%1$6s", navData.getRoll())+"      "+SRC[7]+"     "+getTempNavData(5)+"                                 \r\n" + 
				"                                                                                \r\n" +
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" +
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		newOptionsArea =
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦ SAVE CHANGES  ¦  ¦   LATITUDE    ¦  ¦   LONGITUDE   ¦  ¦   ALTITUDE    ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦     SPEED     ¦  ¦  CLIMB RATE   ¦  ¦   ATTITUDE    ¦  ¦ ABORT CHANGES ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";					
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(70);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(71);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(72);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(73);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(74);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(75);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(76);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(80);  // NOT REAL VALUE JUST FOR TEST
		screenIndex.put(indexNumber, newScreenTemplate(2, true, false, true, false));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// TERMINAL CONTROL POWER DOWN SCREEN
		// LAST SCREEN = TERMINAL CONTROL SCREEN (INDEX = 2)
		indexNumber = 19;
		newPromptArea =
                "--------------------------------------------------------------------------------\r\n" + 
                "     VERIFY TO POWER DOWN                                                       \r\n" + 
                "                                                                                \r\n" + 
                "                                                                                \r\n";
        newOptionsArea =
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		screenIndex.put(indexNumber, newScreenTemplate(2, true, true, false, false));
		
        // ENTER NET NUMBER SCREEN
        // LAST SCREEN = EHF SERVICES SCREEN (INDEX = 3)
        indexNumber = 21;
        newPromptArea = 
                "--------------------------------------------------------------------------------\r\n" + 
                "     ENTER NET NUMBER                                                           \r\n" + 
                "                                                                                \r\n" + 
                "                                                                                \r\n";
        newOptionsArea =
                "----------------------------------** SECRET **----------------------------------\r\n" + 
                "   _______________    _______________    _______________    _______________     \r\n" + 
                "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
                "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
                "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
                "   _______________    _______________    _______________    _______________     \r\n" +  
                "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
                "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
                "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		screenIndex.put(indexNumber, newScreenTemplate(3, true, true, false, false));
		associatedScreensIndex.put(indexNumber, associatedScreens);

		// PTP CALLS SCREEN
		// LAST SCREEN = EHF SERVICES SCREEN (INDEX = 3)
		indexNumber = 22;
        newPromptArea = 
                "--------------------------------------------------------------------------------\r\n" + 
                "     ENTER PTP CALL NUMBER                                                      \r\n" + 
                "                                                                                \r\n" + 
                "                                                                                \r\n";
		screenIndex.put(indexNumber, newScreenTemplate(21, true, true, false, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// TX/RX SUMMARY SCREEN
		// LAST SCREEN = EHF SERVICES SCREEN (INDEX = 3)
		indexNumber = 23;
		newMainArea =
				"MAIN:EHF:TX/RX SUMMARY----------------------------------------------------------\r\n" + 
				"                                                                                \r\n" + 
				"PRIMARY                                                                         \r\n" + 
				"UL SLOT        1                                                  4             \r\n" + 
				"SERVICE        NXX  XXX                                           NXX  XX       \r\n" + 
				"STATUS         IA   NONE NO                                       IA   NONE NO  \r\n" + 
				"                                                                                \r\n" + 
				"SERVICE TYPE   SECURE VOICE                                       KGV-11/DATA   \r\n" + 
				"DATA RATE/KEY  2400 SAV                                           2400  33      \r\n" +
				"RX PORTS       BXX                                                              \r\n" + 
				"TX PORT        BXX                                                R10           \r\n" + 
				"                                                                                \r\n" + 
				"RX BEAM/RSL    "+beam+"   96.2                                          -             \r\n" + 
				"C3 MODE/DLBR   LEAST   D108                                                     \r\n" + 
				"TX BEAM/RSL    "+beam+"   96.2                                          "+beam+"   96.2     \r\n" + 
				"TX C3 MODE     LEAST                                              LEAST         \r\n" + 
				"                                                                                \r\n" +
				"SECONDARY                                                                       \r\n" + 
				"UL SLOT                                                                         \r\n" + 
				"SERVICE                                                                         \r\n" + 
				"STATUS                                                                          \r\n" + 
				"                                                                                \r\n" + 
				"SERVICE TYPE                                                                    \r\n" +
				"DATA RATE/KEY                                                                   \r\n" + 
				"RX PORTS                                                                        \r\n" + 
				"TX PORT                                                                         \r\n" + 
				"                                                                                \r\n" + 
				"RX BEAM/RSL                                                                     \r\n" + 
				"C3 MODE/DLBR                                                                    \r\n" + 
				"TX BEAM/RSL                                                                     \r\n" + 
				"TX C3 MODE                                                                      \r\n";
		newOptionsArea =
                "----------------------------------** SECRET **----------------------------------\r\n" + 
                "   _______________    _______________    _______________    _______________     \r\n" + 
                "  ¦ EXIT DISPLAY  ¦  ¦RX-ONLY SUMMARY¦  ¦               ¦  ¦               ¦    \r\n" + 
                "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
                "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
                "   _______________    _______________    _______________    _______________     \r\n" +  
                "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
                "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
                "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
        associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(3);
		associatedScreens.add(24);
		screenIndex.put(indexNumber, newScreenTemplate(3, true, false, true, false));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// RX-ONLY SUMMARY SCREEN
		// LAST SCREEN = EHF SERVICES SCREEN (INDEX = 3)
		indexNumber = 24;
		newMainArea =
				"MAIN:EHF:RX-ONLY SUMMARY--------------------------------------------------------\r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"SERVICE                                                                         \r\n" + 
				"STATUS                                                                          \r\n" + 
				"                                                                                \r\n" + 
				"SERVICE TYPE                                                                    \r\n" + 
				"DATA RATE/KEY                                                                   \r\n" +
				"RX PORTS                                                                        \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"RX BEAM/RSL                                                                     \r\n" + 
				"C3 MODE/DLBR                                                                    \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" +
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"SERVICE                                                                         \r\n" + 
				"STATUS                                                                          \r\n" + 
				"                                                                                \r\n" + 
				"SERVICE TYPE                                                                    \r\n" +
				"DATA RATE/KEY                                                                   \r\n" + 
				"RX PORTS                                                                        \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"RX BEAM/RSL                                                                     \r\n" + 
				"C3 MODE/DLBR                                                                    \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		newOptionsArea =
                "----------------------------------** SECRET **----------------------------------\r\n" + 
                "   _______________    _______________    _______________    _______________     \r\n" + 
                "  ¦ EXIT DISPLAY  ¦  ¦ TX/RX SUMMARY ¦  ¦               ¦  ¦               ¦    \r\n" + 
                "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
                "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
                "   _______________    _______________    _______________    _______________     \r\n" +  
                "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
                "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
                "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(3);
		associatedScreens.add(23);
		screenIndex.put(indexNumber, newScreenTemplate(3, true, false, true, false));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// RESTART DL ACQ SCREEN
		// LAST SCREEN = EHF ACQ/LOGON SCREEN (INDEX = 4)
		indexNumber = 25;
		newPromptArea =
                "--------------------------------------------------------------------------------\r\n" + 
                "     VERIFY TO RESTART DL ACQ                                                   \r\n" + 
                "                                                                                \r\n" + 
                "                                                                                \r\n";
        newOptionsArea =
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		screenIndex.put(indexNumber, newScreenTemplate(4, true, true, false, false));
		
		// START UL SCREEN
		// LAST SCREEN = EHF ACQ/LOGON SCREEN (INDEX = 4)
		indexNumber = 26;
		newPromptArea =
                "--------------------------------------------------------------------------------\r\n" + 
                "     VERIFY TO START UL ACQ                                                     \r\n" + 
                "                                                                                \r\n" + 
                "                                                                                \r\n";
		screenIndex.put(indexNumber, newScreenTemplate(25, true, true, false, true));
		
		// LOG OFF SCREEN
		// LAST SCREEN = EHF ACQ/LOGON SCREEN (INDEX = 4)
		indexNumber = 27;
		newPromptArea =
                "--------------------------------------------------------------------------------\r\n" + 
                "     VERIFY TO LOG OFF SAT                                                      \r\n" + 
                "                                                                                \r\n" + 
                "                                                                                \r\n";
        screenIndex.put(indexNumber, newScreenTemplate(25, true, true, false, true));
		
		// EHF SATS SCREEN
		// LAST SCREEN = EHF ACQ/LOGON SCREEN
		indexNumber = 29;
		newMainArea =
				"MAIN:EHF ACQ/TRACK:SATELLITES---------------------------------------------------\r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"CURRENT CONSTELLATION  MIL                                                      \r\n" + 
				"                                                                                \r\n" + 
				"SATELLITES                                         TERMINAL LOGON PARAMETERS    \r\n" + 
				"               SET   RISE  BEAMS                          C2      C2     C3     \r\n" + 
				"  LABEL S EL   TIME  TIME  EC SA SB SC AH AL CI RB BEAM   ROBUST  CYCLE  MODE   \r\n" +
				"  SAT01 C XX  >24.0    -   Y  Y  Y  Y  Y  Y  Y  Y  EC/AG  HHR XX  ANY    MOST   \r\n" + 
				"  SAT02 N <0     -  >24.0  Y  Y  Y  Y  Y  Y  Y  Y  SB     HHR X   ANY    AUTO   \r\n" + 
				"  SAT03 N  -     -     -   Y  Y  -  -  -  -  -  -  SB     HHR XX  ANY    MOST   \r\n" + 
				"  SAT04 N  -     -     -   Y  Y  Y  Y  Y  Y  Y  Y  EC/AG  HHR XX  ANY    AUTO   \r\n" + 
				"  SAT05 N  -     -     -   Y  Y  Y  Y  Y  Y  Y  Y  EC/AG  HHR XX  ANY    MOST   \r\n" + 
				"  SAT06 N  -     -     -   Y  Y  Y  Y  Y  Y  Y  Y  EC/AG  HHR XX  ANY    AUTO   \r\n" + 
				"  SAT07 N  -     -     -   Y  Y  Y  Y  Y  Y  Y  Y  EC/AG  HHR XX  ANY    MOST   \r\n" + 
				"  SAT08 N  -     -     -   Y  Y  Y  Y  Y  Y  Y  Y  SB     HHR XX  ANY    AUTO   \r\n" +
				"  SAT09 N  -     -     -   Y  Y  -  -  -  -  -  -         CI      ANY    MOST   \r\n" + 
				"  SAT10 N  -     -     -   Y  Y  -  -  -  -  -  -         CI      ANY    AUTO   \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"KGV-11 STATUS                                                                   \r\n" + 
				"                                     HIGH                                       \r\n" +
				"         KEK      DL      UL         COVER                                      \r\n" + 
				"  TYPE   UNIQUE   "+satName1+"   "+satName1+" ST   "+satName1+"           \r\n" + 
				"  LOAD   R        B       B          B                                          \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		newOptionsArea =
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦ EXIT DISPLAY  ¦  ¦  UPDATE SAT   ¦  ¦   RED FILL    ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(4);
		associatedScreens.add(136);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(137);  // NOT REAL VALUE JUST FOR TEST
		screenIndex.put(indexNumber, newScreenTemplate(4, true, false, true, false));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// POINTING DATA SCREEN
		// LAST SCREEN = EHF ACQ/LOGON SCREEN
		indexNumber = 30;
		newMainArea =
				"MAIN:EHF ACQ/TRACK:POINTING-----------------------------------------------------\r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                SATELLITE   EPHEMERIS     OPERATOR    OFFSETS                   \r\n" + 
				"                                                                                \r\n" + 
				"                AZIMUTH         "+upperAzimuth+"     "+String.format("%1$8s", operatorInputs[0])+"       "+String.format("%1$4s", ephemeris[2])+"                   \r\n" + 
				"                                                                                \r\n" + 
				"                ELEVATION        "+elevation+"     "+String.format("%1$8s", operatorInputs[1])+"       "+String.format("%1$4s", ephemeris[3])+"                   \r\n" +
				"                                                                                \r\n" + 
				"                RANGE         "+String.format("%1$7s", ephemeris[0])+"     "+String.format("%1$8s", operatorInputs[2])+"       "+String.format("%1$4s", ephemeris[4])+"                   \r\n" + 
				"                                                                                \r\n" + 
				"                RANGE RATE    "+String.format("%1$7s", ephemeris[1])+"     "+String.format("%1$8s", operatorInputs[3])+"       "+String.format("%1$4s", ephemeris[5])+"                   \r\n" + 
				"                                                                                \r\n" + 
				"                ACQ MODE     "+String.format("%1$8s", operatorInputs[4])+"                                           \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" +
				"                                                                                \r\n" + 
				"                                          TRACK BEAM "+beam+"                         \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                ANTENNA                   SNRL        "+String.format("%1$7s", ephemeris[6])+"                   \r\n" + 
				"                                                                                \r\n" +
				"                AZIMUTH         "+lowerAzimuth+"     SNRHC       "+String.format("%1$7s", ephemeris[7])+"                   \r\n" + 
				"                                                                                \r\n" + 
				"                ELEVATION        "+elevation+"     SNRHF       "+String.format("%1$7s", ephemeris[8])+"                   \r\n" + 
				"                                                                                \r\n" + 
				"                MODE            "+mode+"     ESNR        "+String.format("%1$7s", ephemeris[9])+"                   \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		newOptionsArea = 
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦ EXIT DISPLAY  ¦  ¦    CHANGE     ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦  PARAMETERS   ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(4);
		associatedScreens.add(145);
		screenIndex.put(indexNumber, newScreenTemplate(4, true, false, true, false));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// EHF ACQ/LOGON SCREEN Cont
		// LAST SCREEN = EHF ACQ/LOGON SCREEN
		indexNumber = 31;
		newOptionsArea =
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦ PREV OPTIONS  ¦  ¦   SWITCHES    ¦  ¦  AGILE BEAM   ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦    STATUS     ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(4);
		associatedScreens.add(128);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(129);  // NOT REAL VALUE JUST FOR TEST
		screenIndex.put(indexNumber, newScreenTemplate(4, true, true, true, false));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// VERIFY FOR DISRUPTIVE TEST SCREEN
		// LAST PAGE = BUILT-IN TEST SCREEN (INDEX = 5)
		indexNumber = 33;
        newPromptArea = 
                "--------------------------------------------------------------------------------\r\n" + 
                "     VERIFY FOR DISRUPTIVE TEST                                                 \r\n" + 
                "                                                                                \r\n" + 
                "                                                                                \r\n";
		newOptionsArea =
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		screenIndex.put(indexNumber, newScreenTemplate(5, true, true, false, false));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// FAULT LOG PAGE
		// LAST PAGE = BUILT-IN TEST SCREEN (INDEX = 5)
		indexNumber = 34;
		newOptionsArea = 
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦     EXIT      ¦  ¦   PRINT LOG   ¦  ¦PRINT ON DEMAND¦  ¦PRINT IMMEDIATE¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦ DELETE FAULT  ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦      LOG      ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";	
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(5);
		associatedScreens.add(141);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(142);   // IS CORRECT
		associatedScreens.add(143);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(144);  // NOT REAL VALUE JUST FOR TEST
		screenIndex.put(indexNumber, newScreenTemplate(5, true, true, true, false));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// VERIFY C2/C3 LOOPBACK SCREEN
		// LAST PAGE = BUILT-IN TEST SCREEN (INDEX = 5)
		indexNumber = 35;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     VERIFY FOR C2/C3 LOOPBACK                                                  \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		newOptionsArea =
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		screenIndex.put(indexNumber, newScreenTemplate(5, true, true, false, false));
		
		// SAVED NAV DATA CHANGES SCREEN
		// LAST SCREEN = NAV DATA SCREEN (INDEX = 17)
		indexNumber = 70;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(0);
		associatedScreens.add(14);
		associatedScreens.add(15);
		associatedScreens.add(16);
		associatedScreens.add(17);
		associatedScreens.add(18);
		associatedScreens.add(19);
		associatedScreens.add(20);
		screenIndex.put(indexNumber, newScreenTemplate(2, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// LATITUDE SCREEN
		// LAST SCREEN = NAV DATA SCREEN (INDEX = 17)
		indexNumber = 71;  // NOT REAL VALUE JUST FOR TEST
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     ENTER LATITUDE (DEG MIN N/S)                                               \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		newOptionsArea =
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		screenIndex.put(indexNumber, newScreenTemplate(17, true, true, false, false));
		
		// LONGITUDE SCREEN
		// LAST SCREEN = NAD DATA SCREEN (INDEX = 17)
		indexNumber = 72;  // NOT REAL VALUE JUST FOR TEST
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     ENTER LONGITUDE (DEG MIN E/W)                                              \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		screenIndex.put(indexNumber, newScreenTemplate(71, true, true, false, true));
		
		// ALTITUDE SCREEN
		// LAST SCREEN = NAD DATA SCREEN (INDEX = 17)
		indexNumber = 73;  // NOT REAL VALUE JUST FOR TEST
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     ENTER ALTITUDE (FT)                                                        \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		screenIndex.put(indexNumber, newScreenTemplate(71, true, true, false, true));
		
		// SPEED SCREEN
		// LAST SCREEN = NAD DATA SCREEN (INDEX = 17)
		indexNumber = 74;  // NOT REAL VALUE JUST FOR TEST
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                           NAV SYSTEM DATA AVAILABLE                            \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(70);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(71);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(72);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(73);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(74);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(75);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(76);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(2);  // NOT REAL VALUE JUST FOR TEST
		screenIndex.put(indexNumber, newScreenTemplate(17, true, true, false, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// CLIMB RATE SCREEN
		// LAST SCREEN = NAD DATA SCREEN (INDEX = 17)
		indexNumber = 75;  // NOT REAL VALUE JUST FOR TEST
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(70);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(71);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(72);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(73);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(74);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(75);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(76);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(2);  // NOT REAL VALUE JUST FOR TEST
		screenIndex.put(indexNumber, newScreenTemplate(74, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// ATTITUDE SCREEN
		// LAST SCREEN = NAV DATA SCREEN (INDEX = 17)
		indexNumber = 76;  // NOT REAL VALUE JUST FOR TEST
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     SELECT PARAM                                                               \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";				
		newOptionsArea =
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦     EXIT      ¦  ¦    HEADING    ¦  ¦     PITCH     ¦  ¦     ROLL      ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(17);
		associatedScreens.add(77);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(78);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(79);  // NOT REAL VALUE JUST FOR TEST
		screenIndex.put(indexNumber, newScreenTemplate(17, true, true, false, false));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// HEADING SCREEN
		// LAST SCREEN = ATTITUDE SCREEN (INDEX = 76)
		indexNumber = 77;  // NOT REAL VALUE JUST FOR TEST
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     ENTER HEADING (DEG)                                                        \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		screenIndex.put(indexNumber, newScreenTemplate(71, true, true, false, true));
		
		// PITCH SCREEN
		// LAST SCREEN = ATTITUDE SCREEN (INDEX = 76)
		indexNumber = 78;  // NOT REAL VALUE JUST FOR TEST
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     ENTER PITCH (DEG)                                                          \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		screenIndex.put(indexNumber, newScreenTemplate(71, true, true, false, true));
		
		// ROLL SCREEN
		// LAST SCREEN = ATTITUDE SCREEN (INDEX = 76)
		indexNumber = 79;  // NOT REAL VALUE JUST FOR TEST
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     ENTER ROLL (DEG)                                                           \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		screenIndex.put(indexNumber, newScreenTemplate(71, true, true, false, true));
		
		// ABORTED NAV DATA CHANGES SCREEN
		// LAST SCREEN = NAV DATA SCREEN (INDEX = 17)
		indexNumber = 80;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(0);
		associatedScreens.add(14);
		associatedScreens.add(15);
		associatedScreens.add(16);
		associatedScreens.add(17);
		associatedScreens.add(18);
		associatedScreens.add(19);
		associatedScreens.add(20);
		screenIndex.put(indexNumber, newScreenTemplate(2, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// OTAR SCREEN
		// LAST SCREEN = CRYPTO KEYS SCREEN (INDEX = 15)
		indexNumber = 82;
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     SELECT OTAR OPTION                                                         \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		newOptionsArea =
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦     EXIT      ¦  ¦   ALL KEYS    ¦  ¦  FUTURE KEYS  ¦  ¦ SEND BINDING  ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦SINGLE CURRENT ¦  ¦ SINGLE FUTURE ¦  ¦  SWITCH AUTO  ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(15);
		associatedScreens.add(93);
		associatedScreens.add(94);
		associatedScreens.add(95);
		associatedScreens.add(96);
		associatedScreens.add(97);
		associatedScreens.add(98);
		screenIndex.put(indexNumber, newScreenTemplate(15, true, true, false, false));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// TSM SCREEN
		// LAST SCREEN = CRYPTO KEYS SCREEN (INDEX = 15)
		indexNumber = 85;
		newOptionsArea =
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦ TSM KEY LOAD  ¦  ¦ TSM KEY SAVE  ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(86);
		associatedScreens.add(89);
		screenIndex.put(indexNumber, newScreenTemplate(15, true, true, true, false));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// TSM KEY LOAD SCREEN
		// LAST SCREEN = TSM SCREEN (INDEX = 85)
		indexNumber = 86;
        newPromptArea = 
                "--------------------------------------------------------------------------------\r\n" + 
                "     SELECT OPTION                                                              \r\n" + 
				"                                    WORKING                                     \r\n" + 
                "                                                                                \r\n";
		newOptionsArea =
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		screenIndex.put(indexNumber, newScreenTemplate(85, true, true, false, false));
		
		// TSM KEY LOAD CONT SCREEN
		// LAST SCREEN = TSM SCREEN (INDEX = 85)
		indexNumber = 87;
        newPromptArea = 
                "--------------------------------------------------------------------------------\r\n" + 
                "     SELECT OPTION                                                              \r\n" + 
				"                                                                                \r\n" + 
                "                                                                                \r\n";
        screenIndex.put(indexNumber, newScreenTemplate(86, true, true, false, true));
        
        // NO TERMINAL KEYS IN TSM SCREEN
        // LAST SCREEN = TSM KEY LOAD SCREEN (INDEX = 86) or TSM KEY LOAD CONT SCREEN (INDEX = 87)
        indexNumber = 88;
        newPromptArea = 
                "--------------------------------------------------------------------------------\r\n" + 
                "     SELECT OPTION                                                              \r\n" + 
				"                                                                                \r\n" + 
                "                            NO TERMINAL KEYS IN TSM                             \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(2);
		associatedScreens.add(81);
		associatedScreens.add(82);
		associatedScreens.add(83);
		associatedScreens.add(84);
		associatedScreens.add(85);
		screenIndex.put(indexNumber, newScreenTemplate(15, true, true, false, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
        
		// 89 COPY OF 86
		indexNumber = 89;
		screenIndex.put(indexNumber, newScreenTemplate(86, true, true, true, true));
		// 90 COPY OF 87
		indexNumber = 90;
		screenIndex.put(indexNumber, newScreenTemplate(87, true, true, true, true));
		// 91 KEYS SAVED
        indexNumber = 91;
        newPromptArea = 
                "--------------------------------------------------------------------------------\r\n" + 
                "     SELECT OPTION                                                              \r\n" + 
				"                                                                                \r\n" + 
                "                               KEYS SAVED TO TSM                                \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(2);
		associatedScreens.add(81);
		associatedScreens.add(82);
		associatedScreens.add(83);
		associatedScreens.add(84);
		associatedScreens.add(85);
		screenIndex.put(indexNumber, newScreenTemplate(15, true, true, false, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// SCREEN 92 KEYS LOADED FROM TSM
		indexNumber = 92;
        newPromptArea = 
                "--------------------------------------------------------------------------------\r\n" + 
                "     SELECT OPTION                                                              \r\n" + 
				"                                                                                \r\n" + 
                "                              KEYS LOADED FROM TSM                              \r\n";
        
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(2);
		associatedScreens.add(81);
		associatedScreens.add(82);
		associatedScreens.add(83);
		associatedScreens.add(84);
		associatedScreens.add(85);
		screenIndex.put(indexNumber, newScreenTemplate(15, true, true, false, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// OTAR ALL KEYS SCREEN
		// LAST SCREEN = OTAR SCREEN (INDEX = 82)
		indexNumber = 93;
        newPromptArea = 
                "--------------------------------------------------------------------------------\r\n" + 
                "     VERIFY TO REQUEST OTAR                                                     \r\n" + 
				"                                                                                \r\n" + 
                "                                                                                \r\n";
		newOptionsArea =
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		screenIndex.put(indexNumber, newScreenTemplate(82, true, true, false, false));
		
		// OTAR FUTURE KEYS SCREEN
		// LAST SCREEN = OTAR SCREEN (INDEX = 82)
		indexNumber = 94;
		screenIndex.put(indexNumber, newScreenTemplate(93, true, true, true, true));
		
		// OTAR SEND BINDING SCREEN
		// LAST SCREEN = OTAR SCREEN (INDEX = 82)
		indexNumber = 95;
		screenIndex.put(indexNumber, newScreenTemplate(93, true, true, true, true));
		
		// OTAR SINGLE CURRENT SCREEN
		// LAST SCREEN = OTAR SCREEN (INDEX = 82)
		indexNumber = 96;
        newPromptArea = 
                "--------------------------------------------------------------------------------\r\n" + 
                "     SELECT TRANSEC KEY TYPE                                                    \r\n" + 
				"                                                                                \r\n" + 
                "                                                                                \r\n";
		newOptionsArea =
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦      DL       ¦  ¦     UL T1     ¦  ¦     UL T2     ¦  ¦     UL ST     ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦     COVER     ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(101);
		associatedScreens.add(102);
		associatedScreens.add(103);
		associatedScreens.add(104);
		associatedScreens.add(105);
		screenIndex.put(indexNumber, newScreenTemplate(82, true, true, false, false));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// OTAR SINGLE FUTURE SCREEN
		// LAST SCREEN = OTAR SCREEN (INDEX = 82)
		indexNumber = 97;
		newOptionsArea =
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦   CHAINING    ¦  ¦      DL       ¦  ¦     UL T1     ¦  ¦     UL T2     ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦     UL ST     ¦  ¦     COVER     ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(1900); // NOT REAL VALUE CHANGE LATER!!!!!!!!!
		associatedScreens.add(106);
		associatedScreens.add(107);
		associatedScreens.add(108);
		associatedScreens.add(109);
		associatedScreens.add(110);
		screenIndex.put(indexNumber, newScreenTemplate(96, true, true, true, false));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// OTAR SWITCH AUTO SCREEN
		// LAST SCREEN = OTAR SCREEN (INDEX = 82)
		indexNumber = 98;
        newPromptArea = 
                "--------------------------------------------------------------------------------\r\n" + 
                "     AUTO OTAR                                                                  \r\n" + 
				"                                                                                \r\n" + 
                "                                                                                \r\n";
		newOptionsArea =
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦      ON       ¦  ¦      OFF      ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(99);
		associatedScreens.add(100);
		screenIndex.put(indexNumber, newScreenTemplate(82, true, true, false, false));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// AUTO OTAR ON SCREEN
		// LAST SCREEN = OTAR SWITCH AUTO SCREEN (INDEX = 98)
		indexNumber = 99;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(15);
		associatedScreens.add(93);
		associatedScreens.add(94);
		associatedScreens.add(95);
		associatedScreens.add(96);
		associatedScreens.add(97);
		associatedScreens.add(98);
		screenIndex.put(indexNumber, newScreenTemplate(82, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// AUTO OTAR OFF SCREEN
		// LAST SCREEN = OTAR SWITCH AUTO SCREEN (INDEX = 98)
		indexNumber = 100;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(15);
		associatedScreens.add(93);
		associatedScreens.add(94);
		associatedScreens.add(95);
		associatedScreens.add(96);
		associatedScreens.add(97);
		associatedScreens.add(98);
		screenIndex.put(indexNumber, newScreenTemplate(82, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// SELECT SAT (SINGLE CURRENT DL) SCREEN
		// LAST SCREEN = OTAR SINGLE CURRENT SCREEN (INDEX = 96) OR OTAR SINGLE FUTURE SCREEN (INDEX = 97)
		indexNumber = 101;
        newPromptArea = 
                "--------------------------------------------------------------------------------\r\n" + 
                "     SELECT SAT                                                                 \r\n" + 
				"                                                                                \r\n" + 
                "                                                                                \r\n";
		newOptionsArea =
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦     SAT01     ¦  ¦     SAT02     ¦  ¦     SAT03     ¦  ¦     SAT04     ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦     SAT05     ¦  ¦     SAT06     ¦  ¦     SAT07     ¦  ¦ MORE OPTIONS  ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(121);
		associatedScreens.add(122);
		associatedScreens.add(123);
		associatedScreens.add(124);
		associatedScreens.add(125);
		associatedScreens.add(126);
		associatedScreens.add(127);
		associatedScreens.add(111);
		screenIndex.put(indexNumber, newScreenTemplate(82, true, true, false, false));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// SELECT SAT (SINGLE CURRENT UL T1) SCREEN
		// LAST SCREEN = OTAR SINGLE CURRENT SCREEN (INDEX = 96) OR OTAR SINGLE FUTURE SCREEN (INDEX = 97)
		indexNumber = 102;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(121);
		associatedScreens.add(122);
		associatedScreens.add(123);
		associatedScreens.add(124);
		associatedScreens.add(125);
		associatedScreens.add(126);
		associatedScreens.add(127);
		associatedScreens.add(112);
		screenIndex.put(indexNumber, newScreenTemplate(101, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// SELECT SAT (SINGLE CURRENT UL T2) SCREEN
		// LAST SCREEN = OTAR SINGLE CURRENT SCREEN (INDEX = 96) OR OTAR SINGLE FUTURE SCREEN (INDEX = 97)
		indexNumber = 103;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(121);
		associatedScreens.add(122);
		associatedScreens.add(123);
		associatedScreens.add(124);
		associatedScreens.add(125);
		associatedScreens.add(126);
		associatedScreens.add(127);
		associatedScreens.add(113);
		screenIndex.put(indexNumber, newScreenTemplate(101, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// SELECT SAT (SINGLE CURRENT UL ST) SCREEN
		// LAST SCREEN = OTAR SINGLE CURRENT SCREEN (INDEX = 96) OR OTAR SINGLE FUTURE SCREEN (INDEX = 97)
		indexNumber = 104;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(121);
		associatedScreens.add(122);
		associatedScreens.add(123);
		associatedScreens.add(124);
		associatedScreens.add(125);
		associatedScreens.add(126);
		associatedScreens.add(127);
		associatedScreens.add(114);
		screenIndex.put(indexNumber, newScreenTemplate(101, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// SELECT SAT (SINGLE CURRENT COVER) SCREEN
		// LAST SCREEN = OTAR SINGLE CURRENT SCREEN (INDEX = 96) OR OTAR SINGLE FUTURE SCREEN (INDEX = 97)
		indexNumber = 105;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(121);
		associatedScreens.add(122);
		associatedScreens.add(123);
		associatedScreens.add(124);
		associatedScreens.add(125);
		associatedScreens.add(126);
		associatedScreens.add(127);
		associatedScreens.add(115);
		screenIndex.put(indexNumber, newScreenTemplate(101, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// SELECT SAT (SINGLE FUTURE DL) SCREEN
		// LAST SCREEN = OTAR SINGLE CURRENT SCREEN (INDEX = 96) OR OTAR SINGLE FUTURE SCREEN (INDEX = 97)
		indexNumber = 106;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(121);
		associatedScreens.add(122);
		associatedScreens.add(123);
		associatedScreens.add(124);
		associatedScreens.add(125);
		associatedScreens.add(126);
		associatedScreens.add(127);
		associatedScreens.add(116);
		screenIndex.put(indexNumber, newScreenTemplate(101, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// SELECT SAT (SINGLE FUTURE UL T1) SCREEN
		// LAST SCREEN = OTAR SINGLE CURRENT SCREEN (INDEX = 96) OR OTAR SINGLE FUTURE SCREEN (INDEX = 97)
		indexNumber = 107;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(121);
		associatedScreens.add(122);
		associatedScreens.add(123);
		associatedScreens.add(124);
		associatedScreens.add(125);
		associatedScreens.add(126);
		associatedScreens.add(127);
		associatedScreens.add(117);
		screenIndex.put(indexNumber, newScreenTemplate(101, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// SELECT SAT (SINGLE FUTURE UL T2) SCREEN
		// LAST SCREEN = OTAR SINGLE CURRENT SCREEN (INDEX = 96) OR OTAR SINGLE FUTURE SCREEN (INDEX = 97)
		indexNumber = 108;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(121);
		associatedScreens.add(122);
		associatedScreens.add(123);
		associatedScreens.add(124);
		associatedScreens.add(125);
		associatedScreens.add(126);
		associatedScreens.add(127);
		associatedScreens.add(118);
		screenIndex.put(indexNumber, newScreenTemplate(101, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// SELECT SAT (SINGLE FUTURE UL ST) SCREEN
		// LAST SCREEN = OTAR SINGLE CURRENT SCREEN (INDEX = 96) OR OTAR SINGLE FUTURE SCREEN (INDEX = 97)
		indexNumber = 109;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(121);
		associatedScreens.add(122);
		associatedScreens.add(123);
		associatedScreens.add(124);
		associatedScreens.add(125);
		associatedScreens.add(126);
		associatedScreens.add(127);
		associatedScreens.add(119);
		screenIndex.put(indexNumber, newScreenTemplate(101, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// SELECT SAT (SINGLE FUTURE COVER) SCREEN
		// LAST SCREEN = OTAR SINGLE CURRENT SCREEN (INDEX = 96) OR OTAR SINGLE FUTURE SCREEN (INDEX = 97)
		indexNumber = 110;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(121);
		associatedScreens.add(122);
		associatedScreens.add(123);
		associatedScreens.add(124);
		associatedScreens.add(125);
		associatedScreens.add(126);
		associatedScreens.add(127);
		associatedScreens.add(120);
		screenIndex.put(indexNumber, newScreenTemplate(101, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// SELECT SAT CONT (SINGLE CURRENT DL) SCREEN
		// LAST SCREEN = SELECT SAT (SINGLE CURRENT DL) SCREEN (INDEX = 101)
		indexNumber = 111;
		newOptionsArea =
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦ PREV OPTIONS  ¦  ¦     SAT08     ¦  ¦     SAT09     ¦  ¦     SAT10     ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(101);
		associatedScreens.add(128);
		associatedScreens.add(129);
		associatedScreens.add(130);
		screenIndex.put(indexNumber, newScreenTemplate(101, true, true, true, false));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// SELECT SAT CONT (SINGLE CURRENT UL T1) SCREEN
		// LAST SCREEN = SELECT SAT (SINGLE CURRENT UL T1) SCREEN (INDEX = 102)
		indexNumber = 112;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(102);
		associatedScreens.add(128);
		associatedScreens.add(129);
		associatedScreens.add(130);
		screenIndex.put(indexNumber, newScreenTemplate(111, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// SELECT SAT CONT (SINGLE CURRENT UL T2) SCREEN
		// LAST SCREEN = SELECT SAT (SINGLE CURRENT UL T2) SCREEN (INDEX = 103)
		indexNumber = 113;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(103);
		associatedScreens.add(128);
		associatedScreens.add(129);
		associatedScreens.add(130);
		screenIndex.put(indexNumber, newScreenTemplate(111, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// SELECT SAT CONT (SINGLE CURRENT UL ST) SCREEN
		// LAST SCREEN = SELECT SAT (SINGLE CURRENT UL ST) SCREEN (INDEX = 104)
		indexNumber = 114;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(104);
		associatedScreens.add(128);
		associatedScreens.add(129);
		associatedScreens.add(130);
		screenIndex.put(indexNumber, newScreenTemplate(111, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// SELECT SAT CONT (SINGLE CURRENT COVER) SCREEN
		// LAST SCREEN = SELECT SAT (SINGLE CURRENT COVER) SCREEN (INDEX = 105)
		indexNumber = 115;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(105);
		associatedScreens.add(128);
		associatedScreens.add(129);
		associatedScreens.add(130);
		screenIndex.put(indexNumber, newScreenTemplate(111, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// SELECT SAT CONT (SINGLE FUTURE DL) SCREEN
		// LAST SCREEN = SELECT SAT (SINGLE FUTURE DL) SCREEN (INDEX = 106)
		indexNumber = 116;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(106);
		associatedScreens.add(128);
		associatedScreens.add(129);
		associatedScreens.add(130);
		screenIndex.put(indexNumber, newScreenTemplate(111, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// SELECT SAT CONT (SINGLE FUTURE UL T1) SCREEN
		// LAST SCREEN = SELECT SAT (SINGLE FUTURE UL T1) SCREEN (INDEX = 107)
		indexNumber = 117;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(107);
		associatedScreens.add(128);
		associatedScreens.add(129);
		associatedScreens.add(130);
		screenIndex.put(indexNumber, newScreenTemplate(111, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// SELECT SAT CONT (SINGLE FUTURE UL T2) SCREEN
		// LAST SCREEN = SELECT SAT (SINGLE FUTURE UL T2) SCREEN (INDEX = 108)
		indexNumber = 118;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(108);
		associatedScreens.add(128);
		associatedScreens.add(129);
		associatedScreens.add(130);
		screenIndex.put(indexNumber, newScreenTemplate(111, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// SELECT SAT CONT (SINGLE FUTURE UL ST) SCREEN
		// LAST SCREEN = SELECT SAT (SINGLE FUTURE UL ST) SCREEN (INDEX = 109)
		indexNumber = 119;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(109);
		associatedScreens.add(128);
		associatedScreens.add(129);
		associatedScreens.add(130);
		screenIndex.put(indexNumber, newScreenTemplate(111, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// SELECT SAT CONT (SINGLE FUTURE COVER) SCREEN
		// LAST SCREEN = SELECT SAT (SINGLE FUTURE COVER) SCREEN (INDEX = 110)
		indexNumber = 120;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(110);
		associatedScreens.add(128);
		associatedScreens.add(129);
		associatedScreens.add(130);
		screenIndex.put(indexNumber, newScreenTemplate(111, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// INDEXES 121 - 130 SELECTED SATS
		// LAST SCREEN = INDEXES 101 - 120
		for (int i = 121; i < 131; i++) {
			indexNumber = i;
			screenIndex.put(indexNumber, newScreenTemplate(93, true, true, true, true));
		}
		
		// NOT LOGGED ON OTAR SCREEN
		// LAST SCREEN = INDEXES 121 - 130 SELECTED SATS
		indexNumber = 131;
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     SELECT OTAR OPTION                                                         \r\n" + 
				"                                                                                \r\n" + 
				"                                 NOT LOGGED ON                                  \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(15);
		associatedScreens.add(93);
		associatedScreens.add(94);
		associatedScreens.add(95);
		associatedScreens.add(96);
		associatedScreens.add(97);
		associatedScreens.add(98);
		screenIndex.put(indexNumber, newScreenTemplate(82, true, true, false, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// NOT LOGGED ON BUILT-IN TEST SCREEN
		// LAST SCREEN = MAIN SCREEN (INDEX = 0)
		indexNumber = 132;
		newOptionsArea = 
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦ EXIT DISPLAY  ¦  ¦   I/O PORTS   ¦  ¦DISRUPTIVE TEST¦  ¦   FAULT LOG   ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(0);
		associatedScreens.add(32);
		associatedScreens.add(33);
		associatedScreens.add(34);
		screenIndex.put(indexNumber, newScreenTemplate(5, true, true, true, false));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		
		
		// UPDATE SAT SCREEN
		// LAST SCREEN = EHF SATS SCREEN
		indexNumber = 136;  // NOT REAL VALUE JUST FOR TEST
		newOptionsArea =
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦     SAT01     ¦  ¦     SAT02     ¦  ¦     SAT03     ¦  ¦     SAT04     ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦     SAT05     ¦  ¦     SAT06     ¦  ¦     SAT07     ¦  ¦ MORE OPTIONS  ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(160);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(161);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(162);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(163);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(164);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(165);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(166);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(170);  // NOT REAL VALUE JUST FOR TEST
		screenIndex.put(indexNumber, newScreenTemplate(29, true, true, true, false));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// FAULT LOG EMPTY SCREEN
		// LAST SCREEN = FAULY LOG SCREEN (INDEX = 34)
		indexNumber = 141;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     SELECT OPTION                                                              \r\n" + 
				"                                                                                \r\n" + 
				"                                FAULT LOG EMPTY                                 \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(5);
		associatedScreens.add(141);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(34);   // IS CORRECT
		associatedScreens.add(142);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(143);  // NOT REAL VALUE JUST FOR TEST
		screenIndex.put(indexNumber, newScreenTemplate(34, true, true, false, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// PRINT ON DEMAND SCREEN
		// LAST PAGE = FAULT LOG SCREEN (INDEX = 34)
		indexNumber = 142;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(5);
		associatedScreens.add(141);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(142);   // IS CORRECT
		associatedScreens.add(143);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(144);  // NOT REAL VALUE JUST FOR TEST
		screenIndex.put(indexNumber, newScreenTemplate(34, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// PRINT IMMEDIATE SCREEN
		// LAST PAGE = FAULT LOG SCREEN (INDEX = 34)
		indexNumber = 143;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(5);
		associatedScreens.add(141);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(142);   // IS CORRECT
		associatedScreens.add(143);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(144);  // NOT REAL VALUE JUST FOR TEST
		screenIndex.put(indexNumber, newScreenTemplate(34, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// CHANGE PARAMETERS SCREEN
		// LAST SCREEN = POINTING DATA SCREEN (INDEX = 30)
		indexNumber = 145;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     SELECT PARAMETER TO CHANGE                                                 \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		newOptionsArea = 
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦ SAVE CHANGES  ¦  ¦    AZIMUTH    ¦  ¦   ELEVATION   ¦  ¦     RANGE     ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦  RANGE RATE   ¦  ¦   ACQ MODE    ¦  ¦ ABORT CHANGES ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(184);
		associatedScreens.add(146);
		associatedScreens.add(147);
		associatedScreens.add(148);
		associatedScreens.add(149);
		associatedScreens.add(150);
		associatedScreens.add(30);
		screenIndex.put(indexNumber, newScreenTemplate(30, true, true, false, false));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// MANUAL AZIMUTH SCREEN
		// LAST SCREEN = CHANGE PARAMETERS SCREEN (INDEX = 145)
		indexNumber = 146;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     SELECT AZIMUTH OPTION                                                      \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		newOptionsArea = 
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦     AUTO      ¦  ¦    MANUAL     ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(151);
		associatedScreens.add(156);
		screenIndex.put(indexNumber, newScreenTemplate(145, true, true, false, false));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// MANUAL ELEVATION SCREEN
		// LAST SCREEN = CHANGE PARAMETERS SCREEN (INDEX = 145)
		indexNumber = 147;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     SELECT ELEVATION OPTION                                                    \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(152);
		associatedScreens.add(157);
		screenIndex.put(indexNumber, newScreenTemplate(146, true, true, false, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// MANUAL RANGE SCREEN
		// LAST SCREEN = CHANGE PARAMETERS SCREEN (INDEX = 145)
		indexNumber = 148;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     SELECT RANGE OPTION                                                        \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(153);
		associatedScreens.add(158);
		screenIndex.put(indexNumber, newScreenTemplate(146, true, true, false, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// MANUAL RANGE RATE SCREEN
		// LAST SCREEN = CHANGE PARAMETERS SCREEN (INDEX = 145)
		indexNumber = 149;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     SELECT RANGE RATE OPTION                                                   \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(154);
		associatedScreens.add(159);
		screenIndex.put(indexNumber, newScreenTemplate(146, true, true, false, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// ACQ MODE SCREEN
		// LAST SCREEN = CHANGE PARAMETERS SCREEN (INDEX = 145)
		indexNumber = 150;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     SELECT ACQ MODE OPTION                                                     \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		newOptionsArea = 
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦     AUTO      ¦  ¦      LDR      ¦  ¦    LDR/MDR    ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(155);
		associatedScreens.add(182);
		associatedScreens.add(183);
		screenIndex.put(indexNumber, newScreenTemplate(146, true, true, false, false));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// AUTO OPTIONS
		for (int i = 151; i < 156; i++) {
			indexNumber = i;
			associatedScreens = new ArrayList<Integer>();
			associatedScreens.add(184);
			associatedScreens.add(146);
			associatedScreens.add(147);
			associatedScreens.add(148);
			associatedScreens.add(149);
			associatedScreens.add(150);
			associatedScreens.add(30);
			screenIndex.put(indexNumber, newScreenTemplate(145, true, true, true, true));
			associatedScreensIndex.put(indexNumber, associatedScreens);
		}
		
		// MANUAL POINTING DATA 156 - 159
		
		// ENTER AZIMUTH SCREEN
		// LAST SCREEN = MANUAL AZIMUTH SCREEN (INDEX = 146)
		indexNumber = 156;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     ENTER AZIMUTH (DEG)                                                        \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		newOptionsArea = 
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		screenIndex.put(indexNumber, newScreenTemplate(146, true, true, false, false));
		
		// ENTER ELEVATION SCREEN
		// LAST SCREEN = MANUAL ELEVATION SCREEN (INDEX = 147)
		indexNumber = 157;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     ENTER ELEVATION (DEG)                                                      \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		screenIndex.put(indexNumber, newScreenTemplate(156, true, true, false, true));
		
		// ENTER RANGE SCREEN
		// LAST SCREEN = MANUAL RANGE SCREEN (INDEX = 148)
		indexNumber = 158;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     ENTER RANGE (KM)                                                           \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		screenIndex.put(indexNumber, newScreenTemplate(156, true, true, false, true));
		
		// ENTER RANGE RATE SCREEN
		// LAST SCREEN = MANUAL RANGE RATE SCREEN (INDEX = 149)
		indexNumber = 159;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     ENTER RANGE RATE (M/S)                                                     \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		screenIndex.put(indexNumber, newScreenTemplate(156, true, true, false, true));
		
		
        /*
         * Satellite Authorization Screens
         */
		// UPDATE SELECTED SAT SCREEN
		// LAST SCREEN = UPDATE SAT SCREEN/ UPDATE SCAT SCREEN CONT
		indexNumber = 160;
		newOptionsArea =
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦     EXIT      ¦  ¦ AUTHORIZE SAT ¦  ¦ REQUEST EPHEM ¦  ¦ DEFAULT LOGON ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦  PARAMETERS   ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(29);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(171);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(172);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(173);  // NOT REAL VALUE JUST FOR TEST
		screenIndex.put(indexNumber, newScreenTemplate(136, true, true, true, false));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		indexNumber = 161;
		screenIndex.put(indexNumber, newScreenTemplate(160, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		indexNumber = 162;
		screenIndex.put(indexNumber, newScreenTemplate(160, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		indexNumber = 163;
		screenIndex.put(indexNumber, newScreenTemplate(160, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		indexNumber = 164;
		screenIndex.put(indexNumber, newScreenTemplate(160, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		indexNumber = 165;
		screenIndex.put(indexNumber, newScreenTemplate(160, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		indexNumber = 166;
		screenIndex.put(indexNumber, newScreenTemplate(160, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		indexNumber = 167;
		screenIndex.put(indexNumber, newScreenTemplate(160, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		indexNumber = 168;
		screenIndex.put(indexNumber, newScreenTemplate(160, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		indexNumber = 169;
		screenIndex.put(indexNumber, newScreenTemplate(160, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// UPDATE SAT SCREEN CONT
		// LAST SCREEN = UPDATE SAT SCREEN
		indexNumber = 170;  // NOT REAL VALUE JUST FOR TEST
		newOptionsArea =
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦ PREV OPTIONS  ¦  ¦     SAT08     ¦  ¦     SAT09     ¦  ¦     SAT10     ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(136);
		associatedScreens.add(167);
		associatedScreens.add(168);
		associatedScreens.add(169);
		screenIndex.put(indexNumber, newScreenTemplate(136, true, true, true, false));
		associatedScreensIndex.put(indexNumber, associatedScreens);

		// AUTHORIZE SAT SCREEN
		// LAST SCREEN = UPDATE SELECTED SAT SCREEN (INDEX = 160)
		indexNumber = 171;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     SELECT AUTHORIZATION                                                       \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		newOptionsArea =
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦      YES      ¦  ¦      NO       ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(180);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(181);  // NOT REAL VALUE JUST FOR TEST
		screenIndex.put(indexNumber, newScreenTemplate(136, true, true, false, false));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// REQUEST EPHEMERIS SCREEN
		// LAST SCREEN = UPDATE SELECTED SAT SCREEN
		indexNumber = 172;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     VERIFY TO REQUEST NEW EPHEMERIS                                            \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		newOptionsArea =
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		screenIndex.put(indexNumber, newScreenTemplate(136, true, true, false, false));
		
		// VERIFIED NEW EPHEMERIS SCREEN
		// LAST SCREEN = REQUEST EPHEMERIS SCREEN (INDEX = 172)
		indexNumber = 174;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     SELECT OPTION                                                              \r\n" + 
				"                                                                                \r\n" + 
				"                          EPHEMERIS REQUEST INITIATED                           \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(29);
		associatedScreens.add(171);
		associatedScreens.add(172);
		associatedScreens.add(173);
		screenIndex.put(indexNumber, newScreenTemplate(160, true, true, false, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// NOT LOGGED ON REQUEST EPHEMERIS SCREEN
		// LAST SCREEN = REQUEST EPHEMERIS SCREEN (INDEX = 172)
		indexNumber = 175;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     SELECT OPTION                                                              \r\n" + 
				"                                                                                \r\n" + 
				"                                 NOT LOGGED ON                                  \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(29);
		associatedScreens.add(171);
		associatedScreens.add(172);
		associatedScreens.add(173);
		screenIndex.put(indexNumber, newScreenTemplate(160, true, true, false, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// SAT AUTHORIZED SCREEN
		// LAST SCREEN = AUTHORIZE SAT SCREEN (163)
		indexNumber = 180;
		screenIndex.put(indexNumber, newScreenTemplate(160, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// SAT NOT AUTHORIZED SCEEN
		// LAST SCREEN = AUTHORIZE SAT SCREEN (163)
		indexNumber = 181;
		screenIndex.put(indexNumber, newScreenTemplate(160, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// ACQ MODES 182 -183
		// LAST SCREEN = ACQ MODE SCREEN (INDEX = 150)
		for (int i = 182; i < 184; i++) {
			indexNumber = i;
			associatedScreens = new ArrayList<Integer>();
			associatedScreens.add(184);
			associatedScreens.add(146);
			associatedScreens.add(147);
			associatedScreens.add(148);
			associatedScreens.add(149);
			associatedScreens.add(150);
			associatedScreens.add(30);
			screenIndex.put(indexNumber, newScreenTemplate(145, true, true, true, true));
			associatedScreensIndex.put(indexNumber, associatedScreens);
		}
		
		
		// SAVE MANUAL POINTING DATA CHANGES SCREEN
		// LAST SCREEN = CHANGE PARAMETERS SCREEN (INDEX = 145)
		indexNumber = 184;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(4);
		associatedScreens.add(145);
		screenIndex.put(indexNumber, newScreenTemplate(30, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// INVALID POINTING DATA ENTRIES  185 - 188
		indexNumber = 185;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     ENTER AZIMUTH (DEG)                                                        \r\n" + 
				"                                                                                \r\n" + 
				"                                 INVALID ENTRY                                  \r\n";
		screenIndex.put(indexNumber, newScreenTemplate(156, true, true, false, true));
		
		indexNumber = 186;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     ENTER ELEVATION (DEG)                                                      \r\n" + 
				"                                                                                \r\n" + 
				"                                 INVALID ENTRY                                  \r\n";
		screenIndex.put(indexNumber, newScreenTemplate(157, true, true, false, true));
		
		indexNumber = 187;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     ENTER RANGE (KM)                                                           \r\n" + 
				"                                                                                \r\n" + 
				"                                 INVALID ENTRY                                  \r\n";
		screenIndex.put(indexNumber, newScreenTemplate(158, true, true, false, true));
		
		indexNumber = 188;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     ENTER RANGE RATE (M/S)                                                     \r\n" + 
				"                                                                                \r\n" + 
				"                                 INVALID ENTRY                                  \r\n";
		screenIndex.put(indexNumber, newScreenTemplate(159, true, true, false, true));
		
		// INVALID NAV DATA SCREENS 201 - 206
		indexNumber = 201;
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     ENTER LATITUDE (DEG MIN N/S)                                               \r\n" + 
				"                                                                                \r\n" + 
				"                                 INVALID ENTRY                                  \r\n";
		screenIndex.put(indexNumber, newScreenTemplate(71, true, true, false, true));
		
		indexNumber = 202;
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     ENTER LONGITUDE (DEG MIN E/W)                                              \r\n" + 
				"                                                                                \r\n" + 
				"                                 INVALID ENTRY                                  \r\n";
		screenIndex.put(indexNumber, newScreenTemplate(72, true, true, false, true));
		
		indexNumber = 203;
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     ENTER ALTITUDE (FT)                                                        \r\n" + 
				"                                                                                \r\n" + 
				"                                 INVALID ENTRY                                  \r\n";
		screenIndex.put(indexNumber, newScreenTemplate(73, true, true, false, true));
		
		indexNumber = 204;
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     ENTER HEADING (DEG)                                                        \r\n" + 
				"                                                                                \r\n" + 
				"                                 INVALID ENTRY                                  \r\n";
		screenIndex.put(indexNumber, newScreenTemplate(77, true, true, false, true));
		
		indexNumber = 205;
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     ENTER PITCH (DEG)                                                          \r\n" + 
				"                                                                                \r\n" + 
				"                                 INVALID ENTRY                                  \r\n";
		screenIndex.put(indexNumber, newScreenTemplate(78, true, true, false, true));
		
		indexNumber = 206;
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     ENTER ROLL (DEG)                                                           \r\n" + 
				"                                                                                \r\n" + 
				"                                 INVALID ENTRY                                  \r\n";
		screenIndex.put(indexNumber, newScreenTemplate(79, true, true, false, true));
		
		// INVALID ENTRY NET SCREEN
		// LAST SCREEN = ENTER NET NUMBER SCREEN (INDEX = 21)
		indexNumber = 299;
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     ENTER NET NUMBER                                                           \r\n" +  
				"                                                                                \r\n" + 
				"                                 INVALID ENTRY                                  \r\n";
		screenIndex.put(indexNumber, newScreenTemplate(21, true, true, false, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// ENTERED NET SCREEN
		// LAST SCREEN = ENTER NET NUMBER SCREEN (INDEX = 21)
		indexNumber = 300;
		newStatusArea = 
				"DTG:      09-220539z-aug19        ** SECRET **                                  \r\n" + 
				"                                                                                \r\n" + 
				"ALARM:                                                                     	 \r\n" + 
				"                                                                                \r\n" + 
				"ADVISORY: "+fetchLogEntry(1)+" \r\n" +
				"                                                                                \r\n";
		newMainArea = 
				"MAIN:EHF:NET--------------------------------------------------------------------\r\n" + 
				"                                             SETUP CAPABLE      NO              \r\n" + 
				"         NET NUMBER         "+nets.get(selectedNet).getNetNumber()+"               CONSTELLATION      MIL             \r\n" + 
				"         STATUS             "+String.format("%1$-8s", nets.get(selectedNet).getStatus())+"         TERMINAL ID        1234            \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                          ACTUAL           BEST               TERMINAL          \r\n" + 
				"         TX BEAM C3 MODE  -                -                    "+String.format("%1$-6s", nets.get(selectedNet).getTerminalParameters()[5])+"          \r\n" +
				"         RX BEAM C3 MODE  -                -                    "+String.format("%1$-6s", nets.get(selectedNet).getTerminalParameters()[5])+"          \r\n" + 
				"         DL BURST RATE    -                -                    HF01  001       \r\n" + 
				"                                                                                \r\n" + 
				"     TERMINAL PARAMETERS                                                        \r\n" + 
				"                                                                                \r\n" + 
				"         INTERRUPTIBLE    "+String.format("%1$-3s", nets.get(selectedNet).getTerminalParameters()[0])+"                                                   \r\n" + 
				"         TX PORT          "+String.format("%1$-3s", nets.get(selectedNet).getTerminalParameters()[1])+"                                                   \r\n" + 
				"         RX PORT          "+String.format("%1$-3s", nets.get(selectedNet).getTerminalParameters()[2])+"                                                   \r\n" +
				"                           -                                                    \r\n" + 
				"                           -                                                    \r\n" + 
				"                                                                                \r\n" + 
				"         TX BEAM          "+String.format("%1$-2s", nets.get(selectedNet).getTerminalParameters()[3])+"               UL SLOT            "+String.format("%1$-7s", nets.get(selectedNet).getTerminalParameters()[6])+"                 \r\n" + 
				"         RX BEAM          "+String.format("%1$-2s", nets.get(selectedNet).getTerminalParameters()[4])+"               AUTO JOIN          "+String.format("%1$-3s", nets.get(selectedNet).getTerminalParameters()[7])+"                \r\n" +
				"                                                                                \r\n" + 
				"     SERVICE PARAMETERS 1              SERVICE PARAMETERS 2                     \r\n" + 
				"                                                                                \r\n" + 
				"         NET NAME           "+String.format("%1$-14s", nets.get(selectedNet).getServiceParameters1()[0])+" BMT CONTROLLED     "+String.format("%1$-3s", nets.get(selectedNet).getServiceParameters2()[0])+"               \r\n" + 
				"         NET CONSTELLATION  "+String.format("%1$-14s", nets.get(selectedNet).getServiceParameters1()[1])+" DEFAULT AGILE NET  "+String.format("%1$-3s", nets.get(selectedNet).getServiceParameters2()[1])+"               \r\n" + 
				"         SERVICE ID         "+String.format("%1$-14s", nets.get(selectedNet).getServiceParameters1()[2])+" CINC               "+String.format("%1$-3s", nets.get(selectedNet).getServiceParameters2()[2])+"               \r\n" + 
				"         SERVICE TYPE       "+String.format("%1$-14s", nets.get(selectedNet).getServiceParameters1()[3])+" CRYPTO KEY         "+String.format("%1$-3s", nets.get(selectedNet).getServiceParameters2()[3])+"               \r\n" + 
				"         DATA RATE          "+String.format("%1$-14s", nets.get(selectedNet).getServiceParameters1()[4])+" PRECEDENCE         "+String.format("%1$-3s", nets.get(selectedNet).getServiceParameters2()[4])+"               \r\n" + 
				"         UL SUBCHANNEL      "+String.format("%1$-14s", nets.get(selectedNet).getServiceParameters1()[5])+" SUBFS              "+String.format("%1$-3s", nets.get(selectedNet).getServiceParameters2()[5])+"               \r\n" + 
				"         INTERLEAVE         "+String.format("%1$-14s", nets.get(selectedNet).getServiceParameters1()[6])+" BOMBER C3 CAPABLE  "+String.format("%1$-3s", nets.get(selectedNet).getServiceParameters2()[6])+"               \r\n";
        newOptionsArea =
	            "----------------------------------** SECRET **----------------------------------\r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" + 
	            "  ¦ EXIT DISPLAY  ¦  ¦   TERMINAL    ¦  ¦NET MANAGEMENT ¦  ¦NET PARAMETERS ¦    \r\n" + 
	            "  ¦               ¦  ¦ PARTICIPATION ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" +  
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(3);
		associatedScreens.add(301);
		associatedScreens.add(302);
		associatedScreens.add(303);
		screenIndex.put(indexNumber, newScreenTemplate(3, true, false, true, false));
        associatedScreensIndex.put(indexNumber, associatedScreens);
		
        // ENTERED NET NUMBER SCREEN
        // LAST SCREEN = NETS SCREEN (INDEX = 21)
        indexNumber = 301; // NOT REAL VALUE JUST FOR TEST
        newOptionsArea =
	            "----------------------------------** SECRET **----------------------------------\r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" + 
	            "  ¦     EXIT      ¦  ¦   JOIN NET    ¦  ¦   EXIT NET    ¦  ¦TX BEAM C3 MODE¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" +  
	            "  ¦RX BEAM C3 MODE¦  ¦ DL BURST RATE ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(300);
		associatedScreens.add(304);
		associatedScreens.add(305);
		associatedScreens.add(306);
		associatedScreens.add(307);
		associatedScreens.add(308);
		screenIndex.put(indexNumber, newScreenTemplate(300, true, true, true, false));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        // NET MANAGEMENT SCREEN
        // LAST SCREEN = ENTERED NET SCREEN (INDEX = 300)
        indexNumber = 302;
        newOptionsArea =
	            "----------------------------------** SECRET **----------------------------------\r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" + 
	            "  ¦     EXIT      ¦  ¦ SETUP CAPABLE ¦  ¦SEND KEEP ALIVE¦  ¦ INVITE MEMBER ¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" +  
	            "  ¦ REQUEST SETUP ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(300);
		associatedScreens.add(310);
		associatedScreens.add(311);
		associatedScreens.add(312);
		associatedScreens.add(313);
		screenIndex.put(indexNumber, newScreenTemplate(300, true, true, true, false));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        // NET PARAMETERS SCREEN
        // LAST SCREEN = ENTERED NET SCREEN (INDEX = 300)
        indexNumber = 303;
        newOptionsArea =
	            "----------------------------------** SECRET **----------------------------------\r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" + 
	            "  ¦ SAVE CHANGES  ¦  ¦     PORTS     ¦  ¦TERMINAL PARAMS¦  ¦ SERV PARAMS 1 ¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" +  
	            "  ¦ SERV PARAMS 2 ¦  ¦ BEAM DEFAULTS ¦  ¦ ABORT CHANGES ¦  ¦               ¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(314);
		associatedScreens.add(315);
		associatedScreens.add(316);
		associatedScreens.add(317);
		associatedScreens.add(318);
		associatedScreens.add(319);
		associatedScreens.add(320);
		screenIndex.put(indexNumber, newScreenTemplate(300, true, true, true, false));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        // JOIN NET SCREEN
        // LAST SCREEN = ENERED NET NUMBER SCREEN (INDEX = 301)
        indexNumber = 304;
        newPromptArea = 
                "--------------------------------------------------------------------------------\r\n" + 
                "     VERIFY TO JOIN NET                                                         \r\n" + 
                "                                                                                \r\n" + 
                "                                                                                \r\n";
        newOptionsArea =
	            "----------------------------------** SECRET **----------------------------------\r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" +  
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
        screenIndex.put(indexNumber, newScreenTemplate(300, true, true, false, false));
        
        // EXIT NET SCREEN
        // LAST SCREEN = ENERED NET NUMBER SCREEN (INDEX = 301)
        indexNumber = 305;
        newPromptArea = 
                "--------------------------------------------------------------------------------\r\n" + 
                "     VERIFY TO EXIT NET                                                         \r\n" + 
                "                                                                                \r\n" + 
                "                                                                                \r\n";
        screenIndex.put(indexNumber, newScreenTemplate(304, true, true, false, true));
        
        // TX BEAM C3 MODE SCREEN
        // LAST SCREEN = ENTERED NET NUMBER SCREEN (INDEX = 301)
        
        
        // NOT LOGGED ON JOIN NET SCREEN
        // LAST SCREEN = ENTERED NET NUMBER SCREEN (INDEX = 301)
        indexNumber = 309;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     SELECT OPTION                                                              \r\n" + 
				"                                                                                \r\n" + 
				"                                 NOT LOGGED ON                                  \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(300);
		associatedScreens.add(304);
		associatedScreens.add(305);
		associatedScreens.add(306);
		associatedScreens.add(307);
		associatedScreens.add(308);
		screenIndex.put(indexNumber, newScreenTemplate(301, true, true, false, true));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        // 310 onwards for nets (delete later)
        // SAVE NET PARAMETERS SCREEN
        // LAST SCREEN = NET PARAMETERS SCREEN (INDEX = 303)
        indexNumber = 314;
        newPromptArea = 
                "--------------------------------------------------------------------------------\r\n" + 
                "     VERIFY TO SAVE CHANGES                                                     \r\n" + 
                "                                                                                \r\n" + 
                "                                                                                \r\n";
        newOptionsArea =
	            "----------------------------------** SECRET **----------------------------------\r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" +  
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
        screenIndex.put(indexNumber, newScreenTemplate(303, true, true, false, false));
        
        // NET PORTS SCREEN
        // LAST SCREEN = NET PARAMETERS SCREEN (INDEX = 303)
        indexNumber = 315;
        newOptionsArea =
	            "----------------------------------** SECRET **----------------------------------\r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" + 
	            "  ¦     EXIT      ¦  ¦ INTERRUPTIBLE ¦  ¦    TX PORT    ¦  ¦               ¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" +  
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(321);
		associatedScreens.add(322);
		screenIndex.put(indexNumber, newScreenTemplate(303, true, true, true, false));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        // TERMINAL PARAMETERS SCREEN
        // LAST SCREEN = NET PARAMETERS SCREEN (INDEX = 303)
        indexNumber = 316;
        newPromptArea = 
                "--------------------------------------------------------------------------------\r\n" + 
                "     SELECT TERMINAL PARAMETER                                                  \r\n" + 
                "                                                                                \r\n" + 
                "                                                                                \r\n";
        newOptionsArea =
	            "----------------------------------** SECRET **----------------------------------\r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" + 
	            "  ¦     EXIT      ¦  ¦    TX BEAM    ¦  ¦TX BEAM C3 MODE¦  ¦    UL SLOT    ¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" +  
	            "  ¦    RX BEAM    ¦  ¦   AUTO JOIN   ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(325);
		associatedScreens.add(326);
		associatedScreens.add(327);
		associatedScreens.add(328);
		associatedScreens.add(329);
		screenIndex.put(indexNumber, newScreenTemplate(303, true, true, false, false));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        // SERV PARAMS 1 SCREEN
        // LAST SCREEN = NET PARAMETERS SCREEN (INDEX = 303)
        indexNumber = 317;
        newPromptArea = 
                "--------------------------------------------------------------------------------\r\n" + 
                "     SELECT SERVICE PARAMETER                                                   \r\n" + 
                "                                                                                \r\n" + 
                "                                                                                \r\n";
        newOptionsArea =
	            "----------------------------------** SECRET **----------------------------------\r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" + 
	            "  ¦     EXIT      ¦  ¦     NAME      ¦  ¦      NET      ¦  ¦  SERVICE ID   ¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦ CONSTELLATION ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" +  
	            "  ¦ SERVICE TYPE  ¦  ¦   DATA RATE   ¦  ¦ UL SUBCHANNEL ¦  ¦  INTERLEAVE   ¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(355);
		associatedScreens.add(356);
		associatedScreens.add(357);
		associatedScreens.add(358);
		associatedScreens.add(359);
		associatedScreens.add(360);
		associatedScreens.add(361);
		screenIndex.put(indexNumber, newScreenTemplate(303, true, true, false, false));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        // SERV PARAMS 2 SCREEN
        // LAST SCREEN = NET PARAMETERS SCREEN (INDEX = 303)
        indexNumber = 318;
        newOptionsArea =
	            "----------------------------------** SECRET **----------------------------------\r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" + 
	            "  ¦     EXIT      ¦  ¦BMT CONTROLLED ¦  ¦     CINC      ¦  ¦  CRYPTO KEY   ¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" +  
	            "  ¦  PRECEDENCE   ¦  ¦     SUBFS     ¦  ¦   BOMBER C3   ¦  ¦               ¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦    CAPABLE    ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(396);
		associatedScreens.add(397);
		associatedScreens.add(398);
		associatedScreens.add(399);
		associatedScreens.add(400);
		associatedScreens.add(401);
		screenIndex.put(indexNumber, newScreenTemplate(317, true, true, true, false));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        // INTERRUPTIBLE NET PARAM SCREEN
        // LAST SCREEN = NET PORTS SCREEN (INDEX = 315)
        indexNumber = 321;
        newOptionsArea =
	            "----------------------------------** SECRET **----------------------------------\r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" + 
	            "  ¦      YES      ¦  ¦      NO       ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" +  
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(323);
		associatedScreens.add(324);
		screenIndex.put(indexNumber, newScreenTemplate(315, true, true, true, false));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        // TX PORT NET PARAM SCREEN
        // LAST SCREEN = NET PORTS SCREEN (INDEX = 315)
        indexNumber = 322;
        newPromptArea = 
                "--------------------------------------------------------------------------------\r\n" + 
                "     SELECT TX PORT                                                             \r\n" + 
                "                                                                                \r\n" + 
                "                                                                                \r\n";
        newOptionsArea =
	            "----------------------------------** SECRET **----------------------------------\r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" + 
	            "  ¦      R01      ¦  ¦      R02      ¦  ¦      R03      ¦  ¦      R04      ¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" +  
	            "  ¦      R06      ¦  ¦      R07      ¦  ¦      R10      ¦  ¦      R11      ¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(451);  // NOT REAL VALUE CHANGE LATER FOR REAL!!!
		associatedScreens.add(452);  // NOT REAL VALUE CHANGE LATER FOR REAL!!!
		associatedScreens.add(453);  // NOT REAL VALUE CHANGE LATER FOR REAL!!!
		associatedScreens.add(454);  // NOT REAL VALUE CHANGE LATER FOR REAL!!!
		associatedScreens.add(455);  // NOT REAL VALUE CHANGE LATER FOR REAL!!!
		associatedScreens.add(456);  // NOT REAL VALUE CHANGE LATER FOR REAL!!!
		associatedScreens.add(457);  // NOT REAL VALUE CHANGE LATER FOR REAL!!!
		associatedScreens.add(458);  // NOT REAL VALUE CHANGE LATER FOR REAL!!!
		screenIndex.put(indexNumber, newScreenTemplate(315, true, true, false, false));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        
        
        // INTERRUPTIBLE YES NET PARAM SCREEN
        // LAST SCREEN = INTERRUPTIBLE NET PARAM SCREEN (INDEX = 321)
        indexNumber = 323;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(321);
		associatedScreens.add(322);
		screenIndex.put(indexNumber, newScreenTemplate(315, true, true, true, true));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        // INTERRUPTIBLE NO NET PARAM SCREEN 
        // LAST SCREEN = INTERRUPTIBLE NET PARAM SCREEN (INDEX = 321)
        indexNumber = 324;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(321);
		associatedScreens.add(322);
		screenIndex.put(indexNumber, newScreenTemplate(315, true, true, true, true));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        // TX BEAM TERMINAL PARAMETER SCREEN
        // LAST SCREEN = TERMINAL PARAMETERS SCREEN (INDEX = 316)
        indexNumber = 325;
        newPromptArea = 
                "--------------------------------------------------------------------------------\r\n" + 
                "     SELECT TX BEAM                                                             \r\n" + 
                "                                                                                \r\n" + 
                "                                                                                \r\n";
        newOptionsArea =
	            "----------------------------------** SECRET **----------------------------------\r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" + 
	            "  ¦     NONE      ¦  ¦      AG       ¦  ¦      EC       ¦  ¦      SA       ¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" +  
	            "  ¦      SB       ¦  ¦      SC       ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(330);
		associatedScreens.add(331);
		associatedScreens.add(332);
		associatedScreens.add(333);
		associatedScreens.add(334);
		associatedScreens.add(335);
		screenIndex.put(indexNumber, newScreenTemplate(316, true, true, false, false));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        // TX BEAM C3 MODE SCREEN
        // LAST SCREEN = TERMINAL PARAMETERS SCREEN (INDEX = 316)
        indexNumber = 326;
        newPromptArea = 
                "--------------------------------------------------------------------------------\r\n" + 
                "     SELECT TX BEAM C3 MODE                                                     \r\n" + 
                "                                                                                \r\n" + 
                "                                                                                \r\n";
        newOptionsArea =
	            "----------------------------------** SECRET **----------------------------------\r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" + 
	            "  ¦     MOST      ¦  ¦    MEDIUM     ¦  ¦     LEAST     ¦  ¦     AUTO      ¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" +  
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(336);
		associatedScreens.add(337);
		associatedScreens.add(338);
		associatedScreens.add(339);
		screenIndex.put(indexNumber, newScreenTemplate(316, true, true, false, false));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        // UL SLOT SCREEN
        // LAST SCREEN = TERMINAL PARAMETERS SCREEN (INDEX = 316)
        indexNumber = 327;
        newPromptArea = 
                "--------------------------------------------------------------------------------\r\n" + 
                "     SELECT UL SLOT                                                             \r\n" + 
                "                                                                                \r\n" + 
                "                                                                                \r\n";
        newOptionsArea =
	            "----------------------------------** SECRET **----------------------------------\r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" + 
	            "  ¦    SLOT 1     ¦  ¦    SLOT 2     ¦  ¦    SLOT 3     ¦  ¦    SLOT 4     ¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" +  
	            "  ¦   SLOTS 1-2   ¦  ¦   SLOTS 3-4   ¦  ¦ SLOTS 1-2-3-4 ¦  ¦               ¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(340);
		associatedScreens.add(341);
		associatedScreens.add(342);
		associatedScreens.add(343);
		associatedScreens.add(344);
		associatedScreens.add(345);
		associatedScreens.add(346);
		screenIndex.put(indexNumber, newScreenTemplate(316, true, true, false, false));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        // RX BEAM TERMINAL PARAMETER SCREEN
        // LAST SCREEN = TERMINAL PARAMETERS SCREEN (INDEX = 316)
        indexNumber = 328;
        newPromptArea = 
                "--------------------------------------------------------------------------------\r\n" + 
                "     SELECT RX BEAM                                                             \r\n" + 
                "                                                                                \r\n" + 
                "                                                                                \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(347);
		associatedScreens.add(348);
		associatedScreens.add(349);
		associatedScreens.add(350);
		associatedScreens.add(351);
		associatedScreens.add(352);
		screenIndex.put(indexNumber, newScreenTemplate(325, true, true, false, true));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        // AUTO JOIN NET PARAM SCREEN
        // LAST SCREEN = TERMINAL PARAMETERS SCREEN (INDEX = 316)
        indexNumber = 329;
        newPromptArea = 
                "--------------------------------------------------------------------------------\r\n" + 
                "     SELECT AUTO JOIN                                                           \r\n" + 
                "                                                                                \r\n" + 
                "                                                                                \r\n";
        newOptionsArea =
	            "----------------------------------** SECRET **----------------------------------\r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" + 
	            "  ¦      YES      ¦  ¦      NO       ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" +  
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(353);
		associatedScreens.add(354);
		screenIndex.put(indexNumber, newScreenTemplate(316, true, true, false, false));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        // SELECTED TX BEAM SCREENS
        // LAST SCREEN = TX BEAM TERMINAL PARAMETER SCREEN (INDEX = 325)
        indexNumber = 330;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(325);
		associatedScreens.add(326);
		associatedScreens.add(327);
		associatedScreens.add(328);
		associatedScreens.add(329);
		screenIndex.put(indexNumber, newScreenTemplate(316, true, true, true, true));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        indexNumber = 331;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(325);
		associatedScreens.add(326);
		associatedScreens.add(327);
		associatedScreens.add(328);
		associatedScreens.add(329);
		screenIndex.put(indexNumber, newScreenTemplate(316, true, true, true, true));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        indexNumber = 332;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(325);
		associatedScreens.add(326);
		associatedScreens.add(327);
		associatedScreens.add(328);
		associatedScreens.add(329);
		screenIndex.put(indexNumber, newScreenTemplate(316, true, true, true, true));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        indexNumber = 333;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(325);
		associatedScreens.add(326);
		associatedScreens.add(327);
		associatedScreens.add(328);
		associatedScreens.add(329);
		screenIndex.put(indexNumber, newScreenTemplate(316, true, true, true, true));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        indexNumber = 334;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(325);
		associatedScreens.add(326);
		associatedScreens.add(327);
		associatedScreens.add(328);
		associatedScreens.add(329);
		screenIndex.put(indexNumber, newScreenTemplate(316, true, true, true, true));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        indexNumber = 335;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(325);
		associatedScreens.add(326);
		associatedScreens.add(327);
		associatedScreens.add(328);
		associatedScreens.add(329);
		screenIndex.put(indexNumber, newScreenTemplate(316, true, true, true, true));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        // TX BEAM C3 MODE
        indexNumber = 336;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(325);
		associatedScreens.add(326);
		associatedScreens.add(327);
		associatedScreens.add(328);
		associatedScreens.add(329);
		screenIndex.put(indexNumber, newScreenTemplate(316, true, true, true, true));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        indexNumber = 337;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(325);
		associatedScreens.add(326);
		associatedScreens.add(327);
		associatedScreens.add(328);
		associatedScreens.add(329);
		screenIndex.put(indexNumber, newScreenTemplate(316, true, true, true, true));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        indexNumber = 338;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(325);
		associatedScreens.add(326);
		associatedScreens.add(327);
		associatedScreens.add(328);
		associatedScreens.add(329);
		screenIndex.put(indexNumber, newScreenTemplate(316, true, true, true, true));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        indexNumber = 339;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(325);
		associatedScreens.add(326);
		associatedScreens.add(327);
		associatedScreens.add(328);
		associatedScreens.add(329);
		screenIndex.put(indexNumber, newScreenTemplate(316, true, true, true, true));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        // 340 - 346 UL SLOT OPTIONS
        
        indexNumber = 340;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(325);
		associatedScreens.add(326);
		associatedScreens.add(327);
		associatedScreens.add(328);
		associatedScreens.add(329);
		screenIndex.put(indexNumber, newScreenTemplate(316, true, true, true, true));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        indexNumber = 341;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(325);
		associatedScreens.add(326);
		associatedScreens.add(327);
		associatedScreens.add(328);
		associatedScreens.add(329);
		screenIndex.put(indexNumber, newScreenTemplate(316, true, true, true, true));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        indexNumber = 342;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(325);
		associatedScreens.add(326);
		associatedScreens.add(327);
		associatedScreens.add(328);
		associatedScreens.add(329);
		screenIndex.put(indexNumber, newScreenTemplate(316, true, true, true, true));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        indexNumber = 343;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(325);
		associatedScreens.add(326);
		associatedScreens.add(327);
		associatedScreens.add(328);
		associatedScreens.add(329);
		screenIndex.put(indexNumber, newScreenTemplate(316, true, true, true, true));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        indexNumber = 344;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(325);
		associatedScreens.add(326);
		associatedScreens.add(327);
		associatedScreens.add(328);
		associatedScreens.add(329);
		screenIndex.put(indexNumber, newScreenTemplate(316, true, true, true, true));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        indexNumber = 345;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(325);
		associatedScreens.add(326);
		associatedScreens.add(327);
		associatedScreens.add(328);
		associatedScreens.add(329);
		screenIndex.put(indexNumber, newScreenTemplate(316, true, true, true, true));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        indexNumber = 346;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(325);
		associatedScreens.add(326);
		associatedScreens.add(327);
		associatedScreens.add(328);
		associatedScreens.add(329);
		screenIndex.put(indexNumber, newScreenTemplate(316, true, true, true, true));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        // RX BEAMS
        indexNumber = 347;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(325);
		associatedScreens.add(326);
		associatedScreens.add(327);
		associatedScreens.add(328);
		associatedScreens.add(329);
		screenIndex.put(indexNumber, newScreenTemplate(316, true, true, true, true));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        indexNumber = 348;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(325);
		associatedScreens.add(326);
		associatedScreens.add(327);
		associatedScreens.add(328);
		associatedScreens.add(329);
		screenIndex.put(indexNumber, newScreenTemplate(316, true, true, true, true));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        indexNumber = 349;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(325);
		associatedScreens.add(326);
		associatedScreens.add(327);
		associatedScreens.add(328);
		associatedScreens.add(329);
		screenIndex.put(indexNumber, newScreenTemplate(316, true, true, true, true));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        indexNumber = 350;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(325);
		associatedScreens.add(326);
		associatedScreens.add(327);
		associatedScreens.add(328);
		associatedScreens.add(329);
		screenIndex.put(indexNumber, newScreenTemplate(316, true, true, true, true));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        indexNumber = 351;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(325);
		associatedScreens.add(326);
		associatedScreens.add(327);
		associatedScreens.add(328);
		associatedScreens.add(329);
		screenIndex.put(indexNumber, newScreenTemplate(316, true, true, true, true));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        indexNumber = 352;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(325);
		associatedScreens.add(326);
		associatedScreens.add(327);
		associatedScreens.add(328);
		associatedScreens.add(329);
		screenIndex.put(indexNumber, newScreenTemplate(316, true, true, true, true));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        // NET AUTO JOIN 353 AND 354
        indexNumber = 353;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(325);
		associatedScreens.add(326);
		associatedScreens.add(327);
		associatedScreens.add(328);
		associatedScreens.add(329);
		screenIndex.put(indexNumber, newScreenTemplate(316, true, true, true, true));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        indexNumber = 354;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(325);
		associatedScreens.add(326);
		associatedScreens.add(327);
		associatedScreens.add(328);
		associatedScreens.add(329);
		screenIndex.put(indexNumber, newScreenTemplate(316, true, true, true, true));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        // NAME SERVICE PARAMETER SCREEN
        // LAST SCREEN = SERV PARAMS 1 SCREEN (INDEX = 317)
        indexNumber = 355;
        newPromptArea = 
                "--------------------------------------------------------------------------------\r\n" + 
                "     ENTER NET NAME                                                             \r\n" + 
                "                                                                                \r\n" + 
                "                                                                                \r\n";
		screenIndex.put(indexNumber, newScreenTemplate(314, true, true, false, true));

        // NET CONSTELLATION SERVICE PARAMETER SCREEN
		// LAST SCREEN = SERV PARAMS 1 SCREEN (INDEX = 317)
		indexNumber = 356;
        newPromptArea = 
                "--------------------------------------------------------------------------------\r\n" + 
                "     SELECT NET CONSTELLATION                                                   \r\n" + 
                "                                                                                \r\n" + 
                "                                                                                \r\n";
        newOptionsArea =
	            "----------------------------------** SECRET **----------------------------------\r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" + 
	            "  ¦      MIL      ¦  ¦      UFO      ¦  ¦      FEP      ¦  ¦      SDL      ¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" +  
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(362);
		associatedScreens.add(363);
		associatedScreens.add(364);
		associatedScreens.add(365);
		screenIndex.put(indexNumber, newScreenTemplate(317, true, true, false, false));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        // SERVICE ID SERVICE PARAMETER SCREEN
        // LAST SCREEN = SERV PARAMS 1 SCREEN (INDEX = 317)
        indexNumber = 357;
        newPromptArea = 
                "--------------------------------------------------------------------------------\r\n" + 
                "     ENTER SERVICE ID                                                           \r\n" + 
                "                                                                                \r\n" + 
                "                                                                                \r\n";
        screenIndex.put(indexNumber, newScreenTemplate(314, true, true, false, true));
        
        // SERVICE TYPE SERVICE PARAMETER SCREEN
        // LAST SCREEN = SERV PARAMS 1 SCREEN (INDEX = 317)
        indexNumber = 358;
        newPromptArea = 
                "--------------------------------------------------------------------------------\r\n" + 
                "     SELECT SERVICE TYPE                                                        \r\n" + 
                "                                                                                \r\n" + 
                "                                                                                \r\n";
        newOptionsArea =
	            "----------------------------------** SECRET **----------------------------------\r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" + 
	            "  ¦  KIV-7M/TEXT  ¦  ¦  KIV-7M/EAM   ¦  ¦ KIV-7M/BAUDOT ¦  ¦  KIV-7M/DATA  ¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" +  
	            "  ¦  KGV-11/DATA  ¦  ¦   RETARGET    ¦  ¦ KGV-11/DATAX4 ¦  ¦ MORE OPTIONS  ¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(366);
		associatedScreens.add(367);
		associatedScreens.add(368);
		associatedScreens.add(369);
		associatedScreens.add(370);
		associatedScreens.add(371);
		associatedScreens.add(372);
		associatedScreens.add(381);
		screenIndex.put(indexNumber, newScreenTemplate(317, true, true, false, false));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        // DATA RATE SERVICE PARAMETER SCREEN
        // LAST SCREEN = SERV PARAMS 1 SCREEN (INDEX = 317)
        indexNumber = 359;
        newPromptArea = 
                "--------------------------------------------------------------------------------\r\n" + 
                "     SELECT DATA RATE                                                           \r\n" + 
                "                                                                                \r\n" + 
                "                                                                                \r\n";
        newOptionsArea =
	            "----------------------------------** SECRET **----------------------------------\r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" + 
	            "  ¦      75       ¦  ¦      150      ¦  ¦      300      ¦  ¦      600      ¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" +  
	            "  ¦     1200      ¦  ¦     2400      ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(383);
		associatedScreens.add(384);
		associatedScreens.add(385);
		associatedScreens.add(386);
		associatedScreens.add(387);
		associatedScreens.add(388);
		screenIndex.put(indexNumber, newScreenTemplate(317, true, true, false, false));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        // UL SUBCHANNEL SERVICE PARAMETER SCREEN
        // LAST SCREEN = SERV PARAMS 1 SCREEN (INDEX = 317)
        indexNumber = 360;
        newPromptArea = 
                "--------------------------------------------------------------------------------\r\n" + 
                "     SELECT UL SUBCHANNEL                                                       \r\n" + 
                "                                                                                \r\n" + 
                "                                                                                \r\n";
        newOptionsArea =
	            "----------------------------------** SECRET **----------------------------------\r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" + 
	            "  ¦    PRIMARY    ¦  ¦   SECONDARY   ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" +  
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(389);
		associatedScreens.add(390);
		screenIndex.put(indexNumber, newScreenTemplate(317, true, true, false, false));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        // INTERLEAVE SERVICE PARAMETER SCREEN
        // LAST SCREEN = SERV PARAMS 1 SCREEN (INDEX = 317)
        indexNumber = 361;
        newPromptArea = 
                "--------------------------------------------------------------------------------\r\n" + 
                "     SELECT TX PORT                                                             \r\n" + 
                "                                                                                \r\n" + 
                "                                                                                \r\n";
        newOptionsArea =
	            "----------------------------------** SECRET **----------------------------------\r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" + 
	            "  ¦  SHORT CONV   ¦  ¦  MEDIUM CONV  ¦  ¦   LONG CONV   ¦  ¦  SHORT BLOCK  ¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" +  
	            "  ¦  LONG BLOCK   ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(391);
		associatedScreens.add(392);
		associatedScreens.add(393);
		associatedScreens.add(394);
		associatedScreens.add(395);
		screenIndex.put(indexNumber, newScreenTemplate(317, true, true, false, false));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        // NET CONSTELLATIONS 362-365
        for (int i = 362; i < 366; i++) {
        	indexNumber = i;
    		associatedScreens = new ArrayList<Integer>();
    		associatedScreens.add(303);
    		associatedScreens.add(355);
    		associatedScreens.add(356);
    		associatedScreens.add(357);
    		associatedScreens.add(358);
    		associatedScreens.add(359);
    		associatedScreens.add(360);
    		associatedScreens.add(361);
    		screenIndex.put(indexNumber, newScreenTemplate(317, true, true, true, true));
            associatedScreensIndex.put(indexNumber, associatedScreens);
        }
        
        // SERVICE TYPES 366-380
        for (int i = 366; i < 381; i++) {
        	indexNumber = i;
    		associatedScreens = new ArrayList<Integer>();
    		associatedScreens.add(303);
    		associatedScreens.add(355);
    		associatedScreens.add(356);
    		associatedScreens.add(357);
    		associatedScreens.add(358);
    		associatedScreens.add(359);
    		associatedScreens.add(360);
    		associatedScreens.add(361);
    		screenIndex.put(indexNumber, newScreenTemplate(317, true, true, true, true));
            associatedScreensIndex.put(indexNumber, associatedScreens);
        }
        
        // SERVICE TYPE SERVICE PARAMETER SCREEN CONT
        // LAST SCREEN = SERVICE TYPE SERVICE PARAMETER SCREEN (INDEX = 358)
        indexNumber = 381;
        newOptionsArea =
	            "----------------------------------** SECRET **----------------------------------\r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" + 
	            "  ¦ PREV OPTIONS  ¦  ¦  KGV-11/TEXT  ¦  ¦  KGV-11/EAM   ¦  ¦  KGV-11/FDM   ¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" +  
	            "  ¦ AF REPORTBACK ¦  ¦ SERVICE VOICE ¦  ¦  BLACK/DATA   ¦  ¦ MORE OPTIONS  ¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(358);
		associatedScreens.add(373);
		associatedScreens.add(374);
		associatedScreens.add(375);
		associatedScreens.add(376);
		associatedScreens.add(377);
		associatedScreens.add(378);
		associatedScreens.add(382);
		screenIndex.put(indexNumber, newScreenTemplate(358, true, true, true, false));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        // SERVICE TYPE SERVICE PARAMETER SCREEN CONT CONT
        // LAST SCREEN = SERVICE TYPE SERVICE PARAMETER SCREEN CONT (INDEX = 381)
        indexNumber = 382;
        newOptionsArea =
	            "----------------------------------** SECRET **----------------------------------\r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" + 
	            "  ¦ PREV OPTIONS  ¦  ¦SUB REPORTBACK ¦  ¦   KIV-7M/AB   ¦  ¦               ¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" +  
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(381);
		associatedScreens.add(379);
		associatedScreens.add(380);
		screenIndex.put(indexNumber, newScreenTemplate(358, true, true, true, false));
        associatedScreensIndex.put(indexNumber, associatedScreens);
		
        // DATA RATES 383 - 388 (CAN COMBINE THE ONES BELOW!!!!!!)
        for (int i = 383; i < 389; i++) {
        	indexNumber = i;
    		associatedScreens = new ArrayList<Integer>();
    		associatedScreens.add(303);
    		associatedScreens.add(355);
    		associatedScreens.add(356);
    		associatedScreens.add(357);
    		associatedScreens.add(358);
    		associatedScreens.add(359);
    		associatedScreens.add(360);
    		associatedScreens.add(361);
    		screenIndex.put(indexNumber, newScreenTemplate(317, true, true, true, true));
            associatedScreensIndex.put(indexNumber, associatedScreens);
        }
        
        // UL SUBCHANNELS 389-390
        for (int i = 389; i < 391; i++) {
        	indexNumber = i;
    		associatedScreens = new ArrayList<Integer>();
    		associatedScreens.add(303);
    		associatedScreens.add(355);
    		associatedScreens.add(356);
    		associatedScreens.add(357);
    		associatedScreens.add(358);
    		associatedScreens.add(359);
    		associatedScreens.add(360);
    		associatedScreens.add(361);
    		screenIndex.put(indexNumber, newScreenTemplate(317, true, true, true, true));
            associatedScreensIndex.put(indexNumber, associatedScreens);
        }
        
        // INTERLEAVES 391-395
        for (int i = 391; i < 396; i++) {
        	indexNumber = i;
    		associatedScreens = new ArrayList<Integer>();
    		associatedScreens.add(303);
    		associatedScreens.add(355);
    		associatedScreens.add(356);
    		associatedScreens.add(357);
    		associatedScreens.add(358);
    		associatedScreens.add(359);
    		associatedScreens.add(360);
    		associatedScreens.add(361);
    		screenIndex.put(indexNumber, newScreenTemplate(317, true, true, true, true));
            associatedScreensIndex.put(indexNumber, associatedScreens);
        }
        
        // BMT CONTROLLED SERVICE PARAMETER SCREEN
        // LAST SCREEN = SERV PARAMS 2 SCREEN (INDEX = 318)
        indexNumber = 396;
        newPromptArea = 
                "--------------------------------------------------------------------------------\r\n" + 
                "     SELECT WHETHER BMT CONTROLLED                                              \r\n" + 
                "                                                                                \r\n" + 
                "                                                                                \r\n";
        newOptionsArea =
	            "----------------------------------** SECRET **----------------------------------\r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" + 
	            "  ¦      YES      ¦  ¦      NO       ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" +  
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(403);
		associatedScreens.add(404);
		screenIndex.put(indexNumber, newScreenTemplate(318, true, true, false, false));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        // CINC SERVICE PARAMETER SCREEN
        // LAST SCREEN = SERV PARAMS 2 SCREEN (INDEX = 318)
        indexNumber = 397;
        newPromptArea = 
                "--------------------------------------------------------------------------------\r\n" + 
                "     SELECT CINC                                                                \r\n" + 
                "                                                                                \r\n" + 
                "                                                                                \r\n";
        newOptionsArea =
	            "----------------------------------** SECRET **----------------------------------\r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" + 
	            "  ¦    CINC NO    ¦  ¦     0.48      ¦  ¦     0.96      ¦  ¦     1.92      ¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" +  
	            "  ¦     2.40      ¦  ¦     3.84      ¦  ¦     4.80      ¦  ¦ MORE OPTIONS  ¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(405);
		associatedScreens.add(406);
		associatedScreens.add(407);
		associatedScreens.add(408);
		associatedScreens.add(409);
		associatedScreens.add(410);
		associatedScreens.add(411);
		associatedScreens.add(402);
		screenIndex.put(indexNumber, newScreenTemplate(318, true, true, false, false));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        // CRYPTO KEY SERVICE PARAMETER SCREEN
        // LAST SCREEN = SERV PARAMS 2 SCREEN (INDEX = 318)
        indexNumber = 398;
        newPromptArea = 
                "--------------------------------------------------------------------------------\r\n" + 
                "     ENTER CRYPTO KEY                                                           \r\n" + 
                "                                                                                \r\n" + 
                "                                                                                \r\n";
        newOptionsArea =
	            "----------------------------------** SECRET **----------------------------------\r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" +  
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
        screenIndex.put(indexNumber, newScreenTemplate(318, true, true, false, false));
        
        // PRECEDENCE SERVICE PARAMETER SCREEN
        // LAST SCREEN = SERV PARAMS 2 SCREEN (INDEX = 318)
        indexNumber = 399;
        newPromptArea = 
                "--------------------------------------------------------------------------------\r\n" + 
                "     SELECT PRECEDENCE                                                          \r\n" + 
                "                                                                                \r\n" + 
                "                                                                                \r\n";
        newOptionsArea =
	            "----------------------------------** SECRET **----------------------------------\r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" + 
	            "  ¦       0       ¦  ¦       1       ¦  ¦       2       ¦  ¦       3       ¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" +  
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(415);
		associatedScreens.add(416);
		associatedScreens.add(417);
		associatedScreens.add(418);
		screenIndex.put(indexNumber, newScreenTemplate(318, true, true, false, false));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        // SUBFS SERVICE PARAMETER SCREEN
        // LAST SCREEN = SERV PARAMS 2 SCREEN (INDEX = 318)
        indexNumber = 400;
        newPromptArea = 
                "--------------------------------------------------------------------------------\r\n" + 
                "     SELECT WHETHER SUBFS                                                       \r\n" + 
                "                                                                                \r\n" + 
                "                                                                                \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(419);
		associatedScreens.add(420);
		screenIndex.put(indexNumber, newScreenTemplate(396, true, true, false, true));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        // BOMBER C3 CAPABLE SERVICE PARAMETER SCREEN
        // LAST SCREEN = SERV PARAMS 2 SCREEN (INDEX = 318)
        indexNumber = 401;
        newPromptArea = 
                "--------------------------------------------------------------------------------\r\n" + 
                "     SELECT WHETHER BOMBER C3 CAPABLE                                           \r\n" + 
                "                                                                                \r\n" + 
                "                                                                                \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(421);
		associatedScreens.add(422);
		screenIndex.put(indexNumber, newScreenTemplate(400, true, true, false, true));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        // CINC SERVICE PARAMETER SCREEN CONT
        // LAST SCREEN = CINC SERVICE PARAMETER SCREEN (INDEX = 397)
        indexNumber = 402;
        newOptionsArea =
	            "----------------------------------** SECRET **----------------------------------\r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" + 
	            "  ¦ PREV OPTIONS  ¦  ¦     7.68      ¦  ¦     9.60      ¦  ¦     19.20     ¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" +  
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(397);
		associatedScreens.add(412);
		associatedScreens.add(413);
		associatedScreens.add(414);
		screenIndex.put(indexNumber, newScreenTemplate(397, true, true, true, false));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        // BMT CONTROLLED? 403-404
        for (int i = 403; i < 405; i++) {
        	indexNumber = i;
    		associatedScreens = new ArrayList<Integer>();
    		associatedScreens.add(303);
    		associatedScreens.add(396);
    		associatedScreens.add(397);
    		associatedScreens.add(398);
    		associatedScreens.add(399);
    		associatedScreens.add(400);
    		associatedScreens.add(401);
    		screenIndex.put(indexNumber, newScreenTemplate(318, true, true, true, true));
            associatedScreensIndex.put(indexNumber, associatedScreens);
        }
        
        // CINC 405 - 414
        for (int i = 405; i < 415; i++) {
        	indexNumber = i;
    		associatedScreens = new ArrayList<Integer>();
    		associatedScreens.add(303);
    		associatedScreens.add(396);
    		associatedScreens.add(397);
    		associatedScreens.add(398);
    		associatedScreens.add(399);
    		associatedScreens.add(400);
    		associatedScreens.add(401);
    		screenIndex.put(indexNumber, newScreenTemplate(318, true, true, true, true));
            associatedScreensIndex.put(indexNumber, associatedScreens);
        }
        
        // PRECEDENCE 415-418
        for (int i = 415; i < 419; i++) {
        	indexNumber = i;
    		associatedScreens = new ArrayList<Integer>();
    		associatedScreens.add(303);
    		associatedScreens.add(396);
    		associatedScreens.add(397);
    		associatedScreens.add(398);
    		associatedScreens.add(399);
    		associatedScreens.add(400);
    		associatedScreens.add(401);
    		screenIndex.put(indexNumber, newScreenTemplate(318, true, true, true, true));
            associatedScreensIndex.put(indexNumber, associatedScreens);
        }
        
        // SUBFS 419-420
        for (int i = 419; i < 421; i++) {
        	indexNumber = i;
    		associatedScreens = new ArrayList<Integer>();
    		associatedScreens.add(303);
    		associatedScreens.add(396);
    		associatedScreens.add(397);
    		associatedScreens.add(398);
    		associatedScreens.add(399);
    		associatedScreens.add(400);
    		associatedScreens.add(401);
    		screenIndex.put(indexNumber, newScreenTemplate(318, true, true, true, true));
            associatedScreensIndex.put(indexNumber, associatedScreens);
        }
        
        // BOMBER C3 CAPABLE? 421-422
        for (int i = 421; i < 423; i++) {
        	indexNumber = i;
    		associatedScreens = new ArrayList<Integer>();
    		associatedScreens.add(303);
    		associatedScreens.add(396);
    		associatedScreens.add(397);
    		associatedScreens.add(398);
    		associatedScreens.add(399);
    		associatedScreens.add(400);
    		associatedScreens.add(401);
    		screenIndex.put(indexNumber, newScreenTemplate(318, true, true, true, true));
            associatedScreensIndex.put(indexNumber, associatedScreens);
        }
        
        // NET ACTIVE JOIN NET SCREEN (NOT SURE IF ACCURATE!!!!!!!!)
        // LAST SCREEN = ENTERED NET NUMBER SCREEN (INDEX = 301)
        indexNumber = 423;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     SELECT OPTION                                                              \r\n" + 
				"                                                                                \r\n" + 
				"                                   NET ACTIVE                                   \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(300);
		associatedScreens.add(304);
		associatedScreens.add(305);
		associatedScreens.add(306);
		associatedScreens.add(307);
		associatedScreens.add(308);
		screenIndex.put(indexNumber, newScreenTemplate(301, true, true, false, true));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        // NET NOT ACTIVE EXIT NET SCREEN
        // LAST SCREEN = ENTERED NET NUMBER SCREEN (INDEX = 301)
        indexNumber = 424;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     SELECT OPTION                                                              \r\n" + 
				"                                                                                \r\n" + 
				"                                 NET NOT ACTIVE                                 \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(300);
		associatedScreens.add(304);
		associatedScreens.add(305);
		associatedScreens.add(306);
		associatedScreens.add(307);
		associatedScreens.add(308);
		screenIndex.put(indexNumber, newScreenTemplate(301, true, true, false, true));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        /*
         * VALUES SHOULD BE CHANGED FOR TX PORTS
         */
        
        // TX PORT NET PARAM SCREENS (INDEX NUMBER VALUES NEED TO BE CHANGED LATER!!!!!!!!)
        // LAST SCREEN = TX PORT NET PARAM SCREEN (INDEX = 322)
        indexNumber = 451; // R01
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(321);
		associatedScreens.add(322);
		screenIndex.put(indexNumber, newScreenTemplate(315, true, true, true, true));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        indexNumber = 452; // R02
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(321);
		associatedScreens.add(322);
		screenIndex.put(indexNumber, newScreenTemplate(315, true, true, true, true));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        indexNumber = 453; // R03
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(321);
		associatedScreens.add(322);
		screenIndex.put(indexNumber, newScreenTemplate(315, true, true, true, true));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        indexNumber = 454; // R04
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(321);
		associatedScreens.add(322);
		screenIndex.put(indexNumber, newScreenTemplate(315, true, true, true, true));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        indexNumber = 455; // R06
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(321);
		associatedScreens.add(322);
		screenIndex.put(indexNumber, newScreenTemplate(315, true, true, true, true));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        indexNumber = 456; // R07
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(321);
		associatedScreens.add(322);
		screenIndex.put(indexNumber, newScreenTemplate(315, true, true, true, true));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        indexNumber = 457; // R10
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(321);
		associatedScreens.add(322);
		screenIndex.put(indexNumber, newScreenTemplate(315, true, true, true, true));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        indexNumber = 458; // R11
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(321);
		associatedScreens.add(322);
		screenIndex.put(indexNumber, newScreenTemplate(315, true, true, true, true));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        
		// ENTERED PTP CALL NUMBER SCREEN
		// LAST SCREEN = PTP CALLS SCREEN (INDEX = 22)
		indexNumber = 500; // NOT REAL VALUE JUST FOR TEST
        newPromptArea = 
                "--------------------------------------------------------------------------------\r\n" + 
                "     ENTER PTP CALL NUMBER                                                      \r\n" + 
                "                                                                                \r\n" + 
                "                                 INVALID ENTRY                                  \r\n";
		screenIndex.put(indexNumber, newScreenTemplate(22, true, true, false, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// TERMINAL CONTROL SHUT POWER OFF SCREEN
		// LAST SCREEN = TERMINAL CONTROL POWER DOWN SCREEN (INDEX = 19)
        indexNumber = 800; // NOT REAL VALUE JUST FOR TEST
		newPromptArea =
                "--------------------------------------------------------------------------------\r\n" + 
                "     SHUT POWER OFF                                                             \r\n" + 
                "                                                                                \r\n" + 
                "                                                                                \r\n";
        newOptionsArea =
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		screenIndex.put(indexNumber, newScreenTemplate(19, true, true, false, false));
        
        /*
         * STARTUP SCREENS
         */
        // WORKING SCREEN
        indexNumber = 998;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"                                                                                \r\n" + 
				"                                    WORKING                                     \r\n" + 
				"                                                                                \r\n";
        newOptionsArea =
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		screenIndex.put(indexNumber, newScreenTemplate(0, true, true, false, false));
        
        // WORKING SCREEN CONT
        indexNumber = 999;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		screenIndex.put(indexNumber, newScreenTemplate(998, true, true, false, true));
        
        // STARTUP SCREEN
        indexNumber = 1000;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     PRESS VERIFY TO START UP                                                   \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		newOptionsArea =
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";	
		screenIndex.put(indexNumber, newScreenTemplate(0, true, true, false, false));
        
        // STARTUP SCREEN CONT
        // LAST SCREEN = STARTUP SCREEN (INDEX = 1000)
        indexNumber = 1001;
        newOptionsArea = 
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦  OPERATIONAL  ¦  ¦ BUILT-IN TEST ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(1002);
		associatedScreens.add(33); // OR 1003?
		screenIndex.put(indexNumber, newScreenTemplate(0, true, true, true, false));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        // OPERATIONAL SCREEN
        // LAST SCREEN = STARTUP SCREEN CONT (INDEX = 1001)
        indexNumber = 1002;
        newOptionsArea = 
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦ SYSTEM READY  ¦  ¦ PARAM CHANGE  ¦  ¦   SET TIME    ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(0);  // NEEDS TO GO THROUGH KGV-11 STEPS
		associatedScreens.add(1004);
		associatedScreens.add(1006);
		screenIndex.put(indexNumber, newScreenTemplate(1001, true, true, true, false));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        // NEED TIME UPDATE SCREEN
        // LAST SCREEN = OPERATIONAL SCREEN (INDEX = 1002)
        indexNumber = 1004;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     SELECT OPTION                                                              \r\n" + 
				"                                                                                \r\n" + 
				"                                NEED TIME UPDATE                                \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(0);  // NEEDS TO GO THROUGH KGV-11 STEPS
		associatedScreens.add(1004);
		associatedScreens.add(1006);
		screenIndex.put(indexNumber, newScreenTemplate(1002, true, true, false, true));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        // PARAM CHANGE SCREEN
        // LAST SCREEN = OPERATIONAL SCREEN (INDEX = 1002)
        indexNumber = 1005;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     SELECT DBASE SOURCE                                                        \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		newOptionsArea =
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦    NORMAL     ¦  ¦   FALLBACK    ¦  ¦    BYPASS     ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(1020);
		associatedScreens.add(1020);
		associatedScreens.add(1020);
		screenIndex.put(indexNumber, newScreenTemplate(1002, true, true, false, false));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        // SET TIME SCREEN
        // LAST SCREEN = OPERATIONAL SCREEN (INDEX = 1002)
        indexNumber = 1006;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     ENTER DATE - TIME (DD-HHMMSSZ-MMMYY)                                       \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		screenIndex.put(indexNumber, newScreenTemplate(1000, true, true, false, true));
        
		// INVALID TIME SCREEN
		// LAST SCREEN = SET TIME SCREEN (INDEX = 1006)
		indexNumber = 1007;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     ENTER DATE - TIME (DD-HHMMSSZ-MMMYY)                                       \r\n" + 
				"                                                                                \r\n" + 
				"                                 INVALID ENTRY                                  \r\n";
		screenIndex.put(indexNumber, newScreenTemplate(1006, true, true, false, true));
		
		// VERIFY AT TIME SCREEN
		indexNumber = 1008;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     VERIFY AT TIME =                                                           \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		screenIndex.put(indexNumber, newScreenTemplate(1006, true, true, false, true));
		
		// VERIFY AT TIME WORKING SCREEN
		indexNumber = 1009;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     VERIFY AT TIME =                                                           \r\n" + 
				"                                    WORKING                                     \r\n" +
				"                                                                                \r\n";
		screenIndex.put(indexNumber, newScreenTemplate(1008, true, true, false, true));
		
		// VERIFY AT TIME WORKING CONT SCREEN
		indexNumber = 1010;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     VERIFY AT TIME =                                                           \r\n" + 
				"                                                                                \r\n" +
				"                                                                                \r\n";
		screenIndex.put(indexNumber, newScreenTemplate(1008, true, true, true, true));
		
		// SET ACCURACY OF TIME SCREEN
		// LAST SCREEN = VERIFY AT TIME SCREEN
		indexNumber = 1012;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     SELECT ACCURACY OF TIME                                                    \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
        newOptionsArea = 
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦ WITHIN 1 SEC  ¦  ¦ WITHIN 10 SEC ¦  ¦ WITHIN 1 MIN  ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(1013);
		associatedScreens.add(1013);
		associatedScreens.add(1013);
		screenIndex.put(indexNumber, newScreenTemplate(1008, true, true, false, false));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        // SELECTED TIME ACCURACY SCREEN
        // LAST SCREEN = SET ACCURACY OF TIME SCREEN (INDEX = 1012)
        indexNumber = 1013;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(0);  // NEEDS TO GO THROUGH KGV-11 STEPS
		associatedScreens.add(1005);
		associatedScreens.add(1006);
		screenIndex.put(indexNumber, newScreenTemplate(1002, true, true, true, true));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        // SELECTED DBASE SOURCE SCREEN
        // LAST SCREEN = PARAM CHANGE SCREEN (INDEX = 1005)
        indexNumber = 1020;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     SELECT DBASE SOURCE                                                        \r\n" + 
				"                                    WORKING                                     \r\n" +  
				"                                                                                \r\n";
		newOptionsArea =
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		screenIndex.put(indexNumber, newScreenTemplate(1005, true, true, false, false));
		
		// SELECTED DBASE SOURCE CONT SCREEN
		// LAST SCREEN = SELECTED DBASE SOURCE SCREEN (INDEX = 1020)
		indexNumber = 1021;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     SELECT DBASE SOURCE                                                        \r\n" + 
				"                                                                                \r\n" +  
				"                                                                                \r\n";
		screenIndex.put(indexNumber, newScreenTemplate(1020, true, true, false, true));
		
		// SELECT PARAMETER TO BE CHANGED SCREEN
		// LAST SCREEN = SELECTED DBASE SOURCE SCREEN (INDEX = 1020) or SELECTED DBASE CONT SCREEN (INDEX = 1021)
		indexNumber = 1022;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     SELECT PARAMETER TO BE CHANGED                                             \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		newOptionsArea =
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦ SYSTEM READY  ¦  ¦ CONSTELLATION ¦  ¦  MODE CHANGE  ¦  ¦ADAPTATION DATA¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦FUNCTION BYPASS¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(0);
		associatedScreens.add(1023);
		associatedScreens.add(1024);
		associatedScreens.add(1025);
		associatedScreens.add(1026);
		screenIndex.put(indexNumber, newScreenTemplate(0, true, true, false, false));
        associatedScreensIndex.put(indexNumber, associatedScreens);
		
        // SELECT CONSTELLATION SCREEN
        // LAST SCREEN = SELECT PARAMETER TO BE CHANGED SCREEN (INDEX = 1022)
        indexNumber = 1023;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     SELECT CONSTELLATION                                                       \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		newOptionsArea =
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦      MIL      ¦  ¦      UFO      ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(1027);
		associatedScreens.add(1028);
		screenIndex.put(indexNumber, newScreenTemplate(1022, true, true, false, false));
        associatedScreensIndex.put(indexNumber, associatedScreens);
		
        // MODE CHANGE SCREEN
        // LAST SCREEN = SELECT PARAMETER TO BE CHANGED SCREEN (INDEX = 1022)
        indexNumber = 1024;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     SELECT MODE                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		newOptionsArea =
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦     AUTO      ¦  ¦    MANUAL     ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(1029);
		associatedScreens.add(1030);
		screenIndex.put(indexNumber, newScreenTemplate(1022, true, true, false, false));
        associatedScreensIndex.put(indexNumber, associatedScreens);
        
        // ADAPTATION DATA SCREEN
        // LAST SCREEN = SELECT PARAMETER TO BE CHANGED SCREEN (INDEX = 1022)
        indexNumber = 1025;
		newMainArea = 
				"MAIN:ADAPTATION DATA------------------------------------------------------------\r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" +  
				"         DATA ID:                                                               \r\n" + 
				"         CHECKSUM:                                                              \r\n" + 
				"                                                                                \r\n" + 
				"         DATA:                                                                  \r\n" + 
				"                                                                                \r\n" +  
				"                                                                                \r\n" + 
				"                                                                                \r\n" +  
				"                                                                                \r\n" +  
				"                                                                                \r\n" + 
				"                                                                                \r\n" +  
				"                                                                                \r\n" +  
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" +  
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" +  
				"                                                                                \r\n" + 
				"                                                                                \r\n" +  
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     SELECT OPTION                                                              \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		newOptionsArea =
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦ EXIT DISPLAY  ¦  ¦    DATA ID    ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(1022);
		associatedScreens.add(1031);
		screenIndex.put(indexNumber, newScreenTemplate(1022, true, false, false, false));
        associatedScreensIndex.put(indexNumber, associatedScreens);
		
        // VERIFY MIL CONSTELLATION SCREEN
        // LAST SCREEN = SELECT CONSTELLATION SCREEN (INDEX = 1023)
        indexNumber = 1027;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     PRESS VERIFY (MAY LOSE DATA)                                               \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		newOptionsArea =
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		screenIndex.put(indexNumber, newScreenTemplate(1023, true, true, false, false));
		
        // VERIFY UFO CONSTELLATION SCREEN
        // LAST SCREEN = SELECT CONSTELLATION SCREEN (INDEX = 1023)
		indexNumber = 1028;
		screenIndex.put(indexNumber, newScreenTemplate(1027, true, true, true, true));
		
		// AUTO MODE SELECTED SCREEN
		// LAST SCREEN = MODE CHANGE SCREEN (INDEX = 1024)
		indexNumber = 1029;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(0);
		associatedScreens.add(1023);
		associatedScreens.add(1024);
		associatedScreens.add(1025);
		associatedScreens.add(1026);
		screenIndex.put(indexNumber, newScreenTemplate(1022, true, true, true, true));
        associatedScreensIndex.put(indexNumber, associatedScreens);
		
        // MANUAL MODE SELECTED SCREEN
		// LAST SCREEN = MODE CHANGE SCREEN (INDEX = 1024)
		indexNumber = 1030;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(0);
		associatedScreens.add(1023);
		associatedScreens.add(1024);
		associatedScreens.add(1025);
		associatedScreens.add(1026);
		screenIndex.put(indexNumber, newScreenTemplate(1022, true, true, true, true));
        associatedScreensIndex.put(indexNumber, associatedScreens);
		
        // DATA ID SCREEN
        // LAST SCREEN = ADAPTATION DATA SCREEN (INDEX = 1025)
        indexNumber = 1031;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     ENTER DATA ID                                                              \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		newOptionsArea =
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		screenIndex.put(indexNumber, newScreenTemplate(1025, true, true, false, false));
		
		
		
		// WORKING VERIFY FOR DISRUPTIVE TEST SCREEN
		// LAST SCREEN = VERIFY FOR DISRUPTIVE TEST SCREEN (INDEX = 33)
		indexNumber = 1300; // NOT REAL VALUE
        newPromptArea = 
                "--------------------------------------------------------------------------------\r\n" + 
                "     VERIFY FOR DISRUPTIVE TEST                                                 \r\n" + 
				"                                    WORKING                                     \r\n" + 
                "                                                                                \r\n";
		associatedScreens = new ArrayList<Integer>();
		screenIndex.put(indexNumber, newScreenTemplate(33, true, true, false, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// DISRUPTIVE BIT TEST SCREEN
		// LAST SCREEN = VERIFY FOR DISRUPTIVE TEST SCREEN (INDEX = 33) OR WORKING VERIFY FOR DISRUPTIVE TEST SCREEN (INDEX = 1300)
		indexNumber = 1301;
        newOptionsArea = 
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦  ABORT TEST   ¦  ¦    RUN ALL    ¦  ¦  COMMON TEST  ¦  ¦   EHF TEST    ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦   I/O PORTS   ¦  ¦  POWER DOWN   ¦  ¦RESTART SYSTEM ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(1301);    // NOT REAL VALUE NEEDS TO CANCEL TEST 
		associatedScreens.add(1501); 
		associatedScreens.add(1502);
		associatedScreens.add(1503);
		associatedScreens.add(1504);
		associatedScreens.add(1505);
		associatedScreens.add(1506);
		screenIndex.put(indexNumber, newScreenTemplate(5, true, true, true, false));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// STARTUP BIT TEST SCREEN
		// LAST SCREEN = OPERATIONAL?
		indexNumber = 1302;
        newOptionsArea = 
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦  ABORT TEST   ¦  ¦    RUN ALL    ¦  ¦  COMMON TEST  ¦  ¦   EHF TEST    ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦   I/O PORTS   ¦  ¦  POWER DOWN   ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(1302);    // NOT REAL VALUE NEEDS TO CANCEL TEST 
		associatedScreens.add(1501); 
		associatedScreens.add(1532);
		associatedScreens.add(1533);
		associatedScreens.add(1504);
		associatedScreens.add(1505);
		associatedScreens.add(1506);
		screenIndex.put(indexNumber, newScreenTemplate(1301, true, true, true, false));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// COMMON TEST SCREEN
		// LAST SCREEN = DISRUPTIVE BIT TEST SCREEN (INDEX = 1301)
		indexNumber = 1502;
        newOptionsArea = 
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦     EXIT      ¦  ¦  ABORT TEST   ¦  ¦RUN ALL COMMON ¦  ¦ BBP INTERNAL  ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦ TAC INTERNAL  ¦  ¦  TAC/BBP IF   ¦  ¦      TFS      ¦  ¦ MORE OPTIONS  ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(1301);    // NOT REAL VALUE NEEDS TO CANCEL TEST 
		associatedScreens.add(1502);	// WHERE DOES IT RETURN? 
		associatedScreens.add(1507);
		associatedScreens.add(1508);
		associatedScreens.add(1509);
		associatedScreens.add(1510);
		associatedScreens.add(1511);
		associatedScreens.add(1512);
		screenIndex.put(indexNumber, newScreenTemplate(5, true, true, true, false));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// EHF TEST SCREEN
		// LAST SCREEN = DISRUPTIVE BIT TEST SCREEN (INDEX = 1301)
		indexNumber = 1503;
        newOptionsArea = 
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦     EXIT      ¦  ¦  ABORT TEST   ¦  ¦  RUN ALL EHF  ¦  ¦   EHF MODEM   ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦ RSU SELF-TEST ¦  ¦RSU VERIF TEST ¦  ¦RECEIVER VERIF ¦  ¦ MORE OPTIONS  ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(1301);    // NOT REAL VALUE NEEDS TO CANCEL TEST 
		associatedScreens.add(1503);	// WHERE DOES IT RETURN? 
		associatedScreens.add(1517);
		associatedScreens.add(1518);
		associatedScreens.add(1519);
		associatedScreens.add(1520);
		associatedScreens.add(1521);
		associatedScreens.add(1522);
		screenIndex.put(indexNumber, newScreenTemplate(5, true, true, true, false));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// I/O PORTS SCREEN
		// LAST SCREEN = DISRUPTIVE BIT TEST SCREEN (INDEX = 1301)
		indexNumber = 1504;
        newOptionsArea = 
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦ EXIT DISPLAY  ¦  ¦ RED I/O TEST  ¦  ¦BLACK I/O TEST ¦  ¦      HSP      ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(1301);
		associatedScreens.add(1527);
		associatedScreens.add(1528);
		associatedScreens.add(1529);
		screenIndex.put(indexNumber, newScreenTemplate(5, true, true, true, false)); // NEEDS TO REFERENCE PORT USAGE SCREEN
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// BIT POWER DOWN SCREEN
		// LAST SCREEN = 
		indexNumber = 1505;
		newPromptArea =
                "--------------------------------------------------------------------------------\r\n" + 
                "     VERIFY TO POWER DOWN                                                       \r\n" + 
                "                                                                                \r\n" + 
                "                                                                                \r\n";
        newOptionsArea =
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
        screenIndex.put(indexNumber, newScreenTemplate(5, true, true, false, false));
		
        // RESTART SYSTEM SCREEN
        // LAST SCREEN = DISRUPTIVE BIT TEST SCREEN
        indexNumber = 1506;
		newPromptArea =
                "--------------------------------------------------------------------------------\r\n" + 
                "     VERIFY TO RESTART SYSTEM                                                   \r\n" + 
                "                                                                                \r\n" + 
                "                                                                                \r\n";
		screenIndex.put(indexNumber, newScreenTemplate(1505, true, true, false, true));
        
		// COMMON TEST CONT SCREEN
		// LAST SCREEN = COMMON TEST SCREEN (INDEX = 1502)
		indexNumber = 1512;
        newOptionsArea = 
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦ PREV OPTIONS  ¦  ¦    DISPLAY    ¦  ¦   KEYBOARD    ¦  ¦      HSP      ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦ CONTROL PANEL ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(1502);    // NOT REAL VALUE NEEDS TO CANCEL TEST 
		associatedScreens.add(1513);	// WHERE DOES IT RETURN? 
		associatedScreens.add(1514);
		associatedScreens.add(1515);
		associatedScreens.add(1516);
		screenIndex.put(indexNumber, newScreenTemplate(5, true, true, true, false));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// EHF TEST CONT SCREEN
		// LAST SCREEN = EHF TEST SCREEN (INDEX = 1503)
		indexNumber = 1522;
        newOptionsArea = 
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦ PREV OPTIONS  ¦  ¦ ANTENNA/APCU  ¦  ¦  KIV-7M LOOP  ¦  ¦    HPA BIT    ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦RADIATING TEST ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(1503);
		associatedScreens.add(1523);
		associatedScreens.add(1524);
		associatedScreens.add(1525);
		associatedScreens.add(1526);
		screenIndex.put(indexNumber, newScreenTemplate(5, true, true, true, false)); 
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// 
		indexNumber = 1530;
		// BIT SHUT POWER OFF SCREEN
		// LAST SCREEN = BIT POWER DOWN SCREEN (INDEX = 1505)
		newPromptArea =
                "--------------------------------------------------------------------------------\r\n" + 
                "     SHUT POWER OFF                                                             \r\n" + 
                "                                                                                \r\n" + 
                "                                                                                \r\n";
        newOptionsArea =
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		screenIndex.put(indexNumber, newScreenTemplate(1505, true, true, false, false));
		
		/*
		 * ALL BIT TEST SCREENS
		 */
		// VERIFY TO INIT TEST SCREEN
		indexNumber = 1501;
        newPromptArea = 
                "--------------------------------------------------------------------------------\r\n" + 
                "     VERIFY TO INIT TEST                                                        \r\n" + 
				"                                                                                \r\n" + 
                "                                                                                \r\n";
        newOptionsArea = 
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		screenIndex.put(indexNumber, newScreenTemplate(5, true, true, false, false));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		indexNumber = 1507; // 
		screenIndex.put(indexNumber, newScreenTemplate(1501, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		indexNumber = 1508;
		screenIndex.put(indexNumber, newScreenTemplate(1501, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		indexNumber = 1509;
		screenIndex.put(indexNumber, newScreenTemplate(1501, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		indexNumber = 1510;
		screenIndex.put(indexNumber, newScreenTemplate(1501, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		indexNumber = 1511;
		screenIndex.put(indexNumber, newScreenTemplate(1501, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		indexNumber = 1513;
		screenIndex.put(indexNumber, newScreenTemplate(1501, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		indexNumber = 1514;
		screenIndex.put(indexNumber, newScreenTemplate(1501, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		indexNumber = 1515;
		screenIndex.put(indexNumber, newScreenTemplate(1501, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		indexNumber = 1516;
		screenIndex.put(indexNumber, newScreenTemplate(1501, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		indexNumber = 1517;
		screenIndex.put(indexNumber, newScreenTemplate(1501, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		indexNumber = 1518;
		screenIndex.put(indexNumber, newScreenTemplate(1501, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		indexNumber = 1519;
		screenIndex.put(indexNumber, newScreenTemplate(1501, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		indexNumber = 1520;
		screenIndex.put(indexNumber, newScreenTemplate(1501, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		indexNumber = 1521;
		screenIndex.put(indexNumber, newScreenTemplate(1501, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		indexNumber = 1523;
		screenIndex.put(indexNumber, newScreenTemplate(1501, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		indexNumber = 1524;
		screenIndex.put(indexNumber, newScreenTemplate(1501, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		indexNumber = 1525;
		screenIndex.put(indexNumber, newScreenTemplate(1501, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		indexNumber = 1526;
		screenIndex.put(indexNumber, newScreenTemplate(1501, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// COMMON TEST SCREEN (STARTUP)
		// LAST SCREEN = STARTUP DISRUPTIVE BIT TEST SCREEN (INDEX = 1302)
		indexNumber = 1532;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(1302);    // NOT REAL VALUE NEEDS TO CANCEL TEST 
		associatedScreens.add(1532);	// WHERE DOES IT RETURN? 
		associatedScreens.add(1507);
		associatedScreens.add(1508);
		associatedScreens.add(1509);
		associatedScreens.add(1510);
		associatedScreens.add(1511);
		associatedScreens.add(1535);
		screenIndex.put(indexNumber, newScreenTemplate(1502, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// EHF TEST SCREEN (STARTUP)
		// LAST SCREEN = STARTUP DISRUPTIVE BIT TEST SCREEN (INDEX = 1302)
		indexNumber = 1533;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(1302);    // NOT REAL VALUE NEEDS TO CANCEL TEST 
		associatedScreens.add(1533);	// WHERE DOES IT RETURN? 
		associatedScreens.add(1517);
		associatedScreens.add(1518);
		associatedScreens.add(1519);
		associatedScreens.add(1520);
		associatedScreens.add(1521);
		associatedScreens.add(1536);
		screenIndex.put(indexNumber, newScreenTemplate(1503, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// I/O PORTS SCREEN (STARTUP)
		// LAST SCREEN = STARTUP DISRUPTIVE BIT TEST SCREEN (INDEX = 1302)
		indexNumber = 1534;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(1302);
		associatedScreens.add(1527);
		associatedScreens.add(1528);
		associatedScreens.add(1529);
		screenIndex.put(indexNumber, newScreenTemplate(1504, true, true, true, true)); // NEEDS TO REFERENCE PORT USAGE SCREEN
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// COMMON TEST CONT SCREEN (STARTUP)
		// LAST SCREEN = COMMON TEST SCREEN (STARTUP) (INDEX = 1532)
		indexNumber = 1535;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(1532);    // NOT REAL VALUE NEEDS TO CANCEL TEST 
		associatedScreens.add(1513);	// WHERE DOES IT RETURN? 
		associatedScreens.add(1514);
		associatedScreens.add(1515);
		associatedScreens.add(1516);
		screenIndex.put(indexNumber, newScreenTemplate(1512, true, true, true, true));
		associatedScreensIndex.put(indexNumber, associatedScreens);
		
		// EHF TEST CONT SCREEN (STARTUP)
		// LAST SCREEN = EHF TEST SCREEN (STARTUP) (INDEX = 1533)
		indexNumber = 1536;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(1533);
		associatedScreens.add(1523);
		associatedScreens.add(1524);
		associatedScreens.add(1525);
		associatedScreens.add(1526);
		screenIndex.put(indexNumber, newScreenTemplate(1522, true, true, true, true)); 
		associatedScreensIndex.put(indexNumber, associatedScreens);
	}
	
	// Prints Status of TRANSEC Keys
	private String printTRANSECKeyStatus(String satName) { 
		ArrayList<Character> keys = sats.get(satName).getKeys();
		String TRANSECKeyStatuses = "";
		for (int i = 0; i < 10; i++) {
			if (i == 4 || i == 9) {
				TRANSECKeyStatuses += keys.get(i)+"   ";
			} else {
				TRANSECKeyStatuses += keys.get(i)+"  ";
			}
		}
		return TRANSECKeyStatuses;
	}
	
	// Returns Formatted Temporary Navigation Data Values
	private String getTempNavData(int index) {
		if (tempNavData[index] != null) {
			switch(index) {
			case 0: 
				return String.format("%1$6s", tempNavData[index]);
			case 1:
				return String.format("%1$7s", tempNavData[index]);
			case 2:
				return String.format("%1$7s", tempNavData[index]);
			case 3:
				return String.format("%1$5s", tempNavData[index]);
			case 4:
				return String.format("%1$5s", tempNavData[index]);
			case 5:
				return String.format("%1$6s", tempNavData[index]);
			default:
				return "";
			}
		} else {
			return "";
		}
	}
	
	// Returns Formatted Log Entries
	private String fetchLogEntry(int i) {
		if (log.size() >= i) {
			if (log.get(log.size()-i).getStatus() == Status.ALARM) {
				return log.get(log.size()-i).getTimeStamp()+"* "+log.get(log.size()-i).getMessage();
			} else {
				return log.get(log.size()-i).getTimeStamp()+"  "+log.get(log.size()-i).getMessage();
			}
		}  else {
			return "";
		}
	}
	
	// Returns Screen By Index Number
	public String getScreen(int screenNumber) {
		try {
			String screen = screenIndex.get(screenNumber).get(0) + screenIndex.get(screenNumber).get(1) 
					+ screenIndex.get(screenNumber).get(2) + screenIndex.get(screenNumber).get(3);
			return screen;
		} catch (NullPointerException npe) {
			return null;
		}
	}
	
	// Returns Indices of Associated Screens
	public ArrayList<Integer> getAssociatedScreens(int screenNumber) {
		return associatedScreensIndex.get(screenNumber);
	}
	
	// Uses Another Screen as Template for New Screen
	// Status = 6, Main = 31, Prompt = 4, Options = 9
	private ArrayList<String> newScreenTemplate(int templateScreenIndex, boolean statusAreaIsSame, boolean mainAreaIsSame,
			boolean promptAreaIsSame, boolean optionsAreaIsSame) {
		ArrayList<String> temp = new ArrayList<String>();
		if (statusAreaIsSame == true) {
			temp.add(screenIndex.get(templateScreenIndex).get(0));
		} else {
			temp.add(newStatusArea);
		}
		if (mainAreaIsSame == true) {
			temp.add(screenIndex.get(templateScreenIndex).get(1));
		} else {
			temp.add(newMainArea);
		}
		if (promptAreaIsSame == true) {
			temp.add(screenIndex.get(templateScreenIndex).get(2));
		} else {
			temp.add(newPromptArea);
		}
		if (optionsAreaIsSame == true) {
			temp.add(screenIndex.get(templateScreenIndex).get(3));
		} else {
			temp.add(newOptionsArea);
		}
		return temp;
	}
}
