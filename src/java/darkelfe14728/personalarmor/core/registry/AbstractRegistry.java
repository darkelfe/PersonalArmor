package darkelfe14728.personalarmor.core.registry;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * @author Julien Rosset
 *
 * An object registry.
 */
public abstract class AbstractRegistry<K, V>
    implements Iterable<Map.Entry<K, V>>
{
    private final BiMap<K, V> map;
    
    public AbstractRegistry()
    {
        this.map = HashBiMap.create();
    }
    
    protected void register(K key, V value)
    {
        this.map.put(key, value);
    }
    public int size()
    {
        return this.map.size();
    }
    
    public boolean hasKey(K key)
    {
        return this.map.containsKey(key);
    }
    public boolean hasValue(V value)
    {
        return this.map.containsValue(value);
    }
    
    public K getKey(V value)
    {
        return this.map.inverse().get(value);
    }
    public V getValue(K key)
    {
        return this.map.get(key);
    }
    
    public Set<Map.Entry<K, V>> getEntries()
    {
        return this.map.entrySet();
    }
    public Set<K> getKeys()
    {
        return this.map.keySet();
    }
    public Set<V> getValues()
    {
        return this.map.values();
    }
    
    @Override
    public void forEach(Consumer<? super Entry<K, V>> action)
    {
        this.map.entrySet().forEach(action);
    }
    @Override
    public Iterator<Entry<K, V>> iterator()
    {
        return this.map.entrySet().iterator();
    }
    @Override
    public Spliterator<Entry<K, V>> spliterator()
    {
        return this.map.entrySet().spliterator();
    }
}
