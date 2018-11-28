package sorting.sort;

/*
* implementation of selection sort
* O(n^2) compares and n swaps
*
* is not stable
* */





import java.util.Comparator;

public class Selection {

    //sorting an array in ascending order
    public static void sort(Comparable[] array) {
        int length = array.length;
        for (int i = 0; i < length; i++) {
            int min_index = i;
            for (int j = i + 1; j < length; j++) {
                if (Sort.isStrictLarger(array[min_index], array[j])) {
                    min_index = j;
                }
            }

            Sort.swap(array, min_index, i);
        }
    }

    //sorting an array in ascending order using a comparator
    public static <T> void sort(T[] array, Comparator<T> comparator) {
        int length = array.length;
        for (int i = 0; i < length; i++) {
            int min_index = i;
            for (int j = i + 1; j < length; j++) {
                if (Sort.isStrictLarger(array[min_index], array[j], comparator)) {
                    min_index = j;
                }
            }

            Sort.swap(array, min_index, i);
        }
    }
}
