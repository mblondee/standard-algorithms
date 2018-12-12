package sorting.sort;

/*
 * implementation of quicksort
 * using median-of-three partitioning
 * not stable
 * */

public class QuickSortMedianOfThree {

    static final int CUTOFF = 3;

    //compare only three elements: low, mid and high and if necessary swap such that
    // array[low] is smallest
    // array[high] is largest
    // array[mid] is in between
    // then pick array[mid] as the pivot element and put at position high-1
    private static Comparable medianOfThree(Comparable[] array, int low, int high){
        int mid = low + (high-low)/2;

        if(Sort.isStrictLess(array[mid],array[low])){
            Sort.swap(array, mid, low);
        }

        if(Sort.isStrictLess(array[high], array[low])){
            Sort.swap(array, low, high);
        }

        if(Sort.isStrictLess(array[high], array[mid])){
            Sort.swap(array, high, mid);
        }

        for(Comparable el:array){
            System.out.println(el);}

        //put pivot next to rightmost position
        Sort.swap(array, mid, high-1);// array[high-1] <= array[high]
        System.out.println("pivot " + array[high-1]);
        for(Comparable el:array){
        System.out.println(el);}
        return array[high-1];

    }

    private static void quicksort(Comparable[] array, int low, int high){
        System.out.println("quick low: " + low + " high "+ high);

        if(high <= low){}

        // check whether subarray has at least CUTOFF elements
        if(high-low + 1 < CUTOFF){
            insertionSort(array, low, high);
        }

        else {
            Comparable pivot = medianOfThree(array, low, high);


            //do the partitioning
            int left = low-1; // first to check is low
            int right = high+1; // first to check is high

            //increment left pointer
            while (true) {
                System.out.println("left " + left + " right " + right);
                while (Sort.isStrictLess(array[++left], pivot)) {
                    // no stop condition necessary: Sort.isStrictLess(array[high], pivot) will never evaluate to true
                }
                while (Sort.isStrictLess(pivot, array[--right])) {
                    // no stop condition necessary: Sort.isStrictLess(pivot, array[low]) will never evaluate to true
                }
                if(right <= left){break;}
                else {
                    // swap elements because they violate the property we want
                    Sort.swap(array, left, right);
                }



            }
            //pivot is now in right and left is right from pivot
            System.out.println(":after");
            for(Comparable el:array){
                System.out.println(el);}

            //quicksort on left and right subarray
            quicksort(array, low, left - 1);
            quicksort(array, left, high);
        }
    }

    public static void sort(Comparable[] array){
        quicksort(array, 0, array.length-1);
    }

    private static void insertionSort(Comparable[] array, int low, int high){
        for(int i = low+1; i<=high; i++){
            for(int j = i; j>low; j--){
                if (Sort.isStrictLess(array[j], array[j-1])){
                    Sort.swap(array, j, j-1);
                }
                else{break;}
            }


        }

    }

}
