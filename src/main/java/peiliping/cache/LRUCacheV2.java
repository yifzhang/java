package peiliping.cache;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class LRUCacheV2 {

	public static final long DEFAULT_EXPIRE = 1000 * 60 * 60;

	public static final int DEFAULT_MAXSIZE = 5000;

	private AtomicLong expiretime = new AtomicLong(0); // 有效时间

	private AtomicInteger maxsize = new AtomicInteger(0); // cache的大小

	private LinkedHashMap<Object, CacheItem> cache;

	private AtomicLong in = new AtomicLong(0);

	private AtomicLong hit = new AtomicLong(0);

	public LRUCacheV2() {
		this(DEFAULT_MAXSIZE);
	}

	public LRUCacheV2(int maxsize) {
		this(maxsize, DEFAULT_EXPIRE);
	}

	public LRUCacheV2(int maxsize, long expiretime) {
		this.maxsize.set(maxsize);
		this.expiretime.set(expiretime);
		cache = new LinkedHashMap<Object, CacheItem>(maxsize, 0.75f, true) {
			private static final long serialVersionUID = 1L;

			protected boolean removeEldestEntry(
					Map.Entry<Object, CacheItem> eldest) {
				return size() > LRUCacheV2.this.maxsize.get();
			}
		};
	}

	public void clear() {
		in.set(0);
		hit.set(0);
		if (cache != null) {
			cache.clear();
		}
	}

	public Object get(Object key) {
		in.addAndGet(1);
		CacheItem item = cache.get(key);
		if (item != null) {
			if (item.isTimeOut(expiretime.get())) {
				CacheItem ci = cache.remove(key);
				ci.destroy();
				ci=null;
			} else {
				hit.addAndGet(1);
				return item.getValue();
			}
		}
		return null;
	}

	public void put(Object key, Object value) {
		cache.put(key, new CacheItem(value));
	}

	public  Object remove(Object key) {
		return cache.remove(key);
	}

	public String toLog() {
		return "in:" + in + " hit:" + hit + "size:" + cache.size();
	}

	public Set<Object> dumpKey() {
		return cache.keySet();
	}

	public void configOnline(long expiretime) {
		this.expiretime.set(expiretime);
	}

}
