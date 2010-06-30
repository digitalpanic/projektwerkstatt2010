package edu.hm.easymoods;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends Activity{
	private ArrayList<String[]> profileList;
	private ArrayList<String> spinnerItems;
	private SetAVR avr;
	private Spinner profileSpinner;
	private ProfileManager profileMan = ProfileManager.getInstance(this);
	private int spinnerSelectedElement = 0;
	private ConfigData config = ConfigData.getConfigDataInstance();
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        
        
        Button refreshButton = (Button)findViewById(R.id.RefreshButton);
        refreshButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				onCreate(null);
			}
		});
        
        spinnerItems = new ArrayList<String>();
        
    	avr = SetAVR.getAVRInstance(); 
    	avr.setIPAddress(getString(R.string.ip_addr));
        
        profileSpinner = (Spinner)findViewById(R.id.ProfilesSpinner);
       
        profileList = new ArrayList<String[]>();
        profileMan.readProfiles(profileList);
       
       
        for(String[] each : profileList)
    	    spinnerItems.add(each[5]);
       
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerItems);
    
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        profileSpinner.setAdapter(adapter);
       
        profileSpinner.setOnItemSelectedListener(
        	new AdapterView.OnItemSelectedListener() {
            	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                	spinnerSelectedElement = position;
                 	String[] profile = profileList.get(position);
                       
                    // Load description
                    TextView profileDesc = (TextView)findViewById(R.id.profileDesc);
                    profileDesc.setText(profile[6]);
                       
                    // Send data to AVR
                    avr.setColor(Integer.parseInt(profile[0]), Integer.parseInt(profile[1]),
                    	Integer.parseInt(profile[2]), Integer.parseInt(profile[3]),
                    	config.getType());	// aus config auslesen
                }

            	public void onNothingSelected(AdapterView<?> parent) { }
        	}
    	);
    }
	
	public boolean onCreateOptionsMenu(Menu menu){
    	menu.add(0,1,0,"Add Profile");
//    	menu.add(0,2,0,"Edit Profile");
    	menu.add(0,2,0,"Delete Profile");
    	return true;
    }
    
    public boolean onOptionsItemSelected (MenuItem item){
    	switch (item.getItemId()){
	    	case 1:
	    		/* Actions in case New Profile is pressed */
	    		Intent newProfileIntent = new Intent(Profile.this, NewProfile.class);
	    	    startActivity(newProfileIntent);
	   
	    	    return true;
	    	    
//	    	EDIT BUTTON
//	    	case 2:
//	    		/* Actions in case Edit Profile is pressed */
////	    		String[] selectedItem = profileList.get(spinnerSelectedElement);
//	    		
//	    		
//	    		// load selected profile into the edit view
////	    		EditProfile editProfile = new EditProfile();
//	    		
//	    		Intent editProfileIntent = new Intent(Profile.this, EditProfile.class);
//	    		startActivity(editProfileIntent);
//	    		
//	    	   
////	    	    editProfile.setRedValue( Integer.parseInt(selectedItem[0]) );
////	    	    editProfile.setGreenValue( Integer.parseInt(selectedItem[1]) );
////	    	    editProfile.setBlueValue( Integer.parseInt(selectedItem[2]) );
////	    	    editProfile.setDimValue( Integer.parseInt(selectedItem[3]) );
////	    	    editProfile.setScent( Integer.parseInt(selectedItem[4]) );
//
//	    		
////	    		editProfile.setProfileTitle( selectedItem[5] );
////		    	editProfile.setProfileDesc( selectedItem[6] );
//	    	    
//	    		// we need this to delete an old (not updated) profile
////	    		editProfile.setProfileId(spinnerSelectedElement);
//	    	    
//	    	    return true;
//	    		
	    	case 2: 
	    		if(spinnerItems.size() <= 1){
		    		Context context = getApplicationContext();
		    		CharSequence text = "Can't delete last profile!";
		    		int duration = Toast.LENGTH_SHORT;
	
		    		Toast toast = Toast.makeText(context, text, duration);
		    		toast.show();
	    		}else{
	    		
		    		// delete profile
		    		profileMan.deleteProfile(spinnerSelectedElement);
		 
		    		// reload
		    		onCreate(null);
	    		}
	    		
	    		return true;
	    }
    	return false;
    }
	

}