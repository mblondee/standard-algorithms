package collections.bag;

import org.junit.Test;
import static org.junit.Assert.*;

public class ResizingBagTest {

    @Test
    public void ResizingBagTest(){
        ResizingArrayBag<Double> bagOfNum = new ResizingArrayBag<>();

        double[] testArray = {100, 99, 101, 120, 98, 107, 109, 81, 101, 90};
        int count = 0;
        assertEquals(0, bagOfNum.size());
        assertTrue(bagOfNum.isEmpty());
        for(double x : testArray){
            bagOfNum.add(x);
            count++;
            assertEquals(count, bagOfNum.size());
        }



        int totalNum = bagOfNum.size();

        double sum = 0.0;
        for(double x : bagOfNum){
            sum += x;
        }

        double average = sum/totalNum;

        double sumToCalcStd = 0.0;

        for (double x : bagOfNum){
            sumToCalcStd += (x - average) * (x + average);
        }
        double std = Math.sqrt(sumToCalcStd/(totalNum - 1));

        assertEquals(100.6, average, 0.00001);
        assertEquals(10.511369505867933, std, 0.00001);

    }

    @Test
    public void resizingTest(){
        ResizingArrayBag<Integer> bagOfNum = new ResizingArrayBag<>();
        bagOfNum.add(1);
        assertEquals(2, bagOfNum.getArrayBag());
        bagOfNum.add(1);
        assertEquals(2, bagOfNum.getArrayBag());
        bagOfNum.add(1);
        assertEquals(4, bagOfNum.getArrayBag());
        bagOfNum.add(1);
        assertEquals(4, bagOfNum.getArrayBag());
        bagOfNum.add(1);
        assertEquals(8, bagOfNum.getArrayBag());

    }
}
