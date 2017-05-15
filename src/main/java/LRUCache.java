import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by jim on 2017/5/16.
 * This class is ...
 */
public class LRUCache<K, V> {
	private static final float floatHashTableLoadFactor = 0.75f;
	private static final Logger LOGGER = LoggerFactory.getLogger(LRUCache.class);
	private LinkedHashMap<K, V> linkedHashMap;
	private int cacheSize;

	public LRUCache(int size) {
		this.cacheSize = size;
		int capacity = (int) (Math.ceil(size / floatHashTableLoadFactor)) + 1;
		LOGGER.debug("max size:" + size);
		this.linkedHashMap = new LinkedHashMap<K, V>(capacity, floatHashTableLoadFactor, true) {
			private static final long serialVersionUID = 1L;

			@Override
			protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
				return LRUCache.this.linkedHashMap.size() > LRUCache.this.cacheSize;
			}
		};
	}

	public synchronized void set(K key, V value) {
		LOGGER.debug("set:" + "\nkey:" + key + "\nvalue:" + value);
		this.linkedHashMap.put(key, value);
	}

	public synchronized V get(String key) {
		LOGGER.debug("get:" + key);
		return this.linkedHashMap.get(key);
	}

	public synchronized int usedEntitiesSize() {
		return this.linkedHashMap.size();
	}

	public synchronized Collection<Map.Entry<K, V>> getAll() {
		return new ArrayList<Map.Entry<K, V>>(this.linkedHashMap.entrySet());
	}

	public synchronized void clear() {
		this.linkedHashMap.clear();
	}

	public synchronized int getCacheSize() {
		return this.linkedHashMap.size();
	}
}
