package sorting.application;

/*
 * a data type for line segments in the plane, coordinates of endpoints are integers
 * */

import help.libraries.StdDraw;

import java.util.Objects;

public class LineSegment {

    private final Point startingPoint; //starting point of a line
    private final Point endPoint; // end point of a line

    /*initializing of a line segment */
    public LineSegment(Point startingPoint, Point endPoint){
        this.startingPoint = startingPoint;
        this.endPoint = endPoint;
    }

    @Override
    public String toString(){
        return "Line from " + startingPoint + " to " + endPoint;
    }

    @Override
    public int hashCode(){
        return Objects.hash(startingPoint, endPoint);
    }

    public void draw(){
        StdDraw.line(startingPoint.getX(), startingPoint.getY(), endPoint.getX(), endPoint.getY());
    }

}
