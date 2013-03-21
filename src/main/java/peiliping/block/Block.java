package peiliping.block;

import java.util.concurrent.atomic.AtomicBoolean;

public class Block {
	
	public static void main(String[] args) throws InterruptedException {
	
		final Object o = new Object(); 
		
		final AtomicBoolean ob= new AtomicBoolean(true);
		
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//				}
				synchronized (o) {
					o.notifyAll();
					ob.set(false);
				}
			}
		});
		t.start();
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//				}
				synchronized (o) {
					try {
						
						if(!ob.get()){
							System.out.println("cccccccc");
							return ;
						}
						
						o.wait(1000);
						System.out.println("t w");
						System.out.println(System.currentTimeMillis());
					} catch (InterruptedException e) {
					}
				}
			}
		});
		t2.start();
		
		System.out.println(System.currentTimeMillis());
////		synchronized (o) {
////			o.wait();
////		}
//		System.out.println(System.currentTimeMillis());
	}

}
