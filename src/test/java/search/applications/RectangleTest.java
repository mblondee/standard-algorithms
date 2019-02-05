package search.applications;

import org.junit.Test;
import static org.junit.Assert.*;

public class RectangleTest {

    @Test
    public void test1(){
        Rectangle2D rect1 = new Rectangle2D(1.5, 3, 6.5, 8);
        Point2D point1 = new Point2D(2,5);
        Point2D point2 = new Point2D(1,5);
        Point2D point3 = new Point2D(10, 2);
        Point2D point4 = new Point2D(2,0);
        Point2D point5 = new Point2D(4, 10);
        assertTrue(rect1.contains(point1));
        assertFalse(rect1.contains(point2));
        assertFalse(rect1.contains(point3));
        assertFalse(rect1.contains(point4));
        assertFalse(rect1.contains(point5));
    }

    @Test
    public void test2(){
        Rectangle2D rect1 = new Rectangle2D(1.5, 3, 6.5, 8);
        Rectangle2D rect2 = new Rectangle2D(3, 4, 6.5, 8);
        Rectangle2D rect3 = new Rectangle2D(0, 4, 2, 8);
        Rectangle2D rect4 = new Rectangle2D(0, 4, 1, 8);
        assertTrue(rect1.intersect(rect2));
        assertTrue(rect1.intersect(rect3));
        assertFalse(rect1.intersect(rect4));
    }
}
