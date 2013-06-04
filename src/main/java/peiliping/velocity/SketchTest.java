package peiliping.velocity;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.Properties;

import org.apache.velocity.app.VelocityEngine;

import com.taobao.sketch.SketchEngine;
import com.taobao.sketch.common.OutputStreamWriterApdater;
import com.taobao.sketch.common.StreamWriterApdater;
import com.taobao.sketch.context.SketchContext;
import com.taobao.sketch.runtime.Template;

public class SketchTest {
	
	public static void main(String[] args) throws Exception {
		
		Properties p = new Properties();
		p.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH,"src/main/velocity/");
		SketchEngine se = new SketchEngine();
		se.init(p);
		Template t = se.getTemplate("template.vm");
		SketchContext sc = new SketchContext();
		sc.put("name", "Liang");
		sc.put("date", (new Date()).toString());
		// 输出流
		
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		StreamWriterApdater writer = new StreamWriterApdater(new OutputStreamWriterApdater(b)); 
		// 转换输出
		t.merge(sc, writer);
		writer.flush();
		System.out.println(b.toString());
		
		
	}

}
