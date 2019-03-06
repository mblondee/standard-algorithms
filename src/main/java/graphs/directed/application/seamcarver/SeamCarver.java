package graphs.directed.application.seamcarver;

/*
* seam carving is a content-aware resizing technique where an image is reduced in size by one pixel of height
* or width at a time
*
* a vertical/horizontal seam in an image is a path of pixels connected from the top to the bottom / left to right
 * with one pixel in each row/ column
*
* width by height picture:
* a pixel (x,y) refers to the pixel in column x and row y
* pixel (0,0) at upper left corner
* pixel (width-1, height-1) at bottom right corner
* */

import help.libraries.Picture;

import java.awt.Color;

public class SeamCarver {
    private Picture picture;
    private double[][] energies;
    private int width; // width of picture
    private int height; //height of picture

    /*
    * create a SeamCarver object given a Picture object
    * makes a new Picture object in order not to mutate the original picture
    * */
    public SeamCarver(Picture picture){
        this.picture = new Picture(picture);
        width = width();
        height = height();

        //initialize energy matrix
        updateEnergies();
    }

    /*
    * every time before a seam is searched for the energy matrix should be updated
    *
    * */
    private void updateEnergies(){
        energies = new double[width][height];
        for(int i = 0; i< width; i++){
            for(int j = 0; j< height; j++){
                energies[i][j] = energy(i,j);
            }
        }
    }

    /*
    * return picture
    * */
    public Picture picture(){
        return picture;
    }

    /*
    * return width of picture
    * */
    public int width(){
        return picture.width();
    }

    /*
     * return height of picture
     * */
    public int height(){
        return picture.height();
    }

    /*
    * return energy of pixel and column x and row y
    * using the dual-gradient energy function
    * at border we define the energy to be equal to 1000 (strictly larger than the energy of the interior points)
    * */
    public double energy(int x, int y){
        // check indices
        if(x < 0 || x >= width || y < 0 || y >= height){
            throw new IllegalArgumentException("indices out of range");
        }
        if(x == 0 || x == width-1 || y == 0 || y == height-1){
            return 1000.0;
        }
        return Math.sqrt(deltaX(x,y) + deltaY(x,y));
    }

    private double deltaX(int x, int y){
        // get colors of pixels (x+1,y) and (x-1,y)
        Color rightColor = picture.get(x+1, y);
        Color leftColor = picture.get(x-1,y);
        double deltaRed = rightColor.getRed() - leftColor.getRed();
        double deltaGreen = rightColor.getGreen() - leftColor.getGreen();
        double deltaBlue = rightColor.getBlue() - leftColor.getBlue();
        return Math.pow(deltaRed,2) + Math.pow(deltaGreen,2) + Math.pow(deltaBlue, 2);
    }

    private double deltaY(int x, int y){
        // get colors of pixels (x,y+1) and (x,y-1)
        Color upperColor = picture.get(x, y+1);
        Color lowerColor = picture.get(x,y-1);
        double deltaRed = upperColor.getRed() - lowerColor.getRed();
        double deltaGreen = upperColor.getGreen() - lowerColor.getGreen();
        double deltaBlue = upperColor.getBlue() - lowerColor.getBlue();
        return Math.pow(deltaRed,2) + Math.pow(deltaGreen,2) + Math.pow(deltaBlue, 2);
    }

    /*
    * sequence of indices for a vertical seam of minimal total energy
    * if vertseam[row] == column, than (column,row) is on vertical seam
    * */
    public int[] findVerticalSeam(){

        // update energies
        updateEnergies();

        double[][] distanceTo = new double[width][height]; // for every pixel the distance of the shortest path
        int[][] edgeFromColumn = new int[width][height]; // for a pixel (column,row) which pixel (x,row-1) is on the shortest path

        // all distances are +infinity (except for top row)
        for(int row = 0; row <height; row++){
            for(int column = 0; column<width; column++){
                // top row all distances are the energy
                if(row == 0){
                    distanceTo[column][row] = energies[column][row];
                    edgeFromColumn[column][row] = -1;
                }
                else {
                    distanceTo[column][row] = Double.POSITIVE_INFINITY;
                    edgeFromColumn[column][row] = Integer.MAX_VALUE;
                }
            }
        }

        // relax
        for(int row = 0; row < height; row++){
            for(int column = 0; column < width; column++){

                // possible to go 1 row down
                if(row<height - 1){
                    // checking pixel (column, row+1)
                    // is there a shorter path via (column,row) than current shortest path for (column, row+1)?
                    if(distanceTo[column][row+1] > distanceTo[column][row] + energies[column][row+1]){
                        // update distance
                        distanceTo[column][row+1] = distanceTo[column][row] + energies[column][row+1];
                        // update coming from
                        edgeFromColumn[column][row+1] = column; // comes from above
                    }
                }
                //possible to go 1 row down + 1 column left
                if(row < height - 1 && column>0){
                    // checking pixel (column-1, row+1)
                    // is there a shorter path via (i,j) than current shortest path for (i-1, j+1)?
                    if(distanceTo[column-1][row+1] > distanceTo[column][row] + energies[column-1][row+1]){
                        // update distance
                        distanceTo[column-1][row+1] = distanceTo[column][row] + energies[column-1][row+1];
                        // update coming from
                        edgeFromColumn[column-1][row+1] = column; // comes from above to the right
                    }
                }
                //possible to go 1 row down + 1 column right
                if(row < height - 1 && column< width -1){
                    // checking pixel (column+1, row+1)
                    // is there a shorter path via (i,j) than current shortest path for (i+1, j+1)?
                    if(distanceTo[column+1][row+1] > distanceTo[column][row] + energies[column+1][row+1]){
                        // update distance
                        distanceTo[column+1][row+1] = distanceTo[column][row] + energies[column+1][row+1];
                        // update coming from
                        edgeFromColumn[column+1][row+1] = column; // comes from above to the left
                    }
                }
            }
        }

        // which pixel in bottom row has minimal distance?
        double minDistance = Double.POSITIVE_INFINITY;
        int minSpot = Integer.MAX_VALUE;
        for(int column = 0; column<width; column++){
            if(distanceTo[column][height-1] < minDistance){
                minDistance = distanceTo[column][height-1];
                minSpot = column;
            }
        }

        //prepare array to return
        if(minDistance < Double.POSITIVE_INFINITY){
            // there is a shortest path
            int[] vertseam = new int[height];
            // reconstruct from bottom to top
            int row = height -1;
            int column = minSpot;
            while(row >= 0){
                vertseam[row] = column;
                column = edgeFromColumn[column][row];
                row --;

            }
            return vertseam;
        }
        else{
            return null;
        }
    }


    /*
     * sequence of indices for a horizontal seam of minimal total energy
     * if horizontalseam[column] == row, than (column,row) is on horizontal seam
     * */
    public int[] findHorizontalSeam(){

        // update energies
        updateEnergies();

        double[][] distanceTo = new double[width][height]; // for every pixel the distance of the shortest path
        int[][] edgeFromRow = new int[width][height]; // for a pixel (column,row) which pixel (x,row-1) is on the shortest path

        // all distances are +infinity (except for left column)
        for(int row = 0; row <height; row++){
            for(int column = 0; column<width; column++){
                // left column all distances are the energy
                if(column == 0){
                    distanceTo[column][row] = energies[column][row];
                    edgeFromRow[column][row] = -1;
                }
                else {
                    distanceTo[column][row] = Double.POSITIVE_INFINITY;
                    edgeFromRow[column][row] = Integer.MAX_VALUE;
                }
            }
        }

        // relax
        for(int column = 0; column < width; column++){
            for(int row = 0; row<height; row++){

                // possible to go 1 column right
                if(column<width-1){
                    // checking pixel (column+1, row)
                    // is there a shorter path via (column,row) than current shortest path for (column, row+1)?
                    if(distanceTo[column+1][row] > distanceTo[column][row] + energies[column+1][row]){
                        // update distance
                        distanceTo[column+1][row] = distanceTo[column][row] + energies[column+1][row];
                        // update coming from
                        edgeFromRow[column+1][row] = row; // comes from left
                    }
                }
                //possible to go 1 column right + 1 row up
                if(column<width-1 && row>0){
                    // checking pixel (column+1, row-1)
                    // is there a shorter path via (i,j) than current shortest path for (i-1, j+1)?
                    if(distanceTo[column+1][row-1] > distanceTo[column][row] + energies[column+1][row-1]){
                        // update distance
                        distanceTo[column+1][row-1] = distanceTo[column][row] + energies[column+1][row-1];
                        // update coming from
                        edgeFromRow[column+1][row-1] = row; // comes from one down and 1 left
                    }
                }
                //possible to go 1 column right + 1 row down
                if(column<width-1 && row<height -1){
                    // checking pixel (column+1, row+1)
                    // is there a shorter path via (i,j) than current shortest path for (i+1, j+1)?
                    if(distanceTo[column+1][row+1] > distanceTo[column][row] + energies[column+1][row+1]){
                        // update distance
                        distanceTo[column+1][row+1] = distanceTo[column][row] + energies[column+1][row+1];
                        // update coming from
                        edgeFromRow[column+1][row+1] = row; // comes from 1 left and 1 up
                    }
                }
            }
        }

        // which pixel in right column minimal distance?
        double minDistance = Double.POSITIVE_INFINITY;
        int minSpot = Integer.MAX_VALUE;
        for(int row = 0; row<height; row++){
            if(distanceTo[width-1][row] < minDistance){
                minDistance = distanceTo[width-1][row];
                minSpot = row;
            }
        }

        //prepare array to return
        if(minDistance < Double.POSITIVE_INFINITY){
            // there is a shortest path
            int[] horizontalseam = new int[width];
            // reconstruct from bottom to top
            int row = minSpot;
            int column = width-1;
            while(column >= 0){
                horizontalseam[column] = row;
                row = edgeFromRow[column][row];
                column --;

            }
            return horizontalseam;
        }
        else{
            return null;
        }
    }

    /*
    * remove vertical {@code seam}
    * seam should be an array of length height
    * and if seam[row] = column, then (column, row) should be removed
    * */
    public void removeVerticalSeam(int[] seam){
        // checking seam
        if(seam == null){
            throw new IllegalArgumentException("no seam to remove");
        }
        if(seam.length != height){
            throw new IllegalArgumentException("seam has incorrect length");
        }
        for(int i : seam){
            if(i<0 || i>= width){
                throw new IllegalArgumentException("seam has out of range elements");
            }
        }

        // check valid seam -> a pixel in seam and the next are connected (distance max 2)
        for(int i = 0; i< seam.length-1; i++){
            if(Math.abs(seam[i] - seam[i+1]) >= 2){
                throw new IllegalArgumentException("invalid seam");
            }
        }

        // make a new picture, height is the same as the original but width is one less
        Picture tempPicture = new Picture(width-1, height);
        // copy pixels of original picture into tempPicture, except for (column, row) in seam
        int columnToPut = 0; // to keep track in which column a pixel should be put (we are removing a column)
        for(int row = 0; row < height; row++){
            for(int column = 0; column<width; column++){
                if(seam[row] != column) {
                    tempPicture.set(columnToPut, row, picture.get(column, row));
                    columnToPut++; // only go to next column when a pixel has been put in place
                }
            }
            // start over again in next row
            columnToPut = 0;

        }
        // replace picture
        picture = tempPicture;
        width = tempPicture.width();
        height = tempPicture.height();

    }


    /*
     * remove horizontal {@code seam}
     * seam should be an array of length width
     * and if seam[column] = row, then (column, row) should be removed
     * */
    public void removeHorizontalSeam(int[] seam){
        // checking seam
        if(seam == null){
            throw new IllegalArgumentException("no seam to remove");
        }
        if(seam.length != width){
            throw new IllegalArgumentException("seam has incorrect length");
        }
        for(int i : seam){
            if(i<0 || i>= height){
                throw new IllegalArgumentException("seam has out of range elements");
            }
        }
        // check valid seam -> a pixel in seam and the next are connected (distance max 2)
        for(int i = 0; i< seam.length-1; i++){
            if(Math.abs(seam[i] - seam[i+1]) >= 2){
                throw new IllegalArgumentException("invalid seam");
            }
        }


        // make a new picture, width is the same as the original but height is one less
        Picture tempPicture = new Picture(width, height-1);
        // copy pixels of original picture into tempPicture, except for (column, row) in seam
        int rowToPut = 0; // to keep track in which column a pixel should be put (we are removing a row)
        for(int column = 0; column < width; column++){
            for(int row = 0; row<height; row++){
                if(seam[column] != row) {
                    tempPicture.set(column, rowToPut, picture.get(column, row));
                    rowToPut++; // only go to next row when a pixel has been put in place
                }
            }
            // start over again in next column
            rowToPut = 0;
        }
        // replace picture
        picture = tempPicture;
        width = tempPicture.width();
        height = tempPicture.height();

    }

}
