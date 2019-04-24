package datacompression;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class LZWTest {

    @Test
    public void test(){
        String test = "ABRACADABRA!";
        List<Integer> compressed = LZW.compress(test);
        for(Integer i : compressed){
            System.out.println(i);
        }
        String reverse = LZW.decompress(compressed);
        System.out.println(reverse);

        assertEquals(test, reverse);

        String test1 = "Is this ok?";
        String reverse1 = LZW.decompress(LZW.compress(test1));
        assertEquals(test1, reverse1);
    }
}
