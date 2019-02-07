package search.applications;

/*
 * data type representing a set of points in the unit square
 * using 2d-trees
 * assumption: all x and y coordinates are distinct
 * */

import help.libraries.StdDraw;
import sorting.sort.Sort;

import java.awt.*;

public class PointSet2dTree {

    // static field representing direction of partitioning in a level
    private static final boolean VERTICAL = true;

    // bounds on coordinates of points in set
    private static final double XLOW = 0.0;
    private static final double YLOW = 0.0;
    private static final double XHIGH = 1.0;
    private static final double YHIGH = 1.0;

    private Node root; // root of the tree
    private int size; //size of the tree


    private class Node {
        private Point2D point; // keys are points
        private Rectangle2D rect; // rectangle corresponding to point
        private Node leftNode; //left subtree
        private Node rightNode; //right subtree

        public Node(Point2D point, Rectangle2D rect, Node leftNode, Node rightNode){
            this.point = point;
            this.rect = rect;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
        }

        @Override
        public String toString(){
            return "point: " + point + " rect " + rect + " left " + Boolean.toString(leftNode != null)
                    + " right " + Boolean.toString(rightNode != null);
        }

    }

    private static boolean isVertical(boolean direction){
        return direction == VERTICAL;
    }

    public PointSet2dTree(){
        root = null;
        size = 0;
    }

    /*
     * is the set empty?
     * */
    public boolean isEmpty(){
        return size == 0;
    }

    /*
     * return size
     * */
    public int size(){
        return size;
    }

    /*
     * add {@code point} to the set (if not already in the set)
     * log(n) time
     * */
    public void insert(Point2D point){
        if(point == null){throw new IllegalArgumentException("you cannot insert null");}

        // in which rectangle should we insert point?
        Rectangle2D rectangle;
        // if tree is empty a Rectangle2D object has to be created that represents entire area
        if(root == null){
            rectangle = new Rectangle2D(XLOW, YLOW, XHIGH, YHIGH);
        }
        else{
            rectangle = root.rect;
        }


        // insert point in tree starting with root
        root = insert(root, point, rectangle, VERTICAL);
    }

    /*
    * add {@code point} in subtree with root {@code node} represented by {@code rectangle}
    * with direction of partitioning {@code direction}
    * */
    private Node insert(Node node, Point2D point, Rectangle2D rectangle, boolean direction){
        // end of path: a new node has to be inserted
        System.out.println("node in tree " + node);
        if(node == null){
            Node nodeToInsert = new Node(point, rectangle, null, null);
            size++;
            System.out.println("inserted");
            System.out.println(nodeToInsert);
            return nodeToInsert;
        }
        // if point is key of current node: do nothing, return node
        // not necessary because of assumption all coordinates different
        if(node.point.equals(point)){
            return node;
        }
        // to know in which subtree point should be added we need to know the direction

        // direction is vertical
        // we have to check whether point is left or right of the partitioning line (x coordinate of point in node)
        // compare by x coordinate
        if(isVertical(direction)){
            // point is on the left of the partitioning line (node.point)
            if(Sort.isStrictLess(point, node.point, Point2D.x_order)){
                // left subtree is not empty
                if(node.leftNode != null){
                    // follow subtree
                    node.leftNode = insert(node.leftNode, point, node.leftNode.rect, !direction);
                }
                // left subtree is empty
                else{
                    // a new rectangle has to be made
                    // coordinates of rectangle but xUpperRight changes to the partitioning x
                    Rectangle2D newRect = new Rectangle2D(rectangle.getxLowerLeft(), rectangle.getyLowerLeft(),
                            node.point.getX(), rectangle.getyUpperRight());
                    node.leftNode = insert(node.leftNode, point, newRect,!direction);
                }
            }
            // point is on the right (assumption: all coordinates are different)
            else {
                //right subtree is not empty
                if(node.rightNode != null){
                    // follow subtree
                    node.rightNode = insert(node.rightNode, point, node.rightNode.rect, !direction);
                }
                //right subtree is empty
                else{
                    // a new rectangle has to be made
                    //coordinates of rectangle but xLowerLeft changes to the partitioning x
                    Rectangle2D newRect = new Rectangle2D(node.point.getX(), rectangle.getyLowerLeft(),
                            rectangle.getxUpperRight(), rectangle.getxUpperRight());
                    node.rightNode = insert(node.rightNode, point, newRect, !direction);
                }

            }
        }


        // direction is horizontal
        // we have to check whether point is below or above of the partitioning line (y coordinate of point in node)
        // compare by y coordinate
        else{
            //point is below
            if(Sort.isStrictLess(point, node.point, Point2D.y_order)){
                // left subtree is not empty
                if(node.leftNode != null){
                    // follow subtree
                    node.leftNode = insert(node.leftNode, point, node.leftNode.rect, !direction);
                }
                //left subtree is empty
                else{
                    // a new rectangle has to be made
                    // coordinates of rectangle but yUpperRight changes to the partitioning y
                    Rectangle2D newRect = new Rectangle2D(rectangle.getxLowerLeft(), rectangle.getyLowerLeft(),
                            rectangle.getxUpperRight(), node.point.getY());
                    node.leftNode = insert(node.leftNode, point, newRect,!direction);
                }
            }
            //point is above
            else{
                //right subtree is not empty
                if(node.rightNode != null){
                    //follow subtree
                    node.rightNode = insert(node.rightNode, point, node.rightNode.rect, !direction);
                }
                //right subtree is empty
                else{
                    // a new rectangle has to be made
                    // coordinates of rectangle but yLowerLeft changes to the partitioning y
                    Rectangle2D newRect = new Rectangle2D(rectangle.getxLowerLeft(), node.point.getY(),
                            rectangle.getxUpperRight(), rectangle.getyUpperRight());
                    node.rightNode = insert(node.rightNode, point, newRect,!direction);
                }
            }
        }

        System.out.println("check");
        System.out.println(node);
        return node;

    }

    /*
    * draw all points and lines
    * vertical lines in red
    * horizontal lines in blue
    * */
    public void draw(){
        draw(root, VERTICAL);
    }


    /*
    * draw tree under a certain node
    * */
    private void draw(Node node, boolean direction){
        if(node == null){
            return;
        }
        // node point introduces vertical line
        if(isVertical(direction)){
            StdDraw.setPenRadius();
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(node.point.getX(), node.rect.getyLowerLeft(), node.point.getX(), node.rect.getyUpperRight());
        }
        // node point introduces horizontal line
        else{
            StdDraw.setPenRadius();
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(node.rect.getxLowerLeft(), node.point.getY(), node.rect.getxUpperRight(), node.point.getY());
        }

        //draw point
        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(StdDraw.BLACK);
        node.point.draw();

        //draw subtrees
        draw(node.leftNode, !direction);
        draw(node.rightNode, !direction);
    }
}
