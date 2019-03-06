package graphs.directed.application.seamcarver;

import help.libraries.Picture;
import help.libraries.Stopwatch;

public class SeamCarverDemo {

    private static String file = "src/main/resources/chameleon.png";
    private static int removeColumns = 100;
    private static int removeRows = 100;

    public static void main(String[] args) {

        Picture inputImg = new Picture(file);


        System.out.println(String.format("image is %d columns by %d rows\n", inputImg.width(), inputImg.height()));
        SeamCarver sc = new SeamCarver(inputImg);

        Stopwatch sw = new Stopwatch();

        for (int i = 0; i < removeRows; i++) {
            int[] horizontalSeam = sc.findHorizontalSeam();
            sc.removeHorizontalSeam(horizontalSeam);
        }

        for (int i = 0; i < removeColumns; i++) {
            int[] verticalSeam = sc.findVerticalSeam();
            sc.removeVerticalSeam(verticalSeam);
        }
        Picture outputImg = sc.picture();

        System.out.println(String.format("new image size is %d columns by %d rows\n", sc.width(), sc.height()));

        System.out.println("Resizing time: " + sw.elapsedTime() + " seconds.");
        inputImg.show();
        outputImg.show();
    }
}
