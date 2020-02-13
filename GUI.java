import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

public class GUI extends JFrame {

	// GUI Dimensions
	private static int WIDTH;
	private static int HEIGHT;
	private int[] componentSpacings;
	private static int fontSize;
	
	// GUI Components
	private static Color ORANGE = new Color(255,70,0);
	private JPanel buttonPanel = new JPanel();
	private JButton button1 = new JButton();
	private JButton button2 = new JButton();
	private JButton button3 = new JButton();
	private JButton button4 = new JButton();
	private JButton button5 = new JButton();
	private JButton button6 = new JButton();
	private JButton button7 = new JButton();
	private JButton button8 = new JButton();
	private JTextArea textArea = new JTextArea();
	private JTextField textField = new JTextField("");
	private JLayeredPane layeredPane = new JLayeredPane();
	
	// Local Variables
	private int previousScreen; // May want to replace this with first index of associated screens
	private int screenNumber = 998;
	private boolean run = true;
	
	// Initial Navigation Data
	private String latitude = "21 45N";
	private String longitude = "9 30W";
	private double altitude = 0.0; 
	private double heading = 25.0; // 360
	private double pitch = 0.4; // 0.0
	private double roll = 0.2; // 0.0
	private boolean isClickable = true;
	private boolean isVerified = false;
	private boolean startupIsComplete = false;
	private boolean newTime = false;
	private boolean autoAcq = true;
	private boolean newEphemerisRequested = false;
	private boolean loggedOnSat = false;
	private boolean alarmIsActive = false;
	private boolean newLogOff = false;
	private int workingCount = 0;
	private int ephemerisCount = 0;
	private int agileBeamCount = 0;
	
	// Class Objects
	Screens screens;
	Ephemeris ephem;
	BIT bit;
	NavData navData;
	SwingWorker<Void, Integer> worker;
	Scanner scanner;
	Satellite currentSat;
	Acquisition acq;
	C2C3Loopback c2c3;
	OTAR otar;
	ArrayList<OTAR> keyRequests = new ArrayList<OTAR>();
	ArrayList<Net> netQueries = new ArrayList<Net>();
	ArrayList<C2C3Loopback> loopbacks = new ArrayList<C2C3Loopback>();
	Time timing;
	Calendar calendar = Calendar.getInstance();
	Clip clip;
	
	String[] satNames = {"SAT01","SAT02","SAT03","SAT04","SAT05","SAT06","SAT07","SAT08","SAT09","SAT10"};
	String[] netNames = {"TSTNET01", "TSTNET02", "TSTNET03", "TSTNET04", "TSTNET05", "TSTNET06", "TSTNET07", "TSTNET08", "TSTNET09",
			 			"TSTNET10", "TSTNET11", "TSTNET12", "TSTNET13", "TSTNET14", "TSTNET15", "TSTNET16", "TSTNET17", "TSTNET18", 
			 			"TSTNET19", "TSTNET20", "TSTNET21", "TSTNET22", "TSTNET23", "TSTNET24", "TSTNET25", "TSTNET26", "TSTNET27",
			 			"TSTNET28", "TSTNET29", "TSTNET30", "TSTNET31", "TSTNET32", "TSTNET33", "TSTNET34", "TSTNET35", "TSTNET36"};
	String[] acqIndicators = {"","",""};
	String satStatus = "";
	String[] ephemeris = {"","","0.0","0.0","0.0","0.0","0.0","0.0","0.0","0.0"};
	String[] COMSECKeys = {"011","022","033","044"};
	ArrayList<Log> log = new ArrayList<Log>();
	String GSData = "";
	String constellation = "MIL";
	String startupMode = "AUTO"; // AUTO
	String mode = "STDBY";
	String dataID = "";
	String dbSource = "IMG2019v23    29-200000Z-APR98";
	String databaseSource = "";
	String time = "";
	String timeSource = "TSM";
	String printMode = "PRINT ON DEMAND";
	String tsmStatus = "";
	String otarAuto = "OFF";
	String ephemSat = "";
	String[] logonParams = {"","","","","",""};
	String[] tempNavData = new String[6];
	String[] correctNavData = {"21 45N","9 30W","0.0","25.0","0.4","0.2"};
	char[] SRC = {'N','N','N','P','P','N','N','N'};
	String[] operatorInputs = {"AUTO","AUTO","AUTO","AUTO",""};
	Random rand = new Random();
	String commonGroupBIT = "";
	String EHFGroupBIT = "";
	String timeInput = "";
	String selectedSat;
	String beam = "";
	String password = "";
	String[] reqLogonParams = {"HHR 8","19.20","MOST"};
	String[] switches = {"OFF","OFF","OFF","OFF","OFF","AUTO OFF"};
	String[] categoryStatus = {"OFF","ON","OFF","OFF","OFF","OFF","OFF","OFF","OFF","OFF","OFF","OFF","OFF","OFF"};
	String agileBeamNoise = "40.0";
	String[] times = {"011200","014554","024708","200958","013839","033112","240000"};
	String[] agileBeamTimes = {"011200","011200","011200","011200"};
	private String[] beams = {"EC/AG","SB","SB","EC/AG","EC/AG","EC/AG","EC/AG","SB","",""};
	int selectedNet = 1; // TO PREVENT NULL (NEEDS TO BE CHANGED LATER!!!!!!!!!)
	int selectedKey;
	private HashMap<String, Satellite> sats = new HashMap<String, Satellite>();
	private HashMap<Integer, Net> nets = new HashMap<Integer, Net>();
	char[] satStatusChars = {'N','N','N','N','N','N','N','N','N','N'};
	
	URL audioURL;
	
	// TEST NET PARAM STRINGS
	String interruptible = "";
	
	// Constructs GUI
	public GUI() {
		
		determineDimensions(Toolkit.getDefaultToolkit().getScreenSize());
		
		createClassObjects();
		
		// Initiates Button Functionality
		createGUIComponents();
		
		// Initializes KeyListeners/MouseListeners for Keyboard & Mouse Input
		startEventListeners();
		
		startActionListeners();
		
		// Starts Initial Swing Worker
		startSwingWorker(998);
		
		// Loads Audio File for Alarm
		audioURL = getClass().getResource("alarm.wav");
	}
	
	// Determines Dimensions of GUI Componenents Based on Screen Resolution
	private void determineDimensions(Dimension screenSize) {
		if (screenSize.height < 864) {
			WIDTH = 544;
			HEIGHT = 680;
			componentSpacings = new int[] {18,23,82,550,362,74,68,34,544,612,68,503,136,10};
			fontSize = 9;
		} else if (screenSize.height >= 864 && screenSize.height < 1024) {
			WIDTH = 640;
			HEIGHT = 800;
			componentSpacings = new int[] {22,27,87,628,435,86,70,25,640,700,70,571,172,12};
			fontSize = 10;
		} else {
			WIDTH = 800;
			HEIGHT = 1000;
			componentSpacings = new int[] {25,30,119,781,510,107,100,50,800,900,100,714,200,15};
			fontSize = 12;
		}
		// 0 - 1 button gaps
		// 2 - 5 buttonPanel bounds
		// 6 - 9 textArea bounds
		// 10 - 13 textfield bounds
	}
	
	// Creates Class Objects for Key Terminal Components
	private void createClassObjects() {
		// Creates initial Time
		timing = new Time(timeSource, timeInput, newTime, calendar);
		time = timing.getTime();
		
		// Creates 10 Satellites
		for (String satName : satNames) {
			sats.put(satName, new Satellite(satName));
		}
		
		// TEST AUTO AUTHORIZATIONS
		sats.get("SAT02").setAuthorization(true);
		sats.get("SAT08").setAuthorization(true);
//		
		// Creates 36 Nets
		for (int i = 0; i < 36; i++) {
			nets.put((i+1), new Net((i+1), netNames[i]));
		}
		
		// Initiates Navigation Data
		navData = new NavData(latitude, longitude, altitude, heading, pitch, roll);	
		
		// Creates Initial Screens Instance
		screens = new Screens(log, currentSat, navData, tempNavData, printMode, satStatus, 
				commonGroupBIT, EHFGroupBIT, nets, selectedNet, tsmStatus, timeSource, startupMode, 
				otarAuto, sats, time, beam, acqIndicators, GSData, ephemeris, logonParams, operatorInputs,
				loggedOnSat, SRC, mode, satStatusChars, beams, switches, categoryStatus, alarmIsActive,
				databaseSource, agileBeamTimes, agileBeamNoise);
		
		// Creates Initial Empty Log Entry (Null Error Otherwise)
		log.add(new Log("", "", Status.ADVISORY));
	}
	
	// Creates Hidden Buttons
	private void createGUIComponents() {
		
		// Button Panel Parameters
		buttonPanel.setLayout(new GridLayout(2,4,componentSpacings[0],componentSpacings[1]));
		buttonPanel.setBounds(componentSpacings[2],componentSpacings[3],componentSpacings[4],componentSpacings[5]);
		buttonPanel.setOpaque(false);
		
		// Button Parameters
		button1.setOpaque(false);
		button1.setContentAreaFilled(false);
		button1.setBorderPainted(false);
		button2.setOpaque(false);
		button2.setContentAreaFilled(false);
		button2.setBorderPainted(false);
		button3.setOpaque(false);
		button3.setContentAreaFilled(false);
		button3.setBorderPainted(false);
		button4.setOpaque(false);
		button4.setContentAreaFilled(false);
		button4.setBorderPainted(false);
		button5.setOpaque(false);
		button5.setContentAreaFilled(false);
		button5.setBorderPainted(false);
		button6.setOpaque(false);
		button6.setContentAreaFilled(false);
		button6.setBorderPainted(false);
		button7.setOpaque(false);
		button7.setContentAreaFilled(false);
		button7.setBorderPainted(false);
		button8.setOpaque(false);
		button8.setContentAreaFilled(false);
		button8.setBorderPainted(false);
		buttonPanel.add(button1);
		buttonPanel.add(button2);
		buttonPanel.add(button3);
		buttonPanel.add(button4);
		buttonPanel.add(button5);
		buttonPanel.add(button6);
		buttonPanel.add(button7);
		buttonPanel.add(button8);
		add(buttonPanel);
		
		// Text Area Parameters
		textArea.setBounds(componentSpacings[6],componentSpacings[7],componentSpacings[8],componentSpacings[9]);
		textArea.setFont(new Font("monospaced", Font.PLAIN, fontSize));
		textArea.setBackground(Color.BLACK);
		textArea.setForeground(ORANGE.brighter());
		textArea.setEditable(false);
		textArea.setHighlighter(null);
		
		// Text Field Parameters
    	textField.setBounds(componentSpacings[10],componentSpacings[11],componentSpacings[12],componentSpacings[13]);
    	textField.setFont(new Font("monospaced", Font.PLAIN, fontSize));
    	textField.setBackground(Color.BLACK);
    	textField.setForeground(ORANGE.brighter());
    	textField.setCaretColor(ORANGE.brighter());
    	textField.setBorder(BorderFactory.createEmptyBorder());
    	textField.setEditable(false);
    	textField.setHighlighter(null);

    	// Layered Pane Parameters
    	layeredPane.setSize(WIDTH,HEIGHT);
    	layeredPane.add(textArea, JLayeredPane.DEFAULT_LAYER);
    	layeredPane.add(textField, JLayeredPane.PALETTE_LAYER);
    	add(layeredPane);
	}
	
	// Event Listeners for GUI Components
	private void startEventListeners() {
		
		// Key Bindings
		textArea.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent evt) {
				switch(evt.getKeyCode()) {
					case KeyEvent.VK_ESCAPE:
				  		if (!(screenNumber == 33 && !startupIsComplete) && screenNumber != 800
				  		&& screenNumber != 282 && screenNumber != 283 && screenNumber < 998) {
				  			loadScreen(0);
				  			tempNavData = new String[6];
				  		}
						break;
					case KeyEvent.VK_P:
					  	try {
					  		PrintWriter writer = new PrintWriter("SystemLog.txt", "UTF-8");
					  		for (Log logEntry : log) {
					  			writer.println(logEntry.getTimeStamp()+"   "+logEntry.getMessage());
					  		}
					  		writer.close();
					  		System.out.println(screens.getScreen(screenNumber));
					  	} catch (IOException ioe) {
					  		System.out.println("PRINT FUNCTION FAILED");
					  	}
						break;
					case KeyEvent.VK_SPACE:
						isVerified = true;
						break;
					case KeyEvent.VK_BACK_QUOTE:
						loadScreen(previousScreen);
						tempNavData = new String[6];
						break;
					case KeyEvent.VK_T:
						soundAlarm();
						break;
					case KeyEvent.VK_A:
						if (clip != null) {
							clip.close();
						}
						alarmIsActive = false;
						break;
					case KeyEvent.VK_R:
						if (screenNumber == 1531) {
							loadScreen(1003);
						}
						break;
					default:
//						System.out.println(evt.getKeyCode());
						break;
				}
			}
			public void keyReleased(KeyEvent arg0) {}
			public void keyTyped(KeyEvent arg0) {}
		});
		textField.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent evt) {
				switch(evt.getKeyCode()) {
					case KeyEvent.VK_ENTER:
					  	if (!textField.getText().equals("")) {
					  		String temp = textField.getText();
					  		textField.setText("");
					  		if (screenNumber == 18) {
					  			password = temp;
					  			loadScreen(280);
					  			break;
					  		}
					  		if (screenNumber == 21 || screenNumber == 299) {
					  			try {
					  				selectedNet = Integer.parseInt(temp);
					  				if (selectedNet >= 1 && selectedNet <= 36) {
					  					Thread.sleep(1000);
						  				loadScreen(300);
					  				} else {
					  					selectedNet = 1;
					  					textField.setText(temp);
					  					loadScreen(299);
					  				}
					  			} catch (NumberFormatException nfe) {
					  				textField.setText(temp);
					  				loadScreen(299);
					  			} catch (InterruptedException e) {
					  				textField.setText(temp);
					  				loadScreen(299);
								}
					  		}
					  		if (screenNumber == 22) {
					  			loadScreen(500); // NOT REAL VALUE JUST FOR TEST
					  		}
					  		if (screenNumber == 71 || screenNumber == 201) {
					  			if (verifyEntry("latitude", temp)) {
					        		if (temp.charAt(0) == '0') {
					        			StringBuilder sb = new StringBuilder(temp);
					        			tempNavData[0] = sb.deleteCharAt(0).toString();
					        		} else {
					        			tempNavData[0] = temp;
					        		}
						  			loadScreen(17);
					  			} else {
					  				textField.setText(temp);
					  				loadScreen(201);
					  			}
					  		}
					  		if (screenNumber == 72 || screenNumber == 202) {
					  			if (verifyEntry("longitude", temp)) {
					        		if (temp.charAt(0) == '0') {
					        			StringBuilder sb = new StringBuilder(temp);
					        			tempNavData[1] = sb.deleteCharAt(0).toString();
					        		} else {
					        			tempNavData[1] = temp;
					        		}
						  			loadScreen(17);
					  			} else {
					  				textField.setText(temp);
					  				loadScreen(202);
					  			}
					  		}
					  		if (screenNumber == 73 || screenNumber == 203) {
					  			if (verifyEntry("altitude", temp)) {
					  				if (!temp.contains(".")) {
					  					temp = temp+'.';
					  				}
					  				if (temp.charAt(0) == '.') {
					  					temp = '0'+temp;
					  				}
				  					if (temp.charAt(temp.length()-1) == '.') {
				  						temp = temp+'0';
				  					}
					  				tempNavData[2] = temp;
						  			loadScreen(17);
					  			} else {
					  				textField.setText(temp);
					  				loadScreen(203);
					  			}
					  		}
					  		if (screenNumber == 77 || screenNumber == 204) {
					  			if (verifyEntry("heading", temp)) {
					  				if (!temp.contains(".")) {
					  					temp = temp+'.';
					  				}
					  				if (temp.charAt(0) == '.') {
					  					temp = '0'+temp;
					  				}
				  					if (temp.charAt(temp.length()-1) == '.') {
				  						temp = temp+'0';
				  					}
					  				tempNavData[3] = temp;
						  			loadScreen(76);
					  			} else {
					  				textField.setText(temp);
					  				loadScreen(204);
					  			}
					  		}
					  		if (screenNumber == 78 || screenNumber == 205) {
					  			if (verifyEntry("pitch", temp)) {
					  				if (!temp.contains(".")) {
					  					temp = temp+'.';
					  				}
					  				if (temp.charAt(0) == '.') {
					  					temp = '0'+temp;
					  				}
				  					if (temp.charAt(temp.length()-1) == '.') {
				  						temp = temp+'0';
				  					}
					  				tempNavData[4] = temp;
						  			loadScreen(76);
					  			} else {
					  				textField.setText(temp);
					  				loadScreen(205);
					  			}
					  		}
					  		if (screenNumber == 79 || screenNumber == 206) {
					  			if (verifyEntry("roll", temp)) {
					  				if (!temp.contains(".")) {
					  					temp = temp+'.';
					  				}
					  				if (temp.charAt(0) == '.') {
					  					temp = '0'+temp;
					  				}
				  					if (temp.charAt(temp.length()-1) == '.') {
				  						temp = temp+'0';
				  					}
					  				tempNavData[5] = temp;
						  			loadScreen(76);
					  			} else {
					  				textField.setText(temp);
					  				loadScreen(206);
					  			}
					  		}
					  		// PERHAPS CHANGE verifyNavDataEntry method to verifyEntry()
					  		if (screenNumber == 156 || screenNumber == 185) {
					  			if (verifyEntry("heading", temp)) {
					  				if (!temp.contains(".")) {
					  					temp = temp+'.';
					  				}
					  				if (temp.charAt(0) == '.') {
					  					temp = '0'+temp;
					  				}
				  					if (temp.charAt(temp.length()-1) == '.') {
				  						temp = temp+"00";
				  					}
				  					if (temp.charAt(temp.length()-2) == '.') {
				  						temp = temp+'0';
				  					}
						  			operatorInputs[0] = temp;
						  			loadScreen(145);
					  			} else {
					  				textField.setText(temp);
					  				loadScreen(185);
					  			}
					  		}
					  		if (screenNumber == 157 || screenNumber == 186) {
					  			if (verifyEntry("pitch", temp)) {
					  				if (!temp.contains(".")) {
					  					temp = temp+'.';
					  				}
					  				if (temp.charAt(0) == '.') {
					  					temp = '0'+temp;
					  				}
				  					if (temp.charAt(temp.length()-1) == '.') {
				  						temp = temp+"00";
				  					}
				  					if (temp.charAt(temp.length()-2) == '.') {
				  						temp = temp+'0';
				  					}
						  			operatorInputs[1] = temp;
						  			loadScreen(145);
					  			} else {
					  				textField.setText(temp);
					  				loadScreen(186);
					  			}
					  		}
					  		if (screenNumber == 158 || screenNumber == 187) {
					  			if (verifyEntry("altitude", temp)) {
					  				if (!temp.contains(".")) {
					  					temp = temp+'.';
					  				}
					  				if (temp.charAt(0) == '.') {
					  					temp = '0'+temp;
					  				}
				  					if (temp.charAt(temp.length()-1) == '.') {
				  						temp = temp+"00";
				  					}
				  					if (temp.charAt(temp.length()-2) == '.') {
				  						temp = temp+'0';
				  					}
						  			operatorInputs[2] = temp;
						  			loadScreen(145);
					  			} else {
					  				textField.setText(temp);
					  				loadScreen(187);
					  			}
					  		}
					  		if (screenNumber == 159 || screenNumber == 188) {
					  			if (verifyEntry("altitude", temp)) {
					  				if (!temp.contains(".")) {
					  					temp = temp+'.';
					  				}
					  				if (temp.charAt(0) == '.') {
					  					temp = '0'+temp;
					  				}
				  					if (temp.charAt(temp.length()-1) == '.') {
				  						temp = temp+"00";
				  					}
				  					if (temp.charAt(temp.length()-2) == '.') {
				  						temp = temp+'0';
				  					}
						  			operatorInputs[3] = temp;
						  			loadScreen(145);
					  			} else {
					  				textField.setText(temp);
					  				loadScreen(188);
					  			}
					  		}
					  		if (screenNumber == 211 || screenNumber == 213) {
					  			boolean isValidKey = false;
					  			for (int i = 0; i < COMSECKeys.length; i++) {
					  				if (temp.equals(COMSECKeys[i])) {
					  					isValidKey = true;
					  					break;
					  				}
					  			}
					  			if (isValidKey) {
					  				selectedKey = 10;
					  				loadScreen(215);
					  			} else {
					  				loadScreen(213);
					  				textField.setText(temp);
					  			}
					  		}
					  		if (screenNumber == 212 || screenNumber == 214) {
					  			boolean isValidKey = false;
					  			for (int i = 0; i < COMSECKeys.length; i++) {
					  				if (temp.equals(COMSECKeys[i])) {
					  					isValidKey = true;
					  					break;
					  				}
					  			}
					  			if (isValidKey) {
					  				selectedKey = 11;
					  				loadScreen(216);
					  			} else {
					  				loadScreen(214);
					  				textField.setText(temp);
					  			}
					  		}
					  		if (screenNumber == 277 || screenNumber == 278) {
					  			try {
					  				int catNum = Integer.parseInt(temp);
					  				if (catNum >= 1 && catNum <= 14) {
					  					if (categoryStatus[catNum-1].equals("ON")) {
					  						categoryStatus[catNum-1] = "OFF";
					  					} else {
					  						categoryStatus[catNum-1] = "ON";
					  					}
					  					loadScreen(275);
					  				} else {
					  					textField.setText("");
					  					loadScreen(278);
					  				}
					  			} catch (NumberFormatException nfe) {
					  				textField.setText("");
					  				loadScreen(278);
					  			}	
					  		}
					  		if (screenNumber == 280 || screenNumber == 281) {
					  			if (temp.equals(password)) {
					  				loadScreen(282);
					  			} else {
					  				loadScreen(281);
					  			}
					  			break;
					  		}
					  		if (screenNumber == 282 || screenNumber == 283) {
					  			if (temp.equals(password)) {
					  				loadScreen(2);
					  			} else {
					  				loadScreen(283);
					  			}
					  		}
					  		if (screenNumber == 355) {
					  			String[] servParam1 = nets.get(selectedNet).getServiceParameters1();
					  			servParam1[0] = temp;
					  			nets.get(selectedNet).setServiceParameters1(servParam1);
					  			loadScreen(317);
					  		}
					  		if (screenNumber == 357) {
					  			String[] servParam1 = nets.get(selectedNet).getServiceParameters1();
					  			servParam1[2] = temp;
					  			nets.get(selectedNet).setServiceParameters1(servParam1);
					  			loadScreen(317);
					  		}
					  		if (screenNumber == 398) {
					  			String[] servParam2 = nets.get(selectedNet).getServiceParameters2();
					  			servParam2[3] = temp;
					  			nets.get(selectedNet).setServiceParameters2(servParam2);
					  			loadScreen(318);
					  		}
					  		if (screenNumber == 1006 || screenNumber == 1007) {
					  			boolean isValidDateTime = checkDateTimeFormat(temp);
					  			if (isValidDateTime) {
					  				timeInput = temp;
					  				loadScreen(1008);
					  			} else {
					  				loadScreen(1007);
					  			}
					  			textField.setText(temp);
					  		}
					  		if (screenNumber == 1031) {
					  			dataID = temp;
					  			loadScreen(1025);
					  		}
					  	}
					  	textArea.requestFocus();
					  	break;
					case KeyEvent.VK_ESCAPE:
						if (!(screenNumber == 33 && !startupIsComplete) && screenNumber != 800
						  		&& screenNumber != 282 && screenNumber != 283 && screenNumber < 998) {
							textField.setText("");
				  			loadScreen(0);
				  			tempNavData = new String[6];
				  		}
				  		textArea.requestFocus();
						break;
					case KeyEvent.VK_BACK_QUOTE:
						textField.setText("");
						loadScreen(previousScreen);
						textArea.requestFocus();
						break;
					default:
						break;
				}
			}
			public void keyReleased(KeyEvent arg0) {}
			public void keyTyped(KeyEvent arg0) {}
		});
		
		// Mouse Bindings
		textArea.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					if (worker.isDone()) {
						worker.cancel(true);
						startSwingWorker(screenNumber);
					}
				}
			}
		});
		
		// Window Activation Listener
		addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent we) {
				if (worker.isDone()) {
					worker.cancel(true);
					startSwingWorker(screenNumber);
				}
			}
		});
	}
	
	// Action Listeners for Buttons
	private void startActionListeners() {
		button1.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  if (screens.getAssociatedScreens(screenNumber) != null && screens.getAssociatedScreens(screenNumber).size() >= 1 && isClickable) {
					  isClickable = false;
					  previousScreen = screenNumber;  // NEEDS TO BE CHAGNED TO AN ASSOCIATED SCREEN INDEX
					  loadScreen(screens.getAssociatedScreens(screenNumber).get(0));
				  }
			  } 
		});
		button2.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  if (screens.getAssociatedScreens(screenNumber) != null && screens.getAssociatedScreens(screenNumber).size() >= 2  && isClickable) {
					  isClickable = false;
					  previousScreen = screenNumber;  // NEEDS TO BE CHAGNED TO AN ASSOCIATED SCREEN INDEX
					  loadScreen(screens.getAssociatedScreens(screenNumber).get(1));
				  }
			  } 
		});
		button3.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) {
				  if (screens.getAssociatedScreens(screenNumber) != null && screens.getAssociatedScreens(screenNumber).size() >= 3  && isClickable) {
					  isClickable = false;
					  previousScreen = screenNumber;  // NEEDS TO BE CHAGNED TO AN ASSOCIATED SCREEN INDEX
					  loadScreen(screens.getAssociatedScreens(screenNumber).get(2));
				  }
			  } 
		});
		button4.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  if (screens.getAssociatedScreens(screenNumber) != null && screens.getAssociatedScreens(screenNumber).size() >= 4  && isClickable) {
					  isClickable = false;
					  previousScreen = screenNumber;  // NEEDS TO BE CHAGNED TO AN ASSOCIATED SCREEN INDEX
					  loadScreen(screens.getAssociatedScreens(screenNumber).get(3));
				  }
			  } 
		});
		button5.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  if (screens.getAssociatedScreens(screenNumber) != null && screens.getAssociatedScreens(screenNumber).size() >= 5  && isClickable) {
					  isClickable = false;
					  previousScreen = screenNumber;  // NEEDS TO BE CHAGNED TO AN ASSOCIATED SCREEN INDEX
					  loadScreen(screens.getAssociatedScreens(screenNumber).get(4));
				  }
			  } 
		});
		button6.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  if (screens.getAssociatedScreens(screenNumber) != null && screens.getAssociatedScreens(screenNumber).size() >= 6  && isClickable) {
					  isClickable = false;
					  previousScreen = screenNumber;  // NEEDS TO BE CHAGNED TO AN ASSOCIATED SCREEN INDEX
					  loadScreen(screens.getAssociatedScreens(screenNumber).get(5));
				  }
			  } 
		});
		button7.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  if (screens.getAssociatedScreens(screenNumber) != null && screens.getAssociatedScreens(screenNumber).size() >= 7  && isClickable) {
					  isClickable = false;
					  previousScreen = screenNumber;  // NEEDS TO BE CHAGNED TO AN ASSOCIATED SCREEN INDEX
					  loadScreen(screens.getAssociatedScreens(screenNumber).get(6));
				  }
			  } 
		});
		button8.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  if (screens.getAssociatedScreens(screenNumber) != null && screens.getAssociatedScreens(screenNumber).size() >= 8  && isClickable) {
					  isClickable = false;
					  previousScreen = screenNumber;  // NEEDS TO BE CHAGNED TO AN ASSOCIATED SCREEN INDEX
					  loadScreen(screens.getAssociatedScreens(screenNumber).get(7));
				  }
			  } 
		});
	}
	
	// Initiates Swing Worker Instance
	private void startSwingWorker(int screenNumber) {
		worker = new SwingWorker<Void, Integer>() {

			// Updates Variables Every Second
			protected Void doInBackground() throws Exception {
				while (run) {
			        checkPreScreenConditions(screenNumber);
					publish(screenNumber);
					TimeUnit.MILLISECONDS.sleep(1000);
					isClickable = true;
					checkObjectStatus();
					updateGUIValues();
					isVerified = false;
					if (!getFocusOwner().toString().contains("JTextField") && screenNumber != 1008) {
						textField.setText("");
						textArea.requestFocus();
						textField.setEditable(false);
				    	textField.setForeground(ORANGE.brighter());
				    	textField.setCaretColor(ORANGE.brighter());
					}
				}
				return null;
			}
			
			// Updates GUI Dynamically
			protected void process(List<Integer> screenNumber) {
				for (int screen : screenNumber) {
					// Retrieves Screen from Screen Index and Generates GUI
					textArea.replaceRange(screens.getScreen(screen),0,textArea.getDocument().getLength());
				}
			}
		};
		worker.execute();
	}
	
	// Checks to See if Certain Screens Can Be Loaded
	private void checkPreScreenConditions(int screenNumber) {
        if (screenNumber == 5 && !loggedOnSat) {
        	loadScreen(132);
        }
        // NEED TO ADD RED FILL SCREENS TO THIS CHECK
        if (screenNumber != 29 && screenNumber != 136 && screenNumber != 137 && screenNumber != 160
        		&& screenNumber != 161 && screenNumber != 162 && screenNumber != 163 && screenNumber != 164
        		&& screenNumber != 165 && screenNumber != 166 && screenNumber != 167 && screenNumber != 168
        		&& screenNumber != 169 && screenNumber != 170 && screenNumber != 171 && screenNumber != 172
        		&& screenNumber != 173 && screenNumber != 174 && screenNumber != 175 && screenNumber != 180
        		&& screenNumber != 181) {
        	for (int i = 0; i < 10; i++) {
        		if (sats.get(satNames[i]).canBeAuthorized()) {
        			satStatusChars[i] = 'Y';
        		} else {
        			satStatusChars[i] = 'N';
        		}
        	}
        }
		if ((screenNumber == 93 || screenNumber == 94 || screenNumber == 95
        		|| screenNumber == 96 || screenNumber == 97) && !loggedOnSat) {
        	loadScreen(131);
        }
		if (screenNumber == 172 && !loggedOnSat) {
			loadScreen(175);
		}
		if (screenNumber == 208 || screenNumber == 209) {
			loadScreen(218);
		}
		if ((screenNumber == 210 || screenNumber == 211 || screenNumber == 212) && !loggedOnSat) {
			loadScreen(217);
		}
		if (screenNumber == 259 && loggedOnSat) {
			loadScreen(260);
		}
		if (screenNumber == 281 && !textField.getText().equals("")) {
			loadScreen(280);
		}
		if (screenNumber == 283 && !textField.getText().equals("")) {
			loadScreen(282);
		}
		if ((screenNumber == 304 || screenNumber == 305) && !loggedOnSat) {
			loadScreen(309);
		}
	}
	
	// Checks the Status of Various Class Objects
	private void checkObjectStatus() throws InterruptedException {
//		System.out.println("current sat -> "+currentSat);
//		if (currentSat != null) {
//			System.out.println("current sat -> "+currentSat.getName());
//		}
//		if (selectedSat != null) {
//			System.out.println("selected sat -> "+selectedSat);
//		}	
//		System.out.println("acq -> "+acq);
		
		// Determines Current Sat if Not Logged On
		if (startupIsComplete && acq == null && !loggedOnSat) {
			if (currentSat == null) {
				for (String satName : satNames) {
					if (sats.get(satName).getAuthorization()) {
						currentSat = sats.get(satName);
						satStatus = "LOGGED OFF";
						break;
					}
					currentSat = null;
				}
			}
			if (currentSat == null) {
				satStatus = "NO SAT AVAILABLE";
			}
			
			// If Startup Mode is "AUTO", Satellite ACQ is Attempted Once after Startup
			if (startupMode.equals("AUTO") && autoAcq) {
				autoAcq = false;
				if (currentSat == null) {
					satStatus = "NO SAT AVAILABLE";
					log.add(new Log(time, "NO SAT FOR DL ACQUISITION", Status.ALARM));
					alarmIsActive = true;
	    			soundAlarm();	
				} else {
					acq = new Acquisition(satStatus, startupMode);
					if (checkCryptoKeys() && checkNavData()) {
						acq.setFlag(true);
					}
				}
			}
		}
		
		// Checks Satellite Acquisition Status
		if (acq != null ) {
			if (acq.getStatus() == Status.ACQUIRING_DL) {
				logonParams[0] = reqLogonParams[0];
				logonParams[1] = reqLogonParams[1];
				logonParams[2] = reqLogonParams[2];
				operatorInputs[4] = "LDR/MDR";
				beam = "SB";
				Log BeginDLAcquisition = new Log(time, "BEGIN DL ACQ ON SAT "+currentSat.getName(), Status.ADVISORY);
				if (!BeginDLAcquisition.getMessage().equals(log.get(log.size()-1).getMessage())) {
					log.add(BeginDLAcquisition);
				}
			}
			if (acq.getStatus() == Status.DL_COMPLETE) {
				Log DLAcquisitionComplete = new Log(time, "COARSE TIME INTERPOLATION SUCCESSFUL", Status.ADVISORY);
				if (!DLAcquisitionComplete.getMessage().equals(log.get(log.size()-1).getMessage())) {
					log.add(DLAcquisitionComplete);
				}
			}
			if (acq.getStatus() == Status.UL_COMPLETE) {
				logonParams[3] = "HHR 8";
				logonParams[4] = "9.60";
				logonParams[5] = "LEAST  OK";
				if (logonParams[0].equals(logonParams[3]) && logonParams[1].equals(logonParams[4]) && (logonParams[2]+"  OK").equals(logonParams[5])) {
					Log ULAcquisitionComplete = new Log(time, "EHF LOGON COMPLETE", Status.ADVISORY);
					if (!ULAcquisitionComplete.getMessage().equals(log.get(log.size()-1).getMessage())) {
						log.add(ULAcquisitionComplete);
					}
				} else {
					Log ULAcquisitionComplete = new Log(time, "EHF LOGON COMPLETE - NON-DESIRED PARAMETERS", Status.ADVISORY);
					if (!ULAcquisitionComplete.getMessage().equals(log.get(log.size()-1).getMessage())) {
						log.add(ULAcquisitionComplete);
					}
				}
			}
			if (acq.getStatus() == Status.FINISHED) {
				if (!currentSat.getEphemerisCurrentness()) {
					Log OutdatedEphemeris = new Log(time, "SAT "+currentSat.getName()+" EPHEMERIDES  7 DAYS OLD", Status.ADVISORY); // PERHAPS NEED ONE FOR EACH SAT THAT IS AUTHORIZED?
					boolean logAlreadyExists = false;
					for (Log logEntry : log) {
						if (OutdatedEphemeris.getMessage().equals(logEntry.getMessage())) {
							logAlreadyExists = true;
							break;
						}
					}
					if (!logAlreadyExists) {
						log.add(OutdatedEphemeris);
					}
				}
				loggedOnSat = true;
			}
			if (acq.getStatus() == Status.DL_FAILED_A) {
				Log FailedStrategyA = new Log(time, "DL ACQ FAILED - STRATEGY A", Status.ADVISORY);
				if (!FailedStrategyA.getMessage().equals(log.get(log.size()-1).getMessage())) {
					log.add(FailedStrategyA);
				}
			}
			if (acq.getStatus() == Status.DL_FAILED_B) {
				Log FailedStrategyB = new Log(time, "DL ACQ FAILED - STRATEGY B", Status.ADVISORY);
				if (!FailedStrategyB.getMessage().equals(log.get(log.size()-1).getMessage())) {
					log.add(FailedStrategyB);
				}
			}
			if (acq.getStatus() == Status.DL_FAILED_C) {
				Log FailedStrategyC = new Log(time, "DL ACQ FAILED - STRATEGY C", Status.ADVISORY);
				if (!FailedStrategyC.getMessage().equals(log.get(log.size()-1).getMessage())) {
					log.add(FailedStrategyC);
				}
			}
			if (acq.getStatus() == Status.DL_FAILED_D) {
				Log FailedStrategyD = new Log(time, "DL ACQ FAILED - STRATEGY D", Status.ADVISORY);
				if (!FailedStrategyD.getMessage().equals(log.get(log.size()-1).getMessage())) {
					log.add(FailedStrategyD);
				}
			}
			if (acq.getStatus() == Status.DL_FAILED) {
				Log DLAcquisitionFailed = new Log(time, "DL ACQ FAILED ON SAT "+currentSat.getName(), Status.ADVISORY);
				if (!DLAcquisitionFailed.getMessage().equals(log.get(log.size()-1).getMessage())) {
					log.add(DLAcquisitionFailed);
				}
			}
			if (acq.getStatus() == Status.NO_SAT) {
				Log NoSatelliteAvaiable = new Log(time, "NO SAT FOR DL ACQUISITION", Status.ALARM);
				if (!NoSatelliteAvaiable.getMessage().equals(log.get(log.size()-1).getMessage())) {
					log.add(NoSatelliteAvaiable);
				}
				alarmIsActive = true;
				soundAlarm();
			}
			if (acq.getStatus() == Status.LOGGED_OFF) {
				Log LoggedOffSatellite = new Log(time, "TERMINAL LOGOFF COMPLETE", Status.ADVISORY);
				if (!LoggedOffSatellite.getMessage().equals(log.get(log.size()-1).getMessage())) {
					log.add(LoggedOffSatellite);
				}
				loggedOnSat = false;
			}
			satStatus = acq.getCurrentStep();
			if (acq.getStatus() == Status.LOGGED_OFF || acq.getStatus() == Status.NO_SAT || acq.getStatus() == Status.FINISHED) {
				acq = null;
			}
		} else if (!loggedOnSat) {
			for (int i = 0; i < 6; i++) {
				logonParams[i] = "";
			}
		}
		
		// Ensure Nets Are Inactive if Not On Satellite
		if (!loggedOnSat) {
			for (int i = 1; i <= 36; i++) {
				nets.get(i).setFlag(false);
				nets.get(i).setStatus(Status.INACTIVE);
				nets.get(i).setNetStatus("IA");
			}
		}
		
		// Checks Status of Net Queries
		for (int i = 0; i < netQueries.size(); i++) {
			if (netQueries.get(i).getStatus() == Status.ACTIVE) {
				log.add(new Log(time, "JOIN N"+netQueries.get(i).getNetNumber()+" SUCCESSFUL", Status.ADVISORY));
			}
			if (netQueries.get(i).getStatus() == Status.INACTIVE) {
				log.add(new Log(time, "N"+netQueries.get(i).getNetNumber()+" - EXIT COMPLETE", Status.ADVISORY));
			}
		}
		for (int i = 0; i < netQueries.size(); i++) {
			if (netQueries.get(i).getStatus() == Status.ACTIVE || netQueries.get(i).getStatus() == Status.INACTIVE) {
				netQueries.remove(i);
			}
		}
		
		// Ensures Nets are Exited if Logging Off Satellite
		if (newLogOff) {
			for (int i = 1; i <= 36; i++) {
				if (nets.get(i).getStatus() == Status.ACTIVE) {
					log.add(new Log(time, "N"+nets.get(i).getNetNumber()+" - EXIT COMPLETE", Status.ADVISORY));
				}
				nets.get(i).setFlag(false);
				nets.get(i).setStatus(Status.INACTIVE);
				nets.get(i).setNetStatus("IA");
			}
			netQueries.clear();
			newLogOff = false;
		}
		
		// Checks Status of C2/C3 Loopbacks
		if (loopbacks.size() == 0) {
			c2c3 = null;
		}
		if (c2c3 != null) {
			for (int i = 0; i < loopbacks.size(); i++) {
				if (loopbacks.get(i).getStatus() == Status.SUCCESSFUL) {
					log.add(new Log(time, "C2/C3 TEST SUCCEEDED AFTER "+loopbacks.get(i).getNumAttempts()+" ATTEMPTS", Status.ADVISORY));
				}
				if (loopbacks.get(i).getStatus() == Status.FAILED) {
					log.add(new Log(time, "C2/C3 TEST FAILED AFTER "+loopbacks.get(i).getNumAttempts()+" ATTEMPTS", Status.ADVISORY));
				}
			}
			for (int i = 0; i < loopbacks.size(); i++) {
				if (loopbacks.get(i).getStatus() == Status.SUCCESSFUL || loopbacks.get(i).getStatus() == Status.FAILED) {
					loopbacks.remove(i);
				}
			}
		}
		
		// Checks Status of OTAR
		// THESE ARE FOR ONLY TRANSEC KEYS, NEED TO INCLUDE COMSEC KEYS
		if (keyRequests.size() == 0) {
			otar = null;
		}
		if (otar != null) {
			for (int i = 0; i < keyRequests.size(); i++) {
				ArrayList<Character> keys = sats.get(keyRequests.get(i).getSelectedSat()).getKeys();
				Status keyRequestStatus = keyRequests.get(i).getStatus();
				if (keyRequestStatus == Status.PENDING) {
					if (keys.get(keyRequests.get(i).getSelectedKey()).equals('Y') || 
							keys.get(keyRequests.get(i).getSelectedKey()).equals('O')) {
						if (loggedOnSat) {
							keys.set(keyRequests.get(i).getSelectedKey(), 'O');
						} else {
							keys.set(keyRequests.get(i).getSelectedKey(), 'Y');
						}
					} else {
						if (loggedOnSat) {
							keys.set(keyRequests.get(i).getSelectedKey(), 'P');
						} else {
							keys.set(keyRequests.get(i).getSelectedKey(), '-');
						}
					}
				} else if (keyRequestStatus == Status.STARTED && keyRequests.get(i).getFlag() && loggedOnSat) {
					log.add(new Log(time, "SINGLE TRANSEC KEY REQUEST - EXPECTED AT "+timing.getExpectedTime(), Status.ADVISORY));
					keyRequests.get(i).setFlag(false);
				} else if (keyRequestStatus == Status.SUCCESSFUL) {
					keys.set(keyRequests.get(i).getSelectedKey(), 'Y');
					keyRequests.remove(i);
				} else if (keyRequestStatus == Status.DENIED || keyRequestStatus == Status.FAILED) {
					keys.set(keyRequests.get(i).getSelectedKey(), '-');
					if (keyRequests.get(i).getFlag()) {
						log.add(new Log(time, "REKEY REQUEST FAILURE - KEY NOT AUTHORIZED", Status.ADVISORY));
						keyRequests.get(i).setFlag(false);
						keyRequests.remove(i);
					}
				}
			}
			if (!loggedOnSat) {
				for (int i = 0; i < keyRequests.size(); i++) {
					ArrayList<Character> keys = sats.get(keyRequests.get(i).getSelectedSat()).getKeys();
					if (keys.get(keyRequests.get(i).getSelectedKey()).equals('P')) {
						keys.set(keyRequests.get(i).getSelectedKey(), '-');
					}
				}
				keyRequests.clear();
			}
		}
	}
	
	// Updates GUI Values
	private void updateGUIValues() throws InterruptedException {
		checkScreenConditions(screenNumber);
		updateTime();
		updateEphemeris();
		updateAgileBeamStatus();
		screens = new Screens(log, currentSat, navData, tempNavData, printMode, satStatus, 
				commonGroupBIT, EHFGroupBIT, nets, selectedNet, tsmStatus, timeSource, startupMode, 
				otarAuto, sats, time, beam, acqIndicators, GSData, ephemeris, logonParams, operatorInputs,
				loggedOnSat, SRC, mode, satStatusChars, beams, switches, categoryStatus, alarmIsActive,
				databaseSource, agileBeamTimes, agileBeamNoise);
	}
	
	// Checks to See if Current Screen Triggers an Action?
	private void checkScreenConditions(int screenNumber) throws InterruptedException {

//		System.out.println("Screen # -> "+screenNumber);
//		System.out.println("associated screens -> "+screens.getAssociatedScreens(screenNumber));
//		System.out.println("Net # -> "+selectedNet);
//		System.out.println("Logged On Sat -> "+loggedOnSat);
		
		if (screenNumber == 0) { // THERE NEEDS TO BE A FLASHING WORKING PROMPT
			startupIsComplete = true;
			GSData = "0700 GSDATA";
			databaseSource = dbSource;
			acqIndicators[0] = "CURRECT SAT";
			acqIndicators[1] = "ACQ/LOGON STATUS";
			acqIndicators[2] = "CURRENT BEAM";
		}
		if (screenNumber == 18) {
        	textField.requestFocus();
        	textField.setEditable(true);
        	textField.setForeground(Color.BLACK);
        	textField.setCaretColor(Color.BLACK);
		}
		if (screenNumber == 19 && isVerified) {
			isVerified = false;
			loadScreen(800);
		}
        if (screenNumber == 21 || screenNumber == 299) {
        	textField.requestFocus();
        	textField.setEditable(true);
        }
        if (screenNumber == 22) {
        	textField.requestFocus();
        	textField.setEditable(true);
        }
        if (screenNumber == 25 && isVerified) {
        	isVerified = false;
        	if (acq != null || loggedOnSat) {
        		loadScreen(41);
        	} else {
        		satStatus = "";
        		loggedOnSat = false;
        		if (currentSat != null) {
        			acq = new Acquisition(satStatus, startupMode);
    				if (checkCryptoKeys() && checkNavData()) {
    					acq.setFlag(true);
    				}
        		} else {
        			log.add(new Log(time, "NO SAT FOR DL ACQUISITION", Status.ALARM));
        			alarmIsActive = true;
        			soundAlarm();
        		}
            	loadScreen(4);
        	}
        }
        if (screenNumber == 26 && isVerified) {
        	isVerified = false;
        	if (acq != null || loggedOnSat) {
        		loadScreen(41);
        	} else {
            	if (satStatus.equals("DL COMPLETE")) {
    				acq = new Acquisition(satStatus, startupMode);
            	} else if ((acq != null && (acq.getStatus() == Status.DL_COMPLETE || acq.getStatus() == Status.ACQUIRING_UL 
            			|| acq.getStatus() == Status.UL_COMPLETE || acq.getStatus() == Status.FINISHED)) 
            			|| satStatus.equals("LOGGED ON")) {
            		satStatus = "DL COMPLETE";
            		loggedOnSat = false;
        			acq = new Acquisition(satStatus, startupMode);
            	} else {
            		// NEED DL FIRST!!!
            	}
            	// ALSO WHAT IF ON AUTO MODE
            	loadScreen(4);
        	}
        }
        if (screenNumber == 27 && isVerified) {
        	isVerified = false;
        	if (satStatus.equals("LOGGED ON")) {
        		newLogOff = true;
				acq = new Acquisition(satStatus, startupMode);
        	} else if (!satStatus.equals("NO SAT AVAILABLE") && !satStatus.equals("LOGOFF PENDING")) {
        		satStatus = "LOGGED OFF";
        		acq = null;
        	}
        	loadScreen(4);
        }
        if (screenNumber == 33 && isVerified) {
        	if (workingCount > 3) {
        		workingCount = 0;
				if (startupIsComplete) {
					loadScreen(1301);
				} else {
					loadScreen(1302);
				}
        	} else {
        		loadScreen(1300);
        	}
        	workingCount++;
        }
        if (screenNumber == 35 && isVerified) {
        	isVerified = false;
        	c2c3 = new C2C3Loopback(satStatus);
        	loopbacks.add(c2c3);
			loadScreen(5);
        }
        if (screenNumber == 70) {
        	if (tempNavData[0] != null) {
        		latitude = tempNavData[0];
        		SRC[0] = 'O';
        	}
        	if (tempNavData[1] != null) {
        		longitude = tempNavData[1];
        		SRC[1] = 'O';
        	}
        	if (tempNavData[2] != null) {
        		altitude = Double.parseDouble(tempNavData[2]);
        		SRC[2] = 'O';
        	}
        	if (tempNavData[3] != null) {
        		heading = Double.parseDouble(tempNavData[3]);
        		SRC[5] = 'O';
        	}
        	if (tempNavData[4] != null) {
        		pitch = Double.parseDouble(tempNavData[4]);
        		SRC[6] = 'O';
        	}
        	if (tempNavData[5] != null) {
        		roll = Double.parseDouble(tempNavData[5]);
        		SRC[7] = 'O';
        	}
        	tempNavData = new String[6];  // Makes all elements null
        	navData = new NavData(latitude, longitude, altitude, heading, pitch, roll);	
        }
        if (screenNumber == 71 || screenNumber == 72 || screenNumber == 73 || screenNumber == 77
        		|| screenNumber == 78 || screenNumber == 79 || screenNumber == 201 || screenNumber == 202
        		|| screenNumber == 203 || screenNumber == 204 || screenNumber == 205 || screenNumber == 206) {
        	textField.requestFocus();
        	textField.setEditable(true);
        }
        if (screenNumber == 80) {
        	tempNavData = new String[6];
        }
        if (screenNumber == 86) {
        	if (workingCount > 10) {
        		workingCount = 0;
        		loadScreen(88); // IF KEYS IN TSM THEN 92
        	} else {
        		loadScreen(87);
        	}
        	workingCount++;
        }
        if (screenNumber == 87) {
        	if (workingCount > 10) {
        		workingCount = 0;
        		loadScreen(88); // IF KEYS IN TSM THEN 92
        	} else {
        		loadScreen(86);
        	}
        	workingCount++;
        }
        if (screenNumber == 89) {
        	if (workingCount > 10) {
        		workingCount = 0;
        		loadScreen(91);
        		workingCount = 0;
        	} else {
        		loadScreen(90);
        	}
        	workingCount++;
        }
        if (screenNumber == 90) {
        	if (workingCount > 10) {
        		workingCount = 0;
        		loadScreen(91);
        	} else {
        		loadScreen(89);
        	}
        	workingCount++;
        }
        if (screenNumber == 92) {
        	sats.get("SAT02").getKeys().set(0, 'Y');
        	sats.get("SAT02").getKeys().set(1, 'Y');
        	sats.get("SAT02").getKeys().set(4, 'Y');
        	sats.get("SAT08").getKeys().set(0, 'Y');
        	sats.get("SAT08").getKeys().set(1, 'Y');
        	sats.get("SAT08").getKeys().set(4, 'Y');
        }
        if (screenNumber == 95 && isVerified) {
        	isVerified = false;
        	// SEND BINDING
        	System.out.println("Send Binding");
        	loadScreen(82);
        }
        if (screenNumber == 99) {
        	otarAuto = "ON";
        }
        if (screenNumber == 100) {
        	otarAuto = "OFF";
        }
        if (screenNumber == 101 || screenNumber == 111) {
        	selectedKey = 0;
        }
        if (screenNumber == 102 || screenNumber == 112) {
        	selectedKey = 1;
        }
        if (screenNumber == 103 || screenNumber == 113) {
        	selectedKey = 2;
        }
        if (screenNumber == 104 || screenNumber == 114) {
        	selectedKey = 3;
        }
        if (screenNumber == 105 || screenNumber == 115) {
        	selectedKey = 4;
        }
        if (screenNumber == 106 || screenNumber == 116) {
        	selectedKey = 5;
        }
        if (screenNumber == 107 || screenNumber == 117) {
        	selectedKey = 6;
        }
        if (screenNumber == 108 || screenNumber == 118) {
        	selectedKey = 7;
        }
        if (screenNumber == 109 || screenNumber == 119) {
        	selectedKey = 8;
        }
        if (screenNumber == 110 || screenNumber == 120) {
        	selectedKey = 9;
        }
        if (screenNumber == 121 && isVerified) {
        	isVerified = false;
        	otar = new OTAR(selectedKey, "SAT01");
        	keyRequests.add(otar);
        	loadScreen(82);
        }
        if (screenNumber == 122 && isVerified) {
        	isVerified = false;
        	otar = new OTAR(selectedKey, "SAT02");
        	keyRequests.add(otar);
        	loadScreen(82);
        }
        if (screenNumber == 123 && isVerified) {
        	isVerified = false;
        	otar = new OTAR(selectedKey, "SAT03");
        	keyRequests.add(otar);
        	loadScreen(82);
        }
        if (screenNumber == 124 && isVerified) {
        	isVerified = false;
        	otar = new OTAR(selectedKey, "SAT04");
        	keyRequests.add(otar);
        	loadScreen(82);
        }
        if (screenNumber == 125 && isVerified) {
        	isVerified = false;
        	otar = new OTAR(selectedKey, "SAT05");
        	keyRequests.add(otar);
        	loadScreen(82);
        }
        if (screenNumber == 126 && isVerified) {
        	isVerified = false;
        	otar = new OTAR(selectedKey, "SAT06");
        	keyRequests.add(otar);
        	loadScreen(82);
        }
        if (screenNumber == 127 && isVerified) {
        	isVerified = false;
        	otar = new OTAR(selectedKey, "SAT07");
        	keyRequests.add(otar);
        	loadScreen(82);
        }
        if (screenNumber == 128 && isVerified) {
        	isVerified = false;
        	otar = new OTAR(selectedKey, "SAT08");
        	keyRequests.add(otar);
        	loadScreen(82);
        }
        if (screenNumber == 129 && isVerified) {
        	isVerified = false;
        	otar = new OTAR(selectedKey, "SAT09");
        	keyRequests.add(otar);
        	loadScreen(82);
        }
        if (screenNumber == 130 && isVerified) {
        	isVerified = false;
        	otar = new OTAR(selectedKey, "SAT10");
        	keyRequests.add(otar);
        	loadScreen(82);
        }
		if (screenNumber == 142) { // IF SCREEN IS 142 (VALUE TO CHANGE LATER!!!!)
			printMode = "PRINT ON DEMAND";
		}
		if (screenNumber == 143) { // IF SCREEN IS 143 (VALUE TO CHANGE LATER!!!!)
			printMode = "PRINT IMMEDIATE";
		}
		if (screenNumber == 151) {
			operatorInputs[0] = "AUTO";
		}
		if (screenNumber == 152) {
			operatorInputs[1] = "AUTO";
		}
		if (screenNumber == 153) {
			operatorInputs[2] = "AUTO";
		}
		if (screenNumber == 154) {
			operatorInputs[3] = "AUTO";
		}
		if (screenNumber == 155) {
			operatorInputs[4] = "AUTO";
		}
		if (screenNumber == 156 || screenNumber == 157 || screenNumber == 158 || screenNumber == 159
				|| screenNumber == 185 || screenNumber == 186 || screenNumber == 187 || screenNumber == 188) {
			textField.requestFocus();
			textField.setEditable(true);
		}
		if (screenNumber == 160) {
			selectedSat = "SAT01";
		}
		if (screenNumber == 161) {
			selectedSat = "SAT02";
		}
		if (screenNumber == 162) {
			selectedSat = "SAT03";
		}
		if (screenNumber == 163) {
			selectedSat = "SAT04";
		}
		if (screenNumber == 164) {
			selectedSat = "SAT05";
		}
		if (screenNumber == 165) {
			selectedSat = "SAT06";
		}
		if (screenNumber == 166) {
			selectedSat = "SAT07";
		}
		if (screenNumber == 167) {
			selectedSat = "SAT08";
		}
		if (screenNumber == 168) {
			selectedSat = "SAT09";
		}
		if (screenNumber == 169) {
			selectedSat = "SAT10";
		}
		if (screenNumber == 172 && isVerified) {
			isVerified = false;
			if (satStatus.equals("LOGGED ON")) {
				if (!newEphemerisRequested) {
					newEphemerisRequested = true;
					ephemSat = selectedSat;
				} else {
					log.add(new Log(time, "EPHEMERIS UPDATE FOR SAT "+selectedSat+" FAILURE - SAT REFUSED", Status.ADVISORY));
				}
				ephemerisCount = 0;
				loadScreen(174);
			}
		}
		if (screenNumber == 180) {
			for (int i = 0; i < 10; i++) {
				if (satNames[i].equals(selectedSat)) {
					satStatusChars[i] = 'Y';
					break;
				}
			}
			if (sats.get(selectedSat).canBeAuthorized()) {
				sats.get(selectedSat).setAuthorization(true);
			}
		}
		if (screenNumber == 181) {
			sats.get(selectedSat).setAuthorization(false);
			for (int i = 0; i < 10; i++) {
				if (satNames[i].equals(selectedSat)) {
					satStatusChars[i] = 'N';
					break;
				}
			}
			if (selectedSat.equals(currentSat.getName())) {
				acq = null;
				beam = "";
				if (loggedOnSat) {
					satStatus = "";
					loggedOnSat = false;
				}
				for (String satName : satNames) {
					if (sats.get(satName).getAuthorization()) {
						currentSat = sats.get(satName);
						satStatus = "LOGGED OFF";
						break;
					}
					currentSat = null;
				}
			}
			if (currentSat == null) {
				satStatus = "NO SAT AVAILABLE";
			}
		}
		if (screenNumber == 182) {
			operatorInputs[4] = "LDR";
		}
		if (screenNumber == 183) {
			operatorInputs[4] = "LDR/MDR";
		}
		if (screenNumber == 208) {
			System.out.println("compromise rollover");
		}
		if (screenNumber == 209) {
			textField.requestFocus();
			textField.setEditable(true);
		}
		if (screenNumber == 210 && isVerified) {
			isVerified = false;
			System.out.println("COMSEC FUTURE KEYS OTAR");
			loadScreen(207);
		}
		if (screenNumber == 211 || screenNumber == 212 || screenNumber == 213 || screenNumber == 214) {
			textField.requestFocus();
			textField.setEditable(true);
		}
		if ((screenNumber == 215 || screenNumber == 216) && isVerified) {
			isVerified = false;
//			otar = new OTAR(selectedKey,currentSat.getName());
//			keyRequests.add(otar);
			System.out.println("COMSEC OTAR selectedKey -> "+selectedKey);
			loadScreen(207);
		}
		if (screenNumber == 227 && isVerified) {
			loadScreen(228);
		}
		if (screenNumber == 234) {
			reqLogonParams[0] = "HHR 8";
		}
		if (screenNumber == 235) {
			reqLogonParams[0] = "HHR 16";
		}
		if (screenNumber == 236) {
			reqLogonParams[0] = "HHR 32";
		}
		if (screenNumber == 237) {
			reqLogonParams[0] = "HHR 256";
		}
		if (screenNumber == 238 && isVerified) {
			isVerified = false;
			reqLogonParams[1] = "ANY";
			loadScreen(28);
		}
		if (screenNumber == 239 && isVerified) {
			isVerified = false;
			reqLogonParams[1] = "0.48";
			loadScreen(28);
		}
		if (screenNumber == 240 && isVerified) {
			isVerified = false;
			reqLogonParams[1] = "0.96";
			loadScreen(28);
		}
		if (screenNumber == 241 && isVerified) {
			isVerified = false;
			reqLogonParams[1] = "1.92";
			loadScreen(28);
		}
		if (screenNumber == 242 && isVerified) {
			isVerified = false;
			reqLogonParams[1] = "2.40";
			loadScreen(28);
		}
		if (screenNumber == 243 && isVerified) {
			isVerified = false;
			reqLogonParams[1] = "3.84";
			loadScreen(28);
		}
		if (screenNumber == 244 && isVerified) {
			isVerified = false;
			reqLogonParams[1] = "4.80";
			loadScreen(28);
		}
		if (screenNumber == 245 && isVerified) {
			isVerified = false;
			reqLogonParams[1] = "7.68";
			loadScreen(28);
		}
		if (screenNumber == 246 && isVerified) {
			isVerified = false;
			reqLogonParams[1] = "9.60";
			loadScreen(28);
		}
		if (screenNumber == 247 && isVerified) {
			isVerified = false;
			reqLogonParams[1] = "19.20";
			loadScreen(28);
		}
		if (screenNumber == 248 && isVerified) {
			isVerified = false;
			reqLogonParams[1] = "38.40";
			loadScreen(28);
		}
		if (screenNumber == 249 && isVerified) {
			isVerified = false;
			reqLogonParams[2] = "MOST";
			loadScreen(28);
		}
		if (screenNumber == 250 && isVerified) {
			isVerified = false;
			reqLogonParams[2] = "MEDIUM";
			loadScreen(28);
		}
		if (screenNumber == 251 && isVerified) {
			isVerified = false;
			reqLogonParams[2] = "LEAST";
			loadScreen(28);
		}
		if (screenNumber == 252 && isVerified) {
			isVerified = false;
			reqLogonParams[2] = "BOMBER";
			loadScreen(28);
		}
		if (screenNumber == 253 && isVerified) {
			isVerified = false;
			reqLogonParams[2] = "AUTO";
			loadScreen(28);
		}
		if (screenNumber == 262) {
			switches[0] = "ON";
		}
		if (screenNumber == 263) {
			switches[0] = "OFF";
		}
		if (screenNumber == 264) {
			switches[1] = "ON";
		}
		if (screenNumber == 265) {
			switches[1] = "OFF";
		}
		if (screenNumber == 266) {
			switches[2] = "ON";
		}
		if (screenNumber == 267) {
			switches[2] = "OFF";
		}
		if (screenNumber == 268) {
			switches[3] = "ON";
		}
		if (screenNumber == 269) {
			switches[3] = "OFF";
		}
		if (screenNumber == 270) {
			switches[4] = "ON";
		}
		if (screenNumber == 271) {
			switches[4] = "OFF";
		}
		if (screenNumber == 272) {
			switches[5] = "MANUAL ON";
		}
		if (screenNumber == 273) {
			switches[5] = "AUTO OFF";
		}
		if (screenNumber == 277 || screenNumber == 278) {
			textField.requestFocus();
			textField.setEditable(true);
		}
		if (screenNumber == 280 || screenNumber == 281) {
			textField.requestFocus();
			textField.setEditable(true);
		}
		if (screenNumber == 282 || screenNumber == 283) {
			textField.requestFocus();
			textField.setEditable(true);
		}
		if (screenNumber == 299) {
			textField.requestFocus();
			textField.setEditable(true);
		}
		if (screenNumber == 304 && isVerified) {
			isVerified = false;
			if (loggedOnSat) {
				if (nets.get(selectedNet).getStatus() == Status.PENDING 
						|| nets.get(selectedNet).getStatus() == Status.ACTIVE) {
					loadScreen(423);
				} else {
					nets.get(selectedNet).setFlag(true);
					if (!nets.get(selectedNet).getThread().isAlive()) {
						nets.get(selectedNet).getThread().start();
					}
					netQueries.add(nets.get(selectedNet));
					loadScreen(301);
				}
			} else {
				loadScreen(309);
			}
		}
		if (screenNumber == 305 && isVerified) {
			isVerified = false;
			if (loggedOnSat) {
				if (nets.get(selectedNet).getStatus() == Status.INACTIVE) {
					loadScreen(424);
				} else if (nets.get(selectedNet).getStatus() == Status.PENDING) {
					loadScreen(425);
				} else {
					nets.get(selectedNet).setFlag(true);
					netQueries.add(nets.get(selectedNet));
					loadScreen(301);
				}
			} else {
				loadScreen(309);
			}
		}
		if (screenNumber == 314 && isVerified) {
			isVerified = false;
			// SAVES TEMP NET PARAM CHANGES
			loadScreen(300);
		}
		if (screenNumber == 323) { // PERHAPS CLEAN THIS CODE UP
			String[] temp = nets.get(selectedNet).getTerminalParameters();
			temp[0] = "YES";
			nets.get(selectedNet).setTerminalParameters(temp);
		}
		if (screenNumber == 324) { // PERHAPS CLEAN THIS CODE UP
			String[] temp = nets.get(selectedNet).getTerminalParameters();
			temp[0] = "NO";
			nets.get(selectedNet).setTerminalParameters(temp);
		}
		if (screenNumber == 330) {
			String[] temp = nets.get(selectedNet).getTerminalParameters();
			temp[3] = "";
			nets.get(selectedNet).setTerminalParameters(temp);
		}
		if (screenNumber == 331) {
			String[] temp = nets.get(selectedNet).getTerminalParameters();
			temp[3] = "AG";
			nets.get(selectedNet).setTerminalParameters(temp);
		}
		if (screenNumber == 332) {
			String[] temp = nets.get(selectedNet).getTerminalParameters();
			temp[3] = "EC";
			nets.get(selectedNet).setTerminalParameters(temp);
		}
		if (screenNumber == 333) {
			String[] temp = nets.get(selectedNet).getTerminalParameters();
			temp[3] = "SA";
			nets.get(selectedNet).setTerminalParameters(temp);
		}
		if (screenNumber == 334) {
			String[] temp = nets.get(selectedNet).getTerminalParameters();
			temp[3] = "SB";
			nets.get(selectedNet).setTerminalParameters(temp);
		}
		if (screenNumber == 335) {
			String[] temp = nets.get(selectedNet).getTerminalParameters();
			temp[3] = "SC";
			nets.get(selectedNet).setTerminalParameters(temp);
		}
		if (screenNumber == 336) {
			String[] temp = nets.get(selectedNet).getTerminalParameters();
			temp[5] = "MOST";
			nets.get(selectedNet).setTerminalParameters(temp);
		}
		if (screenNumber == 337) {
			String[] temp = nets.get(selectedNet).getTerminalParameters();
			temp[5] = "MEDIUM";
			nets.get(selectedNet).setTerminalParameters(temp);
		}
		if (screenNumber == 338) {
			String[] temp = nets.get(selectedNet).getTerminalParameters();
			temp[5] = "LEAST";
			nets.get(selectedNet).setTerminalParameters(temp);
		}
		if (screenNumber == 339) {
			String[] temp = nets.get(selectedNet).getTerminalParameters();
			temp[5] = "AUTO";
			nets.get(selectedNet).setTerminalParameters(temp);
		}
		if (screenNumber == 340) {
			String[] temp = nets.get(selectedNet).getTerminalParameters();
			temp[6] = "1";
			nets.get(selectedNet).setTerminalParameters(temp);
		}
		if (screenNumber == 341) {
			String[] temp = nets.get(selectedNet).getTerminalParameters();
			temp[6] = "2";
			nets.get(selectedNet).setTerminalParameters(temp);
		}
		if (screenNumber == 342) {
			String[] temp = nets.get(selectedNet).getTerminalParameters();
			temp[6] = "3";
			nets.get(selectedNet).setTerminalParameters(temp);
		}
		if (screenNumber == 343) {
			String[] temp = nets.get(selectedNet).getTerminalParameters();
			temp[6] = "4";
			nets.get(selectedNet).setTerminalParameters(temp);
		}
		if (screenNumber == 344) {
			String[] temp = nets.get(selectedNet).getTerminalParameters();
			temp[6] = "1-2";
			nets.get(selectedNet).setTerminalParameters(temp);
		}
		if (screenNumber == 345) {
			String[] temp = nets.get(selectedNet).getTerminalParameters();
			temp[6] = "3-4";
			nets.get(selectedNet).setTerminalParameters(temp);
		}
		if (screenNumber == 346) {
			String[] temp = nets.get(selectedNet).getTerminalParameters();
			temp[6] = "1-2-3-4";
			nets.get(selectedNet).setTerminalParameters(temp);
		}
		if (screenNumber == 347) {
			String[] temp = nets.get(selectedNet).getTerminalParameters();
			temp[4] = "";
			nets.get(selectedNet).setTerminalParameters(temp);
		}
		if (screenNumber == 348) {
			String[] temp = nets.get(selectedNet).getTerminalParameters();
			temp[4] = "AG";
			nets.get(selectedNet).setTerminalParameters(temp);
		}
		if (screenNumber == 349) {
			String[] temp = nets.get(selectedNet).getTerminalParameters();
			temp[4] = "EC";
			nets.get(selectedNet).setTerminalParameters(temp);
		}
		if (screenNumber == 350) {
			String[] temp = nets.get(selectedNet).getTerminalParameters();
			temp[4] = "SA";
			nets.get(selectedNet).setTerminalParameters(temp);
		}
		if (screenNumber == 351) {
			String[] temp = nets.get(selectedNet).getTerminalParameters();
			temp[4] = "SB";
			nets.get(selectedNet).setTerminalParameters(temp);
		}
		if (screenNumber == 352) {
			String[] temp = nets.get(selectedNet).getTerminalParameters();
			temp[4] = "SC";
			nets.get(selectedNet).setTerminalParameters(temp);
		}
		if (screenNumber == 353) {
			String[] temp = nets.get(selectedNet).getTerminalParameters();
			temp[7] = "YES";
			nets.get(selectedNet).setTerminalParameters(temp);
		}
		if (screenNumber == 354) {
			String[] temp = nets.get(selectedNet).getTerminalParameters();
			temp[7] = "NO";
			nets.get(selectedNet).setTerminalParameters(temp);
		}
		if (screenNumber == 355 || screenNumber == 357) {
			textField.requestFocus();
			textField.setEditable(true);
		}
		if (screenNumber == 362) {
			String[] temp = nets.get(selectedNet).getServiceParameters1();
			temp[1] = "MIL";
			nets.get(selectedNet).setServiceParameters1(temp);
		}
		if (screenNumber == 363) {
			String[] temp = nets.get(selectedNet).getServiceParameters1();
			temp[1] = "UFO";
			nets.get(selectedNet).setServiceParameters1(temp);
		}
		if (screenNumber == 364) {
			String[] temp = nets.get(selectedNet).getServiceParameters1();
			temp[1] = "FEP";
			nets.get(selectedNet).setServiceParameters1(temp);
		}
		if (screenNumber == 365) {
			String[] temp = nets.get(selectedNet).getServiceParameters1();
			temp[1] = "SDL";
			nets.get(selectedNet).setServiceParameters1(temp);
		}
		if (screenNumber == 366) {
			String[] temp = nets.get(selectedNet).getServiceParameters1();
			temp[3] = "KIV-7M/TEXT";
			nets.get(selectedNet).setServiceParameters1(temp);
		}
		if (screenNumber == 367) {
			String[] temp = nets.get(selectedNet).getServiceParameters1();
			temp[3] = "KIV-7M/EAM";
			nets.get(selectedNet).setServiceParameters1(temp);
		}
		if (screenNumber == 368) {
			String[] temp = nets.get(selectedNet).getServiceParameters1();
			temp[3] = "KIV-7M/BAUDOT";
			nets.get(selectedNet).setServiceParameters1(temp);
		}
		if (screenNumber == 369) {
			String[] temp = nets.get(selectedNet).getServiceParameters1();
			temp[3] = "KIV-7M/DATA";
			nets.get(selectedNet).setServiceParameters1(temp);
		}
		if (screenNumber == 370) {
			String[] temp = nets.get(selectedNet).getServiceParameters1();
			temp[3] = "KGV-11/DATA";
			nets.get(selectedNet).setServiceParameters1(temp);
		}
		if (screenNumber == 371) {
			String[] temp = nets.get(selectedNet).getServiceParameters1();
			temp[3] = "RETARGET";
			nets.get(selectedNet).setServiceParameters1(temp);
		}
		if (screenNumber == 372) {
			String[] temp = nets.get(selectedNet).getServiceParameters1();
			temp[3] = "KGV-11/DATAX4";
			nets.get(selectedNet).setServiceParameters1(temp);
		}
		if (screenNumber == 373) {
			String[] temp = nets.get(selectedNet).getServiceParameters1();
			temp[3] = "KGV-11/TEXT";
			nets.get(selectedNet).setServiceParameters1(temp);
		}
		if (screenNumber == 374) {
			String[] temp = nets.get(selectedNet).getServiceParameters1();
			temp[3] = "KGV-11/EAM";
			nets.get(selectedNet).setServiceParameters1(temp);
		}
		if (screenNumber == 375) {
			String[] temp = nets.get(selectedNet).getServiceParameters1();
			temp[3] = "KGV-11/FDM";
			nets.get(selectedNet).setServiceParameters1(temp);
		}
		if (screenNumber == 376) {
			String[] temp = nets.get(selectedNet).getServiceParameters1();
			temp[3] = "AF REPORTBACK";
			nets.get(selectedNet).setServiceParameters1(temp);
		}
		if (screenNumber == 377) {
			String[] temp = nets.get(selectedNet).getServiceParameters1();
			temp[3] = "SERVICE VOICE";
			nets.get(selectedNet).setServiceParameters1(temp);
		}
		if (screenNumber == 378) {
			String[] temp = nets.get(selectedNet).getServiceParameters1();
			temp[3] = "BLACK/DATA";
			nets.get(selectedNet).setServiceParameters1(temp);
		}
		if (screenNumber == 379) {
			String[] temp = nets.get(selectedNet).getServiceParameters1();
			temp[3] = "SUB REPORTBACK";
			nets.get(selectedNet).setServiceParameters1(temp);
		}
		if (screenNumber == 380) {
			String[] temp = nets.get(selectedNet).getServiceParameters1();
			temp[3] = "KIV-7M/AB";
			nets.get(selectedNet).setServiceParameters1(temp);
		}
		if (screenNumber == 383) {
			String[] temp = nets.get(selectedNet).getServiceParameters1();
			temp[4] = "75";
			nets.get(selectedNet).setServiceParameters1(temp);
		}
		if (screenNumber == 384) {
			String[] temp = nets.get(selectedNet).getServiceParameters1();
			temp[4] = "150";
			nets.get(selectedNet).setServiceParameters1(temp);
		}
		if (screenNumber == 385) {
			String[] temp = nets.get(selectedNet).getServiceParameters1();
			temp[4] = "300";
			nets.get(selectedNet).setServiceParameters1(temp);
		}
		if (screenNumber == 386) {
			String[] temp = nets.get(selectedNet).getServiceParameters1();
			temp[4] = "600";
			nets.get(selectedNet).setServiceParameters1(temp);
		}
		if (screenNumber == 387) {
			String[] temp = nets.get(selectedNet).getServiceParameters1();
			temp[4] = "1200";
			nets.get(selectedNet).setServiceParameters1(temp);
		}
		if (screenNumber == 388) {
			String[] temp = nets.get(selectedNet).getServiceParameters1();
			temp[4] = "2400";
			nets.get(selectedNet).setServiceParameters1(temp);
		}
		if (screenNumber == 389) {
			String[] temp = nets.get(selectedNet).getServiceParameters1();
			temp[5] = "PRIMARY";
			nets.get(selectedNet).setServiceParameters1(temp);
		}
		if (screenNumber == 390) {
			String[] temp = nets.get(selectedNet).getServiceParameters1();
			temp[5] = "SECONDARY";
			nets.get(selectedNet).setServiceParameters1(temp);
		}
		if (screenNumber == 391) {
			String[] temp = nets.get(selectedNet).getServiceParameters1();
			temp[6] = "SHORT CONV";
			nets.get(selectedNet).setServiceParameters1(temp);
		}
		if (screenNumber == 392) {
			String[] temp = nets.get(selectedNet).getServiceParameters1();
			temp[6] = "MEDIUM CONV";
			nets.get(selectedNet).setServiceParameters1(temp);
		}
		if (screenNumber == 393) {
			String[] temp = nets.get(selectedNet).getServiceParameters1();
			temp[6] = "LONG CONV";
			nets.get(selectedNet).setServiceParameters1(temp);
		}
		if (screenNumber == 394) {
			String[] temp = nets.get(selectedNet).getServiceParameters1();
			temp[6] = "SHORT BLOCK";
			nets.get(selectedNet).setServiceParameters1(temp);
		}
		if (screenNumber == 395) {
			String[] temp = nets.get(selectedNet).getServiceParameters1();
			temp[6] = "LONG CONV";
			nets.get(selectedNet).setServiceParameters1(temp);
		}
		if (screenNumber == 398) {
			textField.requestFocus();
			textField.setEditable(true);
		}
		if (screenNumber == 403) {
			String[] temp = nets.get(selectedNet).getServiceParameters2();
			temp[0] = "YES";
			nets.get(selectedNet).setServiceParameters2(temp);
		}
		if (screenNumber == 404) {
			String[] temp = nets.get(selectedNet).getServiceParameters2();
			temp[0] = "NO";
			nets.get(selectedNet).setServiceParameters2(temp);
		}
		if (screenNumber == 405) {
			String[] temp = nets.get(selectedNet).getServiceParameters2();
			temp[2] = "NO";
			nets.get(selectedNet).setServiceParameters2(temp);
		}
		if (screenNumber == 406 || screenNumber == 407 || screenNumber == 408 || screenNumber == 409 || screenNumber == 410 
				|| screenNumber == 411 || screenNumber == 412 || screenNumber == 413 || screenNumber == 414) {
			String[] temp = nets.get(selectedNet).getServiceParameters2();
			temp[2] = "YES";
			nets.get(selectedNet).setServiceParameters2(temp);
		}
		if (screenNumber == 415) {
			String[] temp = nets.get(selectedNet).getServiceParameters2();
			temp[4] = "0";
			nets.get(selectedNet).setServiceParameters2(temp);
		}
		if (screenNumber == 416) {
			String[] temp = nets.get(selectedNet).getServiceParameters2();
			temp[4] = "1";
			nets.get(selectedNet).setServiceParameters2(temp);
		}
		if (screenNumber == 417) {
			String[] temp = nets.get(selectedNet).getServiceParameters2();
			temp[4] = "2";
			nets.get(selectedNet).setServiceParameters2(temp);
		}
		if (screenNumber == 418) {
			String[] temp = nets.get(selectedNet).getServiceParameters2();
			temp[4] = "3";
			nets.get(selectedNet).setServiceParameters2(temp);
		}
		if (screenNumber == 419) {
			String[] temp = nets.get(selectedNet).getServiceParameters2();
			temp[5] = "YES";
			nets.get(selectedNet).setServiceParameters2(temp);
		}
		if (screenNumber == 420) {
			String[] temp = nets.get(selectedNet).getServiceParameters2();
			temp[5] = "NO";
			nets.get(selectedNet).setServiceParameters2(temp);
		}
		if (screenNumber == 421) {
			String[] temp = nets.get(selectedNet).getServiceParameters2();
			temp[6] = "YES";
			nets.get(selectedNet).setServiceParameters2(temp);
		}
		if (screenNumber == 422) {
			String[] temp = nets.get(selectedNet).getServiceParameters2();
			temp[6] = "NO";
			nets.get(selectedNet).setServiceParameters2(temp);
		}
		if (screenNumber == 451) {
			String[] temp = nets.get(selectedNet).getTerminalParameters();
			temp[1] = "R01";
			temp[2] = "R01";
			nets.get(selectedNet).setTerminalParameters(temp);
		}
		if (screenNumber == 452) {
			String[] temp = nets.get(selectedNet).getTerminalParameters();
			temp[1] = "R02";
			temp[2] = "R02";
			nets.get(selectedNet).setTerminalParameters(temp);
		}
		if (screenNumber == 453) {
			String[] temp = nets.get(selectedNet).getTerminalParameters();
			temp[1] = "R03";
			temp[2] = "R03";
			nets.get(selectedNet).setTerminalParameters(temp);
		}
		if (screenNumber == 454) {
			String[] temp = nets.get(selectedNet).getTerminalParameters();
			temp[1] = "R04";
			temp[2] = "R04";
			nets.get(selectedNet).setTerminalParameters(temp);
		}
		if (screenNumber == 455) {
			String[] temp = nets.get(selectedNet).getTerminalParameters();
			temp[1] = "R06";
			temp[2] = "R06";
			nets.get(selectedNet).setTerminalParameters(temp);
		}
		if (screenNumber == 456) {
			String[] temp = nets.get(selectedNet).getTerminalParameters();
			temp[1] = "R07";
			temp[2] = "R07";
			nets.get(selectedNet).setTerminalParameters(temp);
		}
		if (screenNumber == 457) {
			String[] temp = nets.get(selectedNet).getTerminalParameters();
			temp[1] = "R10";
			temp[2] = "R10";
			nets.get(selectedNet).setTerminalParameters(temp);
		}
		if (screenNumber == 458) {
			String[] temp = nets.get(selectedNet).getTerminalParameters();
			temp[1] = "R11";
			temp[2] = "R11";
			nets.get(selectedNet).setTerminalParameters(temp);
		}
		
        if (screenNumber == 500) { // IF SCREEN IS 500 (VALUE TO CHANGE LATER!!!!)
        	textField.requestFocus();
        	textField.setEditable(true);
        }
		if (screenNumber == 998) {
			if (workingCount > 10) {
				workingCount = 0;
				loadScreen(1000);
			} else {
				loadScreen(999);
			}
			workingCount++;
		}
		if (screenNumber == 999) {
			if (workingCount > 10) {
				workingCount = 0;
				loadScreen(1000);
			} else {
				loadScreen(998);
			}
			workingCount++;
		}
		if (screenNumber == 1000) {
			tsmStatus = "IMG2019v23    29-200000Z-APR98";
			if (isVerified) {
				isVerified = false;
				loadScreen(1001);
			}
		}
		if (screenNumber == 1003) {
			if (workingCount > 5) {
				workingCount = 0;
				loadScreen(0);
			} else {
				if (workingCount == 4) {
					databaseSource = dbSource;
				}
				loadScreen(1011);
			}
			workingCount++;
		}
		if (screenNumber == 1006 || screenNumber == 1007) {
			textField.requestFocus();
			textField.setEditable(true);
		}
		if (screenNumber == 1008 && isVerified) {
			isVerified = false;
			newTime = true;
			textField.setText("");
			loadScreen(1009);
		}
		if (screenNumber == 1009) {
			if (workingCount > 8) {
				workingCount = 0;
				loadScreen(1012);
			} else {
				if (workingCount == 2) {
					timeSource = "KYBD";
				}
				loadScreen(1010);
			}
			workingCount++;
		}
		if (screenNumber == 1010) {
			if (workingCount > 8) {
				workingCount = 0;
				loadScreen(1012);
			} else {
				if (workingCount == 2) {
					timeSource = "KYBD";
				}
				loadScreen(1009);
			}
			workingCount++;
		}
		if (screenNumber == 1011) {
			if (workingCount > 5) {
				workingCount = 0;
				loadScreen(0);
			} else {
				if (workingCount == 4) {
					databaseSource = dbSource;
				}
				loadScreen(1003);
			}
			workingCount++;
		}
		if (screenNumber == 1014 || screenNumber == 1015) {
			dbSource = "IMG2019v23    29-200000Z-APR98";
			loadScreen(1021);
		}
		if (screenNumber == 1016) {
			dbSource = "TAC NVRAM";
			loadScreen(1021);
		}
		if (screenNumber == 1020) {
			if (workingCount > 10) {
				workingCount = 0;
				loadScreen(1022);
			} else {
				if (workingCount == 6) {
					databaseSource = dbSource;
				}
				loadScreen(1021);
			}
			workingCount++;
		}
		if (screenNumber == 1021) {
			if (workingCount > 10) {
				workingCount = 0;
				loadScreen(1022);
			} else {
				if (workingCount == 6) {
					databaseSource = dbSource;
				}
				loadScreen(1020);
			}
			workingCount++;
		}
		if (screenNumber == 1027 && isVerified) {
			isVerified = false;
			constellation = "MIL";
			loadScreen(1022);
		}
		if (screenNumber == 1028 && isVerified) {
			isVerified = false;
			constellation = "UFO";
			loadScreen(1022);
		}
		if (screenNumber == 1029) {
			startupMode = "AUTO";
		}
		if (screenNumber == 1030) {
			startupMode = "MANUAL";
		}
		if (screenNumber == 1031) {
			textField.requestFocus();
			textField.setEditable(true);
		}
		if (screenNumber == 1300) {
			isVerified = false;
			if (workingCount > 3) {
				workingCount = 0;
				if (startupIsComplete) {
					loadScreen(1301);
				} else {
					loadScreen(1302);
				}
			} else {
				loadScreen(33);
			}
			workingCount++;
		}
		
		// 1301 DOWNWARD SHOULD BE BIT SCREEN FOR WHEN THEY CHANGE
		
		if (bit != null && bit.getStatus() == Status.FINISHED) {
//			System.out.println(bit.getFaultCode());
			commonGroupBIT = "";
			EHFGroupBIT = "";
			bit.setStatus(Status.IDLE);
		}
		if (screenNumber == 1301 || screenNumber == 1302) {
			isVerified = false;
			printMode = "PRINT IMMEDIATE"; 
			if (bit == null) {
				bit = new BIT(); // Want to keep it here to check if already running			
			}
		}
		if (screenNumber == 1505 && isVerified) {
			isVerified = false;
			bit.setFlag(false);
			loadScreen(1530);
		}
		if (screenNumber == 1506 && isVerified) {
			isVerified = false;
			loadScreen(1531);
		}
		if (screenNumber == 1508 && isVerified) {
			isVerified = false;
			startBIT("BBP", null, 5);
			if (startupIsComplete) {
				loadScreen(1502);
			} else {
				loadScreen(1532);
			}
		}
		if (screenNumber == 1509 && isVerified) {
			isVerified = false;
			startBIT("TAC", null, 5);
			if (startupIsComplete) {
				loadScreen(1502);
			} else {
				loadScreen(1532);
			}
		}
		if (screenNumber == 1510 && isVerified) {
			isVerified = false;
			startBIT("TBI", null, 5);
			if (startupIsComplete) {
				loadScreen(1502);
			} else {
				loadScreen(1532);
			}
		}
		if (screenNumber == 1511 && isVerified) {
			isVerified = false;
			startBIT("TFSTST", null, 5);
			if (startupIsComplete) {
				loadScreen(1502);
			} else {
				loadScreen(1532);
			}
		}
		if (screenNumber == 1513 && isVerified) {
			isVerified = false;
			startBIT("DISTST", null, 5);
			loadScreen(1512);
		}
		if (screenNumber == 1514 && isVerified) {
			isVerified = false;
			startBIT("KEYTST", null, 5);
			loadScreen(1512);
		}
		if (screenNumber == 1518 && isVerified) {
			isVerified = false;
			startBIT(null, "EHFMOD", 5);
			if (startupIsComplete) {
				loadScreen(1503);
			} else {
				loadScreen(1533);
			}
		}
		if (screenNumber == 1519 && isVerified) {
			isVerified = false;
			startBIT(null, "RSUTST", 5);
			if (startupIsComplete) {
				loadScreen(1503);
			} else {
				loadScreen(1533);
			}
		}
		if (screenNumber == 1520 && isVerified) {
			isVerified = false;
			startBIT(null, "RSUVT", 5);
			if (startupIsComplete) {
				loadScreen(1503);
			} else {
				loadScreen(1533);
			}
		}
		if (screenNumber == 1521 && isVerified) {
			isVerified = false;
			startBIT(null, "RECVT", 5);
			if (startupIsComplete) {
				loadScreen(1503);
			} else {
				loadScreen(1533);
			}
		}
		if (screenNumber == 1523 && isVerified) {
			isVerified = false;
			startBIT(null, "EHFANT", 5);
			loadScreen(1522);
		}
		if (screenNumber == 1524 && isVerified) {
			isVerified = false;
			startBIT(null, "KIV7LP", 5);
			loadScreen(1522);
		}
		if (screenNumber == 1525 && isVerified) {
			isVerified = false;
			startBIT(null, "PABIT", 5);
			loadScreen(1522);
		}
		if (screenNumber == 1526 && isVerified) {
			isVerified = false;
			startBIT(null, "EHFRAD", 5);
			loadScreen(1522);
		}
	}
	
	// Updates Terminal Timing Every Second
	public void updateTime() {
		timing = new Time(timeSource, timeInput, newTime, calendar);
		calendar = timing.getCalendar();
		newTime = timing.isNewTime();
		time = timing.getTime();
	}
	
	// Updates Ephemeris Every 5 Seconds
	public void updateEphemeris() {
		if (currentSat != null) {
			if (ephemerisCount == 4 || ephemerisCount == 9) {
				ephem = new Ephemeris();
				ephemeris[0] = ephem.getRange();
				ephemeris[1] = ephem.getRangeRate();
				ephemeris[2] = ephem.getOffsetOne();
				ephemeris[3] = ephem.getOffsetTwo();
				ephemeris[4] = ephem.getOffsetThree();
				ephemeris[5] = ephem.getOffsetFour();
				if (loggedOnSat) {
					ephemeris[6] = ephem.getSNRL();
					ephemeris[7] = ephem.getSNRHC();
					ephemeris[8] = ephem.getSNRHF();
					ephemeris[9] = ephem.getESNR();
				} else {
					ephemeris[6] = "0.0";
					ephemeris[7] = "0.0";
					ephemeris[8] = "0.0";
					ephemeris[9] = "0.0";
				}
			}
			ephemerisCount++;
			mode = "TRACK";
			if (ephemerisCount >= 10) {
				ephemerisCount = 0;
				if (newEphemerisRequested) {
					newEphemerisRequested = false;
					if (loggedOnSat) {
						log.add(new Log(time, "EPHEMERIS UPDATED FOR SAT "+ephemSat, Status.ADVISORY));
						sats.get(ephemSat).setEphemerisCurrentness(true);
					}
				}
			}
		} else {
			mode = "STDBY";
		}
		if (!operatorInputs[0].equals("AUTO")) {
			ephemeris[2] = "0.0";
		}
		if (!operatorInputs[1].equals("AUTO")) {
			ephemeris[3] = "0.0";
		}
		if (!operatorInputs[2].equals("AUTO")) {
			ephemeris[4] = "0.0";
		}
		if (!operatorInputs[3].equals("AUTO")) {
			ephemeris[5] = "0.0";
		}
	}
	
	// Updates Agile Beam Status Every 54 Seconds
	private void updateAgileBeamStatus() {
		if (agileBeamCount >= 54) {
			agileBeamCount = 0;
			for (int i = 0; i < 4; i++) {
				agileBeamTimes[i] = times[rand.nextInt(7)];
			}
		}
		if (agileBeamCount == 20 || agileBeamCount == 47) {
			DecimalFormat decimalFormat = new DecimalFormat("#0.0");
			agileBeamNoise = decimalFormat.format((Math.random()*(41.0-39.5)+39.5));
		}
		agileBeamCount++;
	}
	
	// Cancels Current Swing Worker & Starts a New One for Specified Screen
	private void loadScreen(int screenNumber) {
		if (screens.getScreen(screenNumber) != null) {
			worker.cancel(true);
			startSwingWorker(screenNumber);
			this.screenNumber = screenNumber;
		}
	}
	
	// Sounds Alarm
	public void soundAlarm() {
		if (clip != null && clip.isActive()) {
			return;
		}
		try {
			AudioInputStream audioInput = AudioSystem.getAudioInputStream(audioURL);
			clip = AudioSystem.getClip();
			clip.open(audioInput);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Initiates BIT Testing
	private void startBIT(String commonGroupBIT, String EHFGroupBIT, int duration) {
		if (commonGroupBIT == null) {
			this.commonGroupBIT = "";
		} else {
			if (bit.getStatus() != Status.RUNNING) {
				this.commonGroupBIT = commonGroupBIT;
				bit.setBITCode(commonGroupBIT);
				bit.setDuration(duration);
				bit.setStatus(Status.RUNNING);
				return;
			}
		}
		if (EHFGroupBIT == null) {
			this.EHFGroupBIT = "";
		} else {
			if (bit.getStatus() != Status.RUNNING) {
				this.EHFGroupBIT = EHFGroupBIT;
				bit.setBITCode(EHFGroupBIT);
				bit.setDuration(duration);
				bit.setStatus(Status.RUNNING);
				return;
			}
		}
	}
	
	// NEED TO REFORMAT TIME (NOT CORRECT)
	// Checks if Manual Timing Input Matches Regex Pattern
	private boolean checkDateTimeFormat(String dateTime) {
		Pattern pattern = Pattern.compile("[\\d][\\d][-]([01]\\d|[0-3])[0-5]\\d[0-5]"
				+ "\\d[Z][-]([J][A][N]|[F][E][B]|[M][A][R]|[A][P][R]|[M][A][Y]|"
				+ "[J][U][N]|[J][U][L]|[A][U][G]|[S][E][P]|[O][C][T]|[N][O][V]|"
				+ "[D][E][C])[0-9][0-9]");
		Matcher matcher = pattern.matcher(dateTime);
		if (matcher.find()) {
			return true;
		} else {
			return false;
		}
	}
	
	// Verifies Navigational Data & Manual Pointing Data Input Matches Regex Pattern
	public boolean verifyEntry(String navDataType, String navDataInput) {
		Pattern pattern = null;
		switch (navDataType) {
		case "latitude": pattern = Pattern.compile("([0-8]?[0-9][ ][0-5][0-9]|[9][0][ ][0][0])([N]|[S])");
			break;
		case "longitude": pattern = Pattern.compile("(([0]?[0-9]|[1][0-7])?[0-9][ ][0-5][0-9]|[1][8][0][ ][0][0])([E]|[W])");
			break;
		case "altitude": pattern = Pattern.compile("([0-9]?[0-9]?[0-9]?[0-9]?[0-9]([.][0-9]?)?|[.][0-9]?)");
			break;
		case "heading": pattern = Pattern.compile("(([0-2]?[0-9]?[0-9]([.][0-9]?)?|[3]([0-5][0-9]([.][0-9]?)?|[6][0]([.][0]?)?))|[.][0-9]?)");
			break;
		case "pitch": pattern = Pattern.compile("[-]?(([0-8]?[0-9]([.][0-9]?)?|[9][0]([.][0]?)?)|[.][0-9]?)");
			break;
		case "roll": pattern = Pattern.compile("[-]?(([0]?[0-9]?[0-9]([.][0-9]?)?|[1]([0-7][0-9]([.][0-9]?)?|[8][0]([.][0]?)?))|[.][0-9]?)");
			break;
		default:
			break;
		}
		Matcher matcher = pattern.matcher(navDataInput);
		if (matcher.matches()) {
			return true;
		} else {
			return false;
		}
	}
	
	// Checks Presence of Crypto Keys for Satellite Acquisition
	public boolean checkCryptoKeys() {
		ArrayList<Character> keys = sats.get(currentSat.getName()).getKeys();
		if (keys.get(0) == 'Y' && keys.get(1) == 'Y' && keys.get(4) == 'Y') {
			return true;
		} else {
			return false;
		}
	}
	
	// Checks if Navigational Data is Accurate for Satellite Acquisition
	public boolean checkNavData() {
		if (!navData.getLatitude().equals(correctNavData[0])) {
			return false;
		}
		if (!navData.getLongitude().equals(correctNavData[1])) {
			return false;
		}
		if (!navData.getAltitude().equals(correctNavData[2])) {
			return false;
		}
		if (!navData.getHeading().equals(correctNavData[3])) {
			return false;
		}
		if (!navData.getPitch().equals(correctNavData[4])) {
			return false;
		}
		if (!navData.getRoll().equals(correctNavData[5])) {
			return false;
		}
		return true;
	}

	// Main Method
	public static void main(String args[]) {
		System.setProperty("sun.java2d.uiScale", "1.0");
		GUI gui = new GUI();
		gui.setSize(WIDTH,HEIGHT);
		gui.setResizable(false);
		gui.setTitle("MILSTAR SIMULATOR");
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.getContentPane().setBackground(Color.BLACK);
		gui.setLocationRelativeTo(null);
		gui.setLayout(null);
		gui.setVisible(true);
	}
}
