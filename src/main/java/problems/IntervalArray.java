package problems;

/*
* Given a sorted set of non-overlapping intervals and a new interval, insert the interval at the correct position.
* If this results in overlapping intervals, then merge the overlapping intervals.
*
* solution 1: iterating over interval list and adding intervals to new empty list: O(n) if n is number of intervals
* (add of arraylist is constant time)
*
* */

import java.util.ArrayList;
import java.util.List;

public class IntervalArray {

    private static class Interval{
        private int start;
        private int end;

        public Interval(int s, int e){
            start = s;
            end = e;
        }



        @Override
        public String toString(){
            return "start: " + start + " end: " + end;
        }
    }


    public static List<Interval> addInterval(List<Interval> lst, Interval intervalToInsert){
        List<Interval> result = new ArrayList<>();

        //loop through lst: which intervals do we need to keep?
        //or how should we update the interval to be inserted?
        for(Interval interval : lst){
            // if intervalToInsert.start > interval.end then the interval has to be kept and inserted as is
            if(intervalToInsert.start > interval.end){
                result.add(interval);
            }
            // else we have intervalToInsert.start <= interval.end
            // is intervalToInsert.end inside of interval?, i.e. is there an overlap with interval?
            // two options: intervalToInsert.end < interval.start or intervalToInsert.end >= interval.start
            else if(intervalToInsert.end < interval.start) {
                // intervalToInsert comes before interval: no overlap
                result.add(intervalToInsert);
                // we still need to insert interval
                intervalToInsert = interval;
            }
            // else we have intervalToInsert.start <= interval.end and intervalToInsert.end >= interval.start
            // there is an overlap: a new interval has to be inserted
            else{
                intervalToInsert = new Interval(
                        Math.min(interval.start, intervalToInsert.start),
                        Math.max(interval.end, intervalToInsert.end)
                );
            }
        }

        result.add(intervalToInsert);

        return result;
    }

    public static void main(String[] args){
        Interval int1 = new Interval(2,5);
        Interval int2 = new Interval(10,15);
        Interval int3 = new Interval(20,25);
        List<Interval> lst = new ArrayList<>();
        lst.add(int1);
        lst.add(int2);
        lst.add(int3);

        Interval interval1 = new Interval(12,27);
        Interval interval2 = new Interval(0,1);
        Interval interval3 = new Interval(26,30);
        Interval interval4 = new Interval(1,30);

        for(Interval interval: addInterval(lst, interval4)){
            System.out.println(interval);
        }


    }

}
