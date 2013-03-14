package peiliping.cache;

public class CacheItem {

	public Object value; // cache的对象
	public long createTime; // 时间戳

	public CacheItem(Object value) {
		this.value = value;
		this.createTime = System.currentTimeMillis();
	}

	public boolean isTimeOut(long expiretime) {
		return System.currentTimeMillis() - createTime > expiretime;
	}
}
