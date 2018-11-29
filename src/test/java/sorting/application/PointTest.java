package sorting.application;

import help.libraries.StdDraw;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class PointTest {

    @Test
    public void testPoint(){
        Point point1 = new Point(0,0);
        Point point2 = new Point(0,5);
        Point point3 = new Point(1,5);
        Point point4 = new Point(1,5);

        assertTrue(point1.compareTo(point2) < 0);
        assertFalse(point2.compareTo(point1) < 0);
        assertTrue(point1.compareTo(point3) < 0);
        assertFalse(point3.compareTo(point1) < 0);
        assertTrue(point2.compareTo(point3) <0);
        assertFalse(point3.compareTo(point2) < 0);
        assertTrue(point3.equals(point4));
        assertFalse(point1.equals(point4));
    }

    @Test
    public void testSlope(){
        Point point1 = new Point(0,0);
        Point point2 = new Point(0,5);
        Point point3 = new Point(0,5);
        Point point4 = new Point(1,6);
        Point point5 = new Point(4,9);
        Point point6 = new Point(1,8);

        assertEquals(Double.POSITIVE_INFINITY, point1.slope(point2), 0.00001);
        assertEquals(Double.NEGATIVE_INFINITY, point3.slope(point2), 0.00001);
        assertEquals(1, point3.slope(point4), 0.00001);
        assertEquals(1, point4.slope(point3), 0.00001);
        assertEquals(1.0/3, point5.slope(point6), 0.00001);

    }

    @Test
    public void TestCompareSlope(){
        Point pointRef = new Point(4,9);

        Point point1 = new Point(4,9);
        Point point2 = new Point(2,7);
        Point point3 = new Point(4,9);
        Point point4 = new Point(4,7);
        Point point5 = new Point(4,8);
        Point point6 = new Point(1,8);

        // one of the points is the same as the reference point
        assertTrue(pointRef.CompareSlope.compare(point1, point2) < 0);

        //both points are the same as the reference point
        assertTrue(pointRef.CompareSlope.compare(point2, point3) > 0);

        // one point has slope Double.POSITIVE_INFINITY (vertical)
        assertTrue(pointRef.CompareSlope.compare(point4, point2)>0);

        // both points have slope Double.POSITIVE_INFINITY (vertical)
        assertTrue(pointRef.CompareSlope.compare(point4, point5) == 0);

        assertTrue(pointRef.CompareSlope.compare(point2, point6) > 0);

    }



}
