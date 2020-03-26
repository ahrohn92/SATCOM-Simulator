import java.util.ArrayList;
import java.util.HashMap;

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
	private int index;
	private ArrayList<Log> log;
	private String[] tempNavData;
	private String lineTwo = "";
	String timeSource;
	private boolean alarmIsActive;

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
			String[] ephemeris, String[] logonParams, String[] operatorInputs, boolean loggedOnSat, char[] SRC, String mode, char[] satStatusChars,
			String[] switches, String[] categoryStatus, boolean alarmIsActive, String databaseSource, String[] agileBeamTimes, String agileBeamNoise, 
			String[] KGV11Status, String constellation, String[] canisterIDs, String[] ULSlots) {
		this.log = log;
		this.tempNavData = tempNavData;
		this.sats = sats;
		this.timeSource = timeSource;
		this.alarmIsActive = alarmIsActive;
		
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
		index = 0;
		newStatusArea =
				"DTG:      "+time+"     ** UNCLASSIFIED **                               \r\n" + 
				"                                                                                \r\n" + 
				"ALARM:    "+getAlarm()+"                                                        \r\n" + 
				"                                                                                \r\n" + 
				"ADVISORY: "+getAdvisory()+"                                                     \r\n" +
				"                            "+lineTwo+"                                         \r\n";
		newMainArea =
				"MAIN----------------------------------------------------------------------------\r\n" + 
				"                                                                          V14.00\r\n" + 
				"                                                                    "+GSData+" \r\n" + 
				"                                                                                \r\n" + 
				"                        OPERATOR 1                                              \r\n" + 
				"                        MODE             NORMAL POWER ON                        \r\n" + 
				"                        STARTUP MODE     "+startupMode+"                                  \r\n" + 
				"                        DATABASE SOURCE  "+databaseSource+"         \r\n" + 
				"                        TIME SOURCE      "+timeSource+"                                    \r\n" +
				"                        CONSTELLATION    "+constellation+"                                    \r\n" + 
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
		screenIndex.put(index, screenAreas);
		associatedScreensIndex.put(index, associatedScreens);
			
		// MESSAGE PROCESSING SCREEN
		// LAST SCREEN = MAIN SCREEN
		index = 1;
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
		screenIndex.put(index, newScreenTemplate(0, true, true, true, false));
		associatedScreensIndex.put(index, associatedScreens);
		
		// TERMINAL CONTROL SCREEN
		// LAST SCREEN = MAIN SCREEN
		index = 2;
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
		screenIndex.put(index, newScreenTemplate(0, true, false, true, false));
		associatedScreensIndex.put(index, associatedScreens);
		
		// EHF SERVICES SCREEN
		// LAST SCREEN = MAIN SCREEN
		index = 3;
		newStatusArea = 
				"DTG:      "+time+"        ** SECRET **                                  \r\n" + 
				"                                                                                \r\n" + 
				"ALARM:                                                                     	 \r\n" + 
				"                                                                                \r\n" + 
				"ADVISORY: "+getAdvisory()+" \r\n" +
				"                            "+lineTwo+"                                         \r\n";
		newMainArea = 
				"MAIN:EHF------------------------------------------------------------------------\r\n" + 
				"           UL SLOTS: PRIMARY    1 "+String.format("%1$-3s",ULSlots[0])+"  2 "+String.format("%1$-3s",ULSlots[1])+"  3 "+String.format("%1$-3s",ULSlots[2])+"  4 "+String.format("%1$-3s",ULSlots[3])+"   CONSTELLATION  "+constellation+" \r\n" + 
				"                     SECONDARY  1 "+String.format("%1$-3s",ULSlots[4])+"  2 "+String.format("%1$-3s",ULSlots[5])+"  3 "+String.format("%1$-3s",ULSlots[6])+"  4 "+String.format("%1$-3s",ULSlots[7])+"   TERMINAL ID   XXXX \r\n" + 
				"                                                                                \r\n" + 
				"KEYS                        TX RX DL                                            \r\n" + 
				"   CON NAME       TX   RX   C3 C3 RT ST                   TX   RX   C3 C3 RT ST \r\n" + 
				" 1 "+String.format("%1$-14s",nets.get(1).getName())+"  -    -   -  -  -  "+nets.get(1).getNetStatus()+" 19 "+String.format("%1$-14s",nets.get(19).getName())+"  -    -   -  -  -  "+nets.get(19).getNetStatus()+" \r\n" + 
				" 2 "+String.format("%1$-14s",nets.get(2).getName())+"  -    -   -  -  -  "+nets.get(2).getNetStatus()+" 20 "+String.format("%1$-14s",nets.get(20).getName())+"  -    -   -  -  -  "+nets.get(20).getNetStatus()+" \r\n" + 
				" 3 "+String.format("%1$-14s",nets.get(3).getName())+"  -    -   -  -  -  "+nets.get(3).getNetStatus()+" 21 "+String.format("%1$-14s",nets.get(21).getName())+"  -    -   -  -  -  "+nets.get(21).getNetStatus()+" \r\n" +
				" 4 "+String.format("%1$-14s",nets.get(4).getName())+"  -    -   -  -  -  "+nets.get(4).getNetStatus()+" 22 "+String.format("%1$-14s",nets.get(22).getName())+"  -    -   -  -  -  "+nets.get(22).getNetStatus()+" \r\n" + 
				" 5 "+String.format("%1$-14s",nets.get(5).getName())+"  -    -   -  -  -  "+nets.get(5).getNetStatus()+" 23 "+String.format("%1$-14s",nets.get(23).getName())+"  -    -   -  -  -  "+nets.get(23).getNetStatus()+" \r\n" + 
				" 6 "+String.format("%1$-14s",nets.get(6).getName())+"  -    -   -  -  -  "+nets.get(6).getNetStatus()+" 24 "+String.format("%1$-14s",nets.get(24).getName())+"  -    -   -  -  -  "+nets.get(24).getNetStatus()+" \r\n" + 
				" 7 "+String.format("%1$-14s",nets.get(7).getName())+"  -    -   -  -  -  "+nets.get(7).getNetStatus()+" 25 "+String.format("%1$-14s",nets.get(25).getName())+"  -    -   -  -  -  "+nets.get(25).getNetStatus()+" \r\n" + 
				" 8 "+String.format("%1$-14s",nets.get(8).getName())+"  -    -   -  -  -  "+nets.get(8).getNetStatus()+" 26 "+String.format("%1$-14s",nets.get(26).getName())+"  -    -   -  -  -  "+nets.get(26).getNetStatus()+" \r\n" + 
				" 9 "+String.format("%1$-14s",nets.get(9).getName())+"  -    -   -  -  -  "+nets.get(9).getNetStatus()+" 27 "+String.format("%1$-14s",nets.get(27).getName())+"  -    -   -  -  -  "+nets.get(27).getNetStatus()+" \r\n" + 
				"10 "+String.format("%1$-14s",nets.get(10).getName())+"  -    -   -  -  -  "+nets.get(10).getNetStatus()+" 28 "+String.format("%1$-14s",nets.get(28).getName())+"  -    -   -  -  -  "+nets.get(28).getNetStatus()+" \r\n" + 
				"11 "+String.format("%1$-14s",nets.get(11).getName())+"  -    -   -  -  -  "+nets.get(11).getNetStatus()+" 29 "+String.format("%1$-14s",nets.get(29).getName())+"  -    -   -  -  -  "+nets.get(29).getNetStatus()+" \r\n" +
				"12 "+String.format("%1$-14s",nets.get(12).getName())+"  -    -   -  -  -  "+nets.get(12).getNetStatus()+" 30 "+String.format("%1$-14s",nets.get(30).getName())+"  -    -   -  -  -  "+nets.get(30).getNetStatus()+" \r\n" + 
				"13 "+String.format("%1$-14s",nets.get(13).getName())+"  -    -   -  -  -  "+nets.get(13).getNetStatus()+" 31 "+String.format("%1$-14s",nets.get(31).getName())+"  -    -   -  -  -  "+nets.get(31).getNetStatus()+" \r\n" + 
				"14 "+String.format("%1$-14s",nets.get(14).getName())+"  -    -   -  -  -  "+nets.get(14).getNetStatus()+" 32 "+String.format("%1$-14s",nets.get(32).getName())+"  -    -   -  -  -  "+nets.get(32).getNetStatus()+" \r\n" + 
				"15 "+String.format("%1$-14s",nets.get(15).getName())+"  -    -   -  -  -  "+nets.get(15).getNetStatus()+" 33 "+String.format("%1$-14s",nets.get(33).getName())+"  -    -   -  -  -  "+nets.get(33).getNetStatus()+" \r\n" + 
				"16 "+String.format("%1$-14s",nets.get(16).getName())+"  -    -   -  -  -  "+nets.get(16).getNetStatus()+" 34 "+String.format("%1$-14s",nets.get(34).getName())+"  -    -   -  -  -  "+nets.get(34).getNetStatus()+" \r\n" + 
				"17 "+String.format("%1$-14s",nets.get(17).getName())+"  -    -   -  -  -  "+nets.get(17).getNetStatus()+" 35 "+String.format("%1$-14s",nets.get(35).getName())+"  -    -   -  -  -  "+nets.get(35).getNetStatus()+" \r\n" +
				"18 "+String.format("%1$-14s",nets.get(18).getName())+" B01   -   -  -  -  "+nets.get(18).getNetStatus()+" 36 "+String.format("%1$-14s",nets.get(36).getName())+"  -    -   -  -  -  "+nets.get(36).getNetStatus()+" \r\n" + 
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
		screenIndex.put(index, newScreenTemplate(0, false, false, true, false));
		associatedScreensIndex.put(index, associatedScreens);
		
		// EHF ACQ/LOGON SCREEN
		// LAST SCREEN = MAIN SCREEN
		index = 4;
		newMainArea =
				"MAIN:EHF ACQ/TRACK--------------------------------------------------------------\r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"          CURRENT CONSTELLATION      "+constellation+"                                        \r\n" + 
				"                                                                                \r\n" + 
				"          CURRENT SAT                "+satName2+"                             \r\n" + 
				"          BEAM                       "+beam+"                                         \r\n" +
				"                                                                                \r\n" + 
				"          STATUS                     "+satStatus+"                                  \r\n" + 
				"                                                                                \r\n" + 
				"       LOGON PARAMETERS                                                         \r\n" + 
				"                                     TERMINAL    REQUESTED                      \r\n" + 
				"          C2 ROBUSTNESS              "+String.format("%1$-7s", logonParams[3])+"     "+String.format("%1$-7s", logonParams[0])+"                        \r\n" + 
				"          C2 CYCLE PERIOD            "+String.format("%1$-5s", logonParams[4])+"       "+String.format("%1$-5s", logonParams[1])+"                           \r\n" + 
				"          C3 MODE                    "+String.format("%1$-9s", logonParams[5])+"   "+String.format("%1$-6s", logonParams[2])+"                          \r\n" +
				"                                                                                \r\n" + 
				"       SWITCHES                                                                 \r\n" + 
				"                                     OFF                                           \r\n" + 
				"          SAT TRANSFER IF BLOCKED    "+switches[0]+"                                        \r\n" + 
				"          ACQ ON DETECTION           "+switches[1]+"                                        \r\n" + 
				"          STRESS MODE                "+switches[2]+"                                        \r\n" +
				"          REPEAT JAM MODE            "+switches[3]+"                                        \r\n" + 
				"          RECEIVE ONLY MODE          "+switches[4]+"                                        \r\n" + 
				"          SCINTILLATION MODE   "+String.format("%1$9s",switches[5])+"                                        \r\n" + 
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
		screenIndex.put(index, newScreenTemplate(0, true, false, true, false));
		associatedScreensIndex.put(index, associatedScreens);
		
		// BUILT-IN TEST SCREEN
		// LAST SCREEN = MAIN SCREEN
		index = 5;
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
		screenIndex.put(index, newScreenTemplate(0, true, false, true, false));
		associatedScreensIndex.put(index, associatedScreens);

		// DAMA SCREEN
		// LAST SCREEN = MAIN SCREEN
		index = 6;
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
		screenIndex.put(index, newScreenTemplate(0, true, false, true, false));
		associatedScreensIndex.put(index, associatedScreens);
        
		// CRYPTO KEYS SCREEN
		// LAST SCREEN = TERMINAL CONTROL SCREEN (INDEX = 2)
		index = 15;
		newMainArea =
				"MAIN:TERMINAL:KGV-11 KEYS-------------------------------------------------------\r\n" + 
				"                                                                                \r\n" + 
				"CURRENT CONSTELLATION  "+constellation+"             AUTO FUTURE ALL         COMPROMISE COT   \r\n" + 
				"                                 OTAR  "+String.format("%1$-3s", otarAuto)+"  -      -           -                \r\n" + 
				"CONSTELLATIONS                                                                  \r\n" + 
				"         CANISTER ID      GROUP            UNIVERSAL  LINKING                   \r\n" + 
				" LABEL   CURRENT FUTURE   ID     CHAINING  A     B    CURRENT FUTURE COMPROMISE \r\n" + 
				" MIL     "+String.format("%1$-3s",canisterIDs[0])+"  Y  "+String.format("%1$-3s",canisterIDs[1])+"  N   -      -         -     -    -       -      -          \r\n" + 
				" UFO     "+String.format("%1$-3s",canisterIDs[2])+"  N  "+String.format("%1$-3s",canisterIDs[3])+"  N   -      -         -     -    -       -      -          \r\n" +
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
		screenIndex.put(index, newScreenTemplate(2, true, false, true, false));
		associatedScreensIndex.put(index, associatedScreens);
		
		// NAV DATA UPDATE SCREENS
		// LAST SCREEN = TERMINAL CONTROL SCREEN (INDEX = 2)
		index = 17;
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
		associatedScreens.add(70);
		associatedScreens.add(71);
		associatedScreens.add(72);
		associatedScreens.add(73);
		associatedScreens.add(74);
		associatedScreens.add(75);
		associatedScreens.add(76);
		associatedScreens.add(80);
		screenIndex.put(index, newScreenTemplate(2, true, false, true, false));
		associatedScreensIndex.put(index, associatedScreens);
		
		// KB LOCKOUT SCREEN
		// LAST SCREEN = TERMINAL CONTROL SCREEN (INDEX = 2)
		index = 18;
		newPromptArea =
                "--------------------------------------------------------------------------------\r\n" + 
                "     ENTER LOCKOUT PASSWORD                                                     \r\n" + 
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
        screenIndex.put(index, newScreenTemplate(2, true, true, false, false));
		
		// TERMINAL CONTROL POWER DOWN SCREEN
		// LAST SCREEN = TERMINAL CONTROL SCREEN (INDEX = 2)
		index = 19;
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
		screenIndex.put(index, newScreenTemplate(2, true, true, false, false));
		
		// DATA RECORDING SCREEN
		// LAST SCREEN = TERMINAL CONTROL SCREEN (INDEX = 2)
		index = 20;
		newMainArea =
				"MAIN:TERMINAL:RECORDING---------------------------------------------------------\r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                           CATEGORY                 STATUS                      \r\n" + 
				"                                                                                \r\n" +
				"                     1  EHF ACQ & TRACK              "+categoryStatus[0]+"                        \r\n" +
				"                     2  MCE, MCI & MDI               "+categoryStatus[1]+"                        \r\n" + 
				"                     3  HI RATE MCI DATA             "+categoryStatus[2]+"                        \r\n" + 
				"                     4  AGILE BEAM TRACK             "+categoryStatus[3]+"                        \r\n" + 
				"                     5  UHF MODEM TX &RX             "+categoryStatus[4]+"                        \r\n" +
				"                     6  CATEGORY 6                   "+categoryStatus[5]+"                        \r\n" +
				"                     7  CATEGORY 7                   "+categoryStatus[6]+"                        \r\n" + 
				"                     8  CATEGORY 8                   "+categoryStatus[7]+"                        \r\n" +
				"                     9  CATEGORY 9                   "+categoryStatus[8]+"                        \r\n" + 
				"                    10  CATEGORY 10                  "+categoryStatus[9]+"                        \r\n" + 
				"                    11  CATEGORY 11                  "+categoryStatus[10]+"                        \r\n" + 
				"                    12  CATEGORY 12                  "+categoryStatus[11]+"                        \r\n" + 
				"                    13  CATEGORY 13                  "+categoryStatus[12]+"                        \r\n" + 
				"                    14  CATEGORY 14                  "+categoryStatus[13]+"                       \r\n" +
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
				"  ¦ EXIT DISPLAY  ¦  ¦ CHANGE STATUS ¦  ¦ CHANGE SETUP  ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(2);
		associatedScreens.add(275);
		associatedScreens.add(276);
		screenIndex.put(index, newScreenTemplate(2, true, false, true, false));
		associatedScreensIndex.put(index, associatedScreens);
		
        // ENTER NET NUMBER SCREEN
        // LAST SCREEN = EHF SERVICES SCREEN (INDEX = 3)
        index = 21;
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
		screenIndex.put(index, newScreenTemplate(3, true, true, false, false));
		associatedScreensIndex.put(index, associatedScreens);

		// PTP CALLS SCREEN
		// LAST SCREEN = EHF SERVICES SCREEN (INDEX = 3)
		index = 22;
        newPromptArea = 
                "--------------------------------------------------------------------------------\r\n" + 
                "     ENTER PTP CALL NUMBER                                                      \r\n" + 
                "                                                                                \r\n" + 
                "                                                                                \r\n";
		screenIndex.put(index, newScreenTemplate(21, true, true, false, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		// TX/RX SUMMARY SCREEN
		// LAST SCREEN = EHF SERVICES SCREEN (INDEX = 3)
		index = 23;
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
		screenIndex.put(index, newScreenTemplate(3, true, false, true, false));
		associatedScreensIndex.put(index, associatedScreens);
		
		// RX-ONLY SUMMARY SCREEN
		// LAST SCREEN = EHF SERVICES SCREEN (INDEX = 3)
		index = 24;
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
		screenIndex.put(index, newScreenTemplate(3, true, false, true, false));
		associatedScreensIndex.put(index, associatedScreens);
		
		// RESTART DL ACQ SCREEN
		// LAST SCREEN = EHF ACQ/LOGON SCREEN (INDEX = 4)
		index = 25;
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
		screenIndex.put(index, newScreenTemplate(4, true, true, false, false));
		
		// START UL SCREEN
		// LAST SCREEN = EHF ACQ/LOGON SCREEN (INDEX = 4)
		index = 26;
		newPromptArea =
                "--------------------------------------------------------------------------------\r\n" + 
                "     VERIFY TO START UL ACQ                                                     \r\n" + 
                "                                                                                \r\n" + 
                "                                                                                \r\n";
		screenIndex.put(index, newScreenTemplate(25, true, true, false, true));
		
		// LOG OFF SCREEN
		// LAST SCREEN = EHF ACQ/LOGON SCREEN (INDEX = 4)
		index = 27;
		newPromptArea =
                "--------------------------------------------------------------------------------\r\n" + 
                "     VERIFY TO LOG OFF SAT                                                      \r\n" + 
                "                                                                                \r\n" + 
                "                                                                                \r\n";
        screenIndex.put(index, newScreenTemplate(25, true, true, false, true));
        
        // LOGON PARAMETERS SCREEN
        // LAST SCREEN = EHF ACQ/LOGON SCREEN (INDEX = 4)
		index = 28;
		newPromptArea =
                "--------------------------------------------------------------------------------\r\n" + 
                "     SELECT LOGON PARAMETER                                                     \r\n" + 
                "                                                                                \r\n" + 
                "                                                                                \r\n";
        newOptionsArea =
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦     EXIT      ¦  ¦ C2 ROBUSTNESS ¦  ¦C2 CYCLE PERIOD¦  ¦    C3 MODE    ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(4);
		associatedScreens.add(230);
		associatedScreens.add(231);
		associatedScreens.add(233);
		screenIndex.put(index, newScreenTemplate(4, true, true, false, false));
		associatedScreensIndex.put(index, associatedScreens);
        
		// EHF SATS SCREEN
		// LAST SCREEN = EHF ACQ/LOGON SCREEN
		index = 29;
		newMainArea =
				"MAIN:EHF ACQ/TRACK:SATELLITES---------------------------------------------------\r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"CURRENT CONSTELLATION  "+constellation+"                                                      \r\n" +
				"                                                                                \r\n" + 
				"SATELLITES                                         TERMINAL LOGON PARAMETERS    \r\n" + 
				"               SET   RISE  BEAMS                          C2      C2     C3     \r\n" + 
				"  LABEL S EL   TIME  TIME  EC SA SB SC AH AL CI RB BEAM   ROBUST  CYCLE  MODE   \r\n" +
				"  SAT01 "+satStatusChars[0]+" "+getSatElevation("SAT01")+"  "+getSatSetTime("SAT01")+" "+getSatRiseTime("SAT01")+"  Y  Y  Y  Y  Y  Y  Y  Y  "
						+ ""+String.format("%1$-5s",sats.get("SAT01").getBeam())+"  "+String.format("%1$-7s",sats.get("SAT01").getC2Robustness())+" "
								+ ""+String.format("%1$-5s",sats.get("SAT01").getC2Cycle())+"  "+String.format("%1$-6s",sats.get("SAT01").getC3Mode())+" \r\n" + 
				"  SAT02 "+satStatusChars[1]+" "+getSatElevation("SAT02")+"  "+getSatSetTime("SAT02")+" "+getSatRiseTime("SAT02")+"  Y  Y  Y  Y  Y  Y  Y  Y  "
						+ ""+String.format("%1$-5s",sats.get("SAT02").getBeam())+"  "+String.format("%1$-7s",sats.get("SAT02").getC2Robustness())+" "
								+ ""+String.format("%1$-5s",sats.get("SAT02").getC2Cycle())+"  "+String.format("%1$-6s",sats.get("SAT02").getC3Mode())+" \r\n" + 
				"  SAT03 "+satStatusChars[2]+" "+getSatElevation("SAT03")+"  "+getSatSetTime("SAT03")+" "+getSatRiseTime("SAT03")+"  Y  Y  -  -  -  -  -  -  "
						+ ""+String.format("%1$-5s",sats.get("SAT03").getBeam())+"  "+String.format("%1$-7s",sats.get("SAT03").getC2Robustness())+" "
								+ ""+String.format("%1$-5s",sats.get("SAT03").getC2Cycle())+"  "+String.format("%1$-6s",sats.get("SAT03").getC3Mode())+" \r\n" + 
				"  SAT04 "+satStatusChars[3]+" "+getSatElevation("SAT04")+"  "+getSatSetTime("SAT04")+" "+getSatRiseTime("SAT04")+"  Y  Y  Y  Y  Y  Y  Y  Y  "
						+ ""+String.format("%1$-5s",sats.get("SAT04").getBeam())+"  "+String.format("%1$-7s",sats.get("SAT04").getC2Robustness())+" "
								+ ""+String.format("%1$-5s",sats.get("SAT04").getC2Cycle())+"  "+String.format("%1$-6s",sats.get("SAT04").getC3Mode())+" \r\n" + 
				"  SAT05 "+satStatusChars[4]+" "+getSatElevation("SAT05")+"  "+getSatSetTime("SAT05")+" "+getSatRiseTime("SAT05")+"  Y  Y  Y  Y  Y  Y  Y  Y  "
						+ ""+String.format("%1$-5s",sats.get("SAT05").getBeam())+"  "+String.format("%1$-7s",sats.get("SAT05").getC2Robustness())+" "
								+ ""+String.format("%1$-5s",sats.get("SAT05").getC2Cycle())+"  "+String.format("%1$-6s",sats.get("SAT05").getC3Mode())+" \r\n" + 
				"  SAT06 "+satStatusChars[5]+" "+getSatElevation("SAT06")+"  "+getSatSetTime("SAT06")+" "+getSatRiseTime("SAT06")+"  Y  Y  Y  Y  Y  Y  Y  Y  "
						+ ""+String.format("%1$-5s",sats.get("SAT06").getBeam())+"  "+String.format("%1$-7s",sats.get("SAT06").getC2Robustness())+" "
								+ ""+String.format("%1$-5s",sats.get("SAT06").getC2Cycle())+"  "+String.format("%1$-6s",sats.get("SAT06").getC3Mode())+" \r\n" + 
				"  SAT07 "+satStatusChars[6]+" "+getSatElevation("SAT07")+"  "+getSatSetTime("SAT07")+" "+getSatRiseTime("SAT07")+"  Y  Y  Y  Y  Y  Y  Y  Y  "
						+ ""+String.format("%1$-5s",sats.get("SAT07").getBeam())+"  "+String.format("%1$-7s",sats.get("SAT07").getC2Robustness())+" "
								+ ""+String.format("%1$-5s",sats.get("SAT07").getC2Cycle())+"  "+String.format("%1$-6s",sats.get("SAT07").getC3Mode())+" \r\n" + 
				"  SAT08 "+satStatusChars[7]+" "+getSatElevation("SAT08")+"  "+getSatSetTime("SAT08")+" "+getSatRiseTime("SAT08")+"  Y  Y  Y  Y  Y  Y  Y  Y  "
						+ ""+String.format("%1$-5s",sats.get("SAT08").getBeam())+"  "+String.format("%1$-7s",sats.get("SAT08").getC2Robustness())+" "
								+ ""+String.format("%1$-5s",sats.get("SAT08").getC2Cycle())+"  "+String.format("%1$-6s",sats.get("SAT08").getC3Mode())+" \r\n" +
				"  SAT09 "+satStatusChars[8]+" "+getSatElevation("SAT09")+"  "+getSatSetTime("SAT09")+" "+getSatRiseTime("SAT09")+"  Y  Y  -  -  -  -  -  -  "
						+ ""+String.format("%1$-5s",sats.get("SAT09").getBeam())+"  "+String.format("%1$-7s",sats.get("SAT09").getC2Robustness())+" "
								+ ""+String.format("%1$-5s",sats.get("SAT09").getC2Cycle())+"  "+String.format("%1$-6s",sats.get("SAT09").getC3Mode())+" \r\n" + 
				"  SAT10 "+satStatusChars[9]+" "+getSatElevation("SAT10")+"  "+getSatSetTime("SAT10")+" "+getSatRiseTime("SAT10")+"  Y  Y  -  -  -  -  -  -  "
						+ ""+String.format("%1$-5s",sats.get("SAT10").getBeam())+"  "+String.format("%1$-7s",sats.get("SAT10").getC2Robustness())+" "
								+ ""+String.format("%1$-5s",sats.get("SAT10").getC2Cycle())+"  "+String.format("%1$-6s",sats.get("SAT10").getC3Mode())+" \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"KGV-11 STATUS                                                                   \r\n" + 
				"                                     HIGH                                       \r\n" +
				"         KEK      DL      UL         COVER                                      \r\n" + 
				"  TYPE   UNIQUE   "+String.format("%1$5s",KGV11Status[0])+"   "+String.format("%1$5s",KGV11Status[1])+" T1   "+String.format("%1$5s",KGV11Status[2])+"           \r\n" + 
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
		screenIndex.put(index, newScreenTemplate(4, true, false, true, false));
		associatedScreensIndex.put(index, associatedScreens);
		
		// POINTING DATA SCREEN
		// LAST SCREEN = EHF ACQ/LOGON SCREEN
		index = 30;
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
		screenIndex.put(index, newScreenTemplate(4, true, false, true, false));
		associatedScreensIndex.put(index, associatedScreens);
		
		// EHF ACQ/LOGON SCREEN CONT
		// LAST SCREEN = EHF ACQ/LOGON SCREEN
		index = 31;
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
		associatedScreens.add(254);
		associatedScreens.add(274);
		screenIndex.put(index, newScreenTemplate(4, true, true, true, false));
		associatedScreensIndex.put(index, associatedScreens);
		
		// VERIFY FOR DISRUPTIVE TEST SCREEN
		// LAST PAGE = BUILT-IN TEST SCREEN (INDEX = 5)
		index = 33;
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
		screenIndex.put(index, newScreenTemplate(5, true, true, false, false));
		associatedScreensIndex.put(index, associatedScreens);
		
		// FAULT LOG PAGE
		// LAST PAGE = BUILT-IN TEST SCREEN (INDEX = 5)
		index = 34;
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
		screenIndex.put(index, newScreenTemplate(5, true, true, true, false));
		associatedScreensIndex.put(index, associatedScreens);
		
		// VERIFY C2/C3 LOOPBACK SCREEN
		// LAST SCREEN = BUILT-IN TEST SCREEN (INDEX = 5)
		index = 35;
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
		screenIndex.put(index, newScreenTemplate(5, true, true, false, false));
		
		// NOT LOGGED OFF EHF ACQ/LOGON SCREEN
		// LAST SCREEN = RESTART DL ACQ SCREEN (INDEX = 25) RESTART UL ACQ SCREEN (INDEX = 26)
		index = 41;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     SELECT OPTION                                                              \r\n" + 
				"                                                                                \r\n" + 
				"                                 NOT LOGGED OFF                                 \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(0);
		associatedScreens.add(25);
		associatedScreens.add(26);
		associatedScreens.add(27);
		associatedScreens.add(28);
		associatedScreens.add(29);
		associatedScreens.add(30);
		associatedScreens.add(31);
		screenIndex.put(index, newScreenTemplate(4, true, true, false, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		// SAVED NAV DATA CHANGES SCREEN
		// LAST SCREEN = NAV DATA SCREEN (INDEX = 17)
		index = 70;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(0);
		associatedScreens.add(14);
		associatedScreens.add(15);
		associatedScreens.add(16);
		associatedScreens.add(17);
		associatedScreens.add(18);
		associatedScreens.add(19);
		associatedScreens.add(20);
		screenIndex.put(index, newScreenTemplate(2, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		// LATITUDE SCREEN
		// LAST SCREEN = NAV DATA SCREEN (INDEX = 17)
		index = 71;  // NOT REAL VALUE JUST FOR TEST
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
		screenIndex.put(index, newScreenTemplate(17, true, true, false, false));
		
		// LONGITUDE SCREEN
		// LAST SCREEN = NAD DATA SCREEN (INDEX = 17)
		index = 72;  // NOT REAL VALUE JUST FOR TEST
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     ENTER LONGITUDE (DEG MIN E/W)                                              \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		screenIndex.put(index, newScreenTemplate(71, true, true, false, true));
		
		// ALTITUDE SCREEN
		// LAST SCREEN = NAD DATA SCREEN (INDEX = 17)
		index = 73;  // NOT REAL VALUE JUST FOR TEST
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     ENTER ALTITUDE (FT)                                                        \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		screenIndex.put(index, newScreenTemplate(71, true, true, false, true));
		
		// SPEED SCREEN
		// LAST SCREEN = NAD DATA SCREEN (INDEX = 17)
		index = 74;  // NOT REAL VALUE JUST FOR TEST
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
		screenIndex.put(index, newScreenTemplate(17, true, true, false, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		// CLIMB RATE SCREEN
		// LAST SCREEN = NAD DATA SCREEN (INDEX = 17)
		index = 75;  // NOT REAL VALUE JUST FOR TEST
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(70);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(71);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(72);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(73);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(74);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(75);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(76);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(2);  // NOT REAL VALUE JUST FOR TEST
		screenIndex.put(index, newScreenTemplate(74, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		// ATTITUDE SCREEN
		// LAST SCREEN = NAV DATA SCREEN (INDEX = 17)
		index = 76;  // NOT REAL VALUE JUST FOR TEST
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
		screenIndex.put(index, newScreenTemplate(17, true, true, false, false));
		associatedScreensIndex.put(index, associatedScreens);
		
		// HEADING SCREEN
		// LAST SCREEN = ATTITUDE SCREEN (INDEX = 76)
		index = 77;  // NOT REAL VALUE JUST FOR TEST
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     ENTER HEADING (DEG)                                                        \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		screenIndex.put(index, newScreenTemplate(71, true, true, false, true));
		
		// PITCH SCREEN
		// LAST SCREEN = ATTITUDE SCREEN (INDEX = 76)
		index = 78;  // NOT REAL VALUE JUST FOR TEST
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     ENTER PITCH (DEG)                                                          \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		screenIndex.put(index, newScreenTemplate(71, true, true, false, true));
		
		// ROLL SCREEN
		// LAST SCREEN = ATTITUDE SCREEN (INDEX = 76)
		index = 79;  // NOT REAL VALUE JUST FOR TEST
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     ENTER ROLL (DEG)                                                           \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		screenIndex.put(index, newScreenTemplate(71, true, true, false, true));
		
		// ABORTED NAV DATA CHANGES SCREEN
		// LAST SCREEN = NAV DATA SCREEN (INDEX = 17)
		index = 80;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(0);
		associatedScreens.add(14);
		associatedScreens.add(15);
		associatedScreens.add(16);
		associatedScreens.add(17);
		associatedScreens.add(18);
		associatedScreens.add(19);
		associatedScreens.add(20);
		screenIndex.put(index, newScreenTemplate(2, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		// COMSEC SCREEN
		// LAST SCREEN = CRYPTO KEYS SCREEN (INDEX = 15)
		index = 81;
		newMainArea =
				"MAIN:TERMINAL:KGV-11 KEYS:COMSEC------------------------------------------------\r\n" + 
				"                                                                                \r\n" + 
				"CURRENT CONSTELLATION  "+constellation+"             AUTO FUTURE ALL          COMPROMISE COT  \r\n" + 
				"                                 OTAR  ON   -      -            -               \r\n" + 
				"                                                                                \r\n" + 
				"COMSEC KEYS                                                                     \r\n" + 
				"                                                                      COMPROMISE\r\n" + 
				"   CON ID    AUT   OLD   CURRENT   FUTURE   COMPROMISE   TRANSITION   TRANSITION\r\n" + 
				"                                                                                \r\n" +
				"   MIL 011    -    29      30U      -           -            31         -       \r\n" + 
				"   MIL 022    -    29      30       -           -            31         -       \r\n" + 
				"   MIL 033    -    29      30       -           -            31         -       \r\n" + 
				"   MIL 044    -    29      30       -           -            31         -       \r\n" + 
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
		newOptionsArea = 
				"----------------------------------** SECRET **----------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦ EXIT DISPLAY  ¦  ¦     OTAR      ¦  ¦ COMP ROLLOVER ¦  ¦  UPDATE KEYS  ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(15);
		associatedScreens.add(207);
		associatedScreens.add(208);
		associatedScreens.add(209);
		screenIndex.put(index, newScreenTemplate(3, true, false, true, false));
		associatedScreensIndex.put(index, associatedScreens);
		
		// OTAR SCREEN
		// LAST SCREEN = CRYPTO KEYS SCREEN (INDEX = 15)
		index = 82;
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
		screenIndex.put(index, newScreenTemplate(15, true, true, false, false));
		associatedScreensIndex.put(index, associatedScreens);
		
		// UPDATE CON KEYS SCREEN
		// LAST SCREEN = CRYPTO KEYS SCREEN (INDEX = 15)
		index = 83;
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
		associatedScreens.add(426);
		associatedScreens.add(427);
		screenIndex.put(index, newScreenTemplate(15, true, true, false, false));
		associatedScreensIndex.put(index, associatedScreens);
		
		// UPDATE SAT KEYS SCREEN
		// LAST SCREEN = CRYPTO KEYS SCREEN (INDEX = 15)
		index = 84;
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
		associatedScreens.add(440);
		associatedScreens.add(441);
		associatedScreens.add(442);
		associatedScreens.add(443);
		associatedScreens.add(444);
		associatedScreens.add(445);
		associatedScreens.add(446);
		associatedScreens.add(439);
		screenIndex.put(index, newScreenTemplate(15, true, true, false, false));
		associatedScreensIndex.put(index, associatedScreens);
		
		// TSM SCREEN
		// LAST SCREEN = CRYPTO KEYS SCREEN (INDEX = 15)
		index = 85;
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
		screenIndex.put(index, newScreenTemplate(15, true, true, true, false));
		associatedScreensIndex.put(index, associatedScreens);
		
		// TSM KEY LOAD SCREEN
		// LAST SCREEN = TSM SCREEN (INDEX = 85)
		index = 86;
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
		screenIndex.put(index, newScreenTemplate(85, true, true, false, false));
		
		// TSM KEY LOAD CONT SCREEN
		// LAST SCREEN = TSM SCREEN (INDEX = 85)
		index = 87;
        newPromptArea = 
                "--------------------------------------------------------------------------------\r\n" + 
                "     SELECT OPTION                                                              \r\n" + 
				"                                                                                \r\n" + 
                "                                                                                \r\n";
        screenIndex.put(index, newScreenTemplate(86, true, true, false, true));
        
        // NO TERMINAL KEYS IN TSM SCREEN
        // LAST SCREEN = TSM KEY LOAD SCREEN (INDEX = 86) or TSM KEY LOAD CONT SCREEN (INDEX = 87)
        index = 88;
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
		screenIndex.put(index, newScreenTemplate(15, true, true, false, true));
		associatedScreensIndex.put(index, associatedScreens);
        
		// 89 COPY OF 86
		index = 89;
		screenIndex.put(index, newScreenTemplate(86, true, true, true, true));
		// 90 COPY OF 87
		index = 90;
		screenIndex.put(index, newScreenTemplate(87, true, true, true, true));
		// 91 KEYS SAVED
        index = 91;
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
		screenIndex.put(index, newScreenTemplate(15, true, true, false, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		// SCREEN 92 KEYS LOADED FROM TSM
		index = 92;
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
		screenIndex.put(index, newScreenTemplate(15, true, true, false, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		// OTAR ALL KEYS SCREEN
		// LAST SCREEN = OTAR SCREEN (INDEX = 82)
		index = 93;
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
		screenIndex.put(index, newScreenTemplate(82, true, true, false, false));
		
		// OTAR FUTURE KEYS SCREEN
		// LAST SCREEN = OTAR SCREEN (INDEX = 82)
		index = 94;
		screenIndex.put(index, newScreenTemplate(93, true, true, true, true));
		
		// OTAR SEND BINDING SCREEN
		// LAST SCREEN = OTAR SCREEN (INDEX = 82)
		index = 95;
		screenIndex.put(index, newScreenTemplate(93, true, true, true, true));
		
		// OTAR SINGLE CURRENT SCREEN
		// LAST SCREEN = OTAR SCREEN (INDEX = 82)
		index = 96;
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
		screenIndex.put(index, newScreenTemplate(82, true, true, false, false));
		associatedScreensIndex.put(index, associatedScreens);
		
		// OTAR SINGLE FUTURE SCREEN
		// LAST SCREEN = OTAR SCREEN (INDEX = 82)
		index = 97;
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
		screenIndex.put(index, newScreenTemplate(96, true, true, true, false));
		associatedScreensIndex.put(index, associatedScreens);
		
		// OTAR SWITCH AUTO SCREEN
		// LAST SCREEN = OTAR SCREEN (INDEX = 82)
		index = 98;
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
		screenIndex.put(index, newScreenTemplate(82, true, true, false, false));
		associatedScreensIndex.put(index, associatedScreens);
		
		// AUTO OTAR ON SCREEN
		// LAST SCREEN = OTAR SWITCH AUTO SCREEN (INDEX = 98)
		index = 99;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(15);
		associatedScreens.add(93);
		associatedScreens.add(94);
		associatedScreens.add(95);
		associatedScreens.add(96);
		associatedScreens.add(97);
		associatedScreens.add(98);
		screenIndex.put(index, newScreenTemplate(82, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		// AUTO OTAR OFF SCREEN
		// LAST SCREEN = OTAR SWITCH AUTO SCREEN (INDEX = 98)
		index = 100;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(15);
		associatedScreens.add(93);
		associatedScreens.add(94);
		associatedScreens.add(95);
		associatedScreens.add(96);
		associatedScreens.add(97);
		associatedScreens.add(98);
		screenIndex.put(index, newScreenTemplate(82, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		// SELECT SAT (SINGLE CURRENT DL) SCREEN
		// LAST SCREEN = OTAR SINGLE CURRENT SCREEN (INDEX = 96) OR OTAR SINGLE FUTURE SCREEN (INDEX = 97)
		index = 101;
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
		screenIndex.put(index, newScreenTemplate(82, true, true, false, false));
		associatedScreensIndex.put(index, associatedScreens);
		
		// SELECT SAT (SINGLE CURRENT UL T1) SCREEN
		// LAST SCREEN = OTAR SINGLE CURRENT SCREEN (INDEX = 96) OR OTAR SINGLE FUTURE SCREEN (INDEX = 97)
		index = 102;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(121);
		associatedScreens.add(122);
		associatedScreens.add(123);
		associatedScreens.add(124);
		associatedScreens.add(125);
		associatedScreens.add(126);
		associatedScreens.add(127);
		associatedScreens.add(112);
		screenIndex.put(index, newScreenTemplate(101, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		// SELECT SAT (SINGLE CURRENT UL T2) SCREEN
		// LAST SCREEN = OTAR SINGLE CURRENT SCREEN (INDEX = 96) OR OTAR SINGLE FUTURE SCREEN (INDEX = 97)
		index = 103;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(121);
		associatedScreens.add(122);
		associatedScreens.add(123);
		associatedScreens.add(124);
		associatedScreens.add(125);
		associatedScreens.add(126);
		associatedScreens.add(127);
		associatedScreens.add(113);
		screenIndex.put(index, newScreenTemplate(101, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		// SELECT SAT (SINGLE CURRENT UL ST) SCREEN
		// LAST SCREEN = OTAR SINGLE CURRENT SCREEN (INDEX = 96) OR OTAR SINGLE FUTURE SCREEN (INDEX = 97)
		index = 104;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(121);
		associatedScreens.add(122);
		associatedScreens.add(123);
		associatedScreens.add(124);
		associatedScreens.add(125);
		associatedScreens.add(126);
		associatedScreens.add(127);
		associatedScreens.add(114);
		screenIndex.put(index, newScreenTemplate(101, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		// SELECT SAT (SINGLE CURRENT COVER) SCREEN
		// LAST SCREEN = OTAR SINGLE CURRENT SCREEN (INDEX = 96) OR OTAR SINGLE FUTURE SCREEN (INDEX = 97)
		index = 105;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(121);
		associatedScreens.add(122);
		associatedScreens.add(123);
		associatedScreens.add(124);
		associatedScreens.add(125);
		associatedScreens.add(126);
		associatedScreens.add(127);
		associatedScreens.add(115);
		screenIndex.put(index, newScreenTemplate(101, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		// SELECT SAT (SINGLE FUTURE DL) SCREEN
		// LAST SCREEN = OTAR SINGLE CURRENT SCREEN (INDEX = 96) OR OTAR SINGLE FUTURE SCREEN (INDEX = 97)
		index = 106;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(121);
		associatedScreens.add(122);
		associatedScreens.add(123);
		associatedScreens.add(124);
		associatedScreens.add(125);
		associatedScreens.add(126);
		associatedScreens.add(127);
		associatedScreens.add(116);
		screenIndex.put(index, newScreenTemplate(101, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		// SELECT SAT (SINGLE FUTURE UL T1) SCREEN
		// LAST SCREEN = OTAR SINGLE CURRENT SCREEN (INDEX = 96) OR OTAR SINGLE FUTURE SCREEN (INDEX = 97)
		index = 107;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(121);
		associatedScreens.add(122);
		associatedScreens.add(123);
		associatedScreens.add(124);
		associatedScreens.add(125);
		associatedScreens.add(126);
		associatedScreens.add(127);
		associatedScreens.add(117);
		screenIndex.put(index, newScreenTemplate(101, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		// SELECT SAT (SINGLE FUTURE UL T2) SCREEN
		// LAST SCREEN = OTAR SINGLE CURRENT SCREEN (INDEX = 96) OR OTAR SINGLE FUTURE SCREEN (INDEX = 97)
		index = 108;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(121);
		associatedScreens.add(122);
		associatedScreens.add(123);
		associatedScreens.add(124);
		associatedScreens.add(125);
		associatedScreens.add(126);
		associatedScreens.add(127);
		associatedScreens.add(118);
		screenIndex.put(index, newScreenTemplate(101, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		// SELECT SAT (SINGLE FUTURE UL ST) SCREEN
		// LAST SCREEN = OTAR SINGLE CURRENT SCREEN (INDEX = 96) OR OTAR SINGLE FUTURE SCREEN (INDEX = 97)
		index = 109;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(121);
		associatedScreens.add(122);
		associatedScreens.add(123);
		associatedScreens.add(124);
		associatedScreens.add(125);
		associatedScreens.add(126);
		associatedScreens.add(127);
		associatedScreens.add(119);
		screenIndex.put(index, newScreenTemplate(101, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		// SELECT SAT (SINGLE FUTURE COVER) SCREEN
		// LAST SCREEN = OTAR SINGLE CURRENT SCREEN (INDEX = 96) OR OTAR SINGLE FUTURE SCREEN (INDEX = 97)
		index = 110;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(121);
		associatedScreens.add(122);
		associatedScreens.add(123);
		associatedScreens.add(124);
		associatedScreens.add(125);
		associatedScreens.add(126);
		associatedScreens.add(127);
		associatedScreens.add(120);
		screenIndex.put(index, newScreenTemplate(101, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		// SELECT SAT CONT (SINGLE CURRENT DL) SCREEN
		// LAST SCREEN = SELECT SAT (SINGLE CURRENT DL) SCREEN (INDEX = 101)
		index = 111;
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
		screenIndex.put(index, newScreenTemplate(101, true, true, true, false));
		associatedScreensIndex.put(index, associatedScreens);
		
		// SELECT SAT CONT (SINGLE CURRENT UL T1) SCREEN
		// LAST SCREEN = SELECT SAT (SINGLE CURRENT UL T1) SCREEN (INDEX = 102)
		index = 112;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(102);
		associatedScreens.add(128);
		associatedScreens.add(129);
		associatedScreens.add(130);
		screenIndex.put(index, newScreenTemplate(111, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		// SELECT SAT CONT (SINGLE CURRENT UL T2) SCREEN
		// LAST SCREEN = SELECT SAT (SINGLE CURRENT UL T2) SCREEN (INDEX = 103)
		index = 113;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(103);
		associatedScreens.add(128);
		associatedScreens.add(129);
		associatedScreens.add(130);
		screenIndex.put(index, newScreenTemplate(111, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		// SELECT SAT CONT (SINGLE CURRENT UL ST) SCREEN
		// LAST SCREEN = SELECT SAT (SINGLE CURRENT UL ST) SCREEN (INDEX = 104)
		index = 114;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(104);
		associatedScreens.add(128);
		associatedScreens.add(129);
		associatedScreens.add(130);
		screenIndex.put(index, newScreenTemplate(111, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		// SELECT SAT CONT (SINGLE CURRENT COVER) SCREEN
		// LAST SCREEN = SELECT SAT (SINGLE CURRENT COVER) SCREEN (INDEX = 105)
		index = 115;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(105);
		associatedScreens.add(128);
		associatedScreens.add(129);
		associatedScreens.add(130);
		screenIndex.put(index, newScreenTemplate(111, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		// SELECT SAT CONT (SINGLE FUTURE DL) SCREEN
		// LAST SCREEN = SELECT SAT (SINGLE FUTURE DL) SCREEN (INDEX = 106)
		index = 116;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(106);
		associatedScreens.add(128);
		associatedScreens.add(129);
		associatedScreens.add(130);
		screenIndex.put(index, newScreenTemplate(111, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		// SELECT SAT CONT (SINGLE FUTURE UL T1) SCREEN
		// LAST SCREEN = SELECT SAT (SINGLE FUTURE UL T1) SCREEN (INDEX = 107)
		index = 117;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(107);
		associatedScreens.add(128);
		associatedScreens.add(129);
		associatedScreens.add(130);
		screenIndex.put(index, newScreenTemplate(111, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		// SELECT SAT CONT (SINGLE FUTURE UL T2) SCREEN
		// LAST SCREEN = SELECT SAT (SINGLE FUTURE UL T2) SCREEN (INDEX = 108)
		index = 118;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(108);
		associatedScreens.add(128);
		associatedScreens.add(129);
		associatedScreens.add(130);
		screenIndex.put(index, newScreenTemplate(111, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		// SELECT SAT CONT (SINGLE FUTURE UL ST) SCREEN
		// LAST SCREEN = SELECT SAT (SINGLE FUTURE UL ST) SCREEN (INDEX = 109)
		index = 119;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(109);
		associatedScreens.add(128);
		associatedScreens.add(129);
		associatedScreens.add(130);
		screenIndex.put(index, newScreenTemplate(111, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		// SELECT SAT CONT (SINGLE FUTURE COVER) SCREEN
		// LAST SCREEN = SELECT SAT (SINGLE FUTURE COVER) SCREEN (INDEX = 110)
		index = 120;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(110);
		associatedScreens.add(128);
		associatedScreens.add(129);
		associatedScreens.add(130);
		screenIndex.put(index, newScreenTemplate(111, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		// INDEXES 121 - 130 SELECTED SATS
		// LAST SCREEN = INDEXES 101 - 120
		for (int i = 121; i < 131; i++) {
			index = i;
			screenIndex.put(index, newScreenTemplate(93, true, true, true, true));
		}
		
		// NOT LOGGED ON OTAR SCREEN
		// LAST SCREEN = INDEXES 121 - 130 SELECTED SATS
		index = 131;
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
		screenIndex.put(index, newScreenTemplate(82, true, true, false, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		// NOT LOGGED ON BUILT-IN TEST SCREEN
		// LAST SCREEN = MAIN SCREEN (INDEX = 0)
		index = 132;
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
		screenIndex.put(index, newScreenTemplate(5, true, true, true, false));
		associatedScreensIndex.put(index, associatedScreens);
		
		// UPDATE SAT SCREEN
		// LAST SCREEN = EHF SATS SCREEN (INDEX = 29)
		index = 136;  // NOT REAL VALUE JUST FOR TEST
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
		screenIndex.put(index, newScreenTemplate(29, true, true, true, false));
		associatedScreensIndex.put(index, associatedScreens);
		
		// RED FILL SCREEN
		// LAST SCREEN = EHF SATS SCREEN (INDEX = 29)
		index = 137;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     SELECT KGV-11 KEY TYPE                                                     \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		newOptionsArea = 
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦     EXIT      ¦  ¦      KEK      ¦  ¦  DL TRANSEC   ¦  ¦  UL TRANSEC   ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦     COVER     ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(29);
		associatedScreens.add(219);
		associatedScreens.add(220);
		associatedScreens.add(222);
		associatedScreens.add(224);
		screenIndex.put(index, newScreenTemplate(29, true, true, false, false));
		associatedScreensIndex.put(index, associatedScreens);
		
		// FAULT LOG EMPTY SCREEN
		// LAST SCREEN = FAULY LOG SCREEN (INDEX = 34)
		index = 141;
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
		screenIndex.put(index, newScreenTemplate(34, true, true, false, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		// PRINT ON DEMAND SCREEN
		// LAST PAGE = FAULT LOG SCREEN (INDEX = 34)
		index = 142;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(5);
		associatedScreens.add(141);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(142);   // IS CORRECT
		associatedScreens.add(143);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(144);  // NOT REAL VALUE JUST FOR TEST
		screenIndex.put(index, newScreenTemplate(34, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		// PRINT IMMEDIATE SCREEN
		// LAST PAGE = FAULT LOG SCREEN (INDEX = 34)
		index = 143;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(5);
		associatedScreens.add(141);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(142);   // IS CORRECT
		associatedScreens.add(143);  // NOT REAL VALUE JUST FOR TEST
		associatedScreens.add(144);  // NOT REAL VALUE JUST FOR TEST
		screenIndex.put(index, newScreenTemplate(34, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		// CHANGE PARAMETERS SCREEN
		// LAST SCREEN = POINTING DATA SCREEN (INDEX = 30)
		index = 145;
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
		screenIndex.put(index, newScreenTemplate(30, true, true, false, false));
		associatedScreensIndex.put(index, associatedScreens);
		
		// MANUAL AZIMUTH SCREEN
		// LAST SCREEN = CHANGE PARAMETERS SCREEN (INDEX = 145)
		index = 146;
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
		screenIndex.put(index, newScreenTemplate(145, true, true, false, false));
		associatedScreensIndex.put(index, associatedScreens);
		
		// MANUAL ELEVATION SCREEN
		// LAST SCREEN = CHANGE PARAMETERS SCREEN (INDEX = 145)
		index = 147;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     SELECT ELEVATION OPTION                                                    \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(152);
		associatedScreens.add(157);
		screenIndex.put(index, newScreenTemplate(146, true, true, false, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		// MANUAL RANGE SCREEN
		// LAST SCREEN = CHANGE PARAMETERS SCREEN (INDEX = 145)
		index = 148;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     SELECT RANGE OPTION                                                        \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(153);
		associatedScreens.add(158);
		screenIndex.put(index, newScreenTemplate(146, true, true, false, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		// MANUAL RANGE RATE SCREEN
		// LAST SCREEN = CHANGE PARAMETERS SCREEN (INDEX = 145)
		index = 149;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     SELECT RANGE RATE OPTION                                                   \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(154);
		associatedScreens.add(159);
		screenIndex.put(index, newScreenTemplate(146, true, true, false, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		// ACQ MODE SCREEN
		// LAST SCREEN = CHANGE PARAMETERS SCREEN (INDEX = 145)
		index = 150;
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
		screenIndex.put(index, newScreenTemplate(146, true, true, false, false));
		associatedScreensIndex.put(index, associatedScreens);
		
		// AUTO OPTIONS
		for (int i = 151; i < 156; i++) {
			index = i;
			associatedScreens = new ArrayList<Integer>();
			associatedScreens.add(184);
			associatedScreens.add(146);
			associatedScreens.add(147);
			associatedScreens.add(148);
			associatedScreens.add(149);
			associatedScreens.add(150);
			associatedScreens.add(30);
			screenIndex.put(index, newScreenTemplate(145, true, true, true, true));
			associatedScreensIndex.put(index, associatedScreens);
		}
		
		// MANUAL POINTING DATA 156 - 159
		
		// ENTER AZIMUTH SCREEN
		// LAST SCREEN = MANUAL AZIMUTH SCREEN (INDEX = 146)
		index = 156;
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
		screenIndex.put(index, newScreenTemplate(146, true, true, false, false));
		
		// ENTER ELEVATION SCREEN
		// LAST SCREEN = MANUAL ELEVATION SCREEN (INDEX = 147)
		index = 157;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     ENTER ELEVATION (DEG)                                                      \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		screenIndex.put(index, newScreenTemplate(156, true, true, false, true));
		
		// ENTER RANGE SCREEN
		// LAST SCREEN = MANUAL RANGE SCREEN (INDEX = 148)
		index = 158;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     ENTER RANGE (KM)                                                           \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		screenIndex.put(index, newScreenTemplate(156, true, true, false, true));
		
		// ENTER RANGE RATE SCREEN
		// LAST SCREEN = MANUAL RANGE RATE SCREEN (INDEX = 149)
		index = 159;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     ENTER RANGE RATE (M/S)                                                     \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		screenIndex.put(index, newScreenTemplate(156, true, true, false, true));
		
		
        /*
         * Satellite Authorization Screens
         */
		// UPDATE SELECTED SAT SCREEN
		// LAST SCREEN = UPDATE SAT SCREEN/ UPDATE SCAT SCREEN CONT
		index = 160;
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
		screenIndex.put(index, newScreenTemplate(136, true, true, true, false));
		associatedScreensIndex.put(index, associatedScreens);
		
		index = 161;
		screenIndex.put(index, newScreenTemplate(160, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		index = 162;
		screenIndex.put(index, newScreenTemplate(160, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		index = 163;
		screenIndex.put(index, newScreenTemplate(160, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		index = 164;
		screenIndex.put(index, newScreenTemplate(160, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		index = 165;
		screenIndex.put(index, newScreenTemplate(160, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		index = 166;
		screenIndex.put(index, newScreenTemplate(160, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		index = 167;
		screenIndex.put(index, newScreenTemplate(160, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		index = 168;
		screenIndex.put(index, newScreenTemplate(160, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		index = 169;
		screenIndex.put(index, newScreenTemplate(160, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		// UPDATE SAT SCREEN CONT
		// LAST SCREEN = UPDATE SAT SCREEN
		index = 170;  // NOT REAL VALUE JUST FOR TEST
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
		screenIndex.put(index, newScreenTemplate(136, true, true, true, false));
		associatedScreensIndex.put(index, associatedScreens);

		// AUTHORIZE SAT SCREEN
		// LAST SCREEN = UPDATE SELECTED SAT SCREEN (INDEX = 160)
		index = 171;
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
		screenIndex.put(index, newScreenTemplate(136, true, true, false, false));
		associatedScreensIndex.put(index, associatedScreens);
		
		// REQUEST EPHEMERIS SCREEN
		// LAST SCREEN = UPDATE SELECTED SAT SCREEN
		index = 172;
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
		screenIndex.put(index, newScreenTemplate(136, true, true, false, false));
		
		// VERIFIED NEW EPHEMERIS SCREEN
		// LAST SCREEN = REQUEST EPHEMERIS SCREEN (INDEX = 172)
		index = 174;
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
		screenIndex.put(index, newScreenTemplate(160, true, true, false, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		// NOT LOGGED ON REQUEST EPHEMERIS SCREEN
		// LAST SCREEN = REQUEST EPHEMERIS SCREEN (INDEX = 172)
		index = 175;
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
		screenIndex.put(index, newScreenTemplate(160, true, true, false, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		// SAT AUTHORIZED SCREEN
		// LAST SCREEN = AUTHORIZE SAT SCREEN (163)
		index = 180;
		screenIndex.put(index, newScreenTemplate(160, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		// SAT NOT AUTHORIZED SCEEN
		// LAST SCREEN = AUTHORIZE SAT SCREEN (163)
		index = 181;
		screenIndex.put(index, newScreenTemplate(160, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		// ACQ MODES 182 -183
		// LAST SCREEN = ACQ MODE SCREEN (INDEX = 150)
		for (int i = 182; i < 184; i++) {
			index = i;
			associatedScreens = new ArrayList<Integer>();
			associatedScreens.add(184);
			associatedScreens.add(146);
			associatedScreens.add(147);
			associatedScreens.add(148);
			associatedScreens.add(149);
			associatedScreens.add(150);
			associatedScreens.add(30);
			screenIndex.put(index, newScreenTemplate(145, true, true, true, true));
			associatedScreensIndex.put(index, associatedScreens);
		}
		
		// SAVE MANUAL POINTING DATA CHANGES SCREEN
		// LAST SCREEN = CHANGE PARAMETERS SCREEN (INDEX = 145)
		index = 184;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(4);
		associatedScreens.add(145);
		screenIndex.put(index, newScreenTemplate(30, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		// INVALID POINTING DATA ENTRIES  185 - 188
		index = 185;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     ENTER AZIMUTH (DEG)                                                        \r\n" + 
				"                                                                                \r\n" + 
				"                                 INVALID ENTRY                                  \r\n";
		screenIndex.put(index, newScreenTemplate(156, true, true, false, true));
		
		index = 186;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     ENTER ELEVATION (DEG)                                                      \r\n" + 
				"                                                                                \r\n" + 
				"                                 INVALID ENTRY                                  \r\n";
		screenIndex.put(index, newScreenTemplate(157, true, true, false, true));
		
		index = 187;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     ENTER RANGE (KM)                                                           \r\n" + 
				"                                                                                \r\n" + 
				"                                 INVALID ENTRY                                  \r\n";
		screenIndex.put(index, newScreenTemplate(158, true, true, false, true));
		
		index = 188;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     ENTER RANGE RATE (M/S)                                                     \r\n" + 
				"                                                                                \r\n" + 
				"                                 INVALID ENTRY                                  \r\n";
		screenIndex.put(index, newScreenTemplate(159, true, true, false, true));
		
		// 189 & 190 saved FOR MANUAL POINTING DATA SCREENS
	
		// INVALID NAV DATA SCREENS 201 - 206
		index = 201;
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     ENTER LATITUDE (DEG MIN N/S)                                               \r\n" + 
				"                                                                                \r\n" + 
				"                                 INVALID ENTRY                                  \r\n";
		screenIndex.put(index, newScreenTemplate(71, true, true, false, true));
		
		index = 202;
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     ENTER LONGITUDE (DEG MIN E/W)                                              \r\n" + 
				"                                                                                \r\n" + 
				"                                 INVALID ENTRY                                  \r\n";
		screenIndex.put(index, newScreenTemplate(72, true, true, false, true));
		
		index = 203;
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     ENTER ALTITUDE (FT)                                                        \r\n" + 
				"                                                                                \r\n" + 
				"                                 INVALID ENTRY                                  \r\n";
		screenIndex.put(index, newScreenTemplate(73, true, true, false, true));
		
		index = 204;
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     ENTER HEADING (DEG)                                                        \r\n" + 
				"                                                                                \r\n" + 
				"                                 INVALID ENTRY                                  \r\n";
		screenIndex.put(index, newScreenTemplate(77, true, true, false, true));
		
		index = 205;
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     ENTER PITCH (DEG)                                                          \r\n" + 
				"                                                                                \r\n" + 
				"                                 INVALID ENTRY                                  \r\n";
		screenIndex.put(index, newScreenTemplate(78, true, true, false, true));
		
		index = 206;
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     ENTER ROLL (DEG)                                                           \r\n" + 
				"                                                                                \r\n" + 
				"                                 INVALID ENTRY                                  \r\n";
		screenIndex.put(index, newScreenTemplate(79, true, true, false, true));
		
		/*
		 * COMSEC SCREENS
		 */
		
		// COMSEC OTAR SCREEN
		// LAST SCREEN = COMSEC SCREEN (INDEX = 81)
		index = 207;
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     SELECT OTAR OPTION                                                         \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
        newOptionsArea =
	            "----------------------------------** SECRET **----------------------------------\r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" + 
	            "  ¦     EXIT      ¦  ¦  FUTURE KEYS  ¦  ¦SINGLE CURRENT ¦  ¦ SINGLE FUTURE ¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
	            "   _______________    _______________    _______________    _______________     \r\n" +  
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
	            "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(81);
		associatedScreens.add(210);
		associatedScreens.add(211);
		associatedScreens.add(212);
		screenIndex.put(index, newScreenTemplate(81, true, true, false, false));
        associatedScreensIndex.put(index, associatedScreens);
        
        // COMPROMISE ROLLOVER SCREEN
        // LAST SCREEN = COMSEC SCREEN (INDEX = 81)
        index = 208;
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     VERIFY TO DO COMPROMISE ROLLOVER                                           \r\n" + 
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
        screenIndex.put(index, newScreenTemplate(81, true, true, false, false));
        
        // UPDATE KEYS SCREEN
        // LAST SCREEN = COMSEC SCREEN (INDEX = 81)
        index = 209;
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     ENTER KEY CONSTELLATION AND ID (CCC DDD)                                   \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		screenIndex.put(index, newScreenTemplate(208, true, true, false, true));
        
        // VERIFY COMSEC FUTURE KEYS SCREEN
        // LAST SCREEN = COMSEC OTAR SCREEN (INDEX = 207)
        index = 210;
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     VERIFY TO REQUEST OTAR                                                     \r\n" + 
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
        screenIndex.put(index, newScreenTemplate(207, true, true, false, false));
        
        // COMSEC SINGLE CURRENT SCREEN
        // LAST SCREEN = COMSEC OTAR SCREEN (INDEX = 207)
        index = 211;
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     ENTER KEY ID                                                               \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		screenIndex.put(index, newScreenTemplate(210, true, true, false, true));
		
		// COMSEC SINGLE FUTURE SCREEN
		// LAST SCREEN = COMSEC OTAR SCREEN (INDEX = 207)
		index = 212;
		screenIndex.put(index, newScreenTemplate(211, true, true, true, true));
		
		// COMSEC SINGLE CURRENT INVALID ENTRY SCREEN
		// LAST SCREEN =  COMSEC SINGLE CURRENT SCREEN (INDEX = 211) or COMSEC SINGLE CURRENT INVALID ENTRY SCREEN (INDEX = 213)
		index = 213;
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     ENTER KEY ID                                                               \r\n" + 
				"                                                                                \r\n" + 
				"                                 INVALID ENTRY                                  \r\n";
		screenIndex.put(index, newScreenTemplate(211, true, true, false, true));
        
		// COMSEC SINGLE FUTURE INVALID ENTRY SCREEN
		// LAST SCREEN =  COMSEC SINGLE FUTURE SCREEN (INDEX = 212) or COMSEC SINGLE FUTURE INVALID ENTRY SCREEN (INDEX = 214)
		index = 214;
		screenIndex.put(index, newScreenTemplate(213, true, true, true, true));
        
		// VERIFY COMSEC SINGLE CURRENT SCREEN
		// LAST SCREEN = COMSEC SINGLE CURRENT SCREEN (INDEX = 211) or COMSEC SINGLE CURRENT INVALID ENTRY SCREEN (INDEX = 213)
		index = 215;
		screenIndex.put(index, newScreenTemplate(210, true, true, true, true));
		
		// VERIFY COMSEC SINGLE FUTURE SCREEN
		// LAST SCREEN = COMSEC SINGLE FUTURE SCREEN (INDEX = 212) or COMSEC SINGLE FUTURE INVALID ENTRY SCREEN (INDEX = 214)
		index = 216;
		screenIndex.put(index, newScreenTemplate(210, true, true, true, true));
		
		// COMSEC OTAR NOT LOGGED ON SCREEN
		// LAST SCREEN = COMSEC OTAR SCREEN (INDEX = 207) or COMSEC OTAR NOT LOGGED ON SCREEN (INDEX = 217)
		index = 217;
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     SELECT OTAR OPTION                                                         \r\n" + 
				"                                                                                \r\n" + 
				"                                 NOT LOGGED ON                                  \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(81);
		associatedScreens.add(210);
		associatedScreens.add(211);
		associatedScreens.add(212);
        associatedScreensIndex.put(index, associatedScreens);
		screenIndex.put(index, newScreenTemplate(207, true, true, false, true));
		
		// COMSEC NOT LOGGED ON SCREEN
		// LAST SCREEN = COMSEC SCREEN (INDEX = 81) or COMSEC NOT LOGGED ON SCREEN (INDEX = 218)
		index = 218;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     SELECT OPTION                                                              \r\n" + 
				"                                                                                \r\n" + 
				"                                 NOT LOGGED ON                                  \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(15);
		associatedScreens.add(207);
		associatedScreens.add(208);
		associatedScreens.add(209);
		screenIndex.put(index, newScreenTemplate(81, true, true, false, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		/*
		 * RED FILL SCREENS
		 */
		
		// KEK RED FILL SCREEN
		// LAST SCREEN = RED FILL SCREEN (INDEX = 137)
		index = 219;
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
		associatedScreens.add(227);
		associatedScreens.add(227);
		screenIndex.put(index, newScreenTemplate(137, true, true, false, false));
		associatedScreensIndex.put(index, associatedScreens);
		
		// DL TRANSEC RED FILL SCREEN
		// LAST SCREEN = RED FILL SCREEN (INDEX = 137)
		index = 220;
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     SELECT DL TRANSEC KEY SAT                                                  \r\n" + 
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
		associatedScreens.add(227);
		associatedScreens.add(227);
		associatedScreens.add(227);
		associatedScreens.add(227);
		associatedScreens.add(227);
		associatedScreens.add(227);
		associatedScreens.add(227);
		associatedScreens.add(221);
		screenIndex.put(index, newScreenTemplate(137, true, true, false, false));
		associatedScreensIndex.put(index, associatedScreens);
		
		// DL TRANSEC RED FILL CONT SCREEN
		// LAST SCREEN = DL TRANSEC RED FILL SCREEN (INDEX = 220)
		index = 221;
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
		associatedScreens.add(220);
		associatedScreens.add(227);
		associatedScreens.add(227);
		associatedScreens.add(227);
		screenIndex.put(index, newScreenTemplate(220, true, true, true, false));
		associatedScreensIndex.put(index, associatedScreens);
		
		// UL TRANSEC RED FILL SCREEN
		// LAST SCREEN = RED FILL SCREEN (INDEX = 137)
		index = 222;
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     SELECT UL TRANSEC KEY SAT                                                  \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(226);
		associatedScreens.add(226);
		associatedScreens.add(226);
		associatedScreens.add(226);
		associatedScreens.add(226);
		associatedScreens.add(226);
		associatedScreens.add(226);
		associatedScreens.add(223);
		screenIndex.put(index, newScreenTemplate(220, true, true, false, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		// UL TRANSEC RED FILL CONT SCREEN
		// LAST SCREEN = UL TRANSEC RED FILL SCREEN (INDEX = 222)
		index = 223;
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
		associatedScreens.add(222);
		associatedScreens.add(226);
		associatedScreens.add(226);
		associatedScreens.add(226);
		screenIndex.put(index, newScreenTemplate(222, true, true, true, false));
		associatedScreensIndex.put(index, associatedScreens);
		
		// COVER RED FILL SCREEN
		// LAST SCREEN = RED FILL SCREEN (INDEX = 137)
		index = 224;
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     SELECT COVER KEY SAT                                                       \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(227);
		associatedScreens.add(227);
		associatedScreens.add(227);
		associatedScreens.add(227);
		associatedScreens.add(227);
		associatedScreens.add(227);
		associatedScreens.add(227);
		associatedScreens.add(225);
		screenIndex.put(index, newScreenTemplate(220, true, true, false, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		// COVER RED FILL CONT SCREEN
		// LAST SCREEN = COVER RED FILL SCREEN (INDEX = 224)
		index = 225;
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
		associatedScreens.add(224);
		associatedScreens.add(227);
		associatedScreens.add(227);
		associatedScreens.add(227);
		screenIndex.put(index, newScreenTemplate(224, true, true, true, false));
		associatedScreensIndex.put(index, associatedScreens);
		
		// UL KEY TYPE SCREEN
		// LAST SCREEN = UL TRANSEC RED FILL SCREEN (INDEX = 222) or UL TRANSEC RED FILL CONT SCREEN (INDEX = 223)
		index = 226;
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     SELECT UL TRANSEC KEY TYPE                                                 \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		newOptionsArea =
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦     TAC1      ¦  ¦   STRATEGIC   ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(227);
		associatedScreens.add(227);
		screenIndex.put(index, newScreenTemplate(222, true, true, false, false));
		associatedScreensIndex.put(index, associatedScreens);
		
		// VERIFY RED FILL SCREEN
		// LAST SCREENS = 226, 225, 224, 221, 220, 219
		index = 227;
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     VERIFY WHEN RED FILL COMPLETE                                              \r\n" + 
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
		screenIndex.put(index, newScreenTemplate(137, true, true, false, false));
		
		// KGV-11 KEY LOAD UNSUCCESSFUL SCREEN
		// LAST SCREEN = VERIFY RED FILL SCREEN (INDEX = 227)
		index = 228;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     SELECT KGV-11 KEY TYPE                                                     \r\n" + 
				"                                                                                \r\n" + 
				"                          KGV-11 KEY LOAD UNSUCCESSFUL                           \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(29);
		associatedScreens.add(219);
		associatedScreens.add(220);
		associatedScreens.add(222);
		associatedScreens.add(224);
		screenIndex.put(index, newScreenTemplate(137, true, true, false, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		/*
		 * LOGON PARAMETER SCREENS
		 */
		
		// C2 ROBUSTNESS SCREEN
		// LAST SCREEN = LOGON PARAMETERS SCREEN (INDEX = 28)
		index = 230;
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     SELECT LOGON C2 ROBUSTNESS                                                 \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		newOptionsArea =
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦     HHR 8     ¦  ¦    HHR 16     ¦  ¦    HHR 32     ¦  ¦    HHR 256    ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(234);
		associatedScreens.add(235);
		associatedScreens.add(236);
		associatedScreens.add(237);
		screenIndex.put(index, newScreenTemplate(28, true, true, false, false));
		associatedScreensIndex.put(index, associatedScreens);
		
		// C2 CYCLE PERIOD SCREEN
		// LAST SCREEN = LOGON PARAMETERS SCREEN (INDEX = 28) or // C2 ROBUSTNESS SCREEN (INDEX = 230)
		index = 231;
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     SELECT LOGON C2 CYCLE PERIOD                                               \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		newOptionsArea =
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦      ANY      ¦  ¦    0.48       ¦  ¦    0.96       ¦  ¦    1.92       ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦    2.40       ¦  ¦    3.84       ¦  ¦    4.80       ¦  ¦ MORE OPTIONS  ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(238);
		associatedScreens.add(239);
		associatedScreens.add(240);
		associatedScreens.add(241);
		associatedScreens.add(242);
		associatedScreens.add(243);
		associatedScreens.add(244);
		associatedScreens.add(232);
		screenIndex.put(index, newScreenTemplate(28, true, true, false, false));
		associatedScreensIndex.put(index, associatedScreens);
		
		// C2 CYCLE PERIOD SCREEN CONT
		// LAST SCREEN = C2 CYCLE PERIOD SCREEN (INDEX = 231)
		index = 232;
		newOptionsArea =
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦ PREV OPTIONS  ¦  ¦    7.68       ¦  ¦    9.60       ¦  ¦    19.20      ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦    38.40      ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(231);
		associatedScreens.add(245);
		associatedScreens.add(246);
		associatedScreens.add(247);
		associatedScreens.add(248);
		screenIndex.put(index, newScreenTemplate(231, true, true, true, false));
		associatedScreensIndex.put(index, associatedScreens);
		
		// C3 MODE SCREEN
		// LAST SCREEN = LOGON PARAMETERS SCREEN (INDEX = 28)
		index = 233;
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     SELECT LOGON C3 MODE                                                       \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		newOptionsArea =
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦     MOST      ¦  ¦    MEDIUM     ¦  ¦     LEAST     ¦  ¦    BOMBER     ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦     AUTO      ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(249);
		associatedScreens.add(250);
		associatedScreens.add(251);
		associatedScreens.add(252);
		associatedScreens.add(253);
		screenIndex.put(index, newScreenTemplate(28, true, true, false, false));
		associatedScreensIndex.put(index, associatedScreens);
		
		// C2 ROBUSTNESS OPTIONS 234-237
        for (int i = 234; i < 238; i++) {
        	index = i;
    		associatedScreens = new ArrayList<Integer>();
    		associatedScreens.add(238);
    		associatedScreens.add(239);
    		associatedScreens.add(240);
    		associatedScreens.add(241);
    		associatedScreens.add(242);
    		associatedScreens.add(243);
    		associatedScreens.add(244);
    		associatedScreens.add(232);
    		screenIndex.put(index, newScreenTemplate(231, true, true, true, true));
    		associatedScreensIndex.put(index, associatedScreens);
        }
		
        // LOGON C2 CYCLE PERIOD OPTIONS 238-248
		// VERIFY C2 CYCLE PERIOD SCREEN
        // LAST SCREEN = C2 CYCLE PERIOD SCREEN (INDEX = 231) or C2 CYCLE PERIOD CONT SCREEN (INDEX = 232)
        index = 238;
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     VERIFY TO CHANGE C2 LOGON SLOT                                             \r\n" + 
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
		screenIndex.put(index, newScreenTemplate(231, true, true, false, false));
        for (int i = 239; i < 249; i++) {
        	index = i;
    		screenIndex.put(index, newScreenTemplate(238, true, true, true, true));
        }
		
        // LOGON C3 MODE OPTIONS 249-253
		// VERIFY C3 MODE SCREEN
        // LAST SCREEN = C2 CYCLE PERIOD SCREEN (INDEX = 231) or C2 CYCLE PERIOD CONT SCREEN (INDEX = 232)
        index = 249;
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     VERIFY TO CHANGE C3 MODE                                                   \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		screenIndex.put(index, newScreenTemplate(238, true, true, false, true));
        for (int i = 250; i < 254; i++) {
        	index = i;
    		screenIndex.put(index, newScreenTemplate(249, true, true, true, true));
        }
		
		// SWITCHES SCREEN
		// LAST SCREEN = EHF ACQ/LOGON SCREEN CONT (INEDX = 31)
        index = 254;
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     SELECT SWITCH                                                              \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		newOptionsArea =
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦     EXIT      ¦  ¦XFER IF BLOCKED¦  ¦ ACQ ON DETECT ¦  ¦  STRESS MODE  ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦REPEAT JAM MODE¦  ¦ RECEIVE ONLY  ¦  ¦  SCINT MODE   ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(31);
		associatedScreens.add(255);
		associatedScreens.add(256);
		associatedScreens.add(257);
		associatedScreens.add(258);
		associatedScreens.add(259);
		associatedScreens.add(261);
		screenIndex.put(index, newScreenTemplate(31, true, true, false, false));
		associatedScreensIndex.put(index, associatedScreens);
		
		// XFER IF BLOCKED SCREEN
		// LAST SCREEN = SWITCHES SCREEN (INDEX = 254)
		index = 255;
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     SAT TRANSFER IF BLOCKED                                                    \r\n" + 
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
		associatedScreens.add(262);
		associatedScreens.add(263);
		screenIndex.put(index, newScreenTemplate(254, true, true, false, false));
		associatedScreensIndex.put(index, associatedScreens);
		
		// ACQ ON DETECT SCREEN
		// LAST SCREEN = SWITCHES SCREEN (INDEX = 254)
		index = 256;
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     ACQ ON DETECTION                                                           \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(264);
		associatedScreens.add(265);
		screenIndex.put(index, newScreenTemplate(255, true, true, false, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		// STRESS MODE SCREEN
		// LAST SCREEN = SWITCHES SCREEN (INDEX = 254)
		index = 257;
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     STRESS MODE                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(266);
		associatedScreens.add(267);
		screenIndex.put(index, newScreenTemplate(255, true, true, false, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		// REPEAT JAM MODE SCREEN
		// LAST SCREEN = SWITCHES SCREEN (INDEX = 254)
		index = 258;
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     REPEAT JAM MODE                                                            \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(268);
		associatedScreens.add(269);
		screenIndex.put(index, newScreenTemplate(255, true, true, false, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		// RECEIVE ONLY MODE SCREEN
		// LAST SCREEN = SWITCHES SCREEN (INDEX = 254)
		index = 259;
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     RECEIVE ONLY MODE                                                          \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(270);
		associatedScreens.add(271);
		screenIndex.put(index, newScreenTemplate(255, true, true, false, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		// RECEIVE ONLY MODE NOT LOGGED OFF SCREEN
		// LAST SCREEN = SWITCHES SCREEN (INDEX = 254) or RECEIVE ONLY MODE NOT LOGGED OFF SCREEN (INDEX = 260)
		index = 260;
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     SELECT SWITCH                                                              \r\n" + 
				"                                                                                \r\n" + 
				"                                 NOT LOGGED OFF                                 \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(31);
		associatedScreens.add(255);
		associatedScreens.add(256);
		associatedScreens.add(257);
		associatedScreens.add(258);
		associatedScreens.add(259);
		associatedScreens.add(261);
		screenIndex.put(index, newScreenTemplate(254, true, true, false, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		// SCINT MODE SCREEN
		// LAST SCREEN = SWITCHES SCREEN (INDEX = 254)
		index = 261;
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     SCINTILLATION MODE                                                         \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		newOptionsArea =
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦   MANUAL ON   ¦  ¦     AUTO      ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(272);
		associatedScreens.add(273);
		screenIndex.put(index, newScreenTemplate(254, true, true, false, false));
		associatedScreensIndex.put(index, associatedScreens);
		
		// 262 - 273 SWITCHES OPTIONS
		for (int i = 262; i < 274; i++) {
			index = i;
			associatedScreens = new ArrayList<Integer>();
			associatedScreens.add(31);
			associatedScreens.add(255);
			associatedScreens.add(256);
			associatedScreens.add(257);
			associatedScreens.add(258);
			associatedScreens.add(259);
			associatedScreens.add(261);
			screenIndex.put(index, newScreenTemplate(254, true, true, true, true));
			associatedScreensIndex.put(index, associatedScreens);
		}
		
		// AGILE BEAM STATUS SCREEN
		// LAST SCREEN = EHF ACQ/LOGON SCREEN CONT (INDEX = 31)
		index = 274;
		newMainArea =
				"MAIN:EHF ACQ/TRACK:AGILES-------------------------------------------------------\r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"   LOGON BEAM    "+beam+"                                                             \r\n" + 
				"                                                                                \r\n" + 
				"   CURR AG BEAM               CURR BEST AG BEAM           BEST AG BEAM AT TT/2  \r\n" + 
				"                                                                                \r\n" + 
				"       34                            34                            34           \r\n" +
				"                                                                                \r\n" +
				"   CURR AG RVL ESTSNR        BEAM TRANSITION TIME        BEAM DETERMINE METHOD  \r\n" + 
				"                                                                                \r\n" +
				"         "+String.format("%1$4s",agileBeamNoise)+" dB                     54 SEC                      SLRSL          \r\n" +
				"                                                                                \r\n" + 
				"   EPH PREDICTED BEST FUTURE AG BEAMS        34      17      18                 \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" +
				"                        ADJ AGILE BEAM CROSSOVER TIME                           \r\n" + 
				"                                                                                \r\n" + 
				"   AG BEAM     17         18         33         35                              \r\n" + 
				"                                                                                \r\n" + 
				"   TIME        "+agileBeamTimes[0]+"     "+agileBeamTimes[1]+"     "+agileBeamTimes[2]+"     "+agileBeamTimes[3]+"                          \r\n" + 
				"                                                                                \r\n" +
				"                                                                                \r\n" + 
				"   SERVICES USING AG BEAM     0                                                 \r\n" + 
				"                                                                                \r\n" + 
				"   SERVICES ON CURR AG BEAM   0                                                 \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     SELECT OPTION TO LEAVE DISPLAY                                             \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		newOptionsArea = 
				"----------------------------------** SECRET **----------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦ EXIT DISPLAY  ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(31);
		screenIndex.put(index, newScreenTemplate(3, true, false, false, false));
		associatedScreensIndex.put(index, associatedScreens);
		
		// CHANGE STATUS SCREEN
		// LAST SCREEN = DATA RECORDING SCREEN (INDEX = 20)
		index = 275;
		newOptionsArea =
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦     EXIT      ¦  ¦TOGGLE CATEGORY¦  ¦  DISABLE ALL  ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(20);
		associatedScreens.add(277);
		associatedScreens.add(279);
		screenIndex.put(index, newScreenTemplate(20, true, true, true, false));
		associatedScreensIndex.put(index, associatedScreens);
		
		// CHANGE SETUP SCREEN
		// LAST SCREEN = DATA RECORDING SCREEN (INDEX = 20)
		index = 276;
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     ENTER CATEGORY NUM                                                         \r\n" + 
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
		screenIndex.put(index, newScreenTemplate(275, true, true, false, false));
		
		// TOGGLE CATEGORY SCREEN
		// LAST SCREEN = CHANGE STATUS SCREEN (INDEX = 275)
		index = 277;
		screenIndex.put(index, newScreenTemplate(276, true, true, true, true));
		
		// TOGGLE CATEGORY INVALID ENTRY SCREEN
		// LAST SCREEN = TOGGLE CATEGORY SCREEN (INDEX = 277) or TOGGLE CATEGORY INVALID ENTRY SCREEN (INDEX = 278)
		index = 278;
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     ENTER CATEGORY NUM                                                         \r\n" + 
				"                                                                                \r\n" + 
				"                                 INVALID ENTRY                                  \r\n";
		screenIndex.put(index, newScreenTemplate(276, true, true, false, true));
		
		// ENTER LOCKOUT PASSWORD AGAIN SCREEN
		// LAST SCREEN = KB LOCKOUT SCREEN (INDEX = 18)
		index = 280;
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     ENTER LOCKOUT PASSWORD AGAIN                                               \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		screenIndex.put(index, newScreenTemplate(18, true, true, false, true));
		
		// INVALID KEYBOARD PASSWORD LOCK SCREEN
		// LAST SCREEN = ENTER LOCKOUT PASSWORD AGAIN SCREEN (INDEX = 280) or INVALID KEYBOARD PASSWORD LOCK SCREEN (INDEX = 281)
		index = 281;
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     ENTER LOCKOUT PASSWORD AGAIN                                               \r\n" + 
				"                                                                                \r\n" + 
				"                           INVALID KEYBOARD PASSWORD                            \r\n";
		screenIndex.put(index, newScreenTemplate(18, true, true, false, true));
		
		// UNLOCK KB SCREEN
		// LAST SCREEN = ENTER LOCKOUT PASSWORD AGAIN SCREEN (INDEX = 280) or INVALID KEYBOARD PASSWORD LOCK SCREEN (INDEX = 281)
		index = 282;
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     ENTER PASSWORD TO UNLOCK KB                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		screenIndex.put(index, newScreenTemplate(18, true, true, false, true));
		
		// INVALID KEYBOARD PASSWORD UNLOCK SCREEN
		// LAST SCREEN = UNLOCK KB SCREEN (INDEX = 282) or INVALID KEYBOARD PASSWORD UNLOCK SCREEN (INDEX = 283)
		index = 283;
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     ENTER PASSWORD TO UNLOCK KB                                                \r\n" + 
				"                                                                                \r\n" + 
				"                           INVALID KEYBOARD PASSWORD                            \r\n";
		screenIndex.put(index, newScreenTemplate(18, true, true, false, true));
		
		/*
		 * NET SCREENS
		 */
		
		// INVALID ENTRY NET SCREEN
		// LAST SCREEN = ENTER NET NUMBER SCREEN (INDEX = 21)
		index = 299;
		newPromptArea = 
				"--------------------------------------------------------------------------------\r\n" + 
				"     ENTER NET NUMBER                                                           \r\n" +  
				"                                                                                \r\n" + 
				"                                 INVALID ENTRY                                  \r\n";
		screenIndex.put(index, newScreenTemplate(21, true, true, false, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		// ENTERED NET SCREEN
		// LAST SCREEN = ENTER NET NUMBER SCREEN (INDEX = 21)
		index = 300;
		newMainArea = 
				"MAIN:EHF:NET--------------------------------------------------------------------\r\n" + 
				"                                             SETUP CAPABLE      NO              \r\n" + 
				"         NET NUMBER         "+nets.get(selectedNet).getNetNumber()+"               CONSTELLATION      "+constellation+"             \r\n" + 
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
		screenIndex.put(index, newScreenTemplate(3, true, false, true, false));
        associatedScreensIndex.put(index, associatedScreens);
		
        // ENTERED NET NUMBER SCREEN
        // LAST SCREEN = NETS SCREEN (INDEX = 21)
        index = 301; // NOT REAL VALUE JUST FOR TEST
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
		screenIndex.put(index, newScreenTemplate(300, true, true, true, false));
        associatedScreensIndex.put(index, associatedScreens);
        
        // NET MANAGEMENT SCREEN
        // LAST SCREEN = ENTERED NET SCREEN (INDEX = 300)
        index = 302;
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
		screenIndex.put(index, newScreenTemplate(300, true, true, true, false));
        associatedScreensIndex.put(index, associatedScreens);
        
        // NET PARAMETERS SCREEN
        // LAST SCREEN = ENTERED NET SCREEN (INDEX = 300)
        index = 303;
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
		screenIndex.put(index, newScreenTemplate(300, true, true, true, false));
        associatedScreensIndex.put(index, associatedScreens);
        
        // JOIN NET SCREEN
        // LAST SCREEN = ENERED NET NUMBER SCREEN (INDEX = 301)
        index = 304;
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
        screenIndex.put(index, newScreenTemplate(300, true, true, false, false));
        
        // EXIT NET SCREEN
        // LAST SCREEN = ENERED NET NUMBER SCREEN (INDEX = 301)
        index = 305;
        newPromptArea = 
                "--------------------------------------------------------------------------------\r\n" + 
                "     VERIFY TO EXIT NET                                                         \r\n" + 
                "                                                                                \r\n" + 
                "                                                                                \r\n";
        screenIndex.put(index, newScreenTemplate(304, true, true, false, true));
        
        // TX BEAM C3 MODE SCREEN
        // LAST SCREEN = ENTERED NET NUMBER SCREEN (INDEX = 301)
        
        
        // NOT LOGGED ON JOIN NET SCREEN
        // LAST SCREEN = ENTERED NET NUMBER SCREEN (INDEX = 301)
        index = 309;
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
		screenIndex.put(index, newScreenTemplate(301, true, true, false, true));
        associatedScreensIndex.put(index, associatedScreens);
        
        // 310 onwards for nets (delete later)
        // SAVE NET PARAMETERS SCREEN
        // LAST SCREEN = NET PARAMETERS SCREEN (INDEX = 303)
        index = 314;
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
        screenIndex.put(index, newScreenTemplate(303, true, true, false, false));
        
        // NET PORTS SCREEN
        // LAST SCREEN = NET PARAMETERS SCREEN (INDEX = 303)
        index = 315;
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
		screenIndex.put(index, newScreenTemplate(303, true, true, true, false));
        associatedScreensIndex.put(index, associatedScreens);
        
        // TERMINAL PARAMETERS SCREEN
        // LAST SCREEN = NET PARAMETERS SCREEN (INDEX = 303)
        index = 316;
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
		screenIndex.put(index, newScreenTemplate(303, true, true, false, false));
        associatedScreensIndex.put(index, associatedScreens);
        
        // SERV PARAMS 1 SCREEN
        // LAST SCREEN = NET PARAMETERS SCREEN (INDEX = 303)
        index = 317;
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
		screenIndex.put(index, newScreenTemplate(303, true, true, false, false));
        associatedScreensIndex.put(index, associatedScreens);
        
        // SERV PARAMS 2 SCREEN
        // LAST SCREEN = NET PARAMETERS SCREEN (INDEX = 303)
        index = 318;
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
		screenIndex.put(index, newScreenTemplate(317, true, true, true, false));
        associatedScreensIndex.put(index, associatedScreens);
        
        // INTERRUPTIBLE NET PARAM SCREEN
        // LAST SCREEN = NET PORTS SCREEN (INDEX = 315)
        index = 321;
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
		screenIndex.put(index, newScreenTemplate(315, true, true, true, false));
        associatedScreensIndex.put(index, associatedScreens);
        
        // TX PORT NET PARAM SCREEN
        // LAST SCREEN = NET PORTS SCREEN (INDEX = 315)
        index = 322;
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
		screenIndex.put(index, newScreenTemplate(315, true, true, false, false));
        associatedScreensIndex.put(index, associatedScreens);
        
        // INTERRUPTIBLE YES NET PARAM SCREEN
        // LAST SCREEN = INTERRUPTIBLE NET PARAM SCREEN (INDEX = 321)
        index = 323;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(321);
		associatedScreens.add(322);
		screenIndex.put(index, newScreenTemplate(315, true, true, true, true));
        associatedScreensIndex.put(index, associatedScreens);
        
        // INTERRUPTIBLE NO NET PARAM SCREEN 
        // LAST SCREEN = INTERRUPTIBLE NET PARAM SCREEN (INDEX = 321)
        index = 324;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(321);
		associatedScreens.add(322);
		screenIndex.put(index, newScreenTemplate(315, true, true, true, true));
        associatedScreensIndex.put(index, associatedScreens);
        
        // TX BEAM TERMINAL PARAMETER SCREEN
        // LAST SCREEN = TERMINAL PARAMETERS SCREEN (INDEX = 316)
        index = 325;
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
		screenIndex.put(index, newScreenTemplate(316, true, true, false, false));
        associatedScreensIndex.put(index, associatedScreens);
        
        // TX BEAM C3 MODE SCREEN
        // LAST SCREEN = TERMINAL PARAMETERS SCREEN (INDEX = 316)
        index = 326;
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
		screenIndex.put(index, newScreenTemplate(316, true, true, false, false));
        associatedScreensIndex.put(index, associatedScreens);
        
        // UL SLOT SCREEN
        // LAST SCREEN = TERMINAL PARAMETERS SCREEN (INDEX = 316)
        index = 327;
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
		screenIndex.put(index, newScreenTemplate(316, true, true, false, false));
        associatedScreensIndex.put(index, associatedScreens);
        
        // RX BEAM TERMINAL PARAMETER SCREEN
        // LAST SCREEN = TERMINAL PARAMETERS SCREEN (INDEX = 316)
        index = 328;
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
		screenIndex.put(index, newScreenTemplate(325, true, true, false, true));
        associatedScreensIndex.put(index, associatedScreens);
        
        // AUTO JOIN NET PARAM SCREEN
        // LAST SCREEN = TERMINAL PARAMETERS SCREEN (INDEX = 316)
        index = 329;
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
		screenIndex.put(index, newScreenTemplate(316, true, true, false, false));
        associatedScreensIndex.put(index, associatedScreens);
        
        // SELECTED TX BEAM SCREENS
        // LAST SCREEN = TX BEAM TERMINAL PARAMETER SCREEN (INDEX = 325)
        index = 330;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(325);
		associatedScreens.add(326);
		associatedScreens.add(327);
		associatedScreens.add(328);
		associatedScreens.add(329);
		screenIndex.put(index, newScreenTemplate(316, true, true, true, true));
        associatedScreensIndex.put(index, associatedScreens);
        
        index = 331;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(325);
		associatedScreens.add(326);
		associatedScreens.add(327);
		associatedScreens.add(328);
		associatedScreens.add(329);
		screenIndex.put(index, newScreenTemplate(316, true, true, true, true));
        associatedScreensIndex.put(index, associatedScreens);
        
        index = 332;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(325);
		associatedScreens.add(326);
		associatedScreens.add(327);
		associatedScreens.add(328);
		associatedScreens.add(329);
		screenIndex.put(index, newScreenTemplate(316, true, true, true, true));
        associatedScreensIndex.put(index, associatedScreens);
        
        index = 333;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(325);
		associatedScreens.add(326);
		associatedScreens.add(327);
		associatedScreens.add(328);
		associatedScreens.add(329);
		screenIndex.put(index, newScreenTemplate(316, true, true, true, true));
        associatedScreensIndex.put(index, associatedScreens);
        
        index = 334;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(325);
		associatedScreens.add(326);
		associatedScreens.add(327);
		associatedScreens.add(328);
		associatedScreens.add(329);
		screenIndex.put(index, newScreenTemplate(316, true, true, true, true));
        associatedScreensIndex.put(index, associatedScreens);
        
        index = 335;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(325);
		associatedScreens.add(326);
		associatedScreens.add(327);
		associatedScreens.add(328);
		associatedScreens.add(329);
		screenIndex.put(index, newScreenTemplate(316, true, true, true, true));
        associatedScreensIndex.put(index, associatedScreens);
        
        // TX BEAM C3 MODE
        index = 336;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(325);
		associatedScreens.add(326);
		associatedScreens.add(327);
		associatedScreens.add(328);
		associatedScreens.add(329);
		screenIndex.put(index, newScreenTemplate(316, true, true, true, true));
        associatedScreensIndex.put(index, associatedScreens);
        
        index = 337;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(325);
		associatedScreens.add(326);
		associatedScreens.add(327);
		associatedScreens.add(328);
		associatedScreens.add(329);
		screenIndex.put(index, newScreenTemplate(316, true, true, true, true));
        associatedScreensIndex.put(index, associatedScreens);
        
        index = 338;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(325);
		associatedScreens.add(326);
		associatedScreens.add(327);
		associatedScreens.add(328);
		associatedScreens.add(329);
		screenIndex.put(index, newScreenTemplate(316, true, true, true, true));
        associatedScreensIndex.put(index, associatedScreens);
        
        index = 339;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(325);
		associatedScreens.add(326);
		associatedScreens.add(327);
		associatedScreens.add(328);
		associatedScreens.add(329);
		screenIndex.put(index, newScreenTemplate(316, true, true, true, true));
        associatedScreensIndex.put(index, associatedScreens);
        
        // 340 - 346 UL SLOT OPTIONS
        
        index = 340;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(325);
		associatedScreens.add(326);
		associatedScreens.add(327);
		associatedScreens.add(328);
		associatedScreens.add(329);
		screenIndex.put(index, newScreenTemplate(316, true, true, true, true));
        associatedScreensIndex.put(index, associatedScreens);
        
        index = 341;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(325);
		associatedScreens.add(326);
		associatedScreens.add(327);
		associatedScreens.add(328);
		associatedScreens.add(329);
		screenIndex.put(index, newScreenTemplate(316, true, true, true, true));
        associatedScreensIndex.put(index, associatedScreens);
        
        index = 342;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(325);
		associatedScreens.add(326);
		associatedScreens.add(327);
		associatedScreens.add(328);
		associatedScreens.add(329);
		screenIndex.put(index, newScreenTemplate(316, true, true, true, true));
        associatedScreensIndex.put(index, associatedScreens);
        
        index = 343;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(325);
		associatedScreens.add(326);
		associatedScreens.add(327);
		associatedScreens.add(328);
		associatedScreens.add(329);
		screenIndex.put(index, newScreenTemplate(316, true, true, true, true));
        associatedScreensIndex.put(index, associatedScreens);
        
        index = 344;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(325);
		associatedScreens.add(326);
		associatedScreens.add(327);
		associatedScreens.add(328);
		associatedScreens.add(329);
		screenIndex.put(index, newScreenTemplate(316, true, true, true, true));
        associatedScreensIndex.put(index, associatedScreens);
        
        index = 345;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(325);
		associatedScreens.add(326);
		associatedScreens.add(327);
		associatedScreens.add(328);
		associatedScreens.add(329);
		screenIndex.put(index, newScreenTemplate(316, true, true, true, true));
        associatedScreensIndex.put(index, associatedScreens);
        
        index = 346;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(325);
		associatedScreens.add(326);
		associatedScreens.add(327);
		associatedScreens.add(328);
		associatedScreens.add(329);
		screenIndex.put(index, newScreenTemplate(316, true, true, true, true));
        associatedScreensIndex.put(index, associatedScreens);
        
        // RX BEAMS
        index = 347;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(325);
		associatedScreens.add(326);
		associatedScreens.add(327);
		associatedScreens.add(328);
		associatedScreens.add(329);
		screenIndex.put(index, newScreenTemplate(316, true, true, true, true));
        associatedScreensIndex.put(index, associatedScreens);
        
        index = 348;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(325);
		associatedScreens.add(326);
		associatedScreens.add(327);
		associatedScreens.add(328);
		associatedScreens.add(329);
		screenIndex.put(index, newScreenTemplate(316, true, true, true, true));
        associatedScreensIndex.put(index, associatedScreens);
        
        index = 349;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(325);
		associatedScreens.add(326);
		associatedScreens.add(327);
		associatedScreens.add(328);
		associatedScreens.add(329);
		screenIndex.put(index, newScreenTemplate(316, true, true, true, true));
        associatedScreensIndex.put(index, associatedScreens);
        
        index = 350;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(325);
		associatedScreens.add(326);
		associatedScreens.add(327);
		associatedScreens.add(328);
		associatedScreens.add(329);
		screenIndex.put(index, newScreenTemplate(316, true, true, true, true));
        associatedScreensIndex.put(index, associatedScreens);
        
        index = 351;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(325);
		associatedScreens.add(326);
		associatedScreens.add(327);
		associatedScreens.add(328);
		associatedScreens.add(329);
		screenIndex.put(index, newScreenTemplate(316, true, true, true, true));
        associatedScreensIndex.put(index, associatedScreens);
        
        index = 352;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(325);
		associatedScreens.add(326);
		associatedScreens.add(327);
		associatedScreens.add(328);
		associatedScreens.add(329);
		screenIndex.put(index, newScreenTemplate(316, true, true, true, true));
        associatedScreensIndex.put(index, associatedScreens);
        
        // NET AUTO JOIN 353 AND 354
        index = 353;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(325);
		associatedScreens.add(326);
		associatedScreens.add(327);
		associatedScreens.add(328);
		associatedScreens.add(329);
		screenIndex.put(index, newScreenTemplate(316, true, true, true, true));
        associatedScreensIndex.put(index, associatedScreens);
        
        index = 354;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(325);
		associatedScreens.add(326);
		associatedScreens.add(327);
		associatedScreens.add(328);
		associatedScreens.add(329);
		screenIndex.put(index, newScreenTemplate(316, true, true, true, true));
        associatedScreensIndex.put(index, associatedScreens);
        
        // NAME SERVICE PARAMETER SCREEN
        // LAST SCREEN = SERV PARAMS 1 SCREEN (INDEX = 317)
        index = 355;
        newPromptArea = 
                "--------------------------------------------------------------------------------\r\n" + 
                "     ENTER NET NAME                                                             \r\n" + 
                "                                                                                \r\n" + 
                "                                                                                \r\n";
		screenIndex.put(index, newScreenTemplate(314, true, true, false, true));

        // NET CONSTELLATION SERVICE PARAMETER SCREEN
		// LAST SCREEN = SERV PARAMS 1 SCREEN (INDEX = 317)
		index = 356;
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
		screenIndex.put(index, newScreenTemplate(317, true, true, false, false));
        associatedScreensIndex.put(index, associatedScreens);
        
        // SERVICE ID SERVICE PARAMETER SCREEN
        // LAST SCREEN = SERV PARAMS 1 SCREEN (INDEX = 317)
        index = 357;
        newPromptArea = 
                "--------------------------------------------------------------------------------\r\n" + 
                "     ENTER SERVICE ID                                                           \r\n" + 
                "                                                                                \r\n" + 
                "                                                                                \r\n";
        screenIndex.put(index, newScreenTemplate(314, true, true, false, true));
        
        // SERVICE TYPE SERVICE PARAMETER SCREEN
        // LAST SCREEN = SERV PARAMS 1 SCREEN (INDEX = 317)
        index = 358;
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
		screenIndex.put(index, newScreenTemplate(317, true, true, false, false));
        associatedScreensIndex.put(index, associatedScreens);
        
        // DATA RATE SERVICE PARAMETER SCREEN
        // LAST SCREEN = SERV PARAMS 1 SCREEN (INDEX = 317)
        index = 359;
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
		screenIndex.put(index, newScreenTemplate(317, true, true, false, false));
        associatedScreensIndex.put(index, associatedScreens);
        
        // UL SUBCHANNEL SERVICE PARAMETER SCREEN
        // LAST SCREEN = SERV PARAMS 1 SCREEN (INDEX = 317)
        index = 360;
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
		screenIndex.put(index, newScreenTemplate(317, true, true, false, false));
        associatedScreensIndex.put(index, associatedScreens);
        
        // INTERLEAVE SERVICE PARAMETER SCREEN
        // LAST SCREEN = SERV PARAMS 1 SCREEN (INDEX = 317)
        index = 361;
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
		screenIndex.put(index, newScreenTemplate(317, true, true, false, false));
        associatedScreensIndex.put(index, associatedScreens);
        
        // NET CONSTELLATIONS 362-365
        for (int i = 362; i < 366; i++) {
        	index = i;
    		associatedScreens = new ArrayList<Integer>();
    		associatedScreens.add(303);
    		associatedScreens.add(355);
    		associatedScreens.add(356);
    		associatedScreens.add(357);
    		associatedScreens.add(358);
    		associatedScreens.add(359);
    		associatedScreens.add(360);
    		associatedScreens.add(361);
    		screenIndex.put(index, newScreenTemplate(317, true, true, true, true));
            associatedScreensIndex.put(index, associatedScreens);
        }
        
        // SERVICE TYPES 366-380
        for (int i = 366; i < 381; i++) {
        	index = i;
    		associatedScreens = new ArrayList<Integer>();
    		associatedScreens.add(303);
    		associatedScreens.add(355);
    		associatedScreens.add(356);
    		associatedScreens.add(357);
    		associatedScreens.add(358);
    		associatedScreens.add(359);
    		associatedScreens.add(360);
    		associatedScreens.add(361);
    		screenIndex.put(index, newScreenTemplate(317, true, true, true, true));
            associatedScreensIndex.put(index, associatedScreens);
        }
        
        // SERVICE TYPE SERVICE PARAMETER SCREEN CONT
        // LAST SCREEN = SERVICE TYPE SERVICE PARAMETER SCREEN (INDEX = 358)
        index = 381;
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
		screenIndex.put(index, newScreenTemplate(358, true, true, true, false));
        associatedScreensIndex.put(index, associatedScreens);
        
        // SERVICE TYPE SERVICE PARAMETER SCREEN CONT CONT
        // LAST SCREEN = SERVICE TYPE SERVICE PARAMETER SCREEN CONT (INDEX = 381)
        index = 382;
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
		screenIndex.put(index, newScreenTemplate(358, true, true, true, false));
        associatedScreensIndex.put(index, associatedScreens);
		
        // DATA RATES 383 - 388 (CAN COMBINE THE ONES BELOW!!!!!!)
        for (int i = 383; i < 389; i++) {
        	index = i;
    		associatedScreens = new ArrayList<Integer>();
    		associatedScreens.add(303);
    		associatedScreens.add(355);
    		associatedScreens.add(356);
    		associatedScreens.add(357);
    		associatedScreens.add(358);
    		associatedScreens.add(359);
    		associatedScreens.add(360);
    		associatedScreens.add(361);
    		screenIndex.put(index, newScreenTemplate(317, true, true, true, true));
            associatedScreensIndex.put(index, associatedScreens);
        }
        
        // UL SUBCHANNELS 389-390
        for (int i = 389; i < 391; i++) {
        	index = i;
    		associatedScreens = new ArrayList<Integer>();
    		associatedScreens.add(303);
    		associatedScreens.add(355);
    		associatedScreens.add(356);
    		associatedScreens.add(357);
    		associatedScreens.add(358);
    		associatedScreens.add(359);
    		associatedScreens.add(360);
    		associatedScreens.add(361);
    		screenIndex.put(index, newScreenTemplate(317, true, true, true, true));
            associatedScreensIndex.put(index, associatedScreens);
        }
        
        // INTERLEAVES 391-395
        for (int i = 391; i < 396; i++) {
        	index = i;
    		associatedScreens = new ArrayList<Integer>();
    		associatedScreens.add(303);
    		associatedScreens.add(355);
    		associatedScreens.add(356);
    		associatedScreens.add(357);
    		associatedScreens.add(358);
    		associatedScreens.add(359);
    		associatedScreens.add(360);
    		associatedScreens.add(361);
    		screenIndex.put(index, newScreenTemplate(317, true, true, true, true));
            associatedScreensIndex.put(index, associatedScreens);
        }
        
        // BMT CONTROLLED SERVICE PARAMETER SCREEN
        // LAST SCREEN = SERV PARAMS 2 SCREEN (INDEX = 318)
        index = 396;
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
		screenIndex.put(index, newScreenTemplate(318, true, true, false, false));
        associatedScreensIndex.put(index, associatedScreens);
        
        // CINC SERVICE PARAMETER SCREEN
        // LAST SCREEN = SERV PARAMS 2 SCREEN (INDEX = 318)
        index = 397;
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
		screenIndex.put(index, newScreenTemplate(318, true, true, false, false));
        associatedScreensIndex.put(index, associatedScreens);
        
        // CRYPTO KEY SERVICE PARAMETER SCREEN
        // LAST SCREEN = SERV PARAMS 2 SCREEN (INDEX = 318)
        index = 398;
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
        screenIndex.put(index, newScreenTemplate(318, true, true, false, false));
        
        // PRECEDENCE SERVICE PARAMETER SCREEN
        // LAST SCREEN = SERV PARAMS 2 SCREEN (INDEX = 318)
        index = 399;
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
		screenIndex.put(index, newScreenTemplate(318, true, true, false, false));
        associatedScreensIndex.put(index, associatedScreens);
        
        // SUBFS SERVICE PARAMETER SCREEN
        // LAST SCREEN = SERV PARAMS 2 SCREEN (INDEX = 318)
        index = 400;
        newPromptArea = 
                "--------------------------------------------------------------------------------\r\n" + 
                "     SELECT WHETHER SUBFS                                                       \r\n" + 
                "                                                                                \r\n" + 
                "                                                                                \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(419);
		associatedScreens.add(420);
		screenIndex.put(index, newScreenTemplate(396, true, true, false, true));
        associatedScreensIndex.put(index, associatedScreens);
        
        // BOMBER C3 CAPABLE SERVICE PARAMETER SCREEN
        // LAST SCREEN = SERV PARAMS 2 SCREEN (INDEX = 318)
        index = 401;
        newPromptArea = 
                "--------------------------------------------------------------------------------\r\n" + 
                "     SELECT WHETHER BOMBER C3 CAPABLE                                           \r\n" + 
                "                                                                                \r\n" + 
                "                                                                                \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(421);
		associatedScreens.add(422);
		screenIndex.put(index, newScreenTemplate(400, true, true, false, true));
        associatedScreensIndex.put(index, associatedScreens);
        
        // CINC SERVICE PARAMETER SCREEN CONT
        // LAST SCREEN = CINC SERVICE PARAMETER SCREEN (INDEX = 397)
        index = 402;
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
		screenIndex.put(index, newScreenTemplate(397, true, true, true, false));
        associatedScreensIndex.put(index, associatedScreens);
        
        // BMT CONTROLLED? 403-404
        for (int i = 403; i < 405; i++) {
        	index = i;
    		associatedScreens = new ArrayList<Integer>();
    		associatedScreens.add(303);
    		associatedScreens.add(396);
    		associatedScreens.add(397);
    		associatedScreens.add(398);
    		associatedScreens.add(399);
    		associatedScreens.add(400);
    		associatedScreens.add(401);
    		screenIndex.put(index, newScreenTemplate(318, true, true, true, true));
            associatedScreensIndex.put(index, associatedScreens);
        }
        
        // CINC 405 - 414
        for (int i = 405; i < 415; i++) {
        	index = i;
    		associatedScreens = new ArrayList<Integer>();
    		associatedScreens.add(303);
    		associatedScreens.add(396);
    		associatedScreens.add(397);
    		associatedScreens.add(398);
    		associatedScreens.add(399);
    		associatedScreens.add(400);
    		associatedScreens.add(401);
    		screenIndex.put(index, newScreenTemplate(318, true, true, true, true));
            associatedScreensIndex.put(index, associatedScreens);
        }
        
        // PRECEDENCE 415-418
        for (int i = 415; i < 419; i++) {
        	index = i;
    		associatedScreens = new ArrayList<Integer>();
    		associatedScreens.add(303);
    		associatedScreens.add(396);
    		associatedScreens.add(397);
    		associatedScreens.add(398);
    		associatedScreens.add(399);
    		associatedScreens.add(400);
    		associatedScreens.add(401);
    		screenIndex.put(index, newScreenTemplate(318, true, true, true, true));
            associatedScreensIndex.put(index, associatedScreens);
        }
        
        // SUBFS 419-420
        for (int i = 419; i < 421; i++) {
        	index = i;
    		associatedScreens = new ArrayList<Integer>();
    		associatedScreens.add(303);
    		associatedScreens.add(396);
    		associatedScreens.add(397);
    		associatedScreens.add(398);
    		associatedScreens.add(399);
    		associatedScreens.add(400);
    		associatedScreens.add(401);
    		screenIndex.put(index, newScreenTemplate(318, true, true, true, true));
            associatedScreensIndex.put(index, associatedScreens);
        }
        
        // BOMBER C3 CAPABLE? 421-422
        for (int i = 421; i < 423; i++) {
        	index = i;
    		associatedScreens = new ArrayList<Integer>();
    		associatedScreens.add(303);
    		associatedScreens.add(396);
    		associatedScreens.add(397);
    		associatedScreens.add(398);
    		associatedScreens.add(399);
    		associatedScreens.add(400);
    		associatedScreens.add(401);
    		screenIndex.put(index, newScreenTemplate(318, true, true, true, true));
            associatedScreensIndex.put(index, associatedScreens);
        }
        
        // NET ACTIVE JOIN NET SCREEN
        // LAST SCREEN = ENTERED NET NUMBER SCREEN (INDEX = 301)
        index = 423;
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
		screenIndex.put(index, newScreenTemplate(301, true, true, false, true));
        associatedScreensIndex.put(index, associatedScreens);
        
        // NET NOT ACTIVE EXIT NET SCREEN
        // LAST SCREEN = ENTERED NET NUMBER SCREEN (INDEX = 301)
        index = 424;
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
		screenIndex.put(index, newScreenTemplate(301, true, true, false, true));
        associatedScreensIndex.put(index, associatedScreens);
        
        // NO EXIT WHILE PENDING NET SCREEN
        // LAST SCREEN = ENTERED NET NUMBER SCREEN (INDEX = 301)
        index = 425;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     SELECT OPTION                                                              \r\n" + 
				"                                                                                \r\n" + 
				"                             NO EXIT WHILE PENDING                              \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(300);
		associatedScreens.add(304);
		associatedScreens.add(305);
		associatedScreens.add(306);
		associatedScreens.add(307);
		associatedScreens.add(308);
		screenIndex.put(index, newScreenTemplate(301, true, true, false, true));
        associatedScreensIndex.put(index, associatedScreens);
        
        // UPDATE MIL CON KEYS SCREEN
        // LAST SCREEN = UPDATE CON KEYS (INDEX = 83)
        index = 426;
		newOptionsArea =
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦     EXIT      ¦  ¦    CURRENT    ¦  ¦    FUTURE     ¦  ¦  DELETE KEY   ¦    \r\n" + 
				"  ¦               ¦  ¦  CANISTER ID  ¦  ¦  CANISTER ID  ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦DELETE CON KEYS¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(15);
		associatedScreens.add(428);
		associatedScreens.add(429);
		associatedScreens.add(430);
		associatedScreens.add(431);
		screenIndex.put(index, newScreenTemplate(83, true, true, true, false));
        associatedScreensIndex.put(index, associatedScreens);
        
        // UPDATE UFO CON KEYS SCREEN
        // LAST SCREEN = UPDATE CON KEYS (INDEX = 83)
        index = 427;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(15);
		associatedScreens.add(428);
		associatedScreens.add(429);
		associatedScreens.add(430);
		associatedScreens.add(431);
		screenIndex.put(index, newScreenTemplate(426, true, true, true, true));
        associatedScreensIndex.put(index, associatedScreens);
        
        // ENTER CURRENT CANISTER ID SCREEN
        // LAST SCREEN = UPDATE MIL CON KEYS SCREEN (INDEX = 426) OR UPDATE UFO CON KEYS SCREEN (INDEX = 427)
        index = 428;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     ENTER CANISTER ID                                                          \r\n" + 
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
		screenIndex.put(index, newScreenTemplate(426, true, true, false, false));
		
		// ENTER FUTURE CANISTER ID SCREEN
		// LAST SCREEN = UPDATE MIL CON KEYS SCREEN (INDEX = 426) OR UPDATE UFO CON KEYS SCREEN (INDEX = 427)
		index = 429;
		screenIndex.put(index, newScreenTemplate(428, true, true, true, true));
		
		// DELETE CON KEY SCREEN
		// LAST SCREEN = UPDATE MIL CON KEYS SCREEN (INDEX = 426) OR UPDATE UFO CON KEYS SCREEN (INDEX = 427)
		index = 430;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     SELECT KEY TO DELETE                                                       \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		newOptionsArea =
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦   CHAINING    ¦  ¦  UNIVERSAL A  ¦  ¦  UNIVERSAL B  ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(435);
		associatedScreens.add(435);
		associatedScreens.add(435);
		screenIndex.put(index, newScreenTemplate(426, true, true, false, false));
        associatedScreensIndex.put(index, associatedScreens);
		
        // DELETE CON KEYS SCREEN
        // LAST SCREEN = UPDATE MIL CON KEYS SCREEN (INDEX = 426)
        index = 431;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     VERIFY TO DELETE ALL CON KEYS                                              \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		screenIndex.put(index, newScreenTemplate(428, true, true, false, true));
        
		// INVALID ENTRY CURRENT CANISTER ID SCREEN
		// LAST SCREEN = ENTER CURRENT CANISTER ID SCREEN (INDEX = 428) OR INVALID ENTRY CURRENT CANISTER ID SCREEN (INDEX = 432)
		index = 432;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     ENTER CANISTER ID                                                          \r\n" + 
				"                                                                                \r\n" + 
				"                                 INVALID ENTRY                                  \r\n";
		screenIndex.put(index, newScreenTemplate(428, true, true, false, true));
		
		// INVALID ENTRY FUTURE CANISTER ID SCREEN
		// LAST SCREEN = ENTER FUTURE CANISTER ID SCREEN (INDEX = 429) OR INVALID ENTRY FUTURE CANISTER ID SCREEN (INDEX = 433)
		index = 433;
		screenIndex.put(index, newScreenTemplate(432, true, true, true, true));
		
        // VERIFY TO CHANGE CANISTER ID SCREEN
		// LAST SCREEN = ENTER CANISTER ID SCREEN (INDEX = 428) OR INVALID ENTRY CANISTER ID SCREEN (INDEX = 431)
		index = 434;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     VERIFY TO CHANGE CANISTER ID                                               \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		screenIndex.put(index, newScreenTemplate(428, true, true, false, true));
		
		// VERIFY TO DELETE CON KEY SCEEN
		// LAST SCREEN = DELETE CON KEY SCREEN (INDEX = 430)
		index = 435;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     VERIFY TO DELETE KEY                                                       \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		screenIndex.put(index, newScreenTemplate(428, true, true, false, true));
		
		// VERIFY TO DELETE CON KEY WORKING SRCEEN
		// LAST SCREEN = VERIFY TO DELETE CON KEY SCREEN (INDEX = 435) OR VERIFY TO DELETE CON KEY WORKING CONT SCREEN (INDEX = 437)
		index = 436;
		screenIndex.put(index, newScreenTemplate(435, true, true, true, true));
		
		// VERIFY TO DELETE CON KEY WORKING CONT SCREEN
		// LAST SCREEN = VERIFY TO DELETE CON KEY WORKING SRCEEN (INDEX = 436)
		index = 437;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     VERIFY TO DELETE KEY                                                       \r\n" + 
				"                                    WORKING                                     \r\n" + 
				"                                                                                \r\n";
		screenIndex.put(index, newScreenTemplate(435, true, true, false, true));
		
		// CON KEY NOT FOUND SCREEN
		// LAST SCREEN = VERIFY TO DELETE CON KEY WORKING SRCEEN (INDEX = 436) OR VERIFY TO DELETE CON KEY WORKING CONT SCREEN (INDEX = 437)
		index = 438;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     SELECT OPTION                                                              \r\n" + 
				"                                                                                \r\n" + 
				"                                 KEY NOT FOUND                                  \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(15);
		associatedScreens.add(428);
		associatedScreens.add(429);
		associatedScreens.add(430);
		associatedScreens.add(431);
		screenIndex.put(index, newScreenTemplate(426, true, true, false, true));
        associatedScreensIndex.put(index, associatedScreens);
		
        // UPDATE SAT KEYS CONT SCREEN
        // LAST SCREEN = UPDATE SAT KEYS SCREEN (INDEX = 84)
        index = 439;
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
		associatedScreens.add(84);
		associatedScreens.add(447);
		associatedScreens.add(448);
		associatedScreens.add(449);
		screenIndex.put(index, newScreenTemplate(84, true, true, true, false));
        associatedScreensIndex.put(index, associatedScreens);
        
        // UPDATED SELECTED SAT KEYS SCREENS (INDICES 440 - 449)
        // LAST SCREEN = UPDATE SAT KEYS SCREEN (INDEX = 84) OR UPDATE SAT KEYS CONT SCREEN (INDEX = 439)
        index = 440;
		newOptionsArea =
				"-------------------------------** UNCLASSIFIED **-------------------------------\r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" + 
				"  ¦     EXIT      ¦  ¦DELETE CURRENT ¦  ¦ DELETE FUTURE ¦  ¦  DELETE COMP  ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n" + 
				"   _______________    _______________    _______________    _______________     \r\n" +  
				"  ¦DELETE SAT KEYS¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"  ¦               ¦  ¦               ¦  ¦               ¦  ¦               ¦    \r\n" + 
				"   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯     \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(15);
		associatedScreens.add(460);
		associatedScreens.add(461);
		associatedScreens.add(462);
		associatedScreens.add(450);
		screenIndex.put(index, newScreenTemplate(15, true, true, true, false));
        associatedScreensIndex.put(index, associatedScreens);
        
        for (int i = 441; i < 450; i++) {
        	index = i;
    		associatedScreens = new ArrayList<Integer>();
    		associatedScreens.add(15);
    		associatedScreens.add(460);
    		associatedScreens.add(461);
    		associatedScreens.add(462);
    		associatedScreens.add(450);
    		screenIndex.put(index, newScreenTemplate(440, true, true, true, true));
            associatedScreensIndex.put(index, associatedScreens);
        }
        
        // DELETE SAT KEYS SCREEN
        // LAST SCREEN = UPDATE SELECTED SAT KEYS SCREENS (INDICES 440 - 449)
        index = 450;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     VERIFY TO DELETE ALL SAT KEYS                                              \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		screenIndex.put(index, newScreenTemplate(431, true, true, false, true));
        
        /*
         * VALUES SHOULD BE CHANGED FOR TX PORTS
         */
        
        // TX PORT NET PARAM SCREENS (INDEX NUMBER VALUES NEED TO BE CHANGED LATER!!!!!!!!)
        // LAST SCREEN = TX PORT NET PARAM SCREEN (INDEX = 322)
        index = 451; // R01
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(321);
		associatedScreens.add(322);
		screenIndex.put(index, newScreenTemplate(315, true, true, true, true));
        associatedScreensIndex.put(index, associatedScreens);
        
        index = 452; // R02
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(321);
		associatedScreens.add(322);
		screenIndex.put(index, newScreenTemplate(315, true, true, true, true));
        associatedScreensIndex.put(index, associatedScreens);
        
        index = 453; // R03
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(321);
		associatedScreens.add(322);
		screenIndex.put(index, newScreenTemplate(315, true, true, true, true));
        associatedScreensIndex.put(index, associatedScreens);
        
        index = 454; // R04
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(321);
		associatedScreens.add(322);
		screenIndex.put(index, newScreenTemplate(315, true, true, true, true));
        associatedScreensIndex.put(index, associatedScreens);
        
        index = 455; // R06
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(321);
		associatedScreens.add(322);
		screenIndex.put(index, newScreenTemplate(315, true, true, true, true));
        associatedScreensIndex.put(index, associatedScreens);
        
        index = 456; // R07
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(321);
		associatedScreens.add(322);
		screenIndex.put(index, newScreenTemplate(315, true, true, true, true));
        associatedScreensIndex.put(index, associatedScreens);
        
        index = 457; // R10
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(321);
		associatedScreens.add(322);
		screenIndex.put(index, newScreenTemplate(315, true, true, true, true));
        associatedScreensIndex.put(index, associatedScreens);
        
        index = 458; // R11
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(303);
		associatedScreens.add(321);
		associatedScreens.add(322);
		screenIndex.put(index, newScreenTemplate(315, true, true, true, true));
        associatedScreensIndex.put(index, associatedScreens);
        
        // SAT KEYS NOT FOUND SCREEN
        // LAST SCREEN = DELETE SAT KEYS SCREEN (INDEX = 450)
        index = 459;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     SELECT OPTION                                                              \r\n" + 
				"                                                                                \r\n" + 
				"                                 KEY NOT FOUND                                  \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(15);
		associatedScreens.add(460);
		associatedScreens.add(461);
		associatedScreens.add(462);
		associatedScreens.add(450);
		screenIndex.put(index, newScreenTemplate(440, true, true, false, true));
        associatedScreensIndex.put(index, associatedScreens);
        
        // DELETE CURRENT SAT KEY SCREEN
        // LAST SCREEN = UPDATED SELECTED SAT KEYS SCREENS (INDICES 440 - 449) OR SAT KEYS NOT FOUND SCREEN (INDEX = 459)
        index = 460;
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
		associatedScreens.add(463);
		associatedScreens.add(464);
		associatedScreens.add(465);
		associatedScreens.add(466);
		associatedScreens.add(467);
		screenIndex.put(index, newScreenTemplate(430, true, true, true, false));
        associatedScreensIndex.put(index, associatedScreens);
        
        // DELETE FUTURE SAT KEY SCREEN
        // LAST SCREEN = UPDATED SELECTED SAT KEYS SCREENS (INDICES 440 - 449) OR SAT KEYS NOT FOUND SCREEN (INDEX = 459)
        index = 461;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(463);
		associatedScreens.add(464);
		associatedScreens.add(465);
		associatedScreens.add(466);
		associatedScreens.add(467);
		screenIndex.put(index, newScreenTemplate(460, true, true, true, true));
        associatedScreensIndex.put(index, associatedScreens);
        
        // DELETE COMPROMISE SAT KEY SCREEN
        // LAST SCREEN = UPDATED SELECTED SAT KEYS SCREENS (INDICES 440 - 449) OR SAT KEYS NOT FOUND SCREEN (INDEX = 459)
        index = 462;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(463);
		associatedScreens.add(464);
		associatedScreens.add(465);
		associatedScreens.add(466);
		associatedScreens.add(467);
		screenIndex.put(index, newScreenTemplate(460, true, true, true, true));
        associatedScreensIndex.put(index, associatedScreens);
        
        // VERIFY TO DELETE SAT KEY SCREENS (INDICES 463 - 467)
        // LAST SCREEN = DELETE CURRENT SAT KEY SCREEN (INDEX = 460) OR DELETE FUTURE SAT KEY SCREEN (INDEX = 461) OR DELETE COMPROMISE SAT KEY SCREEN (INDEX = 462)
        index = 463;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     VERIFY TO DELETE KEY                                                       \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		screenIndex.put(index, newScreenTemplate(435, true, true, true, true));
        
		for (int i = 464; i < 468; i++) {
			index = i;
			screenIndex.put(index, newScreenTemplate(463, true, true, true, true));
		}
		
		// VERIFY TO DELETE SAT KEY WORKING SCREEN
		// LAST SCREEN = VERIFY TO DELETE SAT KEY SCREENS (INDICES 463 - 467)
		index = 468;
		screenIndex.put(index, newScreenTemplate(463, true, true, true, true));
		
		// VERIFY TO DELETE SAT KEY WORKING CONT SCREEN
		// LAST SCREEN = VERIFY TO DELETE SAT KEY WORKING SCREEN (INDEX = 468)
		index = 469;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     VERIFY TO DELETE KEY                                                       \r\n" + 
				"                                    WORKING                                     \r\n" +  
				"                                                                                \r\n";
		screenIndex.put(index, newScreenTemplate(468, true, true, false, true));
		
		// ENTERED PTP CALL NUMBER SCREEN
		// LAST SCREEN = PTP CALLS SCREEN (INDEX = 22)
		index = 500; // NOT REAL VALUE JUST FOR TEST
        newPromptArea = 
                "--------------------------------------------------------------------------------\r\n" + 
                "     ENTER PTP CALL NUMBER                                                      \r\n" + 
                "                                                                                \r\n" + 
                "                                 INVALID ENTRY                                  \r\n";
		screenIndex.put(index, newScreenTemplate(22, true, true, false, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		// TERMINAL CONTROL SHUT POWER OFF SCREEN
		// LAST SCREEN = TERMINAL CONTROL POWER DOWN SCREEN (INDEX = 19)
        index = 800; // NOT REAL VALUE JUST FOR TEST
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
		screenIndex.put(index, newScreenTemplate(19, true, true, false, false));
        
        /*
         * STARTUP SCREENS
         */
        // WORKING SCREEN
        index = 998;
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
		screenIndex.put(index, newScreenTemplate(0, true, true, false, false));
        
        // WORKING SCREEN CONT
        index = 999;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		screenIndex.put(index, newScreenTemplate(998, true, true, false, true));
        
        // STARTUP SCREEN
        index = 1000;
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
		screenIndex.put(index, newScreenTemplate(0, true, true, false, false));
        
        // STARTUP SCREEN CONT
        // LAST SCREEN = STARTUP SCREEN (INDEX = 1000)
        index = 1001;
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
		associatedScreens.add(33);
		screenIndex.put(index, newScreenTemplate(0, true, true, true, false));
        associatedScreensIndex.put(index, associatedScreens);
        
        // OPERATIONAL SCREEN
        // LAST SCREEN = STARTUP SCREEN CONT (INDEX = 1001)
        index = 1002;
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
		associatedScreens.add(1003); // MAY WANT TO START AT 1011  // NEEDS TO GO THROUGH KGV-11 STEPS
		associatedScreens.add(1004);
		associatedScreens.add(1006);
		screenIndex.put(index, newScreenTemplate(1001, true, true, true, false));
        associatedScreensIndex.put(index, associatedScreens);
        
        // SYSTEM READY WORKING SCREEN
        // LAST SCREEN = OPERATIONAL SCREEN (1002) or SYSTEM READY WORKING CONT SCREEN (INDEX = 1011)
        index = 1003;
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
        screenIndex.put(index, newScreenTemplate(1002, true, true, true, false));
        
        // NEED TIME UPDATE SCREEN
        // LAST SCREEN = OPERATIONAL SCREEN (INDEX = 1002)
        index = 1004;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     SELECT OPTION                                                              \r\n" + 
				"                                                                                \r\n" + 
				"                                NEED TIME UPDATE                                \r\n";
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(0);  // NEEDS TO GO THROUGH KGV-11 STEPS
		associatedScreens.add(1004);
		associatedScreens.add(1006);
		screenIndex.put(index, newScreenTemplate(1002, true, true, false, true));
        associatedScreensIndex.put(index, associatedScreens);
        
        // PARAM CHANGE SCREEN
        // LAST SCREEN = OPERATIONAL SCREEN (INDEX = 1002)
        index = 1005;
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
		associatedScreens.add(1014);
		associatedScreens.add(1015);
		associatedScreens.add(1016);
		screenIndex.put(index, newScreenTemplate(1002, true, true, false, false));
        associatedScreensIndex.put(index, associatedScreens);
        
        // SET TIME SCREEN
        // LAST SCREEN = OPERATIONAL SCREEN (INDEX = 1002)
        index = 1006;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     ENTER DATE - TIME (DD-HHMMSSZ-MMMYY)                                       \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		screenIndex.put(index, newScreenTemplate(1000, true, true, false, true));
        
		// INVALID TIME SCREEN
		// LAST SCREEN = SET TIME SCREEN (INDEX = 1006)
		index = 1007;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     ENTER DATE - TIME (DD-HHMMSSZ-MMMYY)                                       \r\n" + 
				"                                                                                \r\n" + 
				"                                 INVALID ENTRY                                  \r\n";
		screenIndex.put(index, newScreenTemplate(1006, true, true, false, true));
		
		// VERIFY AT TIME SCREEN
		index = 1008;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     VERIFY AT TIME =                                                           \r\n" + 
				"                                                                                \r\n" + 
				"                                                                                \r\n";
		screenIndex.put(index, newScreenTemplate(1006, true, true, false, true));
		
		// VERIFY AT TIME WORKING SCREEN
		index = 1009;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     VERIFY AT TIME =                                                           \r\n" + 
				"                                    WORKING                                     \r\n" +
				"                                                                                \r\n";
		screenIndex.put(index, newScreenTemplate(1008, true, true, false, true));
		
		// VERIFY AT TIME WORKING CONT SCREEN
		index = 1010;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     VERIFY AT TIME =                                                           \r\n" + 
				"                                                                                \r\n" +
				"                                                                                \r\n";
		screenIndex.put(index, newScreenTemplate(1008, true, true, true, true));
		
		// SYSTEM READY WORKING CONT SCREEN
		// LAST SCREEN SYSTEM READY SCREEN (INDEX = 1003)
		index = 1011;
        newPromptArea = 
                "--------------------------------------------------------------------------------\r\n" + 
                "     SELECT OPTION                                                              \r\n" + 
				"                                    WORKING                                     \r\n" + 
                "                                                                                \r\n";
        screenIndex.put(index, newScreenTemplate(1003, true, true, false, true));
		
		// SET ACCURACY OF TIME SCREEN
		// LAST SCREEN = VERIFY AT TIME SCREEN
		index = 1012;
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
		screenIndex.put(index, newScreenTemplate(1008, true, true, false, false));
        associatedScreensIndex.put(index, associatedScreens);
        
        // SELECTED TIME ACCURACY SCREEN
        // LAST SCREEN = SET ACCURACY OF TIME SCREEN (INDEX = 1012)
        index = 1013;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(0);  // NEEDS TO GO THROUGH KGV-11 STEPS
		associatedScreens.add(1005);
		associatedScreens.add(1006);
		screenIndex.put(index, newScreenTemplate(1002, true, true, true, true));
        associatedScreensIndex.put(index, associatedScreens);
        
        // NORMAL DBASE SCREEN
        // LAST SCREEN = PARAM CHANGE SCREEN (INDEX = 1005)
        index = 1014;
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
		screenIndex.put(index, newScreenTemplate(1005, true, true, false, false));
		
        // FALLBACK DBASE SCREEN
        // LAST SCREEN = PARAM CHANGE SCREEN (INDEX = 1005)
		index = 1015;
		screenIndex.put(index, newScreenTemplate(1014, true, true, true, true));
		
        // BYPASS DBASE SCREEN
        // LAST SCREEN = PARAM CHANGE SCREEN (INDEX = 1005)
		index = 1016;
		screenIndex.put(index, newScreenTemplate(1014, true, true, true, true));
        
        // SELECTED DBASE SOURCE SCREEN
        // LAST SCREEN = PARAM CHANGE SCREEN (INDEX = 1005)
        index = 1020;
        screenIndex.put(index, newScreenTemplate(1014, true, true, true, true));
		
		// SELECTED DBASE SOURCE CONT SCREEN
		// LAST SCREEN = SELECTED DBASE SOURCE SCREEN (INDEX = 1020)
		index = 1021;
		newPromptArea =
				"--------------------------------------------------------------------------------\r\n" + 
				"     SELECT DBASE SOURCE                                                        \r\n" + 
				"                                                                                \r\n" +  
				"                                                                                \r\n";
		screenIndex.put(index, newScreenTemplate(1020, true, true, false, true));
		
		// SELECT PARAMETER TO BE CHANGED SCREEN
		// LAST SCREEN = SELECTED DBASE SOURCE SCREEN (INDEX = 1020) or SELECTED DBASE CONT SCREEN (INDEX = 1021)
		index = 1022;
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
		associatedScreens.add(1003);
		associatedScreens.add(1023);
		associatedScreens.add(1024);
		associatedScreens.add(1025);
		associatedScreens.add(1026);
		screenIndex.put(index, newScreenTemplate(0, true, true, false, false));
        associatedScreensIndex.put(index, associatedScreens);
		
        // SELECT CONSTELLATION SCREEN
        // LAST SCREEN = SELECT PARAMETER TO BE CHANGED SCREEN (INDEX = 1022)
        index = 1023;
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
		screenIndex.put(index, newScreenTemplate(1022, true, true, false, false));
        associatedScreensIndex.put(index, associatedScreens);
		
        // MODE CHANGE SCREEN
        // LAST SCREEN = SELECT PARAMETER TO BE CHANGED SCREEN (INDEX = 1022)
        index = 1024;
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
		screenIndex.put(index, newScreenTemplate(1022, true, true, false, false));
        associatedScreensIndex.put(index, associatedScreens);
        
        // ADAPTATION DATA SCREEN
        // LAST SCREEN = SELECT PARAMETER TO BE CHANGED SCREEN (INDEX = 1022)
        index = 1025;
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
		screenIndex.put(index, newScreenTemplate(1022, true, false, false, false));
        associatedScreensIndex.put(index, associatedScreens);
		
        // VERIFY MIL CONSTELLATION SCREEN
        // LAST SCREEN = SELECT CONSTELLATION SCREEN (INDEX = 1023)
        index = 1027;
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
		screenIndex.put(index, newScreenTemplate(1023, true, true, false, false));
		
        // VERIFY UFO CONSTELLATION SCREEN
        // LAST SCREEN = SELECT CONSTELLATION SCREEN (INDEX = 1023)
		index = 1028;
		screenIndex.put(index, newScreenTemplate(1027, true, true, true, true));
		
		// AUTO MODE SELECTED SCREEN
		// LAST SCREEN = MODE CHANGE SCREEN (INDEX = 1024)
		index = 1029;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(0);
		associatedScreens.add(1023);
		associatedScreens.add(1024);
		associatedScreens.add(1025);
		associatedScreens.add(1026);
		screenIndex.put(index, newScreenTemplate(1022, true, true, true, true));
        associatedScreensIndex.put(index, associatedScreens);
		
        // MANUAL MODE SELECTED SCREEN
		// LAST SCREEN = MODE CHANGE SCREEN (INDEX = 1024)
		index = 1030;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(0);
		associatedScreens.add(1023);
		associatedScreens.add(1024);
		associatedScreens.add(1025);
		associatedScreens.add(1026);
		screenIndex.put(index, newScreenTemplate(1022, true, true, true, true));
        associatedScreensIndex.put(index, associatedScreens);
		
        // DATA ID SCREEN
        // LAST SCREEN = ADAPTATION DATA SCREEN (INDEX = 1025)
        index = 1031;
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
		screenIndex.put(index, newScreenTemplate(1025, true, true, false, false));
		
		
		
		// WORKING VERIFY FOR DISRUPTIVE TEST SCREEN
		// LAST SCREEN = VERIFY FOR DISRUPTIVE TEST SCREEN (INDEX = 33)
		index = 1300; // NOT REAL VALUE
        newPromptArea = 
                "--------------------------------------------------------------------------------\r\n" + 
                "     VERIFY FOR DISRUPTIVE TEST                                                 \r\n" + 
				"                                    WORKING                                     \r\n" + 
                "                                                                                \r\n";
		associatedScreens = new ArrayList<Integer>();
		screenIndex.put(index, newScreenTemplate(33, true, true, false, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		// DISRUPTIVE BIT TEST SCREEN
		// LAST SCREEN = VERIFY FOR DISRUPTIVE TEST SCREEN (INDEX = 33) OR WORKING VERIFY FOR DISRUPTIVE TEST SCREEN (INDEX = 1300)
		index = 1301;
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
		screenIndex.put(index, newScreenTemplate(5, true, true, true, false));
		associatedScreensIndex.put(index, associatedScreens);
		
		// STARTUP BIT TEST SCREEN
		// LAST SCREEN = OPERATIONAL?
		index = 1302;
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
		screenIndex.put(index, newScreenTemplate(1301, true, true, true, false));
		associatedScreensIndex.put(index, associatedScreens);
		
		// WORKING VERIFY FOR DISRUPTIVE TEST CONT SCREEN
		// LAST SCREEN = WORKING VERIFY FOR DISRUPTIVE TEST SCREEN (INDEX = 1300)
		index = 1303;
		screenIndex.put(index, newScreenTemplate(33, true, true, true, true));
		
		// COMMON TEST SCREEN
		// LAST SCREEN = DISRUPTIVE BIT TEST SCREEN (INDEX = 1301)
		index = 1502;
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
		screenIndex.put(index, newScreenTemplate(5, true, true, true, false));
		associatedScreensIndex.put(index, associatedScreens);
		
		// EHF TEST SCREEN
		// LAST SCREEN = DISRUPTIVE BIT TEST SCREEN (INDEX = 1301)
		index = 1503;
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
		screenIndex.put(index, newScreenTemplate(5, true, true, true, false));
		associatedScreensIndex.put(index, associatedScreens);
		
		// I/O PORTS SCREEN
		// LAST SCREEN = DISRUPTIVE BIT TEST SCREEN (INDEX = 1301)
		index = 1504;
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
		screenIndex.put(index, newScreenTemplate(5, true, true, true, false)); // NEEDS TO REFERENCE PORT USAGE SCREEN
		associatedScreensIndex.put(index, associatedScreens);
		
		// BIT POWER DOWN SCREEN
		// LAST SCREEN = 
		index = 1505;
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
        screenIndex.put(index, newScreenTemplate(5, true, true, false, false));
		
        // RESTART SYSTEM SCREEN
        // LAST SCREEN = DISRUPTIVE BIT TEST SCREEN
        index = 1506;
		newPromptArea =
                "--------------------------------------------------------------------------------\r\n" + 
                "     VERIFY TO RESTART SYSTEM                                                   \r\n" + 
                "                                                                                \r\n" + 
                "                                                                                \r\n";
		screenIndex.put(index, newScreenTemplate(1505, true, true, false, true));
        
		// COMMON TEST CONT SCREEN
		// LAST SCREEN = COMMON TEST SCREEN (INDEX = 1502)
		index = 1512;
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
		screenIndex.put(index, newScreenTemplate(5, true, true, true, false));
		associatedScreensIndex.put(index, associatedScreens);
		
		// EHF TEST CONT SCREEN
		// LAST SCREEN = EHF TEST SCREEN (INDEX = 1503)
		index = 1522;
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
		screenIndex.put(index, newScreenTemplate(5, true, true, true, false)); 
		associatedScreensIndex.put(index, associatedScreens);
		
		// BIT SHUT POWER OFF SCREEN
		// LAST SCREEN = BIT POWER DOWN SCREEN (INDEX = 1505)
		index = 1530;
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
		screenIndex.put(index, newScreenTemplate(1505, true, true, false, false));
		
        // PRESS RESET SCREEN
        // LAST SCREEN = RESTART SYSTEM SCREEN (INDEX = 1506)
        index = 1531;
		newPromptArea =
                "--------------------------------------------------------------------------------\r\n" + 
                "     PRESS RESET                                                                \r\n" + 
                "                                                                                \r\n" + 
                "                                                                                \r\n";
		screenIndex.put(index, newScreenTemplate(1506, true, true, false, true));
		
		/*
		 * ALL BIT TEST SCREENS
		 */
		// VERIFY TO INIT TEST SCREEN
		index = 1501;
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
		screenIndex.put(index, newScreenTemplate(5, true, true, false, false));
		associatedScreensIndex.put(index, associatedScreens);
		
		index = 1507; // 
		screenIndex.put(index, newScreenTemplate(1501, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		index = 1508;
		screenIndex.put(index, newScreenTemplate(1501, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		index = 1509;
		screenIndex.put(index, newScreenTemplate(1501, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		index = 1510;
		screenIndex.put(index, newScreenTemplate(1501, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		index = 1511;
		screenIndex.put(index, newScreenTemplate(1501, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		index = 1513;
		screenIndex.put(index, newScreenTemplate(1501, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		index = 1514;
		screenIndex.put(index, newScreenTemplate(1501, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		index = 1515;
		screenIndex.put(index, newScreenTemplate(1501, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		index = 1516;
		screenIndex.put(index, newScreenTemplate(1501, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		index = 1517;
		screenIndex.put(index, newScreenTemplate(1501, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		index = 1518;
		screenIndex.put(index, newScreenTemplate(1501, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		index = 1519;
		screenIndex.put(index, newScreenTemplate(1501, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		index = 1520;
		screenIndex.put(index, newScreenTemplate(1501, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		index = 1521;
		screenIndex.put(index, newScreenTemplate(1501, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		index = 1523;
		screenIndex.put(index, newScreenTemplate(1501, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		index = 1524;
		screenIndex.put(index, newScreenTemplate(1501, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		index = 1525;
		screenIndex.put(index, newScreenTemplate(1501, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		index = 1526;
		screenIndex.put(index, newScreenTemplate(1501, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		// COMMON TEST SCREEN (STARTUP)
		// LAST SCREEN = STARTUP DISRUPTIVE BIT TEST SCREEN (INDEX = 1302)
		index = 1532;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(1302);    // NOT REAL VALUE NEEDS TO CANCEL TEST 
		associatedScreens.add(1532);	// WHERE DOES IT RETURN? 
		associatedScreens.add(1507);
		associatedScreens.add(1508);
		associatedScreens.add(1509);
		associatedScreens.add(1510);
		associatedScreens.add(1511);
		associatedScreens.add(1535);
		screenIndex.put(index, newScreenTemplate(1502, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		// EHF TEST SCREEN (STARTUP)
		// LAST SCREEN = STARTUP DISRUPTIVE BIT TEST SCREEN (INDEX = 1302)
		index = 1533;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(1302);    // NOT REAL VALUE NEEDS TO CANCEL TEST 
		associatedScreens.add(1533);	// WHERE DOES IT RETURN? 
		associatedScreens.add(1517);
		associatedScreens.add(1518);
		associatedScreens.add(1519);
		associatedScreens.add(1520);
		associatedScreens.add(1521);
		associatedScreens.add(1536);
		screenIndex.put(index, newScreenTemplate(1503, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		// I/O PORTS SCREEN (STARTUP)
		// LAST SCREEN = STARTUP DISRUPTIVE BIT TEST SCREEN (INDEX = 1302)
		index = 1534;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(1302);
		associatedScreens.add(1527);
		associatedScreens.add(1528);
		associatedScreens.add(1529);
		screenIndex.put(index, newScreenTemplate(1504, true, true, true, true)); // NEEDS TO REFERENCE PORT USAGE SCREEN
		associatedScreensIndex.put(index, associatedScreens);
		
		// COMMON TEST CONT SCREEN (STARTUP)
		// LAST SCREEN = COMMON TEST SCREEN (STARTUP) (INDEX = 1532)
		index = 1535;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(1532);    // NOT REAL VALUE NEEDS TO CANCEL TEST 
		associatedScreens.add(1513);	// WHERE DOES IT RETURN? 
		associatedScreens.add(1514);
		associatedScreens.add(1515);
		associatedScreens.add(1516);
		screenIndex.put(index, newScreenTemplate(1512, true, true, true, true));
		associatedScreensIndex.put(index, associatedScreens);
		
		// EHF TEST CONT SCREEN (STARTUP)
		// LAST SCREEN = EHF TEST SCREEN (STARTUP) (INDEX = 1533)
		index = 1536;
		associatedScreens = new ArrayList<Integer>();
		associatedScreens.add(1533);
		associatedScreens.add(1523);
		associatedScreens.add(1524);
		associatedScreens.add(1525);
		associatedScreens.add(1526);
		screenIndex.put(index, newScreenTemplate(1522, true, true, true, true)); 
		associatedScreensIndex.put(index, associatedScreens);
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
	
	// Returns Truncated Satellite Elevation
	private String getSatElevation(String satName) {
		if (sats.get(satName).canBeAuthorized()) {
			Integer elevation = (int) Double.parseDouble(sats.get(satName).getElevation());
			return elevation.toString();
		}
		return " -";
	}
	
	// Returns Satellite Set Time
	private String getSatSetTime(String satName) {
		if (satName.equals("SAT02")) {
			return ">24.0";
		}
		return "   - ";
	}
	
	// Returns Satellite Rise Time
	private String getSatRiseTime(String satName) {
		if (satName.equals("SAT08")) {
			return ">24.0";
		}
		return "   - ";
	}
	
	// Returns Formatted Alarm
	private String getAlarm() {
		if (alarmIsActive) {
			Log logEntry = null;
			for (int i = (log.size()-1); i >= 0; i--) {
				if (log.get(i).getStatus() == Status.ALARM) {
					logEntry = log.get(i);
					break;
				}
			}
			if (logEntry != null) {
				return logEntry.getTimeStamp()+"  "+logEntry.getMessage();
			} else {
				return "";
			}
		} else {
			return "";
		}
	}
	
	// Returns Formatted Advisory
	private String getAdvisory() {
		Log logEntry = null;
		for (int i = (log.size()-1); i >= 0; i--) {
			if (log.get(i).getStatus() == Status.ADVISORY) {
				logEntry = log.get(i);
				break;
			}
		}
		if (logEntry == null) {
			return "";
		} else if (logEntry.getMessage().length() >= 52) {
			String lineOne = "";
			String[] words = logEntry.getMessage().split(" ");
			lineTwo = words[words.length-1];
			for (int i = 0; i < words.length-1; i++) {
				lineOne += words[i]+" ";
			}
			return logEntry.getTimeStamp()+"  "+lineOne;
		} else {
			return logEntry.getTimeStamp()+"  "+logEntry.getMessage();
		}
	}
	
	// Returns Formatted Log Entries
	private String fetchLogEntry(int i) {
		if (log.size() >= i) {
			Log logEntry = log.get(log.size()-i);
			if (logEntry.getStatus() == Status.ALARM) {
				return logEntry.getTimeStamp()+"* "+logEntry.getMessage();
			} else {
				return logEntry.getTimeStamp()+"  "+logEntry.getMessage();
			}
		} else {
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
