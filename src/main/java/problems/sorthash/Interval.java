package problems.sorthash;

import java.util.Comparator;

public class Interval {

    private int start;
    private int end;


    public static Comparator<Interval> compareByStart = Comparator.comparing(Interval :: getStart);

/*    public static Comparator<Interval> compareByStart = ((Interval left, Interval right) ->
            left.getStart() - right.getStart());*/

/*    public static Comparator compareByStart = new Comparator<Interval>() {

        @Override
        public int compare(Interval left, Interval right){
            return right.getEnd() - left.getStart();
        }

    };*/

    public Interval(int start, int end){
        if(end < start){
            throw new IllegalArgumentException("end smaller than start");
        }
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    /*
    * merge with a second interval where
    * start <= otherInterval.start
    * end > otherInterval.start
    * */
    public Interval mergeSortedWithOverlap(Interval otherInterval){
        if(otherInterval.getEnd() > end) {
            return new Interval(start, otherInterval.getEnd());
        }
        return this;
    }
}
