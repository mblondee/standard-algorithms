package graphs.directed.application.baseballelimination;

/*
* There are n teams. At some point during the season, team i has
* - wins[i] wins
* - losses[i] losses
* - remaining[i] remaining games left to play
* - gamesLeft[i][j] games left to play against team j
*
* A team is called mathematically eliminated if it cannot possible finish the season in first place (or tied in first place).
* The goal is to determinate which teams are mathematically eliminated.
*
* Assumptions: no game ends in a tie and every sheduled game is played.
*
*
* This is assignment 3 in the Algorithms II course on Coursera.
* */

import help.libraries.In;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;

public class BaseballElimination {
    private String[] teams;
    private HashMap<String, Integer> getTeamId; // id for each team
    private int[] wins;
    private int[] losses;
    private int[] remaining;
    private int[][] gamesLeft;
    private int numberOfTeams;
    private boolean[] eliminated;
    private HashMap<String, HashSet<String>> certificateOfElimination; // for each team the teams that eliminate the team

    private static double PRECISION = 1E-11;// for rounding

    public BaseballElimination(String fileName){
/*        In in = new In(fileName);
        numberOfTeams = in.readInt();

        teams = new String[numberOfTeams];
        wins = new int[numberOfTeams];
        losses = new int[numberOfTeams];
        remaining = new int[numberOfTeams];
        gamesLeft = new int[numberOfTeams][numberOfTeams];
        getTeamId = new HashMap<>();
        eliminated = new boolean[numberOfTeams];
        certificateOfElimination = new HashMap<>();

        for(int i = 0; i<numberOfTeams; i++){
            String teamName = in.readString();
            teams[i] = teamName;
            getTeamId.put(teamName, i);
            certificateOfElimination.put(teamName, new HashSet<>());
            wins[i] = in.readInt();
            losses[i] = in.readInt();
            remaining[i] = in.readInt();
            for(int j = 0; j< numberOfTeams; j++){
                gamesLeft[i][j] = in.readInt();
            }
        }*/

        System.out.println("Reading file");
        String line;
        try {
            FileReader inputFile = new FileReader(fileName);
            BufferedReader bufferReader = new BufferedReader(inputFile);
            line = bufferReader.readLine();
            // number of teams is first line in file
            numberOfTeams = Integer.parseInt(line);

            //initialize arrays
            teams = new String[numberOfTeams];
            wins = new int[numberOfTeams];
            losses = new int[numberOfTeams];
            remaining = new int[numberOfTeams];
            gamesLeft = new int[numberOfTeams][numberOfTeams];
            getTeamId = new HashMap<>();
            eliminated = new boolean[numberOfTeams];
            certificateOfElimination = new HashMap<>();


            int team = 0;
            while ((line = bufferReader.readLine()) != null) {
                String[] lineSplitted = line.split("\\s+");
                String teamName = lineSplitted[0].trim(); // team name is first el in line
                teams[team] = teamName; // team name is first el in line
                getTeamId.put(teamName, team);
                certificateOfElimination.put(teamName, new HashSet<>());
                wins[team] = Integer.parseInt(lineSplitted[1].trim()); // wins is second el in line
                losses[team] = Integer.parseInt(lineSplitted[2].trim()); // losses is third el in line
                remaining[team] = Integer.parseInt(lineSplitted[3].trim()); // remaining is fourth el in line

                // next are the games left to play against the other teams
                for (int j = 0; j < numberOfTeams; j++) {
                    gamesLeft[team][j] = Integer.parseInt(lineSplitted[4 + j].trim());
                }
                team++;


            }
        }


        catch (Exception e) {
            System.out.println("Error while reading file " + e.getMessage());
        }

        for(String team1 : teams){
            triviallyEliminated(team1);
            // if not trivially eliminated check if there is an other reason to be eliminated
            if(! eliminated[getTeamId.get(team1)]){
                notTriviallyEliminated(team1);
            }
        }


    }


    /*
    * return number of teams
    * */
    public int numberOfTeams(){
        return numberOfTeams;
    }

    /*
    * return all teams
    * */
    public Iterable<String> teams(){
        return getTeamId.keySet();
    }

    /*
    * return number of wins for a team
    * */
    public int wins(String team){
        validateTeam(team);
        return wins[getTeamId.get(team)];
    }

    /*
     * return number of losses for a team
     * */
    public int losses(String team){
        validateTeam(team);
        return losses[getTeamId.get(team)];
    }

    /*
     * return number of remaining games for a team
     * */
    public int remaining(String team){
        validateTeam(team);
        return remaining[getTeamId.get(team)];
    }

    /*
     * return number of remaining games between two teams
     * */
    public int against(String team1, String team2){
        validateTeam(team1);
        validateTeam(team2);
        return gamesLeft[getTeamId.get(team1)][getTeamId.get(team2)];
    }

    /*
    * is a team eliminated?
    * */
    public boolean isEliminated(String team){
        validateTeam(team);
        return eliminated[getTeamId.get(team)];
    }

    /*
    * return subset of teams that elimates a given team, null is not eliminated
    * */
    public Iterable<String> certificateOfElimination(String team){
        validateTeam(team);
        if(isEliminated(team)){
            return certificateOfElimination.get(team);
        }
        else{
            return null;
        }
    }



    /*
    * is a team trivially eliminated: is wins[team] + remaining[team] < wins[someOtherTeam]?
    * add someOtherTeam to set of teams eliminating team
    * */
    private void triviallyEliminated(String team){
        for(String otherTeam : teams()){
            if(wins(team) + remaining(team) < wins(otherTeam)){
                eliminated[getTeamId.get(team)] = true;
                certificateOfElimination.get(team).add(otherTeam);
            }
        }
    }


    /*
    * using maxflow problem to check whether {@code team} has been eliminated in a non trivial manner
    *
    * flow network:
    * - 2 artificial vertices (source and sink)
    * - vertex per game between 2 teams (game vertices)
    * - vertex per team (team vertices)
    *
    * - source is connected to each game vertex (i-j) with capacity gamesLeft[i][j]:
    * - each game vertex (i-j) is connected to team vertices i and j: ensure that one of the two teams earns a win, capacity infinity
    * - each team vertex i is connected to the sink: capacity wins[team] + remaining[team] - wins[i]
    *   we want to know if there is way of completing all games in a way that team wins at least as many games as i
    *   team can win as many as wins[team] + remaining[team]
    *   if we put capacity i -> sink to wins[team] + remaining[team] - wins[i], then we prevent i from winning more than team
    *
    * maxflow:
    * - source -> gamevertices all full: all remaining games have been assigned in such a way that no team wins more games than team
    * - source -> gamevertex not full: there is no scenario in which team can win the division
    * */
    private void notTriviallyEliminated(String team){

        // create flow network for team
        int gameVertices = numberOfTeams*(numberOfTeams - 1)/2 ; //combination of 2 out of n
        int flowVertices = 2 + gameVertices + numberOfTeams; // source + sink + game vertices + team vertices
        FlowNetwork flowNet = new FlowNetwork(flowVertices);

        // initialize vertices and edges
        int source = 0;
        int sink = flowVertices - 1;

        // game vertices + edges (source -> game vertex) + edges (game -> team)
        int gameVertex = 1; // keep track of games
        // get number of remaining games from gamesLeft
        for(int row = 0; row < gamesLeft.length; row++){
            for(int column = row +1; column < gamesLeft[row].length; column++){
                // edge source -> game
                flowNet.addEdge(new FlowEdge(source, gameVertex, gamesLeft[row][column]));
                // edge game (row - column)
                // to get flow netwerk vertex for a team (row or column)
                // 1 (source) + gameVertices + team
                flowNet.addEdge(new FlowEdge(gameVertex, 1 + gameVertices + row, Double.POSITIVE_INFINITY));
                flowNet.addEdge(new FlowEdge(gameVertex, 1 + gameVertices + column, Double.POSITIVE_INFINITY));
                // go to next game
                gameVertex ++;
            }

            // add edge from team in row to sink
            // get vertex for row as before
            // capacity: wins[team] + remaining[team] - wins[i]
            int teamVertex = 1 + gameVertices + row;
            double capacity = Math.max(0, wins(team) + remaining(team) - wins[row]);
            flowNet.addEdge(new FlowEdge(teamVertex, sink, capacity));
        }


        //compute max flow -> flowNet will be changed: team wins as little as possible
        FordFulkerson maxflow = new FordFulkerson(flowNet, source, sink);
        // check edges starting in source: are these full? if one is not full, then team is eliminated
        boolean isEliminated = false;
        for(FlowEdge edge : flowNet.adj(source)){
            if(Math.abs(edge.flow()- edge.capacity())> PRECISION){
                isEliminated = true;
                break; // break loop
            }
        }
        eliminated[getTeamId.get(team)] = isEliminated;

        // if eliminated add eliminating teams
        // these can be found on the source side of the min cut
        if(isEliminated){
            for(String otherTeam: teams){
                if(otherTeam.equals(team)){
                    continue; // go to next iteration
                }
                if(maxflow.inCut(getTeamId.get(otherTeam) + 1 + gameVertices)){
                    certificateOfElimination.get(team).add(otherTeam);
                }
            }
        }


    }

    private void validateTeam(String team){
        if(team == null || ! getTeamId.keySet().contains(team)){
            throw new IllegalArgumentException("not a valid team");
        }
    }


}
