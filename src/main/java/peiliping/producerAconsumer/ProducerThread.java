package peiliping.producerAconsumer;

import peiliping.producerAconsumer.Producers.AddListener;

public abstract class ProducerThread<V> implements Runnable {

	protected AddListener addL;

	public void setAddL(AddListener addL) {
		this.addL = addL;
	}

}
