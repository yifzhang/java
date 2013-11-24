package peiliping;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Test {

//	public static void main(String[] args) {
//		String jsonA = getJsonStr("/home/peiliping/下载/a.txt");
//		String jsonB = getJsonStr("/home/peiliping/下载/b.txt");
//		JSONObject j = mergeProfileTree(jsonA, jsonB);
//		System.out.println(j.toJSONString());
//	}
	
	public static int k = 0;
	
	public static void main(String[] args) {
		String jsonA = getJsonStr("/home/peiliping/下载/a.txt");
		handleProfileTreeJson(jsonA, 10);
		System.out.println(k);
	}
	
	public static JSONObject mergeProfileTree(String jsonA,String jsonB){
		JSONObject joA = JSON.parseObject(jsonA);
		JSONArray jaA = joA.getJSONArray("REQUEST");
		JSONArray jarequestA = jaA.getJSONArray(1);
		
		JSONObject joB = JSON.parseObject(jsonB);
		JSONArray jaB = joB.getJSONArray("REQUEST");
		JSONArray jarequestB = jaB.getJSONArray(1);
		
		mergeHandle(jarequestA,jarequestB);
		return joA ;
	}
	
	
	public static void mergeHandle(JSONArray ja,JSONArray jb){
		ja.set(1, ja.getLong(1) + jb.getLong(1));
		ja.set(2, ja.getLong(2) + jb.getLong(2));
		Map<String,Integer> namemap = new HashMap<String,Integer>(); 
		JSONArray jachilds = ja.getJSONArray(3) ;
		for(int i=0 ; i<jachilds.size() ; i++){
			namemap.put(jachilds.getJSONArray(i).getString(0), i);
		}
		JSONArray jbchilds = jb.getJSONArray(3) ;
		for(int i=0 ; i<jbchilds.size() ; i++){
			Integer hit = namemap.get(jbchilds.getJSONArray(i).getString(0));
			if(hit == null){
				ja.getJSONArray(3).add(jbchilds.getJSONArray(i));
			}else{
				mergeHandle(jachilds.getJSONArray(hit), jbchilds.getJSONArray(i));
			}
		}
	}
	
	public static String handleProfileTreeJson(String json,int level){
		JSONObject jo = JSON.parseObject(json);
		JSONArray ja = jo.getJSONArray("REQUEST");
		JSONArray jarequest = ja.getJSONArray(1);
		handle(jarequest,jarequest.getLong(1),jarequest.getLong(2),10);
		return jo.toJSONString();
	}
	
	private static void handle(JSONArray ja,long runnum,long unrunnum,int level){
		ja.add(ja.getLong(1)*100/runnum);
		ja.add(ja.getLong(2)*100/unrunnum);
		if(ja!=null && ja.size()>=4 && ja.get(3)!=null && ja.getJSONArray(3).size()>0){
			for(int i=0 ; i<ja.getJSONArray(3).size() ; i++){
				handle(ja.getJSONArray(3).getJSONArray(i), runnum ,unrunnum,level);
			}
		}else{
			ja.add(ja.getLong(4)>level?true:false);
			ja.add(ja.getLong(5)>level?true:false);
		}
		k++;
	}
	
	private static String getJsonStr(String filename){
		String json = "";
		File file = new File(filename);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				json = tempString;
			}
			reader.close();
		} catch (IOException e) {
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return json ;
	}
}