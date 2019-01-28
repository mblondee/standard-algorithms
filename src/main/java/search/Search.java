package search;

public interface Search<Key, Value> {

    public int getSize();
    public boolean isEmpty();


    public boolean contains(Key key);
    public Value get(Key key);
    public void put(Key key, Value value);
    public void delete(Key key);

    public void deleteMin();
    public void deleteMax();

    public Key floor(Key key);
    public Key ceiling(Key key);

    public Iterable<Key> keys();
}
