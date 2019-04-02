package datacompression;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class RunLengthTest {

    @Test
    public void test1(){
        String input = "WWWWWWWWWWWWBWWWWWWWWWWWWBBBWWWWWWWWWWWWWWWWWWWWWWWWBWWWWWWWWWWWWWW";
        assertEquals("12W1B12W3B24W1B14W", RunLength.compress(input));

        String compressed = "12W1B12W3B24W1B14W";
        assertEquals(input, RunLength.decompress(compressed));



    }
}
