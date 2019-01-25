package sorting.application.eightpuzzle;


public class Node implements Comparable<Node> {

    private Board board; // current board
    private int manhattanDistance; //sum of manhattan distances to goal board
    private int numberNodesProcessed; // number of changes made up to current board
    private int priority; // heuristic
    private Node parentNode; // previous node

    public Node(Board aBoard, int processed, Node parent){
        board = aBoard;
        manhattanDistance = board.manhattan();
        numberNodesProcessed = processed;
        priority = manhattanDistance + numberNodesProcessed;
        parentNode = parent;
    }

    public Board getBoard(){
        return board;
    }

    public Board getParentBoard(){
        return parentNode.getBoard();
    }

    public Node getParentNode(){
        return parentNode;
    }

    public int getNumberNodesProcessed(){
        return numberNodesProcessed;
    }

    public int getPriority(){
        return priority;
    }

    @Override
    public int compareTo(Node otherNode){
        return priority - otherNode.priority;
    }

    @Override
    public String toString(){
        return board.toString();
    }


/*    @Override
    public boolean equals(Object other){
        if (other == null){return false;}
        if(other.getClass() != this.getClass()){return false;}

        return this.getBoard().equals(((Node) other).getBoard());
    }*/

}
