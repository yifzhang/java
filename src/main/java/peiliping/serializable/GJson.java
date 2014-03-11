package peiliping.serializable;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;

public class GJson {

	private static Gson GSON = new GsonBuilder()
			.setLongSerializationPolicy(LongSerializationPolicy.STRING)
			.disableHtmlEscaping().create();

	public static void main(String[] args) {

//		 Map<String, String> map = new HashMap<String,String>();
//		 map.put("a","1");
//		 map.put("b","2");
//		 map.put("c","3");
//		 map.put("d","4");
//		 String Json = GSON.toJson(map,
//		 new TypeToken<Map<String, String>>() {
//		 }.getType());
//		
//		 System.out.println(Json);
//
//		long t = System.currentTimeMillis();
//		for (int i = 0; i < 10000; i++) {
//			String Json1 = "{\"d\":\"4\",\"b\":\"2\",\"c\":\"3\",\"a\":\"1\"}";
//			HashMap<String, String> gson = GSON.fromJson(Json1,
//					new TypeToken<HashMap<String, String>>() {
//					}.getType());
//			gson.get("a");
//		}
//		System.out.println(System.currentTimeMillis()-t);
//
//
//		t = System.currentTimeMillis();
//		for (int i = 0; i < 10000; i++) {
			String Json1 = "{\"d\":\"4\",\"b\":\"2\",\"c\":\"3\",\"a\":\"1\"}";
			Map jo = JSON.parseObject(Json1,Map.class);
			System.out.println(jo.get("d"));
//		}
//		System.out.println(System.currentTimeMillis()-t);		
//		JSON.toJSON(t);
//		 Map<String, String> map = new HashMap<String,String>();
//		 map.put("success","true");
//		 map.put("topicId","test");
//		 map.put("version","1");
//		 map.put("content","[{\"match_expression\": \".*\\.(ace|arj|ini|txt|udl|plist|css|gif|ico|jpe?g|js|png|swf|woff|caf|aiff|m4v|mpe?g|mp3|mp4|mov)$\",\"each_segment\": false,\"terminate_chain\": true, \"replacement\": \"\\/*.\\\\1\",\"eval_order\": 1000,\"replace_all\": false,\"ignore\": true}]");
//		 String Json = GSON.toJson(map,
//		 new TypeToken<Map<String, String>>() {
//		 }.getType());
//		
//		 System.out.println(Json);
		
		
//		
//		List<Map<String,String>> l = new ArrayList<Map<String,String>>();
//		Map<String,String> m = new HashMap<String, String>();
//		m.put("match_expression", ".*\\.(ace|arj|ini|txt|udl|plist|css|gif|ico|jpe?g|js|png|swf|woff|caf|aiff|m4v|mpe?g|mp3|mp4|mov)$");
//		m.put("each_segment", "false");
//		m.put("terminate_chain", "true");
//		m.put("replacement", null);
//		m.put("eval_order", "1000");
//		m.put("replace_all", "false");
//		m.put("ignore", "true");
//		l.add(m);
//		String Json = GSON.toJson(l, new TypeToken<List<Map<String, String>>>() { }.getType());
//		System.out.println(Json);
	}
}
