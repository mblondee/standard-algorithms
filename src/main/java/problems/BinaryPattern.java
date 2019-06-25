package problems;

/*
* find all binary string combinations from a given pattern (with ? wildcard that can be 0 or 1)
* */

import java.util.ArrayList;
import java.util.List;

public class BinaryPattern {

    private List<StringBuffer> listOfPatterns;
    private String input;

    public BinaryPattern(String input){
        listOfPatterns = new ArrayList<>();
        this.input = input;
        StringBuffer empty = new StringBuffer();
        listOfPatterns.add(empty);
        findPatterns();
    }

    public List<StringBuffer> getListOfPatterns(){
        return listOfPatterns;
    }

    private void findPatterns(){
        for(int i = 0; i<input.length(); i++){
            if(input.charAt(i) == '0' || input.charAt(i) == '1'){
                //just add to all combinations we already have
                for(StringBuffer s : listOfPatterns){
                    s.append(input.charAt(i));
                }
            }
            else if(input.charAt(i) == '?'){
                List<StringBuffer> toAdd = new ArrayList<>();
                // every combination that we already have has to be doubled, one with a 0 at the end
                // and one with a 1 at the end
                for(StringBuffer s: listOfPatterns){
                    StringBuffer copy = new StringBuffer(s);
                    copy.append('0');
                    toAdd.add(copy);
                    s.append('1');
                }
                for(StringBuffer str : toAdd){
                    listOfPatterns.add(str);
                }

            }

            else{
                throw new IllegalArgumentException("input not valid");
            }


        }
    }

    public static void main(String[] args){
        BinaryPattern bin = new BinaryPattern("1??0?101");
        for(StringBuffer str : bin.getListOfPatterns()){
            System.out.println(str);
        }
    }
}
