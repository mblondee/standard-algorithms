package percolation.unionfind;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import percolation.unionfind.impl.QuickFind;
import percolation.unionfind.impl.QuickUnion;
import percolation.unionfind.impl.WeightedQuickUnion;
import percolation.unionfind.impl.WeightedQuickUnionCompression;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/*
 * Class TestUF test the different UF implementations
 * uses textfiles structured as follows
 * first line: number of different elements
 * next lines: pairs of elements to be connected
 * elements are integers
 * */

public class UFTest {

    private static int numberOfElementsTiny; // first line in tiny file: number of different elements
    private static int[][] inputPairsTiny; // pairs that have to be connected

    private static int numberOfElementsMedium; // first line in medium file: number of different elements
    private static int[][] inputPairsMedium; // pairs that have to be connected

    @BeforeClass
    public static void initialize(){
        String locationTiny = "/Users/marjonblondeel/Documents/algorithms-datastructures/src/main/resources/tiny.txt";
        ReadFile readingTiny = new ReadFile();
        readingTiny.readFile(locationTiny);
        numberOfElementsTiny = readingTiny.numberOfElements;
        inputPairsTiny = readingTiny.inputPairs;

        String locationMedium = "/Users/marjonblondeel/Documents/algorithms-datastructures/src/main/resources/medium.txt";
        ReadFile readingMedium = new ReadFile();
        readingMedium.readFile(locationMedium);
        numberOfElementsMedium = readingMedium.numberOfElements;
        inputPairsMedium = readingMedium.inputPairs;

    }

    private static class ReadFile {
        private int numberOfElements; //first line in file: number of different elements
        private int[][] inputPairs; // pairs that have to be connected

        private void readFile(String fileName) {
            System.out.println("Reading file");
            String line;
            ArrayList<int[]> listOfLines = new ArrayList<int[]>();
            try {
                FileReader inputFile = new FileReader(fileName);
                BufferedReader bufferReader = new BufferedReader(inputFile);
                line = bufferReader.readLine();
                //
                numberOfElements = Integer.parseInt(line);
                while ((line = bufferReader.readLine()) != null) {
                    String[] lineSplitted = line.split(";");
                    int p = Integer.parseInt(lineSplitted[0].trim());
                    int q = Integer.parseInt(lineSplitted[1].trim());
                    int[] toAdd = new int[2];
                    toAdd[0] = p;
                    toAdd[1] = q;
                    listOfLines.add(toAdd);
                }
                bufferReader.close();
                inputPairs = new int[listOfLines.size()][2];
                int i = 0;
                for (int[] item : listOfLines) {
                    inputPairs[i++] = item;
                }


            } catch (Exception e) {
                System.out.println("Error while reading file " + e.getMessage());
            }
        }
    }


    @Test
    public void quickFindTestTiny() {
        QuickFind algorithm = new QuickFind(numberOfElementsTiny);
        for (int[] item : inputPairsTiny) {
            int p = item[0];
            int q = item[1];

            // if already connected do nothing, go to next pair
            if (algorithm.connected(p, q)) {
                continue;
            }

            algorithm.union(p, q);
            //System.out.println("connected " + p + " and " + q);

        }
        //System.out.println("components: " + algorithm.count());
        assertEquals(2, algorithm.count);
    }



    @Test
    public void quickFindTestMedium(){
        QuickFind algorithm = new QuickFind(numberOfElementsMedium);
        for(int[] item : inputPairsMedium){
            int p = item[0];
            int q = item[1];

            // if already connected do nothing, go to next pair
            if(algorithm.connected(p,q)) {continue;}

            algorithm.union(p,q);
            //System.out.println("connected " + p + " and " + q);

        }
        //System.out.println("components: " + algorithm.count());
        assertEquals(3,algorithm.count);
    }

    @Test
    public void quickUnionTestTiny() {
        QuickUnion algorithm = new QuickUnion(numberOfElementsTiny);
        for (int[] item : inputPairsTiny) {
            int p = item[0];
            int q = item[1];

            // if already connected do nothing, go to next pair
            if (algorithm.connected(p, q)) {
                continue;
            }

            algorithm.union(p, q);
            //System.out.println("connected " + p + " and " + q);

        }
        //System.out.println("components: " + algorithm.count());
        assertEquals(2, algorithm.count);
    }



    @Test
    public void quickUnionTestMedium(){
        QuickUnion algorithm = new QuickUnion(numberOfElementsMedium);
        for(int[] item : inputPairsMedium){
            int p = item[0];
            int q = item[1];

            // if already connected do nothing, go to next pair
            if(algorithm.connected(p,q)) {continue;}

            algorithm.union(p,q);
            //System.out.println("connected " + p + " and " + q);

        }
        //System.out.println("components: " + algorithm.count());
        assertEquals(3,algorithm.count);
    }

    @Test
    public void weightedQuickUnionTestTiny() {
        WeightedQuickUnion algorithm = new WeightedQuickUnion(numberOfElementsTiny);
        for (int[] item : inputPairsTiny) {
            int p = item[0];
            int q = item[1];

            // if already connected do nothing, go to next pair
            if (algorithm.connected(p, q)) {
                continue;
            }

            algorithm.union(p, q);
            //System.out.println("connected " + p + " and " + q);

        }
        //System.out.println("components: " + algorithm.count());
        assertEquals(2, algorithm.count);
    }



    @Test
    public void weightedQuickUnionTestMedium(){
        WeightedQuickUnion algorithm = new WeightedQuickUnion(numberOfElementsMedium);
        for(int[] item : inputPairsMedium){
            int p = item[0];
            int q = item[1];

            // if already connected do nothing, go to next pair
            if(algorithm.connected(p,q)) {continue;}

            algorithm.union(p,q);
            //System.out.println("connected " + p + " and " + q);

        }
        //System.out.println("components: " + algorithm.count());
        assertEquals(3,algorithm.count);
    }

    @Test
    public void weightedQuickUnionCompressionTestTiny() {
        WeightedQuickUnionCompression algorithm = new WeightedQuickUnionCompression(numberOfElementsTiny);
        for (int[] item : inputPairsTiny) {
            int p = item[0];
            int q = item[1];

            // if already connected do nothing, go to next pair
            if (algorithm.connected(p, q)) {
                continue;
            }

            algorithm.union(p, q);
            //System.out.println("connected " + p + " and " + q);

        }
        //System.out.println("components: " + algorithm.count());
        assertEquals(2, algorithm.count);
    }



    @Test
    public void weightedQuickUnionCompressionTestMedium(){
        WeightedQuickUnionCompression algorithm = new WeightedQuickUnionCompression(numberOfElementsMedium);
        for(int[] item : inputPairsMedium){
            int p = item[0];
            int q = item[1];

            // if already connected do nothing, go to next pair
            if(algorithm.connected(p,q)) {continue;}

            algorithm.union(p,q);
            //System.out.println("connected " + p + " and " + q);

        }
        //System.out.println("components: " + algorithm.count());
        assertEquals(3,algorithm.count);
    }


}
