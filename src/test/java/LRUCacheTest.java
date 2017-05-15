import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by jim on 2017/5/16.
 * This class is ...
 */
public class LRUCacheTest {
	private static final int MAX = 2;
	private LRUCache lruCache = new LRUCache(MAX);

	@BeforeClass
	public void setup() {
		this.lruCache.clear();
	}

	@Test
	public void setKV1() {
		this.lruCache.set("key1", "value1");
		Assert.assertEquals(this.lruCache.get("key1"), "value1");
		Assert.assertEquals(1, this.lruCache.getCacheSize());

		String result = (String) this.lruCache.get("key1");
		Assert.assertEquals("value1", result);

		int usedSize = this.lruCache.usedEntitiesSize();
		Assert.assertEquals(1, usedSize);

		this.lruCache.clear();
		Assert.assertEquals(0, this.lruCache.getCacheSize());

		result = (String) this.lruCache.get("key1");
		Assert.assertEquals(null, result);

		this.lruCache.set("key2", "value2");
		this.lruCache.set("key3", "value3");


		result = (String) this.lruCache.get("key2");
		Assert.assertEquals("value2", result);


		this.lruCache.set("key4", "value4");
		Assert.assertEquals(null, this.lruCache.get("key3"));

		this.lruCache.set("key5", "value5");
		Assert.assertEquals(null, this.lruCache.get("key2"));
	}


	@AfterClass
	public void tearDown() {

	}
}
