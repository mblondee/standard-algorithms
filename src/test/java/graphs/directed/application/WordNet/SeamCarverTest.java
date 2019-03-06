package graphs.directed.application.WordNet;

import graphs.directed.application.seamcarver.SeamCarver;
import help.libraries.Picture;
import org.junit.Test;

import static org.junit.Assert.*;

public class SeamCarverTest {



    @Test
    public void test(){
        String filename = "src/main/resources/3x4.png";
        SeamCarver sc = new SeamCarver(new Picture(filename));
        assertEquals(3, sc.width());
        assertEquals(4, sc.height());
        sc.findVerticalSeam();
    }
}
