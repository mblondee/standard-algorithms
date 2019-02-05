package search.applications;

import org.junit.Test;
import static org.junit.Assert.*;

public class SetTest {

    @Test
    public void test1(){
        PointSet set = new PointSet();
        assertTrue(set.isEmpty());
        Point2D point = new Point2D(1,3);
        Point2D point1 = new Point2D(5,6);
        set.insert(point);
        assertFalse(set.isEmpty());
        set.insert(new Point2D(5,6));
        assertEquals(2, set.size());
        assertTrue(set.contains(point));
        assertTrue(set.contains(point1));
    }

    @Test
    public void test2(){
        PointSet set = new PointSet();
        set.insert(new Point2D(5,6));
        set.insert(new Point2D(1,4));
        set.insert(new Point2D(0,10));
        Rectangle2D rect = new Rectangle2D(0.5, 0.5, 5.5, 7);
        for(Point2D point: set.range(rect)) {
            System.out.println(point);
        }
    }

    public static void main(String[] args) {
        run();
    }

    public static void run(){
        PointSet set = new PointSet();
        Point2D point = new Point2D(1,3);
        Point2D point1 = new Point2D(5,6);
        set.insert(point);
        set.insert(point1);
        set.draw();
    }
}
