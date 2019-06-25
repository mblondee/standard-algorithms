package problems;

/*
 * implement a queue using two stacks
 *
 * solution 1:
 * element first in is at top stack1:
 * - dequeue is O(1)
 * - enqueue is O(n) (move all to stack2 and move all back to stack1)
 *
 * solution 2: (better, implemented here)
 * have element first in at top stack2
 * - enqueue is O(1)
 * - dequeue is O(n) (move only is stack2 is empty, otherwise element to dequeue is at top of stack2)
 * */

import java.util.NoSuchElementException;
import java.util.Stack;

public class QueueStack<Item> {

    private Stack<Item> stack1;
    private Stack<Item> stack2;

    public QueueStack() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void enqueue(Item itemToAdd) {
        stack1.push(itemToAdd);
    }

    public Item dequeue() {
        if (empty()) {
            throw new NoSuchElementException();
        }
        if (stack2.empty()) {
            while (!stack1.empty()) {
                stack2.push(stack1.pop());
            }
        }

        return stack2.pop();
    }

    public boolean empty() {
        return stack1.empty() && stack2.empty();
    }

    public static void main(String[] args) {
        QueueStack<String> queue = new QueueStack<>();
        //queue.dequeue();
        queue.enqueue("a");
        System.out.println(queue.dequeue());
        System.out.println(queue.empty());
        queue.enqueue("b");
        queue.enqueue("c");
        queue.enqueue("d");
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        queue.enqueue("a");
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
    }

}
