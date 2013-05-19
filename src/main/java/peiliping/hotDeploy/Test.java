package peiliping.hotDeploy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException, InterruptedException {
		
		//当前启动加载的class
		System.out.println(new Item().getName());
		//在sleep期间替换掉class文件 name变量换个默认值
		Thread.sleep(10000);
		//初始化替换class的基本信息
		HotClassLoader cl = new HotClassLoader("/home/peiliping/dev/gitwork/java/target/classes/","peiliping.hotDeploy.Item");
		//重新加载class
        Class<?> clazz = cl.getNewClass();
        //执行最新加载的class
        Object newclass = clazz.newInstance();  
        Method m = newclass.getClass().getMethod("getName",null);  
        System.out.println(m.invoke(newclass,null));
        
        System.out.println(((Item)newclass).getName());//会Cast失败
	}

}
