package peiliping;



public class Test {
	
	public static void main(String[] args) {
		
		long t = System.currentTimeMillis();
		
		System.out.println((t+1000*60*60*8)/86400000);
		System.out.println((t-1000*60*60*4)/86400000);
		
	}
}
