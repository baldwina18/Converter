package cs357.up.edu.converter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by alexabaldwin on 4/17/16.
 * @author Alexa Baldwin
 * @version 4/17/16
 *
 * Class DFA - creates a DFA object based off of an NFA (converter)
 * object passed into the constructor
 */
public class DFA {
    protected String[] states;
    protected int[] acceptStates;
    protected Converter converter;
    protected String[][] transitions;
    protected String[][] finalTransitions;
    protected char[] language;
    protected int numStates;
    protected String startState;
    protected Set<Integer> mySet;

    public DFA(Converter converter) {
        this.converter = converter;
        numStates = (int)Math.pow(2.0,(double)converter.numStates);
        states = new String[numStates];
        language = new char[converter.langSize];
        for(int i=0;i<converter.langSize;i++) {
            language[i] = converter.language.get(i);
        }
        transitions = new String[numStates][converter.langSize];
        finalTransitions = new String[numStates][converter.langSize];
        for(int i=0;i<numStates;i++) {
            for(int j=0;j<converter.langSize;j++) {
                transitions[i][j] = "";
                finalTransitions[i][j] = "";
            }
        }
        acceptStates = new int[numStates];
        create();
        makeStates();
        findAcceptStates();
        findStartStates();
        findTransitions();
    }

    /*
        EXTERNAL CITATION
        Date:   4/17/16
        Source: http://stackoverflow.com/questions/1670862/obtaining-a-powerset-of-a-set-in-java
        This code has been utilized for the purpose of creating the powerset of the states
        of the NFA for the new DFA.
        Part 1 of 3
     */
    public void create() {
        mySet = new HashSet<Integer>();
        for(int i=0;i<converter.numStates;i++) {
            mySet.add(i+1);
        }
    }

    /*
        EXTERNAL CITATION
        Date:   4/17/16
        Source: http://stackoverflow.com/questions/1670862/obtaining-a-powerset-of-a-set-in-java
        This code has been utilized for the purpose of creating the powerset of the states
        of the NFA for the new DFA.
        Part 2 of 3
     */
    public <T> Set<Set<T>> powerSet(Set<T> mySet) {
        Set<Set<T>> sets = new HashSet<Set<T>>();
        if (mySet.isEmpty()) {
            sets.add(new HashSet<T>());
            return sets;
        }
        List<T> list = new ArrayList<T>(mySet);
        T head = list.get(0);
        Set<T> rest = new HashSet<T>(list.subList(1, list.size()));
        for (Set<T> set : powerSet(rest)) {
            Set<T> newSet = new HashSet<T>();
            newSet.add(head);
            newSet.addAll(set);
            sets.add(newSet);
            sets.add(set);
        }
        return sets;
    }

    //uses output from powerSet to add each set to the String[] of states for later use
    public void makeStates() {
        int i=0;
        for(Set<Integer> s : powerSet(mySet)) {
            states[i] = ""+ s;
            i++;
        }
    }

    //finds which states are accept states
    //does so by seeing if a part of the state contains an accepted state from the NFA
    public void findAcceptStates() {
        ArrayList<Integer> accept = new ArrayList<Integer>();
        for(int k=0;k<converter.numStates;k++) {
            if (converter.acceptStates[k] == 1) {
                accept.add(k + 1);
            }
        }
        for(int i=0;i<numStates;i++) {
            for(int j=0; j<accept.size();j++) {
                String s = Integer.toString(accept.get(j));
                if(states[i].contains(s)){
                    acceptStates[i] = 1;
                }
            }
            if(acceptStates[i]!=1) { acceptStates[i] = 0; }
        }
    }

    //performs an epsilon closure on teh start state
    public void findStartStates() {
        if(converter.transitions[0][converter.langSize].equals("")) {
            startState = "{1}";
        }
        else {
            startState = "{" + "1 " + converter.transitions[0][converter.langSize] + "}";
        }
    }

    //finds the transitions for the DFA
    //uses the epsilon closure of the NFA transitions
    public void findTransitions() {
        for (int i = 0; i < numStates; i++) {
        //goes through all states
            for (int j = 0; j < states[i].length(); j++) {
            //goes through each letter in the state string
                if (states[i].charAt(j) != ' ' && states[i].charAt(j) != '['
                        && states[i].charAt(j) != ']' && states[i].charAt(j) != ',') {
                    for (int k = 0; k < converter.langSize; k++) {
                    //goes through each letter in the language
                        int idx = Integer.parseInt(states[i].charAt(j)+"")-1;
                        transitions[i][k] += converter.transitions[idx][k] +
                                converter.transitions[idx][converter.langSize];
                                //second part covers epsilon transitions
                    }
                }
            }
        }
        //this covers second degree epsilon transitions
        for(int i=0;i<numStates;i++) {
            for(int j=0;j<converter.langSize;j++) {
                for(int k=0;k<transitions[i][j].length();k++) {
                    if(transitions[i][j].charAt(k) != ' ') {
                        char c = transitions[i][j].charAt(k);
                        int idx = Integer.parseInt(c+"");
                        if(!converter.transitions[idx-1][converter.langSize].equals("")) {
                            transitions[i][j] += converter.transitions[idx-1][converter.langSize];
                        }
                    }
                }
            }
        }
        cleanTransitions();

    }

    //helper method to clean up and make sure there are no duplicate states in
    //the final transition array
    public void cleanTransitions() {
        for(int i=0; i<numStates; i++) {
            for(int j=0;j<converter.langSize;j++) {
                for(int k=0;k<converter.numStates;k++) {
                    if(transitions[i][j].contains(Integer.toString(k+1))){
                        finalTransitions[i][j] += (k+1) + " ";
                    }
                }
            }
        }
    }

}
