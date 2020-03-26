import java.text.DecimalFormat;

public class Net {
	
	// Net Parameters

	private int netNumber;
	private String netName;
	
	
	
	
	
	
	
	
	
	
	
	private Thread thread;
	String netStatus = "IA";
	Status status = Status.INACTIVE;
	boolean isSelected = false;
	boolean flag = false;
	
	// Default Net Configurations
	String[] terminalParameters = {"NO","R01","R01","","","LEAST","1","NO"};
	String[] serviceParameters1 = {"XXXXXX","MIL","XX","KGV-11/DATA","2400","PRIMARY","LONG CONV"};
	String[] serviceParameters2 = {"NO","-","YES","XX","0","NO","NO"};
	
//	
	public Net(int netNumber, String netName) {
		this.netNumber = netNumber;
		this.netName = netName;

		// Executable Thread
		thread = new Thread(new Runnable() {
			@Override
			public void run() {

				while (true) {  	// NEED TO SYNCHRONIZE THREADS
					
					System.out.print(""); // DOESN'T WORK WITHOUT THIS FOR SOME REASON
					  						// HAS TO DO WITH CONCURRENY
					if (status == Status.INACTIVE && flag == true) {
						status = Status.PENDING;
						netStatus = "PD";
						try {
							Thread.sleep(20000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						status = Status.ACTIVE;
						netStatus = "AC";
						flag = false;
					}
				 	if (status == Status.ACTIVE && flag == true) {
						status = Status.PENDING;
						netStatus = "PD";
						try {
							Thread.sleep(20000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						status = Status.INACTIVE;
						netStatus = "IA";
						flag = false;
				 	}
				}
			}
		});
	}
	
	// Returns Net Name
	public String getName() {
		return netName;
	}
	
	// Setter and Getter Methods for Net Parameters
	public void setTerminalParameters(String[] terminalParameters) {
		this.terminalParameters = terminalParameters;
	}
	
	public String[] getTerminalParameters() {
		return terminalParameters;
	}
	
	public void setServiceParameters1(String[] serviceParameters1) {
		this.serviceParameters1 = serviceParameters1;
	}
	
	public String[] getServiceParameters1() {
		return serviceParameters1;
	}
	
	public void setServiceParameters2(String[] serviceParameters2) {
		this.serviceParameters2 = serviceParameters2;
	}
	
	public String[] getServiceParameters2() {
		return serviceParameters2;
	}
	
	// Setter Method for isSelected
	public void isSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	
	// Getter Method for Net Number
	public int getNetNumber() {
		return netNumber;
	}
	
	// Setter Method for Status
	public void setStatus(Status status) {
		this.status = status;
	}
	
	// Getter Method for Status
	public Status getStatus() {
		return status;
	}
	
	// Getter Method for Thread
	public Thread getThread() {
		return thread;
	}
	
	// Setter Method for Flag
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	// Setter Methos for Net Status
	public void setNetStatus(String netStatus) {
		this.netStatus = netStatus;
	}
	
	// Getter Method for Net Status
	public String getNetStatus() {
		return netStatus;
	}
}
