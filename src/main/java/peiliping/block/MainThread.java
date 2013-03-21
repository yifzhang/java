package peiliping.block;

import java.util.concurrent.atomic.AtomicBoolean;

public abstract class MainThread {

	private LOCK lock = new LOCK();

	public LOCK getLock() {
		return lock;
	}

	public void run(IOThread iot) throws InterruptedException {
		new Thread(iot).start();
		synchronized (lock) {
			if(!lock.finished.get()){
				lock.wait();
			}
		}
	}

	public class LOCK {
		 public AtomicBoolean finished = new AtomicBoolean(false);
	}
}
