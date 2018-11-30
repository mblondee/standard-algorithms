package sorting.application;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class TestCollinearPoints {

    private static int numberOfPoints;
    private static Point[] points;

/*    @BeforeClass
    public static void initialize(){
        String locationFile = "src/main/resources/input80.txt";
        ReadFile readingInput80 = new ReadFile();
        readingInput80.readFile(locationFile);
        numberOfPoints = readingInput80.numberOfElements;
        points = readingInput80.inputPoints;
    }*/

    @Test
    public void testReading(){
        String locationFile = "src/main/resources/input80.txt";
        ReadFile readingInput80 = new ReadFile();
        readingInput80.readFile(locationFile);
        numberOfPoints = readingInput80.numberOfElements;
        points = readingInput80.inputPoints;
        assertEquals(80, numberOfPoints);
        assertEquals(80, points.length);
    }

    @Test
    public void testFile(){
        String locationFile = "src/main/resources/input80.txt";
        ReadFile readingInput80 = new ReadFile();
        readingInput80.readFile(locationFile);
        numberOfPoints = readingInput80.numberOfElements;
        points = readingInput80.inputPoints;
        CollinearPoints coll = new CollinearPoints(points, 4);
        coll.checkLineSegments();
        System.out.println(coll.getLineSegments());
    }

    @Test
    public void easyTest(){
        Point point1 = new Point(1,2);
        Point point2 = new Point(2,3);
        Point point3 = new Point(3,4);
        Point point4 = new Point(4,5);
        Point point5 = new Point(5,6);
        Point point6 = new Point(1,6);

        Point[] points = {point1, point2, point3, point4, point5, point6};
        CollinearPoints coll = new CollinearPoints(points, 2);
        coll.checkLineSegments();
        for (LineSegment line : coll.getLineSegments()){
            System.out.println(line);
        }
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
                    Point toAdd = new Point(p,q);
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
