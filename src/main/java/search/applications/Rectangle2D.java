package search.applications;

import com.sun.javafx.font.directwrite.RECT;
import help.libraries.StdDraw;

public final class Rectangle2D {
    private final double xLowerLeft;
    private final double yLowerLeft;
    private final double xUpperRight;
    private final double yUpperRight;


    /*
     * initialize a new rectangle (x,y)
     * */
    public Rectangle2D(double xLowerLeft, double yLowerLeft, double xUpperRight, double yUpperRight){
        if(Double.isNaN(xLowerLeft) || Double.isNaN(yLowerLeft) || Double.isNaN(xUpperRight) || Double.isNaN(yUpperRight)){
            throw new IllegalArgumentException("one of the coordinates is NaN");
        }
        if(xUpperRight < xLowerLeft){
            throw new IllegalArgumentException("xUpperRight < xLowerLeft");
        }
        if(yUpperRight < yLowerLeft){
            throw new IllegalArgumentException("yUpperRight < yLowerLeft");
        }
        this.xLowerLeft = xLowerLeft;
        this.yLowerLeft = yLowerLeft;
        this.xUpperRight = xUpperRight;
        this.yUpperRight = yUpperRight;
    }

    public double getxLowerLeft(){
        return xLowerLeft;
    }

    public double getyLowerLeft(){
        return yLowerLeft;
    }

    public double getxUpperRight(){
        return xUpperRight;
    }

    public double getyUpperRight(){
        return yUpperRight;
    }



    @Override
    public boolean equals(Object otherRectangle){
        if(this == otherRectangle){
            return true;
        }
        if(otherRectangle == null){
            return false;
        }
        if(otherRectangle.getClass() != this.getClass()){
            return false;
        }
        Rectangle2D other = (Rectangle2D) otherRectangle;
        if(this.xLowerLeft != other.xLowerLeft){return false;}
        if(this.xUpperRight != other.xUpperRight){return false;}
        if(this.yLowerLeft != other.yLowerLeft){return false;}
        if(this.yUpperRight != other.yUpperRight){return false;}
        return true;
    }

    @Override
    public int hashCode(){
        int result = 17;
        result = 31 * result + ((Double) xLowerLeft).hashCode();
        result = 31 * result + ((Double) xUpperRight).hashCode();
        result = 31 * result + ((Double) yLowerLeft).hashCode();
        result = 31 * result + ((Double) yUpperRight).hashCode();
        return result;
    }

    @Override
    public String toString(){
        return "( " + xLowerLeft + " , " + yLowerLeft + " ) to (" + xUpperRight + " , " + yUpperRight + " )";
    }

    /****************************
     * contain, intersect, distance
     * *****************************/

    /*
    * does the rectangle contain {@code point}? (inside or on boundary)
    * */
    public boolean contains(Point2D point){
        if(point.getX() < xLowerLeft){return false;}
        if(point.getX() > xUpperRight){return false;}
        if(point.getY() < yLowerLeft){return false;}
        if(point.getY() > yUpperRight){return false;}
        return true;
    }

    /*
    * does the rectangle intersect another rectangle
    * */
    public boolean intersect(Rectangle2D otherRect){
        if(otherRect.getyLowerLeft() > yUpperRight ){return false;}
        if(otherRect.getxLowerLeft() > xUpperRight) {return false;}
        if(otherRect.getyUpperRight() < yLowerLeft){return false;}
        if(otherRect.getxUpperRight() < xLowerLeft){return false;}
        return true;
    }

    /*
    * distance from {@code point} to the closest point in the rectangle
    * is point is inside the rectangle: 0
    * */

    public double distanceTo(Point2D point){
        double dx = 0;
        double dy = 0;
        if(point.getX() < xLowerLeft){
            dx = xLowerLeft - point.getX();
        }
        else if (point.getX() > xUpperRight){
            dx = point.getX() - xUpperRight;
        }

        if(point.getY() < yLowerLeft){
            dy = yLowerLeft - point.getY();
        }
        else if (point.getY() > yUpperRight){
            dy = point.getY() - yUpperRight;
        }

        return Math.sqrt(dx*dx + dy*dy);
    }


    /****************************
     * draw
     * *****************************/

    public void draw(){
        StdDraw.line(xLowerLeft, yLowerLeft, xUpperRight, yLowerLeft);
        StdDraw.line(xUpperRight, yLowerLeft, xUpperRight, yUpperRight);
        StdDraw.line(xUpperRight, yUpperRight, xLowerLeft, yUpperRight);
        StdDraw.line(xLowerLeft, yUpperRight, xLowerLeft, yLowerLeft);
    }
}
