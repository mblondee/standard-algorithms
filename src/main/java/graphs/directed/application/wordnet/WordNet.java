package graphs.directed.application.wordnet;

/*
* data type WordNet: a WordNet digraph every vertex v represents a synset (group of words that are synonyms)
* and there is a directed edge v -> w is w is a hypernym (a more general synset) of v
*
* WordNet is a semantic lexicon for the English language  https://wordnet.princeton.edu/
*
* resource files (CSV format)
* synsets.txt contains all noun synsets in WordNet:
* - line i (counting from i) contains the information for synset i, first field is the synset id i
* - second filed is the synonym set
* - third field the dictionary definition (not relevant here)
*
* hypernyms.txt contains the hypernym relationships
* - line i  (counting from 0) contains the hypernyms of synset i, first field is the synset id i
* - subsequent ids are the id numbers of the hypernyms
*
* measuring the semantic relatedness of two nouns x and y is done as follows:
* - A = set of synsets in which x appears
* - B = set of synsets in which y appears
* - distance(x,y) = length of shortest ancestral path of subsets A and B in the WordNet graph
*
* an ancestral path between vertices v and w is a directed path from v to a common ancestor, together with a directed from w to the ancestor
* a shortest ancestral path is an ancestral path of minimum total length
* the common ancestor in a shortest ancestral path is called a shortest common ancestor
*
* This is based on assigment 1 in the Algorithms II course on Coursera.
* */

import graphs.directed.DiGraph;
import graphs.directed.DirectedEdge;
import graphs.directed.application.wordnet.ShortestAncestralPath;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class WordNet {

    private DiGraph<Integer> graphOfSynsets; // represent synsets by there id
    private HashMap<String, Integer> synsetIDList;  // lookup id of the synset to which a noun belongs
    private HashMap<Integer, String[]> synsetList; //lookup synsets given an id
    private ShortestAncestralPath<Integer> sap; // find shortest ancestral path

    /*
    * create a WordNet from two input files containing the synsets and the hypernyms
    * */
    public WordNet(String synsets, String hypernyms){
        graphOfSynsets = new DiGraph();
        synsetIDList = new HashMap<>();
        synsetList = new HashMap<>();

        // get synsets from file
        String line;
        try {
            FileReader inputFile = new FileReader(synsets);
            BufferedReader bufferReader = new BufferedReader(inputFile);
            while ( (line = bufferReader.readLine()) != null){
                String lineString = line.trim();
                String[] toAdd = lineString.split(",");
                // id is first field
                Integer id = Integer.parseInt(toAdd[0]);
                // synomyms (separated by a ,) are synonyms
                String[] synonymsArray = Arrays.copyOfRange(toAdd, 1, toAdd.length);


                // add synset to synsetList
                synsetList.put(id, synonymsArray);


                // add id to graph
                graphOfSynsets.addVertex(id);

                // add synset to synsetIDList
                for(String str : synonymsArray){
                    synsetIDList.put(str, id);
                }


            }
            bufferReader.close();
        }
        catch(Exception e){
            System.out.println("Error while reading file " + e.getMessage());
        }

        // get hypernyms
        try {
            FileReader inputFile = new FileReader(hypernyms);
            BufferedReader bufferReader = new BufferedReader(inputFile);
            while ( (line = bufferReader.readLine()) != null){
                String lineString = line.trim();
                String[] toAdd = lineString.split(",");
                // id is first field
                Integer id = Integer.parseInt(toAdd[0]);
                // subsequent fields are synonyms
                String[] nextFields = Arrays.copyOfRange(toAdd, 1, toAdd.length);
                for(String s: nextFields){
                    graphOfSynsets.addEdge(new DirectedEdge<>(id, Integer.parseInt(s)));
                }



            }
            bufferReader.close();
        }
        catch(Exception e){
            System.out.println("Error while reading file " + e.getMessage());
        }

        sap = new ShortestAncestralPath<>(graphOfSynsets);


    }

    public DiGraph<Integer> getGraphOfSynsets(){
        return graphOfSynsets;
    }


    /*
    * get all nouns in WordNet
    * */
    public Iterable<String> nouns(){
        return synsetIDList.keySet();
    }


    /*
    * is {@code word} a WordNet noun?
    * */
    public boolean isNoun(String word){
        return synsetIDList.containsKey(word);
    }

    /*
    * return the distance between {@code noun1} and {@code noun2}
    *
    * */

    public int distance(String noun1, String noun2){
        if(! isNoun(noun1) || ! isNoun(noun2)){
            throw new IllegalArgumentException("Noun is not in WordNet");
        }
        return sap.getLength(synsetIDList.get(noun1), synsetIDList.get(noun2));
    }

    /*
    * return a synset that is the common ancestor of {@code noun1} and {@code noun2}
    * in a shortest ancestral path
    * */
    public String sap(String noun1, String noun2){
        if(! isNoun(noun1) || ! isNoun(noun2)){
            throw new IllegalArgumentException("Noun is not in WordNet");
        }
        Integer ancestor = sap.getAncestor(synsetIDList.get(noun1), synsetIDList.get(noun2));
        return String.join(",", synsetList.get(ancestor));
    }

    @Override
    public String toString(){
        return graphOfSynsets.toString();
    }




}
