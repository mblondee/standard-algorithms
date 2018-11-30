package sorting.application;

/*
* Given a set of n distinct points in the plane, find every line segment that connects k>=3 or more of these points.
* */



import help.libraries.StdDraw;
import sorting.sort.MergeSort;

import java.util.ArrayList;

public class CollinearPoints {

    private Point[] points; // given points
    private ArrayList<LineSegment> lineSegments = new ArrayList<>(); //list for adding lineSegments
    private int numberChecking; // at least 3

    public CollinearPoints(Point[] points, int numberChecking){

        this.points = points;
        this.numberChecking = numberChecking;
    }

    // add line segments made from points in array points
    // line point[0] -> point[i] with current<=i<=indexTo
    private void addLines(Point[] points, int current, int indexTo){
        for(int i = current; i <= indexTo; i++){
            System.out.println("adding " + points[0] + points[i]);
            lineSegments.add(new LineSegment(points[0], points[i]));
        }
    }

    public void draw(){
        StdDraw.setXscale(0, 33000);
        StdDraw.setYscale(0, 33000);
        StdDraw.setPenRadius(0.005);
        for (Point p : points){
            p.draw();
        }
        for(LineSegment line : lineSegments){
            line.draw();
        }

    }

    public void checkLineSegments(){
        for(int refPointIndex = 0; refPointIndex <= points.length- numberChecking; refPointIndex++){
            // look for all other points that are on the same line as refPoint
            Point refPoint = points[refPointIndex];
            System.out.println("checking "+ refPoint);
            // make a copy of all pointsLeft (the ones not yet checked)
            Point[] pointsLeft = new Point[points.length - refPointIndex];
            for(int i = 0; i < points.length - refPointIndex; i++){
                pointsLeft[i] = points[i + refPointIndex];
            }
            //sort wrt slope made with refPoint
            MergeSort.sort(pointsLeft, refPoint.CompareSlope);
            System.out.println("sorted array ");
            for(Point p : pointsLeft){
                System.out.println("  " + p + "slope " + p.slope(refPoint));
            }


            int currentIndex = 1; //index of first new point in sorted array (refPoint is in 0)
            int checkingIndex = 2; //index of first point to check

            double currentSlope;
            double checkingSlope;

            // refPoint and point in checkingIndex are collinear
            // if there are more points on the same line, they have to be next in the sorted array
            // (since it is sorted by slope with refPoint)
            // if at least numberChecking we add lines to lineSegments
            // if not we check next point
            int numberOfCollinear = 2;

            while(checkingIndex < pointsLeft.length){
                System.out.println("current " + currentIndex);
                System.out.println("checking " + checkingIndex);
                System.out.println("coll " + numberOfCollinear);
                currentSlope = pointsLeft[currentIndex].slope(refPoint);
                checkingSlope = pointsLeft[checkingIndex].slope(refPoint);

                // next point is on line refPoint -> currentPoint
                if(checkingSlope == currentSlope){
                    System.out.println("next is good");
                    numberOfCollinear ++;
                    // suppose checkingIndex was last index
                    // we check whether we have enough collinear points
                    if(checkingIndex == pointsLeft.length -1 && numberOfCollinear >= numberChecking){
                        // add lines from refPoint to all points between currentIndex and checkingIndex
                        addLines(pointsLeft, currentIndex, checkingIndex );
                        break;
                    }
                    // if not last index we continue checking next index
                    else{
                        checkingIndex ++;
                    }
                }

                // next point is not on line refPoint -> currentPoint

                // we have already enough collinear points
                // we add lines
                else if (numberOfCollinear >= numberChecking){
                    System.out.println("next is not good but we had enough coll points");
                    // add lines from refPoint to all points between currentIndex and checkingIndex-1
                    addLines(pointsLeft, currentIndex, checkingIndex-1 );
                    // reset current and checking
                    currentIndex = checkingIndex;
                    checkingIndex = currentIndex + 1;
                    numberOfCollinear = 2;
                }

                // reset current and checking
                else{
                    System.out.println("next is not good and we do not have enough coll points");
                    currentIndex = checkingIndex;
                    checkingIndex = currentIndex + 1;
                    numberOfCollinear = 2;
                }

            }
        }
    }


    public ArrayList<LineSegment> getLineSegments(){
        return lineSegments;
    }


}
