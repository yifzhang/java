package peiliping.kmeans;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import peiliping.kmeans.Kmeans.Result;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException, InstantiationException, IllegalAccessException {

		List<IItem> list = new ArrayList<IItem>();
		File file = new File("/home/zhanning-plp/dev/zhanning");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String tempString = null;
		while ((tempString = reader.readLine()) != null) {
			Item p = new Item();
			p.setX(Double.parseDouble(tempString.split(" ")[0]));
			p.setY(Double.parseDouble(tempString.split(" ")[1]));
			p.setZ(0);
			list.add(p);
		}
		reader.close();
		
		(new Item()).prehandle(list);
		Kmeans<Item> kmeans = new Kmeans<Item>(list,12,Item.class);
		Result R = kmeans.run();
		List<Item>[] results = R.classifyResults; 
		for (int i = 0; i < results.length; i++) {
			System.out.println("===========类别" + (i + 1) + "================");
			List<Item> ri = results[i];
			for (Item p : ri) {
				System.out.print( "[" + p.getX()*1000 + "," + (p.getY()*1000) + "]" + ",");
			}
			System.out.println("");
		}
		
		int j=0 ;
		List<Item> cores = R.cores;
		for(Item item : cores){
			System.out.println("种子" + (j+1) + "\t\t数据量："+ results[j].size() + "\t\t坐标：" + item.getX()*1000 + "  " + item.getY()*1000);
			j++;
		}

	}
}
