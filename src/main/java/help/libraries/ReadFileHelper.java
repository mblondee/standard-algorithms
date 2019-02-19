package help.libraries;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class ReadFileHelper {


    // reads file of integers (one integer per line)
    //returns ArrayList of integers
    public static int[] readFileInts(String fileName){
        System.out.println("Reading file");
        String line;
        ArrayList<Integer> listOfLines = new ArrayList<Integer>();
        try {
            FileReader inputFile = new FileReader(fileName);
            BufferedReader bufferReader = new BufferedReader(inputFile);
            line = bufferReader.readLine();
            while ( (line = bufferReader.readLine()) != null){
                String lineString = line.trim();
                int p = Integer.parseInt(lineString);
                listOfLines.add(p);
            }
            bufferReader.close();


        }

        catch(Exception e){
            System.out.println("Error while reading file " + e.getMessage());
        }

        return convertListToArray(listOfLines);

    }

    public static int[] convertListToArray(ArrayList<Integer> listOfIntegers){
        int[] arrayOfInt = new int[listOfIntegers.size()];
        int i = 0;
        for(int item : listOfIntegers){
            //System.out.println(item);
            arrayOfInt[i++] = item;
        }
        return arrayOfInt;
    }


}
