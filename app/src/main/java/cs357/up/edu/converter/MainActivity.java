package cs357.up.edu.converter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * @author Alexa Baldwin
 * @version 4/17/16
 */

public class MainActivity extends Activity implements CheckBox.OnCheckedChangeListener,
View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    protected TextView numTextView;
    protected TextView directions;
    protected Button nextPage;
    protected SeekBar numStatesSeekBar;
    protected TextView numLabel;
    static Converter converter = new Converter(); //converter object that will be used throughout


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize GUI objects
        numTextView = (TextView)findViewById(R.id.numStatesTextView);
        directions = (TextView)findViewById(R.id.directions);
        numLabel = (TextView)findViewById(R.id.numLabel);

        addStates();
        numStatesSeekBar = (SeekBar)findViewById(R.id.numStatesSeekBar);
        numStatesSeekBar.setOnSeekBarChangeListener(this);

        nextPage = (Button)findViewById(R.id.nextButton);
        nextPage.setOnClickListener(this);

        //set listeners
        for(int i=0;i<converter.statesList.size();i++) {
            converter.statesList.get(i).setOnCheckedChangeListener(this);
        }
    }

    @Override
    //if a checkbox is pressed on the start screen, this will make it an accept state
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        for (int i=0; i<converter.acceptStates.length; i++) {
            if(converter.statesList.get(i).getId() == buttonView.getId()) {
                converter.acceptStates[i]=1;
                break;
            }
            else {
                if (converter.acceptStates[i] != 1) {
                    converter.acceptStates[i] = 0;
                }
            }
        }
    }


    @Override
    //changes activity to the next when the next button is clicked
    public void onClick(View v) {
        if(v.getId() == R.id.nextButton) {
            for (int i=0; i<converter.statesList.size();i++) {
                converter.statesList.get(i).setChecked(false);
            }
            Intent intent = new Intent(this,LangLayout.class);
            startActivity(intent);
        }
    }

    @Override
    //uses seekbar progress to get the number of states in the NFA
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (seekBar.getId() == R.id.numStatesSeekBar && fromUser) {
            numTextView.setText(""+(progress+1));
            converter.numStates = (progress+1);
            converter.setVisible();
            converter.createAcceptArray();
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        //not used
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        //not used
    }

    //method to add all 50 states to the statesList
    public void addStates() {
        converter.statesList.add(0,(CheckBox)findViewById(R.id.state1));
        converter.statesList.add(1,(CheckBox)findViewById(R.id.state2));
        converter.statesList.add(2,(CheckBox)findViewById(R.id.state3));
        converter.statesList.add(3,(CheckBox)findViewById(R.id.state4));
        converter.statesList.add(4,(CheckBox)findViewById(R.id.state5));
        converter.statesList.add(5,(CheckBox)findViewById(R.id.state6));
        converter.statesList.add(6,(CheckBox)findViewById(R.id.state7));
        converter.statesList.add(7,(CheckBox)findViewById(R.id.state8));
        converter.statesList.add(8,(CheckBox)findViewById(R.id.state9));
        converter.statesList.add(9,(CheckBox)findViewById(R.id.state10));
        converter.statesList.add(10,(CheckBox)findViewById(R.id.state11));
        converter.statesList.add(11,(CheckBox)findViewById(R.id.state12));
        converter.statesList.add(12,(CheckBox)findViewById(R.id.state13));
        converter.statesList.add(13,(CheckBox)findViewById(R.id.state14));
        converter.statesList.add(14,(CheckBox)findViewById(R.id.state15));
        converter.statesList.add(15,(CheckBox)findViewById(R.id.state16));
        converter.statesList.add(16,(CheckBox)findViewById(R.id.state17));
        converter.statesList.add(17,(CheckBox)findViewById(R.id.state18));
        converter.statesList.add(18,(CheckBox)findViewById(R.id.state19));
        converter.statesList.add(19,(CheckBox)findViewById(R.id.state20));
        converter.statesList.add(20,(CheckBox)findViewById(R.id.state21));
        converter.statesList.add(21,(CheckBox)findViewById(R.id.state22));
        converter.statesList.add(22,(CheckBox)findViewById(R.id.state23));
        converter.statesList.add(23,(CheckBox)findViewById(R.id.state24));
        converter.statesList.add(24,(CheckBox)findViewById(R.id.state25));
        converter.statesList.add(25,(CheckBox)findViewById(R.id.state26));
        converter.statesList.add(26,(CheckBox)findViewById(R.id.state27));
        converter.statesList.add(27,(CheckBox)findViewById(R.id.state28));
        converter.statesList.add(28,(CheckBox)findViewById(R.id.state29));
        converter.statesList.add(29,(CheckBox)findViewById(R.id.state30));
        converter.statesList.add(30,(CheckBox)findViewById(R.id.state31));
        converter.statesList.add(31,(CheckBox)findViewById(R.id.state32));
        converter.statesList.add(32,(CheckBox)findViewById(R.id.state33));
        converter.statesList.add(33,(CheckBox)findViewById(R.id.state34));
        converter.statesList.add(34,(CheckBox)findViewById(R.id.state35));
        converter.statesList.add(35,(CheckBox)findViewById(R.id.state36));
        converter.statesList.add(36,(CheckBox)findViewById(R.id.state37));
        converter.statesList.add(37,(CheckBox)findViewById(R.id.state38));
        converter.statesList.add(38,(CheckBox)findViewById(R.id.state39));
        converter.statesList.add(39,(CheckBox)findViewById(R.id.state40));
        converter.statesList.add(40,(CheckBox)findViewById(R.id.state41));
        converter.statesList.add(41,(CheckBox)findViewById(R.id.state42));
        converter.statesList.add(42,(CheckBox)findViewById(R.id.state43));
        converter.statesList.add(43,(CheckBox)findViewById(R.id.state44));
        converter.statesList.add(44,(CheckBox)findViewById(R.id.state45));
        converter.statesList.add(45,(CheckBox)findViewById(R.id.state46));
        converter.statesList.add(46,(CheckBox)findViewById(R.id.state47));
        converter.statesList.add(47,(CheckBox)findViewById(R.id.state48));
        converter.statesList.add(48,(CheckBox)findViewById(R.id.state49));
        converter.statesList.add(49,(CheckBox)findViewById(R.id.state50));
    }
}
