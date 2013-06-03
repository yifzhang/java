package peiliping.velocity;

import java.io.StringWriter;
import java.util.Date;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

public class Test {

	public static void main(String[] args) {
		Properties p = new Properties();
		// 这是模板所在路径
		p.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH,"src/main/velocity/");
		VelocityEngine ve = new VelocityEngine();
		ve.init(p);
		// 取得velocity的模版
		Template t = ve.getTemplate("template.vm");
		// 取得velocity的上下文context
		VelocityContext context = new VelocityContext();
		// 把数据填入上下文
		context.put("name", "Liang");
		context.put("date", (new Date()).toString());
		// 输出流
		StringWriter writer = new StringWriter();
		// 转换输出
		t.merge(context, writer);
		System.out.println(writer.toString());
	}

}
