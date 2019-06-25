package problems;

/*
* get middle element of a linked list
*
* only traverses once the linked list (naive version would traverse twice): O(n)
*
* interleave two linkedlists
* 1-2-3 and 4-5-6
* returns 1-4-2-5-3-6
* */


public class LinkedList<Item> {

    private Node root;

    private static class Node<T>{
        private T item;
        private Node next;

        private Node(T item){
            this.item = item;
            // next default: null
        }
    }

    public LinkedList(){
        this.root = null;
    }


    public void addNode(Item itemToAdd){
        Node nodeToAdd = new Node(itemToAdd);
        nodeToAdd.next = root;
        root = nodeToAdd;
    }

    public Node getMiddle(){
        if(root == null){
            return null;
        }
        else{
            Node<Item> pointer1 = root;
            Node<Item> pointer2 = root;
            while(pointer2 != null && pointer2.next != null){
                pointer1 = pointer1.next;
                pointer2 = pointer2.next.next;
            }

            return pointer1;
        }
    }

    /*
    * mutates linkedlist
    * */
    private static void interleave(LinkedList linkedlist, LinkedList otherList){
        Node tail = linkedlist.root;
        Node second = otherList.root;
        while(second != null){
            if(tail == null){
                tail = second;
                second = null;
            }
            else{
                Node tailnext = tail.next;
                Node secondnext = second.next;
                tail.next = second;
                second.next = tailnext;
                tail = tailnext;
                second = secondnext;
            }
        }
        otherList.root = null;
    }

    public void shuffle(){
        Node secondListNode = getMiddle().next;

        LinkedList secondList = new LinkedList();
        secondList.root = secondListNode;
    }

    public void printNodes(){
        if(root != null){
            Node currentNode = root;
            System.out.println(currentNode.item);
            while(currentNode.next != null){
                currentNode = currentNode.next;
                System.out.println(currentNode.item);
            }
        }

    }

    public static void main(String[] args){
        LinkedList<String> linkedlist = new LinkedList<>();
        linkedlist.addNode("c");
        linkedlist.addNode("b");
        linkedlist.addNode("a");
/*        linkedlist.addNode("d");
        linkedlist.addNode("e");
        linkedlist.addNode("f");*/
        //linkedlist.printNodes();
        //System.out.println(linkedlist.getMiddle());
        LinkedList<String> linkedlist2 = new LinkedList<>();
        linkedlist2.addNode("cc");
        linkedlist2.addNode("bb");
        linkedlist2.addNode("aa");
/*        linkedlist2.addNode("dd");
        linkedlist2.addNode("ee");
        linkedlist2.addNode("ff");*/
        linkedlist.interleave(linkedlist, linkedlist2);
        linkedlist.printNodes();

    }
}
