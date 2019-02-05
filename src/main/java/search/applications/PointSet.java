package search.applications;

/*
* data type representing a set of points in the unit square
* brute force implementation
* */

import help.libraries.StdDraw;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class PointSet {

    private TreeSet<Point2D> setOfPoints ;
    private int size;

    private static int SCALE = 10; //scale for drawing
    private static double RADIUS = 0.005; //pen radius for drawing

    public PointSet(){
        setOfPoints = new TreeSet<>();
        size = 0;
    }

    /*
    * is the set empty?
    * */
    public boolean isEmpty(){
        return size == 0;
    }

    /*
    * return size
    * */
    public int size(){
        return size;
    }

    /*
    * add {@code point} to the set (if not already in the set)
    * log(n) time
    * */
    public void insert(Point2D point){
        setOfPoints.add(point);
        size++;
    }

    /*
    * does the set contain {@code point}?
    * log(n) time
    * */
    public boolean contains(Point2D point){
        return setOfPoints.contains(point);
    }

    /*
    * draw all points
    * */
    public void draw(){
        StdDraw.setXscale(0, SCALE);
        StdDraw.setYscale(0, SCALE);
        StdDraw.setPenRadius(RADIUS);
        for (Point2D p : setOfPoints){
            p.draw();
        }
    }

    /*
    * all points that are inside or on the boundary of {@code rectangle}
    * */
    public Iterable<Point2D> range(Rectangle2D rectangle){
        List<Point2D> listOfPoints = new ArrayList<>();

        for(Point2D point: setOfPoints){
            if(rectangle.contains(point)){
                listOfPoints.add(point);
            }
        }
        return listOfPoints;
    }

    /*
    * a nearest neighbour in the set to {@code point}
    * */
    public Point2D nearest(Point2D point){
        Point2D nearestPoint = null;
        for(Point2D p: setOfPoints){
            if(nearestPoint == null || p.distanceTo(point) < nearestPoint.distanceTo(point)){
                nearestPoint = point;
            }
        }

        return nearestPoint;

    }


}
