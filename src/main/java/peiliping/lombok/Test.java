package peiliping.lombok;

public class Test {

	public static void main(String[] args) {
		
		DO d = new DO();
		d.setId(100);
		
		System.out.println(d.toString());
		
		Result r = new Result();
		r.setD(d);
		
		System.out.println(r.toString());
		
	}
	
}
