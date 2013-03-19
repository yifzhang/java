package peiliping.cache;

public class CacheItem {

	private  Object value; // cache的对象

	private  long createTime; // 时间戳

	public CacheItem(Object value) {
		this.value = value;
		this.createTime = System.currentTimeMillis();
	}

	public boolean isTimeOut(long expiretime) {
		return System.currentTimeMillis() - createTime > expiretime;
	}
	
	public Object getValue() {
		return value;
	}
	
	public void destroy(){
		value = null;
	}
}
