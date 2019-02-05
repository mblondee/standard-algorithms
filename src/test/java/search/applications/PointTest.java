package search.applications;

import org.junit.Test;
import sorting.sort.Sort;

import static org.junit.Assert.*;

public class PointTest {

    @Test
    public void test1(){
        Point2D point1 = new Point2D(1,5);
        Point2D point2 = new Point2D(1.5, 7.1);
        Point2D point3 = new Point2D(1.0, 5.0);
        assertFalse(point1.equals(point2));
        assertTrue(point1.equals(point3));
        assertEquals(2.1587, point1.distanceTo(point2), 0.001);
        assertTrue(Sort.isStrictLess(point1, point2));
        assertTrue(Sort.isStrictLess(point1, point2, Point2D.x_order));
        assertTrue(Sort.isStrictLess(point1, point2, Point2D.y_order));
    }
}
