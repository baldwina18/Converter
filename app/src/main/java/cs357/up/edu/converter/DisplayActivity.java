package cs357.up.edu.converter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by alexabaldwin on 4/17/16.
 * @author Alexa Baldwin
 * @version 4/17/16
 */
public class DisplayActivity extends Activity {
    static Converter converter;
    protected TextView nfaDisplay;
    protected TextView dfaDisplay;
    protected DFA myDfa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_layout);

        //initializes GUI and converter objects
        converter = MainActivity.converter;

        nfaDisplay = (TextView) findViewById(R.id.nfaDisplay);
        dfaDisplay = (TextView) findViewById(R.id.dfaDisplay);

        myDfa = new DFA(converter);

        updateNfaDisplay();
        updateDfaDisplay();
    }

    //formats the display for the NFA so user can verify inputs
    public void updateNfaDisplay() {
        nfaDisplay.setText("- States:\t\t\t\t\t\t" + getNfaStates() + "\n" +
                "- Language:\t\t\t\t" + getNfaLanguage() + "\n" +
                "- Transitions:\n" + getNfaTransitions() +
                "- Start State:\t\t\t\tQ1\n" +
                "- Accept States:\t\t" + getNfaAcceptStates());
    }

    //displays the converted DFA so user can see output
    public void updateDfaDisplay() {
        dfaDisplay.setText("- States:\t\t\t\t\t\t" + getDfaStates() + "\n" +
                "- Language:\t\t\t\t" + getNfaLanguage() + "\n" +
                "- Transitions:\n" + getDfaTransitions() +
                "- Start State:\t\t\t\t" + getDfaStartStates() + "\n" +
                "- Accept States:\t\t" + getDfaAcceptStates());
    }

    //helper method to format NFA states
    public String getNfaStates() {
        String states = "";
        for(int i=0; i<converter.numStates; i++) {
            states += "Q" + (i+1);
            if(i != converter.numStates-1) {
                states +=  ", ";
            }
        }
        return "{" + states + "}";
    }

    //helper method to format NFA language
    public String getNfaLanguage() {
        String lang = "";
        for(int i=0; i<converter.langSize;i++) {
            lang += converter.language.get(i)+" ";
        }
        return "{" + lang.trim() + "}";
    }

    //helper method to format NFA accept states
    public String getNfaAcceptStates() {
        String accept = "";
        for(int i=0; i<converter.numStates;i++) {
            if(converter.acceptStates[i] == 1) {
                accept += "Q" + (i+1) + " ";
            }
        }
        return "{" + accept.trim() + "}";
    }

    //helper method to format NFA transitions
    public String getNfaTransitions() {
        String rows = "";
        for(int i=0;i<converter.numStates;i++) {
            for (int j = 0; j < converter.langSize + 1; j++) {
                if(j != converter.langSize) {
                    rows += "\t\t\t\t\t\t\t\t\t\t\tState: Q" + (i + 1) + "\t on: " +
                            converter.language.get(j) + "\t\t\t\t\t{" +
                            converter.transitions[i][j] + " }\n";
                }
                else { //if on epsilon transition
                    rows += "\t\t\t\t\t\t\t\t\t\t\tState: Q" + (i + 1) + "\t on: epsilon\t{" +
                            converter.transitions[i][j] + " }\n";
                }
            }
        }
        return rows;
    }

    /*
        EXTERNAL CITATION
        Date:   4/17/16
        Source: http://stackoverflow.com/questions/1670862/obtaining-a-powerset-of-a-set-in-java
        This code has been utilized for the purpose of creating the powerset of the states
        of the NFA for the new DFA.
        Part 3 of 3
    */
    //helper method to format DFA states
    public String getDfaStates() {
        String states = "";
        for(int i=0;i<myDfa.numStates;i++) {
            states += myDfa.states[i] + ", ";
            if(i%3==0 && i!=0) { states+="\n\t\t\t\t\t\t\t\t\t\t\t"; }

        }
        return "{"+states+"}";
    }

    //helper method to format DFA transitions
    public String getDfaTransitions() {
        String transitions = "";
        for(int i=0; i<myDfa.numStates; i++) {
            for(int j=0; j<converter.langSize; j++) {
                transitions += "\t\t\t\t\t\t\t\t\t\t\tState: " + myDfa.states[i] + " on: " +
                        myDfa.language[j] + " { " + myDfa.finalTransitions[i][j] + " }\n";
            }
        }
        return transitions;
    }

    //helper method to format DFA accept states
    public String getDfaAcceptStates() {
        String accept = "";
        for(int i=0; i<myDfa.numStates;i++) {
            if(myDfa.acceptStates[i] == 1) {
                accept += myDfa.states[i] + " ";
            }
            if(i%3==0 && i!=0) { accept+="\n\t\t\t\t\t\t\t\t\t\t\t"; }
        }
        return "{" + accept.trim() + "}";
    }

    //helper method to format DFA start states
    public String getDfaStartStates() {
        return myDfa.startState;
    }
}
