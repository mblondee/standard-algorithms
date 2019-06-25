package problems.stack;

/*
 * Stack coding problems
 *
 * 1) efficient return find min/max
 *
 * */

import java.util.NoSuchElementException;

public class Stack<Item extends Comparable<Item>> {

    private Node<Item> head;
    private int size;
    private boolean min;

    public Stack(boolean min){
        this.min = min;
    }

    public void push(Item value){
        if(min){
            pushMin(value);
        }
        else{
            pushNormal(value);
        }
    }

    private void pushMin(Item value){
        Node newHead;
        if(head == null){
            newHead = new Node(value, value, value);
        }
        else{
            if(value.compareTo(head.getMin()) < 0){
                // new min
                newHead = new Node(value, value, head.getMax());
            }
            else if(value.compareTo(head.getMax()) > 0){
                // new max
                newHead = new Node(value, head.getMin(), value);
            }
            else{
                newHead = new Node(value, head.getMin(), head.getMax());
            }

        }
        newHead.setNext(head);
        head = newHead;
        size++;
    }

    private void pushNormal(Item value){
        Node newHead = new Node(value);
        newHead.setNext(head);
        head = newHead;
        size++;
    }


    public Item pop(){
        if(size == 0){
            throw new NoSuchElementException("empty stack");
        }
        Item popped = head.getValue();
        head = head.getNext();
        size--;
        return popped;
    }


    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public Item getMinElement(){
        if(head == null){
            throw new NoSuchElementException("empty stack");
        }

        return head.getMin();
    }

    public Item getMaxElement(){
        if(head == null){
            throw new NoSuchElementException("empty stack");
        }
        return head.getMax();
    }
}
