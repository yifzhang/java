package peiliping.producerAconsumer;

import java.util.ArrayList;
import java.util.List;

public class Storage<V> {

	private int capacity;		//容积

	private List<V> storage;	//容器

	public Storage(int capacity) {
		this.capacity = capacity;
		this.storage = new ArrayList<V>();
	}

	/**
	 * 添加数据
	 */
	public synchronized boolean add(V v) {
		if (isFull()) {
			return false;
		}
		storage.add(v);
		return true;
	}

	/**
	 * 删除数据
	 */
	public synchronized V del() {
		if (storage == null || storage.size() == 0) {
			return null;
		}
		V v = storage.get(0);
		storage.remove(0);
		return v;
	}

	/**
	 *容器是否满了 
	 */
	public boolean isFull() {
		return storage == null ? true : storage.size() >= capacity;
	}
	
	/**
	 *当前容器中的数量 
	 */
	public int size(){
		return storage==null ? 0 : storage.size();
	}
}
