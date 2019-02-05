package search.applications;

import help.libraries.StdDraw;

import java.util.Comparator;

public final class Point2D implements Comparable<Point2D>{

    private final double x; // x-coordinate
    private final double y; // y-coordinate

    /*
    * compare two points by x-coordinate
    * */
    public static final Comparator<Point2D> x_order = new xOrder();

    /*
     * compare two points by y-coordinate
     * */
    public static final Comparator<Point2D> y_order = new yOrder();


    /*
    * initialize a new point (x,y)
    * */
    public Point2D(double x, double y){
        if(Double.isInfinite(x) || Double.isInfinite(y)){
            throw new IllegalArgumentException("coordinates must be finite");
        }
        if(Double.isNaN(x) || Double.isNaN(y)){
            throw new IllegalArgumentException("coordinates cannot by NaN");
        }
        this.x = x;
        this.y = y;
    }

    /*
    * return x-coordinate
    * */
    public double getX(){
        return x;
    }

    /*
     * return y-coordinate
     * */
    public double getY(){
        return y;
    }

    @Override
    public boolean equals(Object otherPoint){
        if(otherPoint == this){
            return true;
        }
        if(otherPoint == null){
            return false;
        }
        if(otherPoint.getClass() != this.getClass()){
            return false;
        }
        Point2D other = (Point2D) otherPoint;
        return this.x == other.x && this.y == other.y;
    }

    @Override
    public String toString(){
        return "( " + x + " , " + y + " )";
    }

    @Override
    public int hashCode(){
        int result = 17;
        result = 31 * result + ((Double) x).hashCode();
        result = 31 * result + ((Double) y).hashCode();
        return result;
    }


    /****************************
     * distance between points
     * *****************************/

    public double distanceTo(Point2D otherPoint){
        double dx = x - otherPoint.x;
        double dy = y - otherPoint.y;
        return Math.sqrt(dx*dx + dy*dy);
    }

    /****************************
     * draw
     * *****************************/

    /*
    * draw a point
    * */
    public void draw(){
        StdDraw.point(x,y);
    }

    /*
    * draw a line
    * */

    public void drawTo(Point2D otherPoint){
        StdDraw.line(x,y, otherPoint.x, otherPoint.y);
    }

    /****************************
    * compare points
    * *****************************/


    /*
    * compare two points by y-coordinate, breaking ties by x-coordinate
    * */
    @Override
    public int compareTo(Point2D otherPoint) {
        if(y < otherPoint.y){
            return -1;
        }
        if(y > otherPoint.y){
            return 1;
        }
        if(x < otherPoint.x){
            return -1;
        }
        if(x > otherPoint.x){
            return 1;
        }
        return 0;
    }

    /*
     * compare two points by x-coordinate
     * */
    private static class xOrder implements Comparator<Point2D>{
        @Override
        public int compare(Point2D point1, Point2D point2){
            if (point1.x < point2.x){
                return -1;
            }
            if (point1.x > point2.x){
                return 1;
            }
            return 0;
        }
    }


    /*
     * compare two points by y-coordinate
     * */
    private static class yOrder implements Comparator<Point2D>{
        @Override
        public int compare(Point2D point1, Point2D point2){
            if (point1.y < point2.y){
                return -1;
            }
            if (point1.y > point2.y){
                return 1;
            }
            return 0;
        }
    }
}
