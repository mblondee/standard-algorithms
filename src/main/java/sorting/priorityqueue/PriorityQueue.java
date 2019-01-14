package sorting.priorityqueue;

public interface PriorityQueue<Item> {

    public boolean isEmpty();
    public int size();
    public void insert(Item keyToAdd);
    public Item delMax();

}
