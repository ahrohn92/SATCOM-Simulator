import java.util.Random;

public class Acquisition {
	
	String currentStep;
	Status status;
	boolean flag = false;
	Random rand = new Random();
	
	public Acquisition(String satStatus) throws InterruptedException { // boolean prerequisitesMet
		

		//System.out.println("satStatus passed to Acq class -> "+satStatus);
		
		if (satStatus.equals("LOGGED OFF") || satStatus.equals("")) {
			Thread thread = new Thread(new Runnable() {
				public void run() {
					status = Status.ACQUIRING_DL;
					currentStep = "DL WORKING   -  INITIALIZATION";
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace(); // REPLACE WITH ACQ FAILED MESSAGE ???
					}
					
					
					if (flag == true) {
						int x = rand.nextInt(18)+1; // RANDOM NUMBER BETWEEN 1 and 18
						//System.out.println("MIN TO ACQUIRE -> "+x); // TEST PRINT
						for (int i = 0; i < x; i++) {
							currentStep = "DL WORKING   -   "+(28-i)+" MIN TO ACQUIRE";
							try {
								Thread.sleep(1000); // 60000 = MINUTE, 1000 = 1 SECOND
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
						currentStep = "DL COMPLETE  -  WAITING FOR DL TRACK";
						try {
							Thread.sleep(60000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						currentStep = "DL COMPLETE  -  WAITING FOR HPA WARMUP";
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						status = Status.ACQUIRING_UL;
						currentStep = "DL COMPLETE  -  STARTING UL";
						try {
							Thread.sleep(30000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						currentStep = "DL COMPLETE  -  WAITING FOR ACCESS";
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						currentStep = "DL COMPLETE  -  COARSE PROBE   -  1 MIN";
						try {
							Thread.sleep(2000);
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
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						status = Status.FINISHED;
						currentStep = "LOGGED ON";
						
					} else { // IF DL ACQ FAILS (CRYPTO KEYS OR NAV DATA IS INCORRECT)
						
						for (int i = 0; i < 4; i++) {
							currentStep = "DL WORKING   -   0 MIN TO ACQUIRE";
							try {
								Thread.sleep(1000); // 60000 = MINUTE, 1000 = 1 SECOND
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							currentStep = "DL WORKING   -   1 MIN TO ACQUIRE";
							try {
								Thread.sleep(1000); // 60000 = MINUTE, 1000 = 1 SECOND
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							currentStep = "DL WORKING   -   0 MIN TO ACQUIRE";
							try {
								Thread.sleep(8000); // 60000 = MINUTE, 1000 = 1 SECOND
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
								try {
									Thread.sleep(1000); // 60000 = MINUTE, 1000 = 1 SECOND
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								break;
							default:
								break;
							}
						}
						status = Status.DL_FAILED;
						currentStep = "LOGGED OFF";
					}
				}
			});
			thread.start();
		}
		
		if (satStatus.equals("LOGGED ON")) {
			Thread thread = new Thread(new Runnable() {

				@Override
				public void run() {
					status = Status.PENDING;
					currentStep = "LOGOFF PENDING";
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					status = Status.LOGGED_OFF;
					currentStep = "LOGGED OFF";
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
	
	public boolean getFlag() {
		return flag;
	}
	
}
