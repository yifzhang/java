package peiliping.serializable;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class FJson {
	
	public static void main(String[] args) {
		
		HashMap<String,Map> m = new HashMap<String, Map>();
		Map<String,String> d = new HashMap<String, String>();
		d.put("driverClassName", "com.mysql.jdbc.Driver");
		d.put("url","jdbc:mysql://127.0.0.1:3306/test");
		d.put("username","plp");
		d.put("password","plp");
		d.put("initialSize","5");
		d.put("minIdle","5");
		d.put("maxActive","50");
		d.put("maxWait","5000");
		d.put("filters","stat,slf4j");
		d.put("connectionProperties","druid.stat.slowSqlMillis=1000");
		d.put("timeBetweenLogStatsMillis","120000");
		d.put("timeBetweenEvictionRunsMillis","60000");
		d.put("minEvictableIdleTimeMillis","300000");		
		d.put("validationQuery","SELECT 'x' FROM DUAL");
		d.put("testWhileIdle","true");
		d.put("testOnBorrow","false"); 
		d.put("testOnReturn","false"); 		
		d.put("poolPreparedStatements","false");
		d.put("maxPoolPreparedStatementPerConnectionSize","20");
		d.put("removeAbandoned","true");
		d.put("removeAbandonedTimeout","1200");
		d.put("logAbandoned","true");
		m.put("vpsds", d);
		
		System.out.println(JSON.toJSONString(m));
	}

}
