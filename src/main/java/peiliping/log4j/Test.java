package peiliping.log4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {
	
	private static final Logger logger = LoggerFactory.getLogger(Test.class); 
	
	public static void main(String[] args) {
		
		logger.info("Hello {}","TestLogback"); 
		
	}

}
