package hm.edu.easymoods;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

public class EasyMoods extends Activity {
	public ToggleButton vqToggle;
	public Button setColorButton;
	public Button duftButton;
	public SeekBar seekBar;
	public EditText redValue;
	public EditText greenValue;
	public EditText blueValue;
	public EditText dimValue;
	public TextView previewHex;
	public View preview;
	
    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main);
        setContentView(R.layout.manuelcolor);
        
        // Preview und PreviewHex auswaehlen
        preview = (View)findViewById(R.id.preview);
        previewHex = (TextView)findViewById(R.id.previewHex);
       
        // Listener fuer vqToggle setzen
        vqToggle = (ToggleButton)findViewById(R.id.VisualEqToggle);
        VQToggleListener vtl = new VQToggleListener();
        vqToggle.setOnClickListener(vtl);
        
        // Listener fuer duftButton setzen und Context und ip-adresse uebergeben
        duftButton = (Button)findViewById(R.id.duftButton);
        DuftButtonListener dbl = new DuftButtonListener();
        duftButton.setOnClickListener(dbl);
        dbl.ctx = this;
        dbl.ipAddr = getString(R.string.ip_addr);
        
        // Listener fuer SetColorButton setzen und Context und ip-adresse uebergeben
        setColorButton = (Button)findViewById(R.id.SetColorButton);
        SetColorButtonListener sbl = new SetColorButtonListener();
        setColorButton.setOnClickListener(sbl);
        sbl.ctx = this;
        sbl.ipAddr = getString(R.string.ip_addr);
        
        // EditTexts auswaehlen
        redValue = (EditText)findViewById(R.id.redVal);     
        greenValue = (EditText)findViewById(R.id.greenVal);        
        blueValue = (EditText)findViewById(R.id.blueVal);     
        dimValue = (EditText)findViewById(R.id.dimVal);
        
        // EditTexts an der SetColorButton Listener uebergeben
        sbl.redValue = redValue;     
        sbl.greenValue = greenValue;        
        sbl.blueValue = blueValue;     
        sbl.dimValue = dimValue;
        
        // rote SeekBar auswahlen und ein onChange-Listener fuer ihm erzeugen
        seekBar = (SeekBar)findViewById(R.id.redSeekbar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				redValue.setText( Integer.toString(progress) );
				updatePreview();
			}

			public void onStartTrackingTouch(SeekBar seekBar) { }
			public void onStopTrackingTouch(SeekBar seekBar) { }
        });
        
        // gruene SeekBar auswahlen und ein onChange-Listener fuer ihm erzeugen
        seekBar = (SeekBar)findViewById(R.id.greenSeekbar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				greenValue.setText( Integer.toString(progress) );
				updatePreview();
			}

			public void onStartTrackingTouch(SeekBar seekBar) { }
			public void onStopTrackingTouch(SeekBar seekBar) { }
        });
        
        // blaue SeekBar auswahlen und ein onChange-Listener fuer ihm erzeugen
        seekBar = (SeekBar)findViewById(R.id.blueSeekbar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				blueValue.setText( Integer.toString(progress) );
				updatePreview();
			}

			public void onStartTrackingTouch(SeekBar seekBar) { }
			public void onStopTrackingTouch(SeekBar seekBar) { }
        });
        
        // dim SeekBar auswahlen und ein onChange-Listener fuer ihm erzeugen
        seekBar = (SeekBar)findViewById(R.id.dimSeekbar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				dimValue.setText( Integer.toString(progress) );
				updatePreview();
			}

			public void onStartTrackingTouch(SeekBar seekBar) { }
			public void onStopTrackingTouch(SeekBar seekBar) { }
        });
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
    	previewHex.setText(Integer.toHexString(previewColor));	// Farbe-Hex ausgeben
    }
}