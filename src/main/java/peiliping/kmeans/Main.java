package peiliping.kmeans;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import peiliping.kmeans.Kmeans.Result;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException, InstantiationException, IllegalAccessException {

		List<IItem> list = new ArrayList<IItem>();
		File file = new File("/home/peiliping/dev/gitwork/self/java/src/main/java/peiliping/kmeans/zhanning");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String tempString = null;
		
		IItem p =  null ;
		while ((tempString = reader.readLine()) != null) {
			p = new Item3();
			double[] s = {0,0,0};
			s[0]=Double.parseDouble(tempString.split(" ")[0]);
			s[1]=Double.parseDouble(tempString.split(" ")[1]);
			s[2]=Double.parseDouble(tempString.split(" ")[2]);
			p.initPoint(s);
			list.add(p);
		}
		reader.close();
		
		Kmeans<Item> kmeans = new Kmeans<Item>(list,5,Item3.class);
		Result R = kmeans.run();
		List<IItem>[] results = R.classifyResults;
		List<IItem> cores = R.cores;
		for (int i = 0; i < results.length; i++) {
			System.out.println("===========类别" + (i + 1) + "================");
			System.out.print( "种子:" + Arrays.toString(cores.get(i).getDatas()) + "\t数据:");
			for (IItem op : results[i]) {
				System.out.print( Arrays.toString(op.getDatas()) );
			}
			System.out.println("");
		}
	}
}
