package graphs.directed.application;

import graphs.directed.application.baseballelimination.BaseballElimination;
import org.junit.Test;
import static org.junit.Assert.*;

public class BaseballEliminationTest {

    @Test
    public void test(){
        String fileName = "src/main/resources/teams4.txt";
        BaseballElimination elimination = new BaseballElimination(fileName);
        assertEquals(4, elimination.numberOfTeams());
        assertEquals(83, elimination.wins("Atlanta"));
        assertEquals(80, elimination.wins("Philadelphia"));
        assertEquals(78, elimination.wins("New_York"));
        assertEquals(77, elimination.wins("Montreal"));

        assertEquals(71, elimination.losses("Atlanta"));
        assertEquals(79, elimination.losses("Philadelphia"));
        assertEquals(78, elimination.losses("New_York"));
        assertEquals(82, elimination.losses("Montreal"));


        assertEquals(8, elimination.remaining("Atlanta"));
        assertEquals(3, elimination.remaining("Philadelphia"));
        assertEquals(6, elimination.remaining("New_York"));
        assertEquals(3, elimination.remaining("Montreal"));

        for(String team : elimination.teams()){
            assertEquals(0, elimination.against(team, team));
        }

        assertEquals(1, elimination.against("Atlanta", "Philadelphia"));
        assertEquals(6, elimination.against("Atlanta", "New_York"));
        assertEquals(1, elimination.against("Atlanta", "Montreal"));

        // private methods
/*        assertTrue(elimination.triviallyEliminated("Montreal"));
        assertFalse(elimination.triviallyEliminated("Atlanta"));
        assertFalse(elimination.triviallyEliminated("Philadelphia"));
        assertFalse(elimination.triviallyEliminated("New_York"));*/

/*        assertFalse(elimination.notTriviallyEliminated("Atlanta"));
        assertTrue(elimination.notTriviallyEliminated("Philadelphia"));
        assertFalse(elimination.notTriviallyEliminated("New_York"));
        assertTrue(elimination.notTriviallyEliminated("Montreal"));*/


        assertTrue(elimination.isEliminated("Montreal"));
        System.out.println("R: ");
        for(String s : elimination.certificateOfElimination("Montreal")){
            System.out.println(s);
        }
        assertTrue(elimination.isEliminated("Philadelphia"));
        System.out.println("-----");
        for(String s : elimination.certificateOfElimination("Philadelphia")){
            System.out.println(s);
        }
        assertFalse(elimination.isEliminated("Atlanta"));
        assertFalse(elimination.isEliminated("New_York"));


    }

    @Test
    public void test1(){
        String fileName = "src/main/resources/teams5c.txt";
        BaseballElimination elimination = new BaseballElimination(fileName);
        for(String s: elimination.certificateOfElimination("Philadelphia")){
            System.out.println(s);
        }
    }
}
