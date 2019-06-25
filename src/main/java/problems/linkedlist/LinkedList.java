package problems.linkedlist;

/*
* Linked list coding problems
*
* linked list of integers
* 1) insert at the end of the linked list
* 2) insert at the front of the linked list
* 3) reverse a linked list
* 4) interleave a linked list with another linked list form the same length changing the linked list on which
* the method is called without allocating any new linked lists cells
* 5) given a number k, return the kth-to-last element of the linked list (without using the length)
* 6) sort using mergesort
* 7) sort using quicksort
* */

public class LinkedList {

    private Node root;

    public LinkedList(){
    }


    @Override
    public String toString(){
        Node pointer = root;
        StringBuffer str = new StringBuffer();
        while(pointer != null){
            str.append(pointer.getValue());
            str.append(" ");
            pointer = pointer.getNext();
        }
        return str.toString();
    }

    @Override
    public boolean equals(Object other){
        if(other == this){
            return true;
        }
        if(! (other instanceof LinkedList)){
            return false;
        }

        Node pointer = root;
        Node otherPointer = ((LinkedList) other).root;
        if((pointer == null && otherPointer != null) || (pointer != null && otherPointer == null)){
            return false;
        }
        while(pointer != null){
            if(pointer.getValue() != otherPointer.getValue()){
                return false;
            }
            pointer = pointer.getNext();
            otherPointer = otherPointer.getNext();
        }
        return true;
    }

    /*
     * solution to 1) O(n) if there are n nodes in the linked list
     * */
    public void addToEnd(int k){
        if(root == null){
            root = new Node(k);
            return;
        }
        Node pointer = root;
        while(pointer.getNext() != null){
            pointer = pointer.getNext();
        }
        pointer.setNext(new Node(k));
    }

    /*
     * solution to 2) O(1)
     * */
    public void addToFront(int k){
        Node newNode = new Node(k);
        newNode.setNext(root);
        root = newNode;
    }


    /*
     * solution to 3)
     * O(1) space and O(n) time complexity
     * */
    public void reverse(){
        if(root == null){
            return;
        }
        Node previous = null;
        Node current = root;
        Node next;

        while(current != null){
            next = current.getNext();
            current.setNext(previous);
            previous = current;
            current = next;
        }
        //if current is null
        //set root to previous (also works when linked list has 1 item)
        root = previous;
    }

    /*
     * solution to 4)
     * O(n)
     * */
    public void interleave(LinkedList otherList){
        Node pointer = root;
        Node otherPointer = otherList.root;

        Node next;
        Node otherNext;


        while(pointer != null){
            next = pointer.getNext();
            pointer.setNext(otherPointer);
            otherNext = otherPointer.getNext();
            otherPointer.setNext(next);

            pointer = next;
            otherPointer = otherNext;
        }


    }

    /*
     * solution to 5)
     * O(1) space and O(n) time
     * */
    public int toLast(int k){
        Node pointer = root;
        Node refPointer = root;
        for(int i = 0; i < k; i++){
            refPointer = refPointer.getNext();
        }

        while(refPointer.getNext() != null){
            pointer = pointer.getNext();
            refPointer = refPointer.getNext();
        }
        return pointer.getValue();
    }

    /*
     * solution to 6)
     * O(n log n) time
     * */
    public void mergeSort(){
        root = mergeSort(root);
    }

    private Node mergeSort(Node node){
       if(node == null){
           return node;
       }
       if(node.getNext() == null){
           return node;
       }
       Node middleNode = getMiddle(node);
       Node startOtherList = middleNode.getNext();
       middleNode.setNext(null);

       Node left = mergeSort(node);
       Node right = mergeSort(startOtherList);

       return merge(left, right);

    }

    private static Node merge(Node node1, Node node2){
        Node result;

        if(node1 == null){
            return node2;
        }
        if(node2 == null){
            return node1;
        }
        if(node1.getValue() <= node2.getValue()){
            result = node1;
            result.setNext(merge(node1.getNext(), node2));
        }
        else{
            result = node2;
            result.setNext(merge(node1, node2.getNext()));
        }
        return result;
    }

    /*
    * return start node of second half of linkedlist with root rootNode
    * if length is even: node in position length/2 -1 (length/2 -th element) (eg length 8 -> index 3)
    * if length is odd: node in position length/2 (length/2+1 -th element) (eg length 9 -> index 4)
    * hence this returns last node of first half
    * */
    private Node getMiddle(Node rootNode){
        if(rootNode == null){
            return rootNode;
        }

        Node pointer = rootNode;
        Node refPointer = rootNode.getNext();

        while(refPointer != null && refPointer.getNext() != null){
            pointer = pointer.getNext();
            refPointer = refPointer.getNext().getNext();
        }
        return pointer;
    }

    /*
     * solution to 7)
     * */
    public void quicksort(){
        if(root == null){
            return;
        }
        Node pivot = getLast(root);
        Node last = pivot;
        root = quicksort(root, null, root, last, pivot);
    }

    private Node getLast(Node node){
        if(node == null){
            throw new IllegalArgumentException();// make sure getLast() is only called on not null Node
        }
        if(node.getNext() == null){
            return node;
        }
        return getLast(node.getNext());
    }

    private Node quicksort(Node current, Node previous, Node first, Node last, Node pivot){
        if(first == last){
            return first;
        }
        if(current == pivot){
            Node firstPartStart = null;
            Node firstPartEnd = null;

            if(previous != null){
                firstPartStart = first;
                firstPartEnd = previous;
            }

            Node secondPartEnd = null;
            Node secondPartStart = null;
            if(pivot != last){
                secondPartStart = pivot.getNext();
                secondPartEnd = last;
            }



            if(firstPartEnd != null) {
                firstPartEnd.setNext(null);
            }


            Node node1 = quicksort(firstPartStart, null, firstPartStart, firstPartEnd, firstPartEnd);
            Node node2 = quicksort(secondPartStart, null, secondPartStart, secondPartEnd, secondPartEnd);

            if(node1 != null) {
                getLast(node1).setNext(pivot);
                pivot.setNext(node2);
                return node1;
            }
            else{
                pivot.setNext(node2);
                return pivot;
            }
        }
        if(current.getValue() <= pivot.getValue()){
            // current needs to stay where it is
            current = current.getNext();
            if(previous == null){
                previous = first;
            }
            else{
                previous = previous.getNext();
            }
        }
        else{
            //current needs to go at the end of the list
            if(previous == null){
                first = current.getNext();
                last.setNext(current);
                current.setNext(null);
                last = last.getNext();
                current = first;
            }
            else {
                previous.setNext(current.getNext());
                last.setNext(current);
                current.setNext(null);
                current = previous.getNext();
                last = last.getNext();
            }
        }

        return quicksort(current, previous, first, last, pivot);
    }



}
