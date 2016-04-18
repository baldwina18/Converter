package cs357.up.edu.converter;

import android.view.View;
import android.widget.CheckBox;
import java.util.ArrayList;

/**
 * Created by alexabaldwin on 3/8/16.
 * @author Alexa Baldwin
 * @version 4/17/16
 */

/**
 * Converter - stores information on the incoming NFA to later convert
 * to an equivalent DFA
 */
public class Converter {
    protected int numStates;
    protected int langSize;
    protected ArrayList<CheckBox> statesList = new ArrayList<>();
    protected int[] acceptStates;
    protected ArrayList<Character> language = new ArrayList<>();
    protected String[][] transitions;

    public Converter() {
        langSize = 1;
        numStates = 1;
    }

    //method to set visiblity of the state checkboxes
    public void setVisible() {
        for(int i=0; i<statesList.size();i++) {
            statesList.get(i).setVisibility(View.INVISIBLE);
        }
        for(int i=0; i<numStates; i++) {
            statesList.get(i).setVisibility(View.VISIBLE);
        }
    }

    //methods to initialize arrays once we have the needed information
    public void createArray() {
        transitions = new String[numStates][langSize+1];
    }

    public void createAcceptArray() {
        acceptStates = new int[numStates];
    }


}
