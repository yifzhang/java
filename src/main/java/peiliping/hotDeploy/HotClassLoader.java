package peiliping.hotDeploy;

import java.io.File;
import java.io.FileInputStream;

public class HotClassLoader extends ClassLoader {

	private String classname = ""; // package+filename example:peiliping.hotDeply.Item
	private String target = "" ;

	public HotClassLoader(String path, String classname) {
		super();
		this.classname = classname;
		this.target = path + this.classname.replace('.', File.separatorChar) + ".class";
		init();
	}

	private void init() {
		try {
			File classFile = new File(target);
			byte[] contents = new byte[(int) classFile.length()];
			FileInputStream fileInput = new FileInputStream(classFile);
			fileInput.read(contents);
			fileInput.close();
			defineClass(classname, contents, 0, contents.length);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Class<?> getNewClass(){
		try {
			return loadClass(target);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}