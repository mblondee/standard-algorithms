package sorting.sort;

public class Sort {

    public static boolean isStrictLess(Comparable element1, Comparable element2){
        return element1.compareTo(element2) < 0;
    }

    public static boolean isStrictLarger(Comparable element1, Comparable element2){
        return element1.compareTo(element2) > 0;
    }

    public static void swap(Comparable[] array, int indexToSwap, int index){
        Comparable toSwap = array[indexToSwap];
        array[indexToSwap] = array[index];
        array[index] = toSwap;
    }
}
