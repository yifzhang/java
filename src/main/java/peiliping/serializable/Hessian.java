package peiliping.serializable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.caucho.hessian.io.HessianOutput;

public class Hessian {
	
	public static void main(String[] args) throws IOException {
		
		BizOrderDO b = new BizOrderDO();
//		b.map.put("id",123);
//		b.map.put("id1",123);
//		b.map.put("id2",123);
//		b.map.put("id3",123);
//		b.map.put("id4",123);
//		b.map.put("id5",123);
//		b.map.put("id6",123);
//		b.map.put("id7",123);
//		b.map.put("id8",123);
//		b.map.put("id9",123);
//		b.map.put("name","sdf");
//		b.map.put("status",1);
//		b.map.put("status1",1);
//		b.map.put("status2",1);
//		b.map.put("status3",1);
//		b.map.put("status4",1);
		
		long to = 0 ;
		
		for(int j=0;j<1;j++){
			long t = System.currentTimeMillis();
		
		for(int i=0;i<1;i++){
			ByteArrayOutputStream os = new ByteArrayOutputStream();  
			HessianOutput ho = new HessianOutput(os);  
			ho.writeObject(b);  
			System.out.println(os.toByteArray().length);
		}
		
		to+=(System.currentTimeMillis()-t);
		}
//		System.out.println(to/100);
	}

}
