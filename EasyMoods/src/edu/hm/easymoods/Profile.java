package edu.hm.easymoods;


import java.util.ArrayList;
import java.io.*;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class Profile extends Activity{
	private ArrayList<String[]> profileList = new ArrayList<String[]>();
	private final SetAVR avr = new SetAVR("192.168.1.90");
	private String FILENAME = "profiles.txt";


	public boolean onCreateOptionsMenu(Menu menu){
    	menu.add(0,1,0,"Add Profile");
    	menu.add(0,2,0,"Edit Profile");
    	menu.add(0,3,0,"Delete Profile");
    	return true;
    }
    
	public boolean onOptionsItemSelected (MenuItem item){
    	switch (item.getItemId()){
	    	case 1:
	    	/* Actions in case that Add Profile is pressed */
	    	return true;
	    	
	    	case 2:
	    	/* Actions in case that Edit Profile is pressed */
	    	return true;
	    	
	    	case 3: 
	    		
	    	/* Actions in case that Delete Profile is pressed */
	    }
    	
    	return false;
    }
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        
       //initCustomProfiles();
       Spinner profilesSpinner = (Spinner)findViewById(R.id.ProfilesSpinner);
       
       createDefaults();
       
       readProfiles();
       
       ArrayList<String> spinnerItems = new ArrayList<String>();
       
       for(String[] each : profileList)
    	   spinnerItems.add(each[5]);
       
       ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerItems);
       
       adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       profilesSpinner.setAdapter(adapter);
       
       
       profilesSpinner.setOnItemSelectedListener(
               new AdapterView.OnItemSelectedListener() {
                   public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                       String[] profile = profileList.get(position);
                       
                       // Load description
                       TextView profileDesc = (TextView)findViewById(R.id.profileDesc);
                       profileDesc.setText(profile[6]);
                       
                       // Send data to AVR
                       avr.setColor(Integer.parseInt(profile[0]), Integer.parseInt(profile[1]),
                    		   Integer.parseInt(profile[2]), Integer.parseInt(profile[3]),
                    		   StellaSetType.STELLA_SET_FADE);	// aus config auslesen
                   }

                   public void onNothingSelected(AdapterView<?> parent) {
                   }
               }
           );
    }
    
    private void createDefaults(){
    	// Profile structure
    	// RED, GREEN, BLUE, DIM, SCENT_ID, NAME, DESCRIPTION
    	
    	ArrayList<String> defProfiles = new ArrayList<String>();
    	defProfiles.add("255,83,255,206,0,Romantic,For your romantic evenings");
    	defProfiles.add("0,0,255,255,0,Cool,Description");
    	defProfiles.add("0,225,212,255,1,Summer,Feel the summer every day!");
    	defProfiles.add("76,255,55,206,0,Meadow,Lay at your sofa and feel the nature");
    	defProfiles.add("255,255,255,255,0,White nights,Let there be light!");
    	
		try {	// Write default profiles to file
			FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
			
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(fos));
			
			for(String each: defProfiles){
				pw.println(each);
				pw.flush();
			}
			
			pw.close();
    	
		} catch (IOException e) {}
    }

	
	private void readProfiles(){
		try {
			FileInputStream fin = openFileInput(FILENAME);
			BufferedReader bw = new BufferedReader(new InputStreamReader(fin));
			
			String row;
			for (row = bw.readLine(); row != null; row = bw.readLine()){
				// parse row and add it into profileList
				profileList.add( row.split(",") );
			}
			
			bw.close();
			
		}
		catch (FileNotFoundException e){
			Log.d("Profile","File not found, try to create defaults");
			createDefaults();
		}catch (IOException e) { Log.d("Profile","IOException"); }
		
	}

}