
public class OTAR {

	int selectedKey;
	String selectedSat;
	boolean flag = false;
	Status status;
	
	public OTAR(int selectedKey, String selectedSat) {
		this.selectedKey = selectedKey;
		this.selectedSat = selectedSat;
		
		// TRANSEC Keys
		if (selectedKey < 10) {
			Thread thread = new Thread(new Runnable() {
				public void run() {
					flag = true;
					status = Status.PENDING;
					try {
						Thread.sleep(20000);
					} catch (InterruptedException e) {
						status = Status.FAILED;
						e.printStackTrace();
					}
					if ((selectedSat.equals("SAT02") || selectedSat.equals("SAT08")) && 
							!(selectedKey == 2 || selectedKey == 3 || selectedKey == 7 || selectedKey == 8)) {  // Authorized Keys & Sats
						status = Status.STARTED;
						try {
							Thread.sleep(1000);  // MAYBE REMOVE
						} catch (InterruptedException e) {
							status = Status.FAILED;
							e.printStackTrace();
						}
						try {
							Thread.sleep(15000);
						} catch (InterruptedException e) {
							status = Status.FAILED;
							e.printStackTrace();
						}
						status = Status.SUCCESSFUL;
					} else {
						status = Status.DENIED;
					}
				}
			});
			thread.start();
		}
		
		// COMSEC Keys
//		if (selectedKey == 10 || selectedKey == 11) { // TEST VALUES
//			Thread thread = new Thread(new Runnable() {
//				public void run() {
//					System.out.println();
////					status = Status.PENDING;
//				}
//			});
//			thread.start();
//		}
	}
	
	// Returns Selected Key
	public int getSelectedKey() {
		return selectedKey;
	}
	
	// Returns Selected Sat
	public String getSelectedSat() {
		return selectedSat;
	}
	
	// Sets New Message() {
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	// Returns New Message
	public boolean getFlag() {
		return flag;
	}
	
	// Returns Status
	public Status getStatus() {
		return status;
	}
}