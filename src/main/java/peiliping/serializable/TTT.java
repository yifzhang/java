package peiliping.serializable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;

public class TTT {
	
	private static Gson GSON = new GsonBuilder()
	.setLongSerializationPolicy(LongSerializationPolicy.STRING)
	.disableHtmlEscaping().create();
	
	public static void main(String[] args) throws IOException {
		String test = "";
		String filepath = "/home/peiliping/dev/gitwork/self/java/src/main/java/peiliping/serializable/G.txt" ;
		File file = new File(filepath);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String tempString = null;
		while ((tempString = reader.readLine()) != null) {
			test = test + tempString ;
		}
		System.out.println(test);
		long t = System.currentTimeMillis();
		for(int i=0;i<1000;i++){
			List<Object> jo = JSON.parseArray(test);
			((Map)jo.get(0)).get("agent_version");
			((Map)jo.get(0)).get("agent_version");
			((Map)jo.get(0)).get("agent_version");
		}
		System.out.println(System.currentTimeMillis()-t);
//		System.out.println(((Map<?, ?>)l.get(0)).get("agent_version"));
		t = System.currentTimeMillis();
		for(int i=0;i<1000;i++){
			ObjectMapper mapper = new ObjectMapper();		
			List l = mapper.readValue(test, List.class);
			((Map<?, ?>)l.get(0)).get("agent_version");
			((Map<?, ?>)l.get(0)).get("agent_version");
			((Map<?, ?>)l.get(0)).get("agent_version");
		}
		System.out.println(System.currentTimeMillis()-t);
//		System.out.println(((Map)jo.get(0)).get("agent_version"));
		
	}
}
