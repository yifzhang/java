package peiliping.serializable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class FJson {
	
	public static void main(String[] args) {
		
		System.out.println(JSON.toJSONString(new BizOrderDO(),SerializerFeature.PrettyFormat));
	}

}
