package peiliping.block;

public abstract class IOThread implements Runnable{
	
	private Object lock;
	
	public IOThread(Object lock ) {
		this.lock = lock ;
	}
	
	@Override
	public void run() {
		
		handle();
		
		synchronized (lock) {
			lock.notifyAll();
		}
	}
	
	public abstract void handle();
	
}
