package sorting.application.collinearpoints;

import help.libraries.StdDraw;
import sorting.application.collinearpoints.LineSegment;
import sorting.application.collinearpoints.Point;

public class StdDrawTest {
    public static void main(String[] args) {
        StdDraw.setXscale(0, 50);
        StdDraw.setYscale(0, 50);
        StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(StdDraw.BLUE);
        Point refPoint = new Point(5,5);
        refPoint.draw();
        StdDraw.setPenColor(StdDraw.MAGENTA);
        Point start = new Point(2,2);
        Point end = new Point(8,2);
        LineSegment line = new LineSegment(start, end);
        line.draw();
    }
}
