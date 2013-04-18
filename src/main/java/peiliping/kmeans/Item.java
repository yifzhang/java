package peiliping.kmeans;

import java.util.List;

public class Item extends IItem {

	private long id;

	private String name;

	private double[] datas;

	public double[] getDatas() {
		return datas;
	}

	public void setDatas(double[] datas) {
		this.datas = datas;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(IItem obj) {
		if (obj == null || getDimensionNum() != ((Item) obj).getDimensionNum()) {
			return false;
		}

		return false;
	}

	@Override
	public double distance(IItem obj) {
		Item i = (Item) obj;
		double r = 0;
		for (int t = 0; t < datas.length; t++) {
			r = r + Math.pow((datas[t] - i.getDatas()[t]), 2);
		}
		return Math.sqrt(r);
	}

	@Override
	public int getDimensionNum() {
		return datas != null ? datas.length : 0;
	}

	@Override
	public void initPoint(double[] ds) {
		this.datas = ds;
	}

	@Override
	public String toLog() {
		return "";
	}

	@Override
	public void prehandle(List<IItem> items) {

		double[] maxs = getArray(getDimensionNum(), 0);
		double[] minxs = getArray(getDimensionNum(), Double.MAX_VALUE);

		Item p;
		for (IItem i : items) {
			p = (Item) i;
			for (int j = 0; j < p.getDimensionNum(); j++) {
				maxs[j] = Math.max(p.getDatas()[j], maxs[j]);
				minxs[j] = Math.min(p.getDatas()[j], minxs[j]);
			}
		}

		for (IItem tmp : items) {
			p = (Item) tmp;
			for(int j = 0; j < p.getDimensionNum(); j++){
				p.getDatas()[j] = (p.getDatas()[j] -minxs[j]) /(maxs[j] - minxs[j] );
			}
		}
	}

	public static double[] getArray(int num, double def) {
		double[] array = new double[num];
		for (int i = 0; i < num; i++) {
			array[i] = def;
		}
		return array;
	}

}
