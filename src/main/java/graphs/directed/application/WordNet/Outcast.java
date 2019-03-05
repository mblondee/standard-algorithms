package graphs.directed.application.WordNet;

/*
* a data type for detecting outcasts in a list of WordNet nouns: which noun is least related to the others?
* to identify an outcast
* - compute for each noun the sum of distances to the other nouns
* - return the noun with highest sum
* */

public class Outcast {
    private WordNet wordNet;

    public Outcast(WordNet wordNet){
        this.wordNet = wordNet;
    }

    /*
    * return an outcast given an array of WordNet nouns
    * */
    public String outcast(String[] nouns){
        if(nouns.length == 0){
            throw new IllegalArgumentException("Noun list should not be empty");
        }
        int maxDistance = 0;
        String maxString = nouns[0];
        for(String str: nouns){
            int distance = 0;
            for(String otherStr : nouns) {
                distance += wordNet.distance(str, otherStr);
            }
            if(distance > maxDistance){
                maxDistance = distance;
                maxString = str;
            }
        }
        return maxString;
    }
}
