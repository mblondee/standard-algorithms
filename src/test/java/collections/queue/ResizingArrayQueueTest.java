package collections.queue;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class ResizingArrayQueueTest {

    @Test
    public void testQueue(){
        ResizingArrayQueue<String> queueOfStr = new ResizingArrayQueue<>();
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
        ResizingArrayQueue<String> queueOfStr = new ResizingArrayQueue<String>();

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

    @Test
    public void resizingTest(){
        ResizingArrayQueue<Integer> queueOfNum = new ResizingArrayQueue<>();
        queueOfNum.enqueue(1);
        assertEquals(2, queueOfNum.getArrayQueue());
        queueOfNum.enqueue(1);
        assertEquals(2, queueOfNum.getArrayQueue());
        queueOfNum.enqueue(1);
        assertEquals(4, queueOfNum.getArrayQueue());
        queueOfNum.enqueue(1);
        assertEquals(4, queueOfNum.getArrayQueue());
        queueOfNum.enqueue(1);
        assertEquals(8, queueOfNum.getArrayQueue());
        queueOfNum.dequeue();
        assertEquals(8, queueOfNum.getArrayQueue());
        queueOfNum.dequeue();
        assertEquals(8, queueOfNum.getArrayQueue());
        queueOfNum.dequeue();
        assertEquals(4, queueOfNum.getArrayQueue());
        queueOfNum.dequeue();
        assertEquals(2, queueOfNum.getArrayQueue());
        queueOfNum.dequeue();
        assertEquals(2, queueOfNum.getArrayQueue());


    }

    @Test(expected = NoSuchElementException.class)
    public void testException(){
        ResizingArrayQueue<Integer> bagOfNum = new ResizingArrayQueue<>();
        bagOfNum.dequeue();
    }

}
