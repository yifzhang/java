package peiliping.kmeans;

import java.util.List;

public class Item extends IItem{
	
	private long id;  
	
	private String name;  
	  
	private double x;  
	
	private double y;
	
	private double z;
	
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

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	@Override
	public boolean equals(IItem obj) {
		if(obj == null){
			return false ;
		}
		Item i = (Item) obj;
		if(this.x == i.getX() && this.y == i.getY() && this.z == i.getZ()){
			return true ;
		}
		return false ;
	}
	
	@Override
	public double distance(IItem obj) {
		Item i = (Item) obj;
		double r = Math.pow((this.x-i.getX()),2) + Math.pow((this.y-i.getY()),2) + Math.pow((this.z-i.getZ()),2);
		return Math.sqrt(r) ;
	}
	
	@Override
	public double[] getData() {
		return new double[]{x,y,z};
	}
	
	@Override
	public int getDimensionNum() {
		return 3;
	}
	
	@Override
	public void initPoint(double[] ds) {
		this.x = ds[0];
		this.y = ds[1];
		this.z = ds[2];
	}

	@Override
	public String toLog() {
		return "x:" + x + "," + "y:" + y + "," + "z:" + z ;
	}

	@Override
	public void prehandle(List<IItem> items) {
		 double maxx=0 ,maxy=0 ,maxz=0;
		 double minx=Double.MAX_VALUE ,miny=Double.MAX_VALUE ,minz=Double.MAX_VALUE;
		 Item p;
		 for(IItem i: items){
			p = (Item)i;
			maxx=Math.max(p.getX(), maxx);minx=Math.min(p.getX(), minx);
			maxy=Math.max(p.getY(), maxy);miny=Math.min(p.getY(), miny);
			maxz=Math.max(p.getZ(), maxz);minz=Math.min(p.getZ(), minz);
		 }
		 for(IItem tmp : items){
			p = (Item)tmp;
	        p.setX((p.getX()-minx)/maxx);
	        p.setY((p.getY()-miny)/maxy);
	        p.setX((p.getZ()-minz)/maxz);
	        }
	}
}
