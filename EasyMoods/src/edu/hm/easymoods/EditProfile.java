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

public class EditProfile extends Activity{
	private ListView preview = (ListView)findViewById(R.id.profilePreview);
	private TextView redValue = (TextView)findViewById(R.id.profileRVal);
	private TextView greenValue = (TextView)findViewById(R.id.profileGVal);
	private TextView blueValue = (TextView)findViewById(R.id.profileBVal);
	private TextView dimValue = (TextView)findViewById(R.id.profileDVal);
	private EditText title = (EditText)findViewById(R.id.profileTitle);
	private EditText desc = (EditText)findViewById(R.id.profileDesc);
	private SeekBar seekBarRed = (SeekBar)findViewById(R.id.profileSeekRed);
	private SeekBar seekBarGreen = (SeekBar)findViewById(R.id.profileSeekGreen);
	private SeekBar seekBarBlue = (SeekBar)findViewById(R.id.profileSeekBlue);
	private SeekBar  seekBarDim = (SeekBar)findViewById(R.id.profileSeekDim);
	private ProfileManager profileMan = ProfileManager.getInstance(this);
	private int profileRed;
	private int profileGreen;
	private int profileBlue;
	private int profileDim;
	private int profileScent;
	private String profileTitle;
	private String profileDesc;
	private Button button;
	private int profileId;

	public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.modifyprofile);
	        
	        TextView caption = (TextView)findViewById(R.id.profileCaption);
	        caption.setText("EDIT");
	        
	        // rote SeekBar auswahlen und ein onChange-Listener fuer ihm erzeugen
	        seekBarRed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
				public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
					redValue.setText( Integer.toString(progress) );
					updatePreview();
				}

				public void onStartTrackingTouch(SeekBar seekBar) { }
				public void onStopTrackingTouch(SeekBar seekBar) { }
	        });
	        
	        // gruene SeekBar auswahlen und ein onChange-Listener fuer ihm erzeugen
	        seekBarGreen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
				public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
					greenValue.setText( Integer.toString(progress) );
					updatePreview();
				}

				public void onStartTrackingTouch(SeekBar seekBar) { }
				public void onStopTrackingTouch(SeekBar seekBar) { }
	        });
	        
	        // blaue SeekBar auswahlen und ein onChange-Listener fuer ihm erzeugen
	        seekBarBlue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
				public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
					blueValue.setText( Integer.toString(progress) );
					updatePreview();
				}

				public void onStartTrackingTouch(SeekBar seekBar) { }
				public void onStopTrackingTouch(SeekBar seekBar) { }
	        });
	        
	        // dim SeekBar auswahlen und ein onChange-Listener fuer ihm erzeugen
	        seekBarDim.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
				public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
					dimValue.setText( Integer.toString(progress) );
					updatePreview();
				}

				public void onStartTrackingTouch(SeekBar seekBar) { }
				public void onStopTrackingTouch(SeekBar seekBar) { }
	        });
	        
	        title.setText(profileTitle);
	    	desc.setText(profileDesc);
	    	seekBarRed.setProgress(profileRed);
	    	seekBarGreen.setProgress(profileGreen);
	    	seekBarBlue.setProgress(profileBlue);
	    	seekBarDim.setProgress(profileDim);
	    	setScent(profileScent);
	        
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
					
					// first, we delete an old profile
					profileMan.deleteProfile(profileId);
					
					// then add its updated version
					profileMan.addProfile(Integer.parseInt(redValue.getText().toString()),
							Integer.parseInt(greenValue.getText().toString()),
							Integer.parseInt(blueValue.getText().toString()),
							Integer.parseInt(dimValue.getText().toString()),
							scentId, title.getText().toString(), desc.getText().toString());
					finish();
				}
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
	    
	    public void setScent(int sId){
	    	RadioButton rb;
	    	switch(sId){
	    	case 1:
	    		rb = (RadioButton)findViewById(R.id.profileScentLemon);
	    		rb.setChecked(true);
	    		break;
	    	case 2:
	    		rb = (RadioButton)findViewById(R.id.profileScentFresh);
	    		rb.setChecked(true);
	    		break;
	    	case 0:
	    	default:
	    		rb = (RadioButton)findViewById(R.id.profileScentNone);
	    		rb.setChecked(true);
	    		break;
	    	}
	    }
	    
	    public void setProfileId(int profileId){
	    	this.profileId = profileId;
	    }

		public void setProfileMan(ProfileManager profileMan) {
			this.profileMan = profileMan;
		}

		public void setProfileRed(int profileRed) {
			this.profileRed = profileRed;
		}

		public void setProfileGreen(int profileGreen) {
			this.profileGreen = profileGreen;
		}

		public void setProfileBlue(int profileBlue) {
			this.profileBlue = profileBlue;
		}

		public void setProfileDim(int profileDim) {
			this.profileDim = profileDim;
		}

		public void setProfileScent(int profileScent) {
			this.profileScent = profileScent;
		}

		public void setProfileTitle(String profileTitle) {
			this.profileTitle = profileTitle;
		}

		public void setProfileDesc(String profileDesc) {
			this.profileDesc = profileDesc;
		}
	    
	    

}
