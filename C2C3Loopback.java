import java.util.Random;

public class C2C3Loopback {

	Thread thread;
	Status status = Status.PENDING;
	int numAttempts;
	Random rand;
	
	public C2C3Loopback(String satStatus) {
		
		thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				if (satStatus.equals("LOGGED ON")) {
					status = Status.STARTED;
					determineNumAttempts();
					try {
						Thread.sleep(4000);
						status = Status.SUCCESSFUL;
					} catch (InterruptedException e) {
						numAttempts = 4;
						status = Status.FAILED;
					}
				} else {
					status = Status.STARTED;
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						status = Status.FAILED;
					}
					numAttempts = 4;
					status = Status.FAILED;
				}
			}
		});
		thread.start();
	}
	
	// Determines Probability of Number of Attempts
	private void determineNumAttempts() {
		rand = new Random();
		int x = rand.nextInt(100) + 1;
		if (x < 95) {
			numAttempts = 1;
		} else if (95 <= x && x < 98) {
			numAttempts = 2;
		} else if (98 <= x && x < 100) {
			numAttempts = 3;
		} else {
			numAttempts = 4;
		}
	}
	
	// Getter Method for Status
	public Status getStatus() {
		return status;
	}
	
	// Getter Method for Number of Attempts
	public int getNumAttempts() {
		return numAttempts;
	}
}
