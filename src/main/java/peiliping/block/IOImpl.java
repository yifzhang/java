package peiliping.block;


public class IOImpl extends IOThread {

	public IOImpl(MainThread mt) {
		super(mt);
	}

	@Override
	protected void handle() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
	}

}
