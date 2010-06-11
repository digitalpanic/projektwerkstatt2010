package hm.edu.easymoods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.ToggleButton;

public class Profile extends Activity{
	private HashMap<String, CustomProfile> profileList;
	private ArrayList<RadioButton> radioButtons;
	private RadioButton isFocused=null;
	private Button setProfile;
	private final SetAVR avr = new SetAVR("192.168.1.90");

	
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        
       initCustomProfiles();
       radioButtons = new ArrayList<RadioButton>();
       int[] widgets = new int[5];
       widgets[0] = R.id.widget91;
       widgets[1] = R.id.widget94;
       widgets[2] = R.id.widget112;
       widgets[3] = R.id.widget93;
       widgets[4] = R.id.widget92;
     
        ArrayList<CustomProfile> temp = new ArrayList<CustomProfile>();
        temp.addAll(profileList.values());
        
        for (int i =0; i<profileList.size(); i++) {
        final RadioButton button = (RadioButton)findViewById(widgets[i]);
        button.setText(temp.get(i).getName());
        radioButtons.add(button);
        button.setOnClickListener( new OnClickListener() {
			public void onClick(View v) {
				isFocused = button;
				
			}	
        });
        }
        
        // Listener fuer SetColorButton setzen und Context und ip-adresse uebergeben
        setProfile = (Button)findViewById(R.id.SetProfileButton);
        setProfile.setOnClickListener( new OnClickListener() {
        	public void onClick(View v) {
        		if (isFocused != null) {
        			CustomProfile profile = profileList.get(isFocused.getText());
        			avr.setColor(profile.getRValue(), profile.getGValue(), profile.getBValue(),
        				profile.getDimValue(), profile.getStellaSetType());
        		} else {
        			AlertDialog .Builder builder = new AlertDialog.Builder(v.getContext());
        			builder.setTitle("No Profile selected!");
        			builder.setCancelable(true);
        			builder.setNeutralButton("OK", null);
        			AlertDialog ad = builder.create();
        			ad.show();
        		}
        		
        	}
        });
      
        
        
        
        
    }

	private void initCustomProfiles() {
		this.profileList = new HashMap<String, CustomProfile>();
		
		CustomProfile romantic = new CustomProfile("Romantic");
		romantic.setColor(255, 83, 255, 206);
		romantic.setStellaSetType(StellaSetType.STELLA_SET_FADE);
		profileList.put("Romantic", romantic);
		
		CustomProfile cool = new CustomProfile("Cool");
		cool.setColor(0, 0, 255, 255);
		cool.setStellaSetType(StellaSetType.STELLA_SET_FADE);
		profileList.put("Cool", cool);
		
		CustomProfile summer = new CustomProfile("Summer Feeling");
		summer.setColor(0, 225, 212, 255);
		summer.setStellaSetType(StellaSetType.STELLA_SET_FADE);
		profileList.put("Summer Feeling", summer);
		
		CustomProfile meadow = new CustomProfile("Meadow");
		meadow.setColor(76, 255, 55, 206);
		meadow.setStellaSetType(StellaSetType.STELLA_SET_FADE);
		profileList.put("Meadow", meadow);
		
		CustomProfile neutral = new CustomProfile("Neutral");
		neutral.setColor(255, 255, 255, 255);
		neutral.setStellaSetType(StellaSetType.STELLA_SET_FADE);
		profileList.put("Neutral", neutral);
		
	}

}