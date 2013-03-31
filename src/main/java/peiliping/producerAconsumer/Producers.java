package peiliping.producerAconsumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Producers<V> {
	
	protected ExecutorService producersPool;

	protected Storage<V> storage;
	
	protected long sleep_time = 500 ; 

	public Producers(int maxThreadsNum,int concurrencyThreadsNum,long sleep_time,Storage<V> storage) {
		this.producersPool = new ThreadPoolExecutor(concurrencyThreadsNum,
				maxThreadsNum, 0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());
		this.storage = storage;
		this.sleep_time = sleep_time;
	}

	public void run(ProducerThread<V> ...pthreads) {
		if (pthreads == null) {
			return;
		}
		for (ProducerThread<V> p : pthreads) {
			p.setAddL(new AddListener());
			producersPool.execute(p);
		}
	}
	
	public class AddListener{
		public void add(V v) {
			while(true){
				if(storage.add(v)){
					System.out.println("add");
					return ;
				}else{
					try {
						Thread.sleep(sleep_time);
					} catch (InterruptedException e) {
					}
				}
			}
		}
	}
}
