package strings;

import org.junit.Test;
import strings.substringsearch.BruteForce;
import strings.substringsearch.KMP;
import strings.substringsearch.RabinKarp;

import static org.junit.Assert.*;

public class SubStringTest {
    @Test
    public void testBruteForce(){
        String text = "abracadabra";
        assertEquals(4, BruteForce.search("cada", text));
        assertEquals(0, BruteForce.search("ab", "ababababab"));
        assertEquals(10, BruteForce.search("cb", "ababababab"));

        String text1 = "abacadabrabracabracadabrabrabracad";
        String pattern1 = "abracadabra";
        assertEquals(14, BruteForce.search(pattern1, text1));
    }

    @Test
    public void testKMP(){
        String text1 = "abacadabrabracabracadabrabrabracad";
        String text2 = "abracadabra";
        assertEquals(4, new KMP("cada").search(text2));
        assertEquals(4, new KMP("adabra").search(text1));
    }

    @Test
    public void RabinKarp(){
        String text1 = "abacadabrabracabracadabrabrabracad";
        String text2 = "abracadabra";
        assertEquals(4, new RabinKarp("cada").search(text2));
        assertEquals(4, new RabinKarp("adabra").search(text1));
    }
}
