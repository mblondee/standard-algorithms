package problems.leetcode.leetcode41_60;

/*Given a collection of intervals, merge all overlapping intervals.*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals_56 {


    public static int[][] merge(int[][] intervals) {
        if(intervals.length == 0){
            return intervals;
        }
        //Arrays.sort(intervals, (int[] interval1, int[] interval2) -> interval1[0] - interval2[0]);
        Arrays.sort(intervals, Comparator.comparing(o -> o[0]));

        int[] current = intervals[0];
        List<int[]> list = new ArrayList<>();

        for(int i = 1; i < intervals.length; i++){
            // if current and next (intervals[i]) do not overlap, i.e. current.end < next.start
            // no need to merge
            if(current[1] < intervals[i][0]){
                list.add(current);
                current = intervals[i];
            }
            else{
                // we merge the intervals
                int[] newInterval = {Math.min(current[0], intervals[i][0]), Math.max(current[1], intervals[i][1])};
                current = newInterval;
            }
        }
        list.add(current);


        return list.toArray(new int[list.size()][2]);

    }
}
