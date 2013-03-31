package peiliping.producerAconsumer;

import peiliping.producerAconsumer.Consumers.DelListener;

public abstract class ConsumerThread<V> implements Runnable {

	protected DelListener delL;

	public void setDelL(DelListener delL) {
		this.delL = delL;
	}

}
