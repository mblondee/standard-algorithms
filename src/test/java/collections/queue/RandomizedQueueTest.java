package collections.queue;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class RandomizedQueueTest {



    @Test
    public void resizingTest(){
        RandomizedQueue<Integer> queueOfNum = new RandomizedQueue<>();
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
        RandomizedQueue<Integer> bagOfNum = new RandomizedQueue<>();
        bagOfNum.dequeue();
    }
}
