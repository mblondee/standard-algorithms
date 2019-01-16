package sorting.sort;

/*
* implementation of heap sort
* at most N log N compares
* in place sorting
* not stable
* */

import java.util.Comparator;

public class HeapSort {

    /*
    * items in array are in positions 1 ... array.length
    * and in indices 0 ... array.length -1
    * using natural order
    * */
    public static void sort(Comparable[] array){
        int lastPosition = array.length; // position of last node
        // sink all nodes that are not leafs (leafs cannot sink)
        // rearranges array such that it satisfies the heap condition
        for(int positionSinking = lastPosition/2; positionSinking >= 1; positionSinking --){
            sink(array, positionSinking, lastPosition);
        }

        //
        while(lastPosition > 1){
            // move item in position 1 to lastPosition (this is the max element and is now in place)
            // however the item that moved from lastPosition to the first condition might nog satisfy the heap condition and
            // should sink (up until the corresponding index of lastPosition)
            Sort.swap(array, getIndex(1), getIndex(lastPosition));
            lastPosition --;
            sink(array, 1, lastPosition);
        }
    }

    /*
     * items in array are in positions 1 ... array.length
     * and in indices 0 ... array.length -1
     * using a comparator
     * */
    public static <T> void sort(T[] array, Comparator<T> comparator){
        int lastPosition = array.length; // position of last node
        // sink all nodes that are not leafs (leafs cannot sink)
        // rearranges array such that it satisfies the heap condition
        for(int positionSinking = lastPosition/2; positionSinking >= 1; positionSinking --){
            sink(array, positionSinking, lastPosition, comparator);
        }

        //
        while(lastPosition > 1){
            // move item in position 1 to lastPosition (this is the max element and is now in place)
            // however the item that moved from lastPosition to the first condition might nog satisfy the heap condition and
            // should sink (up until the corresponding index of lastPosition)
            Sort.swap(array, getIndex(1), getIndex(lastPosition));
            lastPosition --;
            sink(array, 1, lastPosition, comparator);
        }
    }


    /*
    * helper function
    * item in position has to be sinked in the heap (not further than index finalPosition)
    * using natural order
    * */

    private static void sink(Comparable[] array, int positionToSink, int finalPosition){
        // if positionToSink still has children
        while(2*positionToSink <= finalPosition){
            int maxChild = 2*positionToSink;

            // check if there is a second child
            // check which child is max item
            if(maxChild < finalPosition && Sort.isStrictLess(array[getIndex(maxChild)], array[getIndex(maxChild + 1)])){
                maxChild ++;
            }
            // check if parent (positionToSink) is smaller than maxChild, if so replace and repeat process, else break
            if(Sort.isStrictLess(array[getIndex(positionToSink)], array[getIndex(maxChild)])){
                Sort.swap(array, getIndex(positionToSink), getIndex(maxChild));
                positionToSink = maxChild;
            }
            else{
                break;
            }
        }
    }

    /*
     * helper function
     * item in position has to be sinked in the heap (not further than index finalPosition)
     * using a comparator
     * */

    private static <T> void sink(T[] array, int positionToSink, int finalPosition, Comparator<T> comparator){
        // if positionToSink still has children
        while(2*positionToSink <= finalPosition){
            int maxChild = 2*positionToSink;

            // check if there is a second child
            // check which child is max item
            if(maxChild < finalPosition
                    && Sort.isStrictLess(array[getIndex(maxChild)], array[getIndex(maxChild + 1)], comparator)){
                maxChild ++;
            }
            // check if parent (positionToSink) is smaller than maxChild, if so replace and repeat process, else break
            if(Sort.isStrictLess(array[getIndex(positionToSink)], array[getIndex(maxChild)], comparator)){
                Sort.swap(array, getIndex(positionToSink), getIndex(maxChild));
                positionToSink = maxChild;
            }
            else{
                break;
            }
        }
    }

    // in a heap we use positions 1 .. array.length but their respective indices are one less
    private static int getIndex(int position) {
        return position - 1;
    }

}
