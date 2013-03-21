package peiliping.block;

public class Block extends MainThread {
	
	public static void main(String[] args) throws InterruptedException {
	
		Block b = new Block();
		
		b.run(new IOImpl(b));
		
	} 
}
