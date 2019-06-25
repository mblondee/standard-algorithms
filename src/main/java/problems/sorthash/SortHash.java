package problems.sorthash;

/*
* exercises using sorting or hashing
*
* 1) which value appears most frequently in an array of int
* 2) given a number k, determine whether the array contains two numbers whose sum is k
* 3) given a list of closed intervals, find the smallest nonnegative integer that does not
* belong to any of the intervals
* 4) given an array of n integers, find the largest range [a,b] where every integer in the range appears
* at least once in the array
* 5) given a list of sets each consisting of 2 element, determine whether there are any two disjoints sets
* in the list
* 6) given an array of n distinct values, the rank of a value is the number of values in this array
* that are smaller than it, determine the rank of each element in the array
* 7) given n intervals, find the maximum number of overlapping intervals, i.e. a set of interval that all
* overlap with each other
* 8) given a list of positive integers, determine the smallest positive integer that cannot be written
* as a sum of some of those integers
* 9) given a list of n positive integers, determine for each number whether it is a Fibonacci number
*
* */

import java.util.*;

public class SortHash {

    /*
    * solution of 1): O(n) time complexity, O(n) space complexity
    * sorting + traversing array would be O(n log n) time complexity and O(1) space complexity
    * */
    public static Integer mostFrequent(int[] array){
        HashMap<Integer, Integer> counts = new HashMap<>();
        for(int i : array){
            if(counts.containsKey(i)) {
                counts.put(i, counts.get(i) + 1);
            }
            else{
                counts.put(i, 1);
            }
        }

        int freq = 0;
        Integer mostFreq = null;

        for(int i : array){
            if(counts.get(i) > freq){
                freq = counts.get(i);
                mostFreq = array[i];
            }
        }
        return mostFreq;
    }


    /*
     * solution of 2): O(n) time complexity, O(n) space complexity
     * */
    public static boolean sumTo(int k, int[] array){
        HashSet<Integer> set = new HashSet<>();

        for(int i = 0; i < array.length; i++){
            if(set.contains(k-array[i])){
                return true;
            }
            set.add(array[i]);
        }

        return false;

    }

    /*
     * solution of 3) O(n log n)
     * sort is O(n log n)
     * */
    public static int smallestNotBelonging(List<Interval> lstIntervals){
        //sort list by start points
        Collections.sort(lstIntervals, Interval.compareByStart);
        if(lstIntervals.get(0).getStart() > 0){
            return 0;
        }
        // first interval starts with 0
        Interval currentInterval = lstIntervals.get(0);
        int size = lstIntervals.size();
        for(int i = 1; i < size; i++){
            if(currentInterval.getEnd() < lstIntervals.get(i).getStart()){
                // there is no overlap with next interval
                return currentInterval.getEnd() + 1;
            }
            //there is overlap : merge current with next
            currentInterval = currentInterval.mergeSortedWithOverlap(lstIntervals.get(i));
        }
        return currentInterval.getEnd() + 1;
    }


    /*
     * solution of 4) O(n)
     * */
    public static Interval getLargestInterval(int[] input){
        Set<Integer> values = new HashSet<>();
        for(int i : input){
            values.add(i);
        }

        int bestLength = 0;
        int bestStart = input[0];

        for(int element : input){
            if(values.isEmpty()){
                break;
            }
            if(! values.contains(element)){
                //element has already been checked
                continue;
            }
            values.remove(element);
            //look for largest interval containing element
            int start = element;
            int end = element;

            while(values.contains(start-1)){
                start = start -1;
                values.remove(start);
            }

            while(values.contains(end + 1)){
                end = end + 1;
                values.remove(end);
            }

            // is start-end largest than current largest interval?
            if(end - start + 1 > bestLength){
                bestLength = end - start +1 ;
                bestStart = start;
            }
        }
        return new Interval(bestStart, bestStart + bestLength-1);

    }


    /*
     * solution of 5)
     *
     * suppose input is correct: all sets contain 2 different integers
     * */
    public static boolean disjoint(List<Set<Integer>> listOfSets){
        if(listOfSets.isEmpty()){
            return false;
        }
        // randomSet {a,b} will be used to divide the other sets in 2 groups
        // sets that contain a and not b (group A)
        // sets that contain b and not a (group B)
        // (sets that contain a and b, hence are equal to randomSet can be discarded)
        Set<Integer> randomSet = listOfSets.get(0);

        Iterator<Integer> iter = randomSet.iterator();
        int a = iter.next();
        int b = iter.next();

        // set containing the second element from group A
        Set<Integer> otherElementGroupA = new HashSet<>();
        // set containing the second element from group B
        Set<Integer> otherElementGroupB = new HashSet<>();

        boolean containsA;
        boolean containsB;

        for(int i = 1; i < listOfSets.size(); i++){
            Set<Integer> currentSet = listOfSets.get(i);
            containsA = currentSet.contains(a);
            containsB = currentSet.contains(b);
            if(! containsA && ! containsB){
                //currentSet is disjoint with randomSet
                return true;
            }
            else if(containsA && containsB){
                //ignore
                continue;
            }
            else if(containsA){
                Iterator<Integer> iterCurrent = currentSet.iterator();
                int first = iterCurrent.next();
                int second = iterCurrent.next();
                int elToAdd;
                if(first == a){
                    elToAdd = second;
                }
                else{
                    elToAdd = first;
                }
                otherElementGroupA.add(elToAdd);
            }
            else{
                //containsB == true
                Iterator<Integer> iterCurrent = currentSet.iterator();
                int first = iterCurrent.next();
                int second = iterCurrent.next();
                int elToAdd;
                if(first == b){
                    elToAdd = second;
                }
                else{
                    elToAdd = first;
                }
                otherElementGroupB.add(elToAdd);
            }
        }

        if(otherElementGroupA.isEmpty() || otherElementGroupB.isEmpty()){
            // otherElementGroupA.isEmpty() => all pairs contain b
            // otherElementGroupB.isEmpty() => all pairs contain a
            // in both cases there cannot be a pair of disjoint sets
            return false;
        }

        //if there exists an element x in otherElementGroupA that is not in otherElementGroupB
        // then {a,x} is disjoint with all {b,y}

        for(int i : otherElementGroupA){
            if(!otherElementGroupB.contains(i)){
                return true;
            }
        }

        // if no such element exists in otherElementGroupA, there is are no disjoints sets
        return false;
    }


    /*
     * solution of 6) O(n*d) with d maximum number of digits in input array
     *
     * make copy of input array is O(n)
     * radix sort is O(n*d) with d maximum number of digits in input array
     * create hashmap is O(n)
     * */
    public static HashMap<Integer, Integer> rank(int[] input){
        int[] sortedArray = new int[input.length];
        for(int i = 0; i < input.length; i++){
            sortedArray[i] = input[i];
        }

        //use radix sort to sort the array

        // array of 10 linked lists -> one for each digit 0-9
        // we choose linked list for buckets so original order does not change
        LinkedList<Integer>[] buckets = new LinkedList[10];
        //initialize
        for(int i = 0 ; i<10; i++){
            buckets[i] = new LinkedList<>();
        }

        //control variable: are there any digits left for the current whichDigit (i.e. >0)
        boolean sorted = false;
        int whichDigit = 1; // start with rightmost digit

        while(! sorted){

            // we assume there are no digits left, if we find one >0 we put sorted to false
            sorted = true;

            for(int number : sortedArray){
                // get correct digit
                int digit = number/whichDigit % 10;
                if(digit > 0){
                    // if we find digit > 0 we put sorted to false
                    sorted = false;
                }
                // put number in correct bucket
                buckets[digit].add(number);
            }

            if(sorted){
                break;
            }

            //update array
            int index = 0;
            for(LinkedList<Integer> bucket : buckets){
                while(! bucket.isEmpty()){
                    sortedArray[index++] = bucket.remove();
                }
            }

            whichDigit *= 10;

        }
        HashMap<Integer, Integer> ranks = new HashMap<>();

        for(int i = 0; i < input.length; i++){
            ranks.put(sortedArray[i], i);
        }

        return ranks;
    }


    /*
     * solution of 7) O(n log n)
     * could be O(n*d) when using radix sort
     *
     * declaring arrays is O(n)
     * sort is O(n log n)
     * traversing arrays is O(n)
     * */

    public static int maxOverlap(List<Interval> listOfIntervals){
        int numberOfIntervals = listOfIntervals.size();
        // sort array containing starts points
        // sort array containing end points
        int[] startpoints = new int[numberOfIntervals];
        int[] endpoints = new int[numberOfIntervals];

        int index = 0;
        for(Interval interval : listOfIntervals){
            startpoints[index] = interval.getStart();
            endpoints[index] = interval.getEnd();
            index++;
        }

        Arrays.sort(startpoints);
        Arrays.sort(endpoints);

        int currentNumberOfIntervals = 0;
        int maxNumberOfIntervals = 0;

        // we go through the startpoints and endpoints and find first point considering both arrays
        // if it is a startpoint currentNumberOfIntervals increases with one
        // if it is a endpoint currentNumberOfIntervals decreases with one
        // if it is both a start and endpoint then currentNumberOfIntervals remains the same
        // we do this until there are no more startpoints to be considered

        int s = 0;
        int e = 0;

        while(s < numberOfIntervals){
            if(startpoints[s] < endpoints[e]){
                // next is startingpoint
                currentNumberOfIntervals++;
                if(maxNumberOfIntervals < currentNumberOfIntervals){
                    maxNumberOfIntervals = currentNumberOfIntervals;
                }
                s++;
            }
            else if(endpoints[e] < startpoints[s]){
                // next is endpoint
                currentNumberOfIntervals--;
                e++;
            }
            else{
                s++;
                e++;
            }
        }





        return maxNumberOfIntervals;
    }


    /*
     * solution of 8) O(n log n)
     *
     * sorting is O(n log n)
     * traversing array is O(n)
     * */

    public static int smallestNoSum(int[] input){
        // sort array
        Arrays.sort(input);

        // candidate is smallest that cannot be a sum of the first k-1 elements
        int candidate = 1;

        for(int k = 0; k <input.length; k++) {
            // compare candidate with input[k]
            if (candidate < input[k]) {
                // all elements input[i] with i >= k are strictly greater than candidate
                // hence it will not be possible to find elements in the array that sum to candidate
                return candidate;
            }
            else{
                // take 0 < i < input[k], then i - input[k] < 0
                // suppose candidate + i is not a sum of the first k, candidate != sum(k)
                // then candidate + i - input[k] != sum(k-1)
                // but candidate + i - input[k] < candidate != sum(k-1) and candidate
                // is the smallest, a contradiction => candidate + i = sum(k)
                // also, candidate + input[k] != sum(k), otherwise candidate = sum(k-1)
                // hence the smallest number that is not a sum is candidate + input[k]
                candidate = candidate + input[k];
            }

        }
        return candidate;
    }


    /*
     * solution of 9) O(n log n + n + max (input))
     *
     * copy is O(n)
     * sort is O(n log n)
     *
     * traverse array is O(n + max (input))
     * since computing next fibonacci is O(max (input))
     *
     * put result in array is O(n)
     * */
    public static boolean[] isFibonacci(int[] input){
        HashMap<Integer, Boolean> result = new HashMap<>();
        boolean[] resultArray = new boolean[input.length];

        int[] copy = new int[input.length];
        for(int i = 0; i < input.length; i++){
            copy[i] = input[i];
        }
        Arrays.sort(copy);

        int first = 0;
        int second = 1;

        outerloop:
        for(int i = 0; i < copy.length; i++){
            if(second == copy[i]){
                result.put(copy[i], true);
                continue;
            }
            while(second <= copy[i]){
                if(second == copy[i]){
                    result.put(copy[i], true);
                    continue outerloop;
                }
                int aux = second + first;
                first = second;
                second = aux;
            }
            result.put(copy[i], false);
        }

        for(int i = 0; i <resultArray.length; i++){
            resultArray[i] = result.get(input[i]);
        }

        return resultArray;
    }
}
