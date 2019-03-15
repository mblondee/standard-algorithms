package strings;

import org.junit.Test;
import strings.substringsearch.BruteForce;

import static org.junit.Assert.*;

public class SubStringTest {
    @Test
    public void testBruteForce(){
        String text = "abracadabra";
        assertEquals(4, BruteForce.search("cada", text));
        assertEquals(0, BruteForce.search("ab", "ababababab"));
        assertEquals(10, BruteForce.search("cb", "ababababab"));
    }
}
