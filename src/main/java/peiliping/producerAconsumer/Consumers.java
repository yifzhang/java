package peiliping.producerAconsumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Consumers<V>{

	protected ExecutorService consumersPool;

	protected Storage<V> storage;
	
	protected long sleep_time = 500 ;

	public Consumers(int maxThreadsNum,int concurrencyThreadsNum,long sleep_time,Storage<V> storage) {
		this.consumersPool = new ThreadPoolExecutor(concurrencyThreadsNum,
				maxThreadsNum, 0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());
		this.storage = storage;
		this.sleep_time = sleep_time;
	}

	public void run(ConsumerThread<V> ...cthreads) {
		if (cthreads == null) {
			return;
		}
		for (ConsumerThread<V> c : cthreads) {
			c.setDelL(new DelListener());
			consumersPool.execute(c);
		}
	}
	
	public class DelListener {
		public V del(){
			V v ;
			while(true){
				 v = storage.del();
				if(v!=null){
					System.out.println("Del");
					return v ;
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
