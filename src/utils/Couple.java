package utils;

public class Couple<K, V> {
	
	public Couple(final K key, final V value) {
		this.key = key;
		this.value = value;
	}
	
	public Couple(final K key) {
		this(key, null);
	}
	
	public K getKey() {
		return key;
	}
	
	public V getValue() {
		return value;
	}
	
	public void setValue(V value) {
		this.value = value;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj instanceof Couple) {
			final var c = (Couple<K, V>) obj;
			return c.key.equals(this.key);
		}
		return false;
	}
	
	protected final K key;
	protected V value;
}
