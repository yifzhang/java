package peiliping.guava;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

public class Test {
	
	public static class DO {
		
		public String id ;
		
		public String name ;
		
		public DO(String id,String name){
			this.id = id ;
			this.name = name;
		}
		
	}
	
	
	public static void main(String[] args) {
	    ArrayList<DO> a = Lists.newArrayList();
	    a.add(new DO("a","b"));
	    a.add(new DO("c","d"));
	    a.add(new DO("e","f"));
	    a.add(new DO("g","h"));

	    List<String> newlist = Lists.transform(a, new Function<DO, String>(){
			@Override
			public String apply(DO input) {
				return input.name;
			}
	    });
	    
	    for(String t : newlist){
	    	System.out.println(t);
	    }
	    
	    
	}

}
