package strings.applications.grep;

/*
* check if given text has substring described by a regular expression
* */

import strings.search.NFA;

public class Grep {

    private String regexp;

    public Grep(String regexp){
        this.regexp = "(.*" + regexp + ".*)";
    }

    public boolean grepIt(String text){
        NFA nfa = new NFA(regexp);
        return nfa.matches(text);
    }
}
