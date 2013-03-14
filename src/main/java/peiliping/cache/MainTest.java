package peiliping.cache;

public class MainTest {

	public static void main(String[] args) throws InterruptedException {

		LRUCache LC = new LRUCache(6, 6000 * 1000);
		
		Task t0 = new Task(LC,0);			 t0.start();
		Task t3 = new Task(LC,2);			 t3.start();
		Task t4 = new Task(LC,3);			 t4.start();
		Task t2 = new Task(LC,0);			 t2.start();
		Task t5 = new Task(LC,4);			 t5.start();
		Task t6 = new Task(LC,5);			 t6.start();
		Task t7 = new Task(LC,6);			 t7.start();
		Task t1 = new Task(LC,0);			 t1.start();
		Task t8 = new Task(LC,7);			 t8.start();
		Task t9 = new Task(LC,8);			 t9.start();
		
		while(true){
			System.out.println(LC.toLog());
			Thread.sleep(1000);
		}
		
	}

}
