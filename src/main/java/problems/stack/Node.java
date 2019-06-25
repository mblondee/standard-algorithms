package problems.stack;

public class Node<Item> {

    private Item value;
    private Node next;

    private Item min;
    private Item max;

    public Node(Item str){
        this.value = str;
    }

    public Node(Item str, Item min, Item max){

        this.value = str;
        this.min = min;
        this.max = max;
    }

    public Item getValue() {
        return value;
    }

    public void setValue(Item value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }


    public Item getMin() {
        return min;
    }

    public void setMin(Item min) {
        this.min = min;
    }

    public Item getMax() {
        return max;
    }

    public void setMax(Item max) {
        this.max = max;
    }
}
