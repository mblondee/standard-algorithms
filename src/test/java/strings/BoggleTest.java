package strings;

import help.libraries.In;
import org.junit.Test;
import strings.applications.Boggle.BoggleBoard;
import strings.applications.Boggle.BoggleSolver;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class BoggleTest {

    @Test
    public void test(){
        BoggleBoard board = new BoggleBoard("src/main/resources/board4x4.txt");
        In in = new In("src/main/resources/dictionary-algs4.txt");
        ArrayList<String> dict = new ArrayList<>();
        while(in.hasNextLine()){
            dict.add(in.readLine());
        }
        String[] dictString = dict.toArray(new String[dict.size()]);

        BoggleSolver solver = new BoggleSolver(dictString);
        int score = 0;
        for(String s : solver.getAllValidWords(board)){
            System.out.println(s);
            score += solver.scoreOf(s);
        }
        assertEquals(33, score);
        System.out.println(score);
    }
}
