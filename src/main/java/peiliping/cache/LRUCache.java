package peiliping.cache;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class LRUCache {

	public static final long DEFAULT_EXPIRE = 1000 * 60 * 60;

	public static final int DEFAULT_MAXSIZE = 5000;

	private AtomicLong expiretime = new AtomicLong(0); // 有效时间

	private AtomicInteger maxsize = new AtomicInteger(0); // cache的大小

	private LinkedHashMap<Object, CacheItem> cache;

	private AtomicLong in = new AtomicLong(0);

	private AtomicLong hit = new AtomicLong(0);

	public LRUCache() {
		this(DEFAULT_MAXSIZE);
	}

	public LRUCache(int maxsize) {
		this(maxsize, DEFAULT_EXPIRE);
	}

	public LRUCache(int maxsize, long expiretime) {
		this.maxsize.set(maxsize);
		this.expiretime.set(expiretime);
		cache = new LinkedHashMap<Object, CacheItem>(maxsize, 0.75f, true) {
			private static final long serialVersionUID = 1L;

			protected boolean removeEldestEntry(
					Map.Entry<Object, CacheItem> eldest) {
				return size() > LRUCache.this.maxsize.get();
			}
		};
	}

	public synchronized void clear() {
		in.set(0);
		hit.set(0);
		if (cache != null) {
			cache.clear();
		}
	}

	public synchronized Object get(Object key) {
		in.addAndGet(1);
		CacheItem item = cache.get(key);
		if (item != null) {
			if (item.isTimeOut(expiretime.get())) {
				cache.remove(key);
			} else {
				hit.addAndGet(1);
				return item;
			}
		}
		return null;
	}

	public synchronized void put(Object key, Object value) {
		cache.put(key, new CacheItem(value));
	}

	public synchronized Object remove(Object key) {
		return cache.remove(key);
	}

	public String toLog() {
		return "in:" + in + " hit:" + hit + "size:" + cache.size();
	}

	public Set<Object> dumpKey() {
		return cache.keySet();
	}

	public synchronized void configOnline(int maxsize, long expiretime) {
		this.maxsize.set(maxsize);
		this.expiretime.set(expiretime);
	}

}
