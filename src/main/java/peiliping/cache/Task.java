package peiliping.cache;


public class Task extends Thread {
	
	LRUCache LC; 
	
	int r;
	
	Task(LRUCache LC,int r){
		this.LC = LC ;
		this.r=r;
	}

	@Override
	public void run() {
		int i = 0 ;
		while (i++ < 100) {
			Object o = LC.get(r);
			if (o == null) {
				LC.put(r, r);
			}
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
			}
		}
	}

}
