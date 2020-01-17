import java.util.HashMap;

public class BIT {

	private Status status;
	private long duration;
	String BITCode;
	boolean run = true;
	private String faultCode;
	// Create HashMap of Fault Codes, Key ex = "EHFMOD", Value ex = array of LRU specific
	// fault codes
	private HashMap<String, String[]> faultCodes = new HashMap<String, String[]>();
	Thread thread;
	
	public BIT() {
		
		String ehfmodFaults[] = {"EHFMOD FAULT 1", "EHFMOD FAULT 2", "EHFMOD FAULT 3"};
		// Create Fault Codes Here
		faultCodes.put("EHFMOD", ehfmodFaults);
		
		thread = new Thread(new Runnable() {
			
			public void run() {
				
				while(run == true) {
					
					System.out.print(""); // DOESN'T WORK WITHOUT THIS FOR SOME REASON
										  // HAS TO DO WITH CONCURRENY
					
					if (status == Status.RUNNING) {
	
						try {
							Thread.sleep(duration);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

						status = Status.FINISHED;
						
						// CONDITIONS FOR MATCHING FAULTS TO BITCODE
						if (BITCode.equals("EHFMOD")) {
							faultCode = ehfmodFaults[2]; // RANDOMIZE
						}
					}
				}
			}
		});
		thread.start();
	}
		
	public String getCode() {
		return null;
	}
	
	public void setBITCode(String BITCode) {
		this.BITCode = BITCode;
	}
	
	public void setDuration(int x) {
		duration = 1000 * x;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public void setFlag(boolean run) {
		this.run = run;
	}
	
	public Status getStatus() {
		return this.status;
	}

	public String getFaultCode() {
		return faultCode;
	}
}
