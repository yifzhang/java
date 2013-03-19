package peiliping.cache;

import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import com.googlecode.concurrentlinkedhashmap.ConcurrentLinkedHashMap;
import com.googlecode.concurrentlinkedhashmap.Weighers;

public class LRUCache {

	public final String title;

	public static final long DEFAULT_EXPIRE = 1000 * 60 * 60;

	public static final int DEFAULT_MAXSIZE = 5000;

	private AtomicLong expiretime = new AtomicLong(0); // 有效时间

	private AtomicInteger maxsize = new AtomicInteger(0); // cache的大小

	private ConcurrentLinkedHashMap<Object, CacheItem> cache;

	private AtomicLong in = new AtomicLong(0);

	private AtomicLong hit = new AtomicLong(0);

	public LRUCache(String title) {
		this(title, DEFAULT_MAXSIZE);
	}

	public LRUCache(String title, int maxsize) {
		this(title, maxsize, DEFAULT_EXPIRE);
	}

	public LRUCache(String title, int maxsize, long expiretime) {
		this.title = title;
		this.maxsize.set(maxsize);
		this.expiretime.set(expiretime);
		cache = new ConcurrentLinkedHashMap.Builder<Object, CacheItem>()
				.maximumWeightedCapacity(maxsize).weigher(Weighers.singleton())
				.build();
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
				CacheItem ci = cache.remove(key);
				ci.destroy();
				ci = null;
			} else {
				hit.addAndGet(1);
				return item.getValue();
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
		return "title:" + title + " in:" + in + " hit:" + hit + " size:" + cache.size();
	}

	public Set<Object> dumpKey() {
		return cache.keySet();
	}

	public synchronized void configOnline(long expiretime) {
		this.expiretime.set(expiretime);
	}

}
