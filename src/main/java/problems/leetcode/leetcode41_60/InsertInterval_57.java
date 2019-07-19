package problems.leetcode.leetcode41_60;

/*Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

        You may assume that the intervals were initially sorted according to their start times.*/

import java.util.ArrayList;
import java.util.List;

public class InsertInterval_57 {
    public static int[][] insert(int[][] intervals, int[] newInterval) {

        if(intervals.length == 0){
            return new int[][]{newInterval};
        }

        List<int[]> list = new ArrayList<>();

        for(int i = 0; i < intervals.length; i++){
            //if newInterval.start > current.end -> no overlap
            if(newInterval[0] > intervals[i][1]){
                list.add(intervals[i]);
            }
            // if newInterval.end < current.start -> no overlap
            else if(newInterval[1] < intervals[i][0]){
                list.add(newInterval);
                newInterval = intervals[i]; // intervals[i] still has to be inserted

            }
            else{
                // overlap -> merge and switch with newInterval
                int[] toInsert = new int[]{Math.min(newInterval[0], intervals[i][0]), Math.max(newInterval[1], intervals[i][1])};
                newInterval = toInsert;
            }

        }

        list.add(newInterval);

        return list.toArray(new int[list.size()][2]);


    }
}
