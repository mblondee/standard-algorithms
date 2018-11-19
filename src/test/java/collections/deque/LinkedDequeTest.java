package collections.deque;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class LinkedDequeTest {

    @Test
    public void testDeque(){
        LinkedDeque<String> dequeOfStr = new LinkedDeque<>();
        assertTrue(dequeOfStr.isEmpty());
        assertEquals(0, dequeOfStr.size());

        dequeOfStr.addFirst("a");
        assertEquals(1, dequeOfStr.size());

        dequeOfStr.addFirst("b");
        assertEquals(2, dequeOfStr.size());

        dequeOfStr.addFirst("c");
        assertEquals(3, dequeOfStr.size());

        String popped = dequeOfStr.removeFirst();
        assertEquals(2, dequeOfStr.size());
        assertEquals("c", popped);

        String popped1 = dequeOfStr.removeLast();
        assertEquals(1, dequeOfStr.size());
        assertEquals("a", popped1);

        dequeOfStr.addLast("d");
        assertEquals(2, dequeOfStr.size());

        dequeOfStr.addLast("e");
        assertEquals(3, dequeOfStr.size());

        String popped2 = dequeOfStr.removeLast();
        assertEquals(2, dequeOfStr.size());
        assertEquals("e", popped2);

        String popped3 = dequeOfStr.removeLast();
        assertEquals(1, dequeOfStr.size());
        assertEquals("d", popped3);

        String popped4 = dequeOfStr.removeFirst();
        assertEquals(0, dequeOfStr.size());
        assertEquals("b", popped4);

        assertTrue(dequeOfStr.isEmpty());

    }


    @Test(expected = NoSuchElementException.class)
    public void testException(){
        LinkedDeque<Integer> bagOfNum = new LinkedDeque<>();
        bagOfNum.removeFirst();
    }


    @Test(expected = NoSuchElementException.class)
    public void testException1(){
        LinkedDeque<Integer> bagOfNum = new LinkedDeque<>();
        bagOfNum.removeLast();
    }

    @Test
    public void testFor(){
        LinkedDeque<String> queueOfStr = new LinkedDeque<String>();

        String[] stringsToAdd = {"to", "be", "or", "not", "to", "-", "be", "-", "-", "that", "-", "-", "-", "is"};

        for(String s : stringsToAdd){
            if(s.equals("-")){
                queueOfStr.removeFirst();
            }
            else{
                queueOfStr.addLast(s);
            }
        }

        String[] left = {"that", "is"};
        int i = 0;
        for(String s : queueOfStr){
            assertEquals(left[i++], s);
        }
    }

}
