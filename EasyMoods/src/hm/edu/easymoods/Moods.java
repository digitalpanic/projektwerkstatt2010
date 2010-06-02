package hm.edu.easymoods;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

public class Moods extends Activity {
	public Button setColorButton;
	public SeekBar seekBar;
	public EditText redValue;
	public EditText greenValue;
	public EditText blueValue;
	public EditText dimValue;
	
    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // Listener fuer SetColorButton setzen
        setColorButton = (Button)findViewById(R.id.SetColorButton);
        SetButtonListener setButtonListener = new SetButtonListener();
        setButtonListener.ctx = this;
        setColorButton.setOnClickListener(setButtonListener);
        
        // EditTexts auswaehlen
        redValue = (EditText)findViewById(R.id.redVal);     
        greenValue = (EditText)findViewById(R.id.greenVal);        
        blueValue = (EditText)findViewById(R.id.blueVal);     
        dimValue = (EditText)findViewById(R.id.dimVal);
        
        // EditTexts an der SetColorButton Listener uebergeben
        setButtonListener.redValue = redValue;     
        setButtonListener.greenValue = greenValue;        
        setButtonListener.blueValue = blueValue;     
        setButtonListener.dimValue = dimValue;
        
        // rote SeekBar auswahlen und ein onChange-Listener fuer ihm erzeugen
        seekBar = (SeekBar)findViewById(R.id.redSeekbar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				redValue.setText( Integer.toString(progress) );
			}

			public void onStartTrackingTouch(SeekBar seekBar) { }
			public void onStopTrackingTouch(SeekBar seekBar) { }
        });
        
        // gruene SeekBar auswahlen und ein onChange-Listener fuer ihm erzeugen
        seekBar = (SeekBar)findViewById(R.id.greenSeekbar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				greenValue.setText( Integer.toString(progress) );
			}

			public void onStartTrackingTouch(SeekBar seekBar) { }
			public void onStopTrackingTouch(SeekBar seekBar) { }
        });
        
        // blaue SeekBar auswahlen und ein onChange-Listener fuer ihm erzeugen
        seekBar = (SeekBar)findViewById(R.id.blueSeekbar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				blueValue.setText( Integer.toString(progress) );
			}

			public void onStartTrackingTouch(SeekBar seekBar) { }
			public void onStopTrackingTouch(SeekBar seekBar) { }
        });
        
        // dim SeekBar auswahlen und ein onChange-Listener fuer ihm erzeugen
        seekBar = (SeekBar)findViewById(R.id.dimSeekbar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				dimValue.setText( Integer.toString(progress) );
			}

			public void onStartTrackingTouch(SeekBar seekBar) { }
			public void onStopTrackingTouch(SeekBar seekBar) { }
        });


    }
}