package peiliping.asm;


public class ClassLoader extends java.lang.ClassLoader {
	
	  @SuppressWarnings("unchecked")  
	    public  Class defineClassByName(String name,byte[] b,int off,int len){   
	        Class clazz = super.defineClass(name,b, off, len);  
	        return clazz;   
	      } 

}
