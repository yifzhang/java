package peiliping;

public class Test {
	
	
	public static String a = "aBC" ;
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
	
		
		Class c = Class.forName("peiliping.Test") ;
		
		Test t = (Test) c.newInstance();
		
		System.out.println(t.a);
		
	}
}
