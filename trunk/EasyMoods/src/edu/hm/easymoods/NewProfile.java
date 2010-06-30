package edu.hm.easymoods;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class NewProfile extends Activity{
	private ListView preview;
	private TextView redValue;
	private TextView greenValue;
	private TextView blueValue;
	private TextView dimValue;
	private EditText title;
	private EditText desc;
	private Button button;
	private SeekBar seekBar;
	private ProfileManager profileMan = ProfileManager.getInstance(this);

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modifyprofile);
        
        TextView caption = (TextView)findViewById(R.id.profileCaption);
        caption.setText("NEW");
        
        preview = (ListView)findViewById(R.id.profilePreview);
        redValue = (TextView)findViewById(R.id.profileRVal);     
        greenValue = (TextView)findViewById(R.id.profileGVal);        
        blueValue = (TextView)findViewById(R.id.profileBVal);     
        dimValue = (TextView)findViewById(R.id.profileDVal);
        
        title = (EditText)findViewById(R.id.profileTitle);
        desc = (EditText)findViewById(R.id.profileDesc);
        
        // rote SeekBar auswahlen und ein onChange-Listener fuer ihm erzeugen
        seekBar = (SeekBar)findViewById(R.id.profileSeekRed);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				redValue.setText( Integer.toString(progress) );
				updatePreview();
			}

			public void onStartTrackingTouch(SeekBar seekBar) { }
			public void onStopTrackingTouch(SeekBar seekBar) { }
        });
        
        // gruene SeekBar auswahlen und ein onChange-Listener fuer ihm erzeugen
        seekBar = (SeekBar)findViewById(R.id.profileSeekGreen);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				greenValue.setText( Integer.toString(progress) );
				updatePreview();
			}

			public void onStartTrackingTouch(SeekBar seekBar) { }
			public void onStopTrackingTouch(SeekBar seekBar) { }
        });
        
        // blaue SeekBar auswahlen und ein onChange-Listener fuer ihm erzeugen
        seekBar = (SeekBar)findViewById(R.id.profileSeekBlue);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				blueValue.setText( Integer.toString(progress) );
				updatePreview();
			}

			public void onStartTrackingTouch(SeekBar seekBar) { }
			public void onStopTrackingTouch(SeekBar seekBar) { }
        });
        
        // dim SeekBar auswahlen und ein onChange-Listener fuer ihm erzeugen
        seekBar = (SeekBar)findViewById(R.id.profileSeekDim);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				dimValue.setText( Integer.toString(progress) );
				updatePreview();
			}

			public void onStartTrackingTouch(SeekBar seekBar) { }
			public void onStopTrackingTouch(SeekBar seekBar) { }
        });
        
        button = (Button)findViewById(R.id.profileCancel);
        button.setOnClickListener(new View.OnClickListener() {
			
			@Override public void onClick(View v) {
				finish();
			}
		});
        
        button = (Button)findViewById(R.id.profileDone);
        button.setOnClickListener(new View.OnClickListener() {
			
			@Override public void onClick(View v) {
				int scentId = getScentId();
				
				profileMan.addProfile(Integer.parseInt(redValue.getText().toString()),
						Integer.parseInt(greenValue.getText().toString()),
						Integer.parseInt(blueValue.getText().toString()),
						Integer.parseInt(dimValue.getText().toString()),
						scentId, title.getText().toString(), desc.getText().toString());
				finish();
			}
		});
        
    }
    
    private int getScentId(){
    	RadioButton rb = (RadioButton)findViewById(R.id.profileScentNone);
    	
    	if(rb.isChecked()){
    		return 0;
    	}else{
    		rb = (RadioButton)findViewById(R.id.profileScentLemon);
    		if (rb.isChecked()){
    			return 1;
    		}else{
    			return 2;
    		}
    	}
     }
    
    
    // preview Farbe berechnen
    public void updatePreview(){
    	int redVal = Integer.parseInt( redValue.getText().toString() );
    	int greenVal = Integer.parseInt( greenValue.getText().toString() );
    	int blueVal = Integer.parseInt( blueValue.getText().toString() );
    	int dimVal = Integer.parseInt( dimValue.getText().toString() );    	
    	
    	// es gibt keinen unsigned int, aber man muss unbedigt alle 4-int bytes setzen.
    	// das ist das beste was ich gefunden hab, um zB. 0xffffffff zu speichern
    	long buffer = ((long) (dimVal << 24 
    								| redVal << 16
    								| greenVal << 8
    								| blueVal))	& 0xFFFFFFFFL;
    	
    	int previewColor = (int)buffer;
    	
    	preview.setBackgroundColor(previewColor);		// Farbe uebernehmen
    }
    
}
