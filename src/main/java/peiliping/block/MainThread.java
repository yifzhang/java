package peiliping.block;


public abstract class MainThread {
	
	private Object LOCK = new Object();
	
	public abstract void handle(); 
	
	public void run(IOThread iot){
		new Thread(iot).run();
		try {
			LOCK.wait(10000);
		} catch (InterruptedException e) {
		}
	}
	

}
