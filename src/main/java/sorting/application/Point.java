package sorting.application;

/*
* a data type for points in the plane
* */

//TODO override hashCode()


import java.util.Comparator;

public class Point implements Comparable<Point> {

    private final int x; // x-coordinate of the point, cannot change
    private final int y; // y-coordinate of the point, cannot change


    //initializing a point
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
            return (otherPoint.getY() - y) / (otherPoint.getX() - x);
        }
    }

    /*
    * return a comparator that compares two points by the slopes they make with this point
    * (x1,y1) is less than (x2, y2) if slope(x1,y1) < slope(x2,y2)
    * */




}
