package collections.stack;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class ResizingStackTest {

    @Test
    public void testStack(){
        ResizingArrayStack<String> stackOfStr = new ResizingArrayStack<>();
        assertTrue(stackOfStr.isEmpty());
        assertEquals(0, stackOfStr.sizeOfStack());

        stackOfStr.push("a");
        assertEquals(1, stackOfStr.sizeOfStack());

        stackOfStr.push("b");
        assertEquals(2, stackOfStr.sizeOfStack());

        stackOfStr.push("c");
        assertEquals(3, stackOfStr.sizeOfStack());

        String popped = stackOfStr.pop();
        assertEquals(2, stackOfStr.sizeOfStack());
        assertEquals("c", popped);

        String popped1 = stackOfStr.pop();
        assertEquals(1, stackOfStr.sizeOfStack());
        assertEquals("b", popped1);

        String popped2 = stackOfStr.pop();
        assertEquals(0, stackOfStr.sizeOfStack());
        assertEquals("a", popped2);

    }

    @Test
    public void testFor(){
        ResizingArrayStack<String> stackOfStr = new ResizingArrayStack<>();

        String[] stringsToAdd = {"to", "be", "or", "not", "to", "-", "be", "-", "-", "that", "-", "-", "-", "is"};

        for(String s : stringsToAdd){
            if(s.equals("-")){
                String stringToPop = stackOfStr.pop();
            }
            else{
                stackOfStr.push(s);
            }
        }

        String[] left = {"is", "to"};
        int i = 0;
        for(String s : stackOfStr){
            assertEquals(left[i++], s);
        }
    }

    @Test
    public void resizingTest(){
        ResizingArrayStack<Integer> bagOfNum = new ResizingArrayStack<>();
        bagOfNum.push(1);
        assertEquals(2, bagOfNum.getArrayStack());
        bagOfNum.push(1);
        assertEquals(2, bagOfNum.getArrayStack());
        bagOfNum.push(1);
        assertEquals(4, bagOfNum.getArrayStack());
        bagOfNum.push(1);
        assertEquals(4, bagOfNum.getArrayStack());
        bagOfNum.push(1);
        assertEquals(8, bagOfNum.getArrayStack());
        bagOfNum.pop();
        assertEquals(8, bagOfNum.getArrayStack());
        bagOfNum.pop();
        assertEquals(8, bagOfNum.getArrayStack());
        bagOfNum.pop();
        assertEquals(4, bagOfNum.getArrayStack());
        bagOfNum.pop();
        assertEquals(2, bagOfNum.getArrayStack());
        bagOfNum.pop();
        assertEquals(2, bagOfNum.getArrayStack());

    }

    @Test(expected = NoSuchElementException.class)
    public void testException(){
        ResizingArrayStack<Integer> bagOfNum = new ResizingArrayStack<>();
        bagOfNum.pop();
    }
}
