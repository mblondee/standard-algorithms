package misc;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import help.libraries.StdIn;
import help.libraries.StdOut;

public class BinarySearch {

    //return index of key in a if exists
    // else -1
    public static int rank(int key, int[] a) {
        // Array must be sorted
        int low = 0;
        int high = a.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (key < a[mid]) high = mid - 1;
            else if (key > a[mid]) low = mid + 1;
            else return mid;
        }
        return -1;
    }

    public static void main(String[] args){
        ArrayList<Integer> inputNumbers = new ArrayList<Integer>();
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter a sequence of numbers and 'done' if finished: ");

        while (in.hasNextInt()){
            int input = in.nextInt();
            inputNumbers.add(input);
        }

        System.out.println(inputNumbers);

        //int[] inputNumbersArray = inputNumbers.stream().mapToInt(Integer::intValue).toArray();

        //Arrays.sort(inputNumbersArray);

        //while(!StdIn.isEmpty()){
            //int key = StdIn.readInt();
            //if (rank(key, inputNumbersArray) < 0) StdOut.println("not in list");
        //}







    }

}
