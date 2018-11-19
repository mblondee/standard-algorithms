package collections.queue;


import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;


public class LinkedQueueTest {

    @Test
    public void testQueue(){
        LinkedQueue<String> queueOfStr = new LinkedQueue<>();
        assertTrue(queueOfStr.isEmpty());
        assertEquals(0, queueOfStr.sizeOfQueue());

        queueOfStr.enqueue("a");
        assertEquals(1, queueOfStr.sizeOfQueue());

        queueOfStr.enqueue("b");
        assertEquals(2, queueOfStr.sizeOfQueue());

        queueOfStr.enqueue("c");
        assertEquals(3, queueOfStr.sizeOfQueue());

        String popped = queueOfStr.dequeue();
        assertEquals(2, queueOfStr.sizeOfQueue());
        assertEquals("a", popped);

        String popped1 = queueOfStr.dequeue();
        assertEquals(1, queueOfStr.sizeOfQueue());
        assertEquals("b", popped1);

        String popped2 = queueOfStr.dequeue();
        assertEquals(0, queueOfStr.sizeOfQueue());
        assertEquals("c", popped2);

    }

    @Test
    public void testFor(){
        LinkedQueue<String> queueOfStr = new LinkedQueue<String>();

        String[] stringsToAdd = {"to", "be", "or", "not", "to", "-", "be", "-", "-", "that", "-", "-", "-", "is"};

        for(String s : stringsToAdd){
            if(s.equals("-")){
                String stringToPop = queueOfStr.dequeue();
            }
            else{
                queueOfStr.enqueue(s);
            }
        }

        String[] left = {"that", "is"};
        int i = 0;
        for(String s : queueOfStr){
            assertEquals(left[i++], s);
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void testException(){
        LinkedQueue<Integer> bagOfNum = new LinkedQueue<>();
        bagOfNum.dequeue();
    }
}
