package sorting.application;

/*
* a data type for points in the plane, coordinates are integers
* two points can be compared to a reference point by there respective slopes
* */



import help.libraries.StdDraw;

import java.util.Comparator;
import java.util.Objects;

public class Point implements Comparable<Point> {

    private final int x; // x-coordinate of the point, cannot change
    private final int y; // y-coordinate of the point, cannot change

    /* a comparator with respect to this point*/
    public final Comparator<Point> CompareSlope = new CompareSlope();


    /*initializing a point*/
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    /*
     * a comparator that compares two points by the slopes they make with this point
     * (x1,y1) is less than (x2, y2) if slope(x1,y1) < slope(x2,y2)
     * */
    private class CompareSlope implements Comparator<Point>{
        @Override
        public int compare(Point point1, Point point2){
            double slope1 = slope(point1);
            double slope2 = slope(point2);
            if (slope1 == slope2){
                return 0; // also 0 when both Double.POSITIVE_INFINITY
            }
            else if (slope1 < slope2){
                return -1;
            }
            else {
                return 1;
            }
        }

    }




    @Override
    public String toString(){
        return "(" + x + ", " + y + ")";
    }

    /*
    * natural order of points:
    * a point (xo,yo) is equal to a point (x1, y1) if x0 = y0 and x1=y1
    * (xo, yo) is less than (x1,y1) if y0<y1 or y0 = y1 and x0 < x1
    * */
    @Override
    public int compareTo(Point otherPoint){
        if(y == otherPoint.getY()){
            return x - otherPoint.getX();
        }
        else {
            return y - otherPoint.getY();
        }
    }

    @Override
    public boolean equals(Object otherPoint){
        return compareTo((Point)otherPoint) == 0;
    }

    @Override
    public int hashCode(){
        return Objects.hash(x,y);
    }

    /*
    * return slope between the point and another point
    * slope for points (x0,y0) and (x1,y1) is (y1 - y0)/(x1 - x0)
    * if x0 = x1 and y0 != y1 the slope is positive infinity (vertical line between the two points)
    * if x0 = x1 and y0 = y1 return negative infinity
     */
    public double slope(Point otherPoint){
        if (equals(otherPoint)){
            return Double.NEGATIVE_INFINITY;
        }
        else if (x == otherPoint.getX() && y != otherPoint.getY()){
            return Double.POSITIVE_INFINITY;
        }
        else{
            return (double) (otherPoint.getY() - y) / (otherPoint.getX() - x);
        }
    }

    public void draw(){
        StdDraw.point((double)x,(double)y);
    }





}
