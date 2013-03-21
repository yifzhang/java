package peiliping.block;

import peiliping.block.MainThread.LOCK;

public abstract class IOThread implements Runnable{
	
	private LOCK lock;
	
	public IOThread(MainThread mt) {
		this.lock = mt.getLock() ;
	}
	
	@Override
	public void run() {
		handle();
		synchronized (lock) {
			lock.finished.set(true);
			lock.notify();
		}
	}
	
	protected abstract void handle();
	
}
