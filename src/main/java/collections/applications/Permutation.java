package collections.applications;

import collections.queue.RandomizedQueue;

/*
*given an input array with length n and a number k
* get k elements from array in random order
* */

public class Permutation {

    public static void main(String[] args){
        String[] input = {"a", "b", "c", "d", "e", "f", "g", "h", "i"};
        run(3, input);
        System.out.println("\n");
        run(3, input);
        System.out.println("a permutation:");
        run(input.length, input);
    }

    private static void run(int k, String[] input){
        if(k> input.length){throw new IllegalArgumentException(k + " is too large");};
        RandomizedQueue<String> queue = new RandomizedQueue<String>();

        for(String s: input){
            queue.enqueue(s);
        }

        for(int i = 0; i<k; i++){
            String removing = queue.dequeue();
            System.out.println(removing);
        }
    }
}
