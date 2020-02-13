import java.util.Random;

public class Acquisition {
	
	private String currentStep;
	private Status status;
	private boolean flag = false;
	private Random rand = new Random();
	
	public Acquisition(String satStatus, String startupMode) throws InterruptedException {
		
		if (satStatus.equals("LOGGED ON")) {
			Thread thread = new Thread(new Runnable() {
				public void run() {
					status = Status.PENDING;
					currentStep = "LOGOFF PENDING";
					try {
						Thread.sleep(10000); // 10000
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					status = Status.LOGGED_OFF;
					currentStep = "LOGGED OFF";
				}
			});
			thread.start();
		} else if (satStatus.equals("DL COMPLETE")) {
			Thread thread = new Thread(new Runnable() {
				public void run() {
					currentStep = "DL COMPLETE  -  WAITING FOR DL TRACK";
					try {
						Thread.sleep(1000); // 60000
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					currentStep = "DL COMPLETE  -  WAITING FOR HPA WARMUP";
					try {
						Thread.sleep(1000); // 5000
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					status = Status.ACQUIRING_UL;
					currentStep = "DL COMPLETE  -  STARTING UL";
					try {
						Thread.sleep(1000); // 30000
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					currentStep = "DL COMPLETE  -  WAITING FOR ACCESS";
					try {
						Thread.sleep(1000); // 2000
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					currentStep = "DL COMPLETE  -  COARSE PROBE   -  1 MIN";
					try {
						Thread.sleep(1000); // 2000
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					currentStep = "DL COMPLETE  -  CONTENTION PROBING";
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					currentStep = "DL COMPLETE  -  FINE PROBING";
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					status = Status.UL_COMPLETE;
					currentStep = "DL COMPLETE  -  UL COMPLETE";
					try {
						Thread.sleep(5000); // 5000
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					status = Status.FINISHED;
					currentStep = "LOGGED ON";
				}
			});
			thread.start();
		} else {
			Thread thread = new Thread(new Runnable() {
				public void run() {
					status = Status.ACQUIRING_DL;
					currentStep = "DL WORKING   -  INITIALIZATION";
					try {
						Thread.sleep(1000); // 5000
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (flag) {
						int x = rand.nextInt(18)+1; // RANDOM NUMBER BETWEEN 1 and 18
						//System.out.println("MIN TO ACQUIRE -> "+x); // TEST PRINT
						for (int i = 0; i < x; i++) {
							currentStep = "DL WORKING   -   "+(28-i)+" MIN TO ACQUIRE";
							try {
								Thread.sleep(1000); // 60000
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						currentStep = "DL WORKING   -  COARSE TIME INTERP";
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						status = Status.DL_COMPLETE;
						
						// If Startup Mode is "AUTO", Then Uplink Will be Acquired Automatically
						if (startupMode.equals("AUTO")) {
							currentStep = "DL COMPLETE  -  WAITING FOR DL TRACK";
							try {
								Thread.sleep(1000); // 60000
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							currentStep = "DL COMPLETE  -  WAITING FOR HPA WARMUP";
							try {
								Thread.sleep(1000); // 5000
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							status = Status.ACQUIRING_UL;
							currentStep = "DL COMPLETE  -  STARTING UL";
							try {
								Thread.sleep(1000); // 30000
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							currentStep = "DL COMPLETE  -  WAITING FOR ACCESS";
							try {
								Thread.sleep(1000); // 2000
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							currentStep = "DL COMPLETE  -  COARSE PROBE   -  1 MIN";
							try {
								Thread.sleep(1000); // 2000
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							currentStep = "DL COMPLETE  -  CONTENTION PROBING";
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							currentStep = "DL COMPLETE  -  FINE PROBING";
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							status = Status.UL_COMPLETE;
							currentStep = "DL COMPLETE  -  UL COMPLETE";
							try {
								Thread.sleep(1000); // 5000
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							status = Status.FINISHED;
							currentStep = "LOGGED ON";
						} else {
							currentStep = "DL COMPLETE"; // NO IDEA WHAT THIS IS SUPPOSED TO SAY
						}
					} else { // IF DL ACQ FAILS (CRYPTO KEYS OR NAV DATA IS INCORRECT)
						for (int i = 0; i < 5; i++) {
							currentStep = "DL WORKING   -   0 MIN TO ACQUIRE";
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							currentStep = "DL WORKING   -   1 MIN TO ACQUIRE";
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							currentStep = "DL WORKING   -   0 MIN TO ACQUIRE";
							try {
								Thread.sleep(1000); // 23000
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							switch (i) {
							case 0: status = Status.DL_FAILED_A;
								break;
							case 1: status = Status.DL_FAILED_B;
								break;
							case 2: status = Status.DL_FAILED_C;
								break;
							case 3: status = Status.DL_FAILED_D;
								break;
							case 4: status = Status.DL_FAILED;
								try {
									Thread.sleep(1000); // 2000
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								status = Status.NO_SAT;
								break;
							default: status = Status.DL_FAILED;
								break;
							}
						}
						currentStep = "LOGGED OFF";
					}
				}
			});
			thread.start();
		}
	}
	
	// Returns Current Step
	public String getCurrentStep() {
		return currentStep;
	}
	
	// Returns Status
	public Status getStatus() {
		return status;
	}
	
	// Set Flag
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	// Returns Flag
	public boolean getFlag() {
		return flag;
	}
}
