package sorting.application.eightpuzzle;


public class Node implements Comparable<Node> {

    private Board board;
    private int manhattenDistance;
    private int numberNodesProcessed;
    private int priority;
    private Board parentBoard;

    public Node(Board aBoard, int processed, Board parent){
        board = aBoard;
        manhattenDistance = board.manhattan();
        numberNodesProcessed = processed;
        priority = manhattenDistance + numberNodesProcessed;
        parentBoard = parent;
    }

    @Override
    public int compareTo(Node otherNode){
        return priority - otherNode.priority;
    }

}
