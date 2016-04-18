package cs357.up.edu.converter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by alexabaldwin on 3/8/16.
 * @author Alexa Baldwin
 * @version 4/17/16
 */
public class LangLayout extends Activity implements View.OnClickListener,
        SeekBar.OnSeekBarChangeListener {

    static Converter converter;
    protected TextView countTextView;
    protected Button nextPage;
    protected SeekBar langSizeSeekBar;
    protected Button nextSymbolButton;
    protected EditText symbol;
    protected int count = 1;
    protected TextView langMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lang_layout);

        //initialize GUI objects and converter object
        converter = MainActivity.converter;

        countTextView = (TextView)findViewById(R.id.countTextView);

        langSizeSeekBar = (SeekBar) findViewById(R.id.langSizeSeekBar);
        langSizeSeekBar.setOnSeekBarChangeListener(this);

        nextPage = (Button)findViewById(R.id.nextPageButton);
        nextPage.setOnClickListener(this);

        nextSymbolButton = (Button)findViewById(R.id.nextSymbolButton);
        nextSymbolButton.setOnClickListener(this);

        symbol = (EditText)findViewById(R.id.symbolEditText);
        langMessage = (TextView)findViewById(R.id.langMessage);
        converter.langSize = 1;

    }

    @Override
    //uses seekbar to determine size of langauge
    //seek bar no longer moves once all elements have been entered
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (seekBar.getId() == R.id.langSizeSeekBar && fromUser) {
            if (count <= converter.langSize) {
                countTextView.setText("" + (progress + 1));
                if(converter.language.size() !=0) {
                    for (int i = converter.langSize - 1; i >= 0; i--) {
                        if (converter.language.get(i) != null) {
                            converter.language.remove(converter.language.get(i));
                        }
                    }
                }
                converter.langSize = (progress + 1);
                langMessage.setText("Please enter element #1 of the language: ");
            }
            else {
                seekBar.setClickable(false);
            }
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

    @Override
    //displays difference messages depending on which language element is being recorded
    public void onClick(View v) {
        if(v.getId() == R.id.nextSymbolButton) {
            count++;
            if(count>converter.langSize) {
                //once complete, you can no longer add elements to the language
                converter.language.add(symbol.getText().toString().charAt(0));
                nextSymbolButton.setClickable(false);
                nextSymbolButton.setTextColor(Color.GRAY);
                symbol.setText("");
                langMessage.setText("Language complete. Hit DONE to continue.");
            }
            else {
                //only uses first character of input on textedit
                converter.language.add(symbol.getText().toString().charAt(0));
                symbol.setText("");
                langMessage.setText("Please enter element #" + count + " of the language");
            }
        }
        //changes to next activity
        if(v.getId() == R.id.nextPageButton) {
            if(converter.langSize>=count) {}
            else {
                Intent intent = new Intent(this,GraphSearchActivity.class);
                startActivity(intent);
            }
        }

    }

}
