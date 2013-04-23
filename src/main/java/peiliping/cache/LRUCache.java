package peiliping.cache;

import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

import com.googlecode.concurrentlinkedhashmap.ConcurrentLinkedHashMap;
import com.googlecode.concurrentlinkedhashmap.Weighers;

public class LRUCache {

	public final String title;
	
	public AtomicBoolean IN_USE = new AtomicBoolean(true);

	public static final long DEFAULT_EXPIRE = 1000 * 60 * 60;

	public static final int DEFAULT_MAXSIZE = 5000;

	private AtomicLong expiretime = new AtomicLong(0); // 有效时间

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
		this.expiretime.set(expiretime);
		cache = new ConcurrentLinkedHashMap.Builder<Object, CacheItem>()
				.maximumWeightedCapacity(maxsize).weigher(Weighers.singleton())
				.build();
	}

	public void clear() {
		in.set(0);
		hit.set(0);
		if (cache != null) {
			cache.clear();
		}
	}

	public Object get(Object key) {
		if(!IN_USE.get()){return null;}
		
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

	public void put(Object key, Object value) {
		if(!IN_USE.get()){return ;}
		if(cache.size() > 3 * cache.capacity()){
		 /**
		  * 在特别高的并发下，异步的删除跟不上put的速度会导致cache的体积非常大甚至导致JVM OOM掉
		  * 所以在这里做个判断限制一下，起到一个保护的作用。 
		  */
			return ;
		}	
		cache.put(key, new CacheItem(value));
	}

	public Object remove(Object key) {
		return cache.remove(key);
	}

	public String toLog() {
		return "title:" + title + " in:" + in + " hit:" + hit + " size:" + cache.size();
	}

	public Set<Object> dumpKey() {
		return cache.keySet();
	}

	public void configOnline(long expiretime,int maxsize) {
		this.expiretime.set(expiretime);
		this.cache.setCapacity(maxsize);
	}

}
