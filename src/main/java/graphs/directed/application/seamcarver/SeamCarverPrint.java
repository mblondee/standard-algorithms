package graphs.directed.application.seamcarver;

import help.libraries.Picture;


/*
The tables give the dual-gradient energies of each pixel.
The asterisks denote a minimum energy vertical or horizontal seam.

* 7x10
*
* Vertical seam: { 2 3 4 3 4 3 3 2 2 1 }
1000.00  1000.00  1000.00* 1000.00  1000.00  1000.00  1000.00
1000.00   183.54   295.55   193.89*  205.60   294.62  1000.00
1000.00   255.25   274.19   302.82   170.77*  306.23  1000.00
1000.00   253.63   258.40   174.81*  389.01   231.09  1000.00
1000.00   319.87   253.40   353.10   253.29*  369.46  1000.00
1000.00   244.46   339.17   113.74*  245.15   285.13  1000.00
1000.00   281.78   293.41   260.67*  200.70   227.75  1000.00
1000.00   161.37    72.79*  263.82   290.37   282.19  1000.00
1000.00   258.97   203.24*  293.78   169.46   230.88  1000.00
1000.00  1000.00* 1000.00  1000.00  1000.00  1000.00  1000.00
Total energy = 3443.197820


Horizontal seam: { 6 7 7 7 8 8 7 }
1000.00  1000.00  1000.00  1000.00  1000.00  1000.00  1000.00
1000.00   183.54   295.55   193.89   205.60   294.62  1000.00
1000.00   255.25   274.19   302.82   170.77   306.23  1000.00
1000.00   253.63   258.40   174.81   389.01   231.09  1000.00
1000.00   319.87   253.40   353.10   253.29   369.46  1000.00
1000.00   244.46   339.17   113.74   245.15   285.13  1000.00
1000.00*  281.78   293.41   260.67   200.70   227.75  1000.00
1000.00   161.37*   72.79*  263.82*  290.37   282.19  1000.00*
1000.00   258.97   203.24   293.78   169.46*  230.88* 1000.00
1000.00  1000.00  1000.00  1000.00  1000.00  1000.00  1000.00
Total energy = 2898.313922

3x4

Vertical seam: { 0 1 1 0 }
1000.00* 1000.00  1000.00
1000.00   228.53* 1000.00
1000.00   228.09* 1000.00
1000.00* 1000.00  1000.00
Total energy = 2456.615600


Horizontal seam: { 1 2 1 }
1000.00  1000.00  1000.00
1000.00*  228.53  1000.00*
1000.00   228.09* 1000.00
1000.00  1000.00  1000.00
Total energy = 2228.087702

* */


public class SeamCarverPrint {
        private static final boolean HORIZONTAL   = true;
        private static final boolean VERTICAL     = false;
        private static String file = "src/main/resources/7x10.png";

        private static void printSeam(SeamCarver carver, int[] seam, boolean direction) {
            double totalSeamEnergy = 0.0;

            for (int row = 0; row < carver.height(); row++) {
                for (int col = 0; col < carver.width(); col++) {
                    double energy = carver.energy(col, row);
                    String marker = " ";
                    if ((direction == HORIZONTAL && row == seam[col]) ||
                            (direction == VERTICAL   && col == seam[row])) {
                        marker = "*";
                        totalSeamEnergy += energy;
                    }
                    System.out.print(String.format("%7.2f%s ", energy, marker));
                }
                System.out.println();
            }
            // StdOut.println();
            System.out.println(String.format("Total energy = %f\n", totalSeamEnergy));
            System.out.println();
            System.out.println();
        }

        public static void main(String[] args) {
            Picture picture = new Picture(file);
            System.out.println(String.format("%s (%d-by-%d image)\n", file, picture.width(), picture.height()));
            System.out.println();
            System.out.println("The table gives the dual-gradient energies of each pixel.");
            System.out.println("The asterisks denote a minimum energy vertical or horizontal seam.");
            System.out.println();

            SeamCarver carver = new SeamCarver(picture);

            System.out.print("Vertical seam: { ");
            int[] verticalSeam = carver.findVerticalSeam();
            for (int x : verticalSeam)
                System.out.print(x + " ");
            System.out.println("}");
            printSeam(carver, verticalSeam, VERTICAL);

            System.out.print("Horizontal seam: { ");
            int[] horizontalSeam = carver.findHorizontalSeam();
            for (int y : horizontalSeam)
                System.out.print(y + " ");
            System.out.println("}");
            printSeam(carver, horizontalSeam, HORIZONTAL);

        }

}
