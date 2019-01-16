package sorting.application.collinearpoints;


/*given a set of points (read from a file)
 * find all lines that have at least numberOfCollPoints points
  * draw all points and lines
  * */

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class CollinearPointsExample {

    private static String locationFile = "src/main/resources/input80.txt";
    private static int numberOfCollPoints = 4;


    public static void main(String[] args) {
        run();
    }

    public static void run(){
        ReadFile readingInput = new ReadFile();
        readingInput.readFile(locationFile);
        Point[] points = readingInput.inputPoints;
        CollinearPoints coll = new CollinearPoints(points,numberOfCollPoints);
        coll.checkLineSegments();
        for (LineSegment line : coll.getLineSegments()){
            System.out.println(line);
        }
        coll.draw();
    }

    private static class ReadFile {
        private int numberOfElements; //first line in file: number of different elements
        private Point[] inputPoints; // points

        private void readFile(String fileName) {
            System.out.println("Reading file");
            String line;
            ArrayList<Point> listOfPoints = new ArrayList<>();
            try {
                FileReader inputFile = new FileReader(fileName);
                BufferedReader bufferReader = new BufferedReader(inputFile);
                line = bufferReader.readLine();
                //
                numberOfElements = Integer.parseInt(line);
                while ((line = bufferReader.readLine()) != null) {
                    String lineTrim = line.trim();
                    String[] lineSplitted = lineTrim.split("\\s+"); //split on any number of whitespace
                    int p = Integer.parseInt(lineSplitted[0].trim());
                    int q = Integer.parseInt(lineSplitted[1].trim());
                    Point toAdd = new Point(p, q);
                    listOfPoints.add(toAdd);
                }
                bufferReader.close();
                inputPoints = new Point[listOfPoints.size()];
                int i = 0;
                for (Point point : listOfPoints) {
                    inputPoints[i++] = point;
                }


            } catch (Exception e) {
                System.out.println("Error while reading file " + e.getMessage());
            }
        }
    }




}



