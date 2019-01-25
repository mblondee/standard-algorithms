package sorting.application.eightpuzzle;


/*
* A solver for an n-by-n puzzle: rearrange the blocks (up-down-left-right) using as few moves as possible.
*
* A* algorithm: find optimal path from start node to goal node using the following heuristic:
* distance from start node + sum of manhattan distances to goal node
*
* to reduce the number of times a board is enqueued on the priority queue,
* when considering the neighbours of a node, it is not enqueued if its board is the same as the board of the predecessor
* search node
*
* to detect unsolvable puzzles:
* boards are divided into two equivalence classes
* 1) those that lead to the goal board
* 2) those that lead to the goal board if we modify the initial board by swapping any pair of blocks
* */

import sorting.priorityqueue.MinPQ;

public class Solver {

    private MinPQ<Node> nextNodes = new MinPQ<>(); // pq to enlist nodes to be visited
    private MinPQ<Node> parallelList = new MinPQ<>(); // parallel pq to check solvability
    private boolean solvable;
    private Node endNode = null; //node containing goal board if solvable


    public Solver(Board initialBoard){

        nextNodes.insert(new Node(initialBoard, 0, null));

        Board parallelBoard = initialBoard.exchangeTwoBlocks();
        parallelList.insert(new Node(parallelBoard, 0, null));

        while(! nextNodes.isEmpty() && !parallelList.isEmpty()){
            // get minimal next node
            Node current = nextNodes.delMin();
            System.out.println("current node ");
            System.out.println(current);
            Node currentParallel = parallelList.delMin();
            System.out.println("current parallel node ");
            System.out.println(currentParallel);

            // current board is goal board
            // puzzle is solvable
            if (current.getBoard().isGoal()){
                solvable = true;
                endNode = current;
                System.out.println("solvable! printing path: ");
                printNodes(current);
                break;
            }

            // current parallel board is goal board
            // puzzle is not solvable
            if (currentParallel.getBoard().isGoal()){
                solvable = false;
                System.out.println("not solvable: printing path of parallel");
                printNodes(currentParallel);
                break;
            }

            // check neighbouring boards of current
            for (Board child : current.getBoard().neighbours()){
               if(current.getParentNode() == null || !child.equals(current.getParentBoard())){
                    Node childNode = new Node(child, current.getNumberNodesProcessed() + 1, current);
                    nextNodes.insert(childNode);
                }

            }

            System.out.println("in pq: ");
            for(Node node : nextNodes){
                System.out.println(node);
                System.out.println("processed " + node.getNumberNodesProcessed());
                System.out.println("priority " + node.getPriority());
            }
            System.out.println("------");


            // check neighbouring boards of currentParallel
            for (Board child : currentParallel.getBoard().neighbours()){
                if(currentParallel.getParentNode() == null || !child.equals(currentParallel.getParentBoard())){
                    Node childNode = new Node(child, currentParallel.getNumberNodesProcessed() + 1, currentParallel);
                    parallelList.insert(childNode);
                }

            }

        }

    }

    public boolean isSolvable(){
        return solvable;
    }

    public void printPath(){
        if (solvable){
            printNodes(endNode);
        }
    }

    private void printNodes(Node endNode){
        while(endNode.getParentNode() != null){
            System.out.println(endNode);
            endNode = endNode.getParentNode();
        }
        System.out.println(endNode);
    }
}
