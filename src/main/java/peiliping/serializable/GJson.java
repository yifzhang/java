package peiliping.serializable;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import com.google.gson.reflect.TypeToken;

public class GJson {

	private static Gson GSON = new GsonBuilder()
			.setLongSerializationPolicy(LongSerializationPolicy.STRING)
			.disableHtmlEscaping().create();

	public static void main(String[] args) {

		 Map<String, String> map = new HashMap<String,String>();
		 map.put("a","1");
		 map.put("b","2");
		 map.put("c","3");
		 map.put("d","4");
		 String Json = GSON.toJson(map,
		 new TypeToken<Map<String, String>>() {
		 }.getType());
		
		 System.out.println(Json);

		long t = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			String Json1 = "{\"d\":\"4\",\"b\":\"2\",\"c\":\"3\",\"a\":\"1\"}";
			HashMap<String, String> gson = GSON.fromJson(Json1,
					new TypeToken<HashMap<String, String>>() {
					}.getType());
			gson.get("a");
		}
		System.out.println(System.currentTimeMillis()-t);


		t = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			String Json1 = "{\"d\":\"4\",\"b\":\"2\",\"c\":\"3\",\"a\":\"1\"}";
			JSONObject jo = JSON.parseObject(Json1);
			jo.get("d");
		}
		System.out.println(System.currentTimeMillis()-t);
	}
}
