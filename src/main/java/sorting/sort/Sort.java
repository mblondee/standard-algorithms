package sorting.sort;

import java.util.Comparator;

public class Sort {


    public static boolean isStrictLess(Comparable element1, Comparable element2){
        return element1.compareTo(element2) < 0;
    }

    public static <T> boolean isStrictLess(T element1, T element2, Comparator<T> comparator){
        return comparator.compare(element1, element2) < 0;
    }

    public static boolean isStrictLarger(Comparable element1, Comparable element2){
        return element1.compareTo(element2) > 0;
    }

    public static <T> boolean isStrictLarger(T element1, T element2, Comparator<T> comparator){
        return comparator.compare(element1, element2) > 0;
    }

    public static <T> void swap(T[] array, int indexToSwap, int index){
        T toSwap = array[indexToSwap];
        array[indexToSwap] = array[index];
        array[index] = toSwap;
    }

    public static boolean isSorted(Comparable[] array, int startIndex, int endIndex){
        for (int i = startIndex; i < endIndex; i++){
            if(isStrictLarger(array[i], array[i+1])){
                return false;
            }
        }
        return true;
    }



    public static <T> boolean isSorted(T[] array, int startIndex, int endIndex, Comparator<T> comparator){
        for (int i = startIndex; i < endIndex; i++){
            if (isStrictLarger(array[i], array[i+1], comparator)){
                return false;
            }
        }
        return true;
    }
}
