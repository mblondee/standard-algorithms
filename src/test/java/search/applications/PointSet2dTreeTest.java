package search.applications;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PointSet2dTreeTest {

    @Test
    public void test1(){
        PointSet2dTree BST = new PointSet2dTree();
        assertTrue(BST.isEmpty());
        assertEquals(0, BST.size());
        Point2D point1 = new Point2D(0.7, 0.2);
        assertFalse(BST.contains(point1));
        BST.insert(point1);
        assertFalse(BST.isEmpty());
        assertEquals(1, BST.size());
        assertTrue(BST.contains(point1));

        Point2D point2 = new Point2D(0.5, 0.4);
        assertFalse(BST.contains(point2));
        BST.insert(point2);
        assertEquals(2, BST.size());
        assertTrue(BST.contains(point2));

        Point2D point3 = new Point2D(0.2, 0.3);
        assertFalse(BST.contains(point3));
        BST.insert(point3);
        assertEquals(3, BST.size());
        assertTrue(BST.contains(point3));

        Point2D point4 = new Point2D(0.4, 0.7);
        assertFalse(BST.contains(point4));
        BST.insert(point4);
        assertEquals(4, BST.size());
        assertTrue(BST.contains(point4));

        Point2D point5 = new Point2D(0.9, 0.6);
        assertFalse(BST.contains(point5));
        BST.insert(point5);
        assertEquals(5, BST.size());
        assertTrue(BST.contains(point5));
    }

    @Test
    public void test2(){
        PointSet2dTree BST = new PointSet2dTree();
        BST.insert(new Point2D(0.7, 0.2));
        BST.insert(new Point2D(0.5, 0.4));
        BST.insert(new Point2D(0.2, 0.3));
        BST.insert(new Point2D(0.9, 0.6));
        BST.insert(new Point2D(0.4, 0.7));
        for(Point2D p : BST.range(new Rectangle2D(0.1,0.1, 0.3, 0.3))){
            System.out.println(p);
        }
        System.out.println("------");
        for(Point2D p : BST.range(new Rectangle2D(0.1,0.1, 0.9, 0.9))){
            System.out.println(p);
        }
        System.out.println("------");
        for(Point2D p : BST.range(new Rectangle2D(0.3,0.4, 0.9, 0.8))){
            System.out.println(p);
        }
        System.out.println("------");
    }

    @Test
    public void test3(){
        PointSet2dTree BST = new PointSet2dTree();
        Point2D point1 = new Point2D(0.7, 0.2);
        BST.insert(point1);

        Point2D point2 = new Point2D(0.5, 0.4);
        BST.insert(point2);

        Point2D point3 = new Point2D(0.2, 0.3);
        BST.insert(point3);

        Point2D point4 = new Point2D(0.4, 0.7);
        BST.insert(point4);

        Point2D point5 = new Point2D(0.9, 0.6);
        BST.insert(point5);

        Point2D point1a = new Point2D(0.1, 0.1);
        assertEquals(BST.nearest(point1a), point3);
        Point2D point2a = new Point2D(0.1, 0.45);
        assertEquals(BST.nearest(point2a), point3);
        Point2D point3a = new Point2D(0.5, 0.1);
        assertEquals(BST.nearest(point3a), point1);

        Point2D point4a = new Point2D(0.45, 0.45);
        assertEquals(BST.nearest(point4a), point2);

    }

    public static void main(String[] args) {
        run();
    }

    public static void run(){
        PointSet2dTree BST = new PointSet2dTree();
        BST.insert(new Point2D(0.7, 0.2));
        BST.insert(new Point2D(0.5, 0.4));
        BST.insert(new Point2D(0.2, 0.3));
        BST.insert(new Point2D(0.4, 0.7));
        BST.insert(new Point2D(0.9, 0.6));
        BST.draw();
        Point2D point = new Point2D(0.45, 0.45);
        point.draw();
    }
}
