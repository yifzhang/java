package peiliping.kmeans;
	
import java.util.ArrayList;
import java.util.List;

public class Kmeans<T extends IItem> {
	 
	/** 
     * 所有数据列表 
     */   
    private List<IItem> items ;  
  
    /** 
     * 数据类别 
     */    
    private Class<? extends IItem> clazz ;  
  
    /** 
     * 中心点集合 
     */  
    private List<IItem> seedList;  

    /** 
     * 分类数 
     */  
    private int k = 1;  

    public Kmeans(List<IItem> list, int k,Class<? extends IItem> clazz) {  
        this.items = list;  
        this.k = k;
        this.clazz = clazz;
    }  

    /**
     * 执行聚类运算
     * @return
     */
    @SuppressWarnings("unchecked")
    public Result run() throws InstantiationException, IllegalAccessException {  
    	(clazz.newInstance()).prehandle(items);
    	seedList = new ArrayList<IItem>(items.subList(0,k)); //默认选几个数据点当中心  
		List<IItem>[] results = new ArrayList[k];  
        boolean centerChanged = true;  
        while (centerChanged) {  
            centerChanged = false; 
            //清空结果数组
            for (int i = 0; i < k; i++) {  
                if(results[i]==null){
                	results[i]=new ArrayList<IItem>();
                }else{
                	results[i].clear();
                } 
            }  
            //运算每个数据点与种子的距离，投放到距离近的种子对应的结果集中
        	IItem tmp_item;
        	int min_index=0;
            double min_dist=Double.MAX_VALUE,tmp_dist;
            for (int i = 0; i < items.size(); i++) {  
            	tmp_item = items.get(i);
            	min_dist=Double.MAX_VALUE;
                for (int j = 0; j < seedList.size(); j++) {  
                    tmp_dist = seedList.get(j).distance(tmp_item);  
                    if(tmp_dist<min_dist){
                    	min_index=j;
                    	min_dist=tmp_dist;
                    }  
                }  
                results[min_index].add(tmp_item);  
            } 
            //找新的中心点，更换掉种子
            for (int i = 0; i < k; i++) {  
            	if(results[i]==null || results[i].size()==0){
            		continue;
            	}
            	IItem t_new = findNewCenter(results[i]);  
                if (!seedList.get(i).equals(t_new)){  
                    centerChanged = true;  
                    seedList.set(i, t_new);  
                }  
            }  
            System.out.println("==");
        }  
        return new Result(true, results, seedList);  
    }    
  
    /** 
     * 得到新聚类中心对象 
     * @param ps 
     * @return 
     */  
	public IItem findNewCenter(List<IItem> ps) throws InstantiationException,IllegalAccessException {
		IItem t = clazz.newInstance();
		int fieldnum = t.getDimensionNum();
		double[] ds = new double[fieldnum];
		double[] tmpd;
		for (IItem vo : ps) {
			tmpd = vo.getDatas();
			for (int i = 0; i < fieldnum; i++) {
				ds[i] += tmpd[i];
			}
		}
		for (int i = 0; i < fieldnum; i++) {
			ds[i] = ds[i] / ps.size();
		}
		t.initPoint(ds);
		return t;
	}
     
    public class Result {
    	/**
    	 * 处理结果
    	 */
    	public boolean success = true ;
    	/**
    	 * 数据分组后的结果
    	 */
    	public List<IItem>[] classifyResults ;
    	/**
    	 * 中心点集合
    	 */
    	public List<IItem> cores ;

    	public Result(boolean success,List<IItem>[] classifyResults,List<IItem> cores){
    		this.success = success;
    		this.classifyResults = classifyResults;
    		this.cores = cores;
    	}
    }
}