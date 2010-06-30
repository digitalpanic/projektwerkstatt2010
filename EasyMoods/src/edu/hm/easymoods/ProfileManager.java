package edu.hm.easymoods;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.util.Log;

public class ProfileManager {
	private final static String FILENAME = "profiles.txt";
	private static Context ctx;
	private static ProfileManager profileManInstance;

	private ProfileManager(){}
	
	public static ProfileManager getInstance(Context ctx) {
		if(profileManInstance == null){
			profileManInstance = new ProfileManager();
			profileManInstance.setContext(ctx);
		}
		
		return profileManInstance;
	}
	
	private void setContext(Context ctx){
		ProfileManager.ctx = ctx;
	}

	public List<String[]> readProfiles(List<String[]> profileList) {
		try {
			FileInputStream fin = ctx.openFileInput(FILENAME);
			BufferedReader bw = new BufferedReader(new InputStreamReader(fin));

			String row;
			for (row = bw.readLine(); row != null; row = bw.readLine()) {
				// parse row and add it into profileList
				if (row.length() != 0)
					profileList.add(row.split(","));
			}

			bw.close();

		} catch (FileNotFoundException e) {
			Log.d("ProfileMan", "File not found, try to create defaults");
			createDefaults();
		} catch (IOException e) {
			Log.d("ProfileMan", "IOException reading");
		}

		Log.d("ProfileMan", "Profiles read successfully");
		return profileList;
	}

	public void createDefaults() {
		// Profile.txt Structure
		// RED, GREEN, BLUE, DIM, SCENT_ID, NAME, DESCRIPTION

		ArrayList<String> defProfiles = new ArrayList<String>();
		defProfiles.add("255,83,255,206,0,Romantic,For your romantic evenings");
		defProfiles.add("0,0,255,255,0,Cool,Description");
		defProfiles.add("0,225,212,255,1,Summer,Feel the summer every day!");
		defProfiles.add("76,255,55,206,0,Meadow,Lay at your sofa and feel the nature");
		defProfiles.add("255,255,255,255,0,White nights,Let there be light!");

		try { // Write default profiles to file
			FileOutputStream fos = ctx.openFileOutput(FILENAME,
					Context.MODE_PRIVATE);

			PrintWriter pw = new PrintWriter(new OutputStreamWriter(fos));

			for (String each : defProfiles) {
				pw.println(each);
				pw.flush();
			}

			pw.close();
			Log.d("ProfileMan", "Default profiles created");
		} catch (IOException e) {
			Log.d("ProfileMan", "IOException writing");
		}
	}

	public void addProfile(int r, int g, int b, int d, int scent, String name,
			String desc) {
		try { // append new profile
			FileOutputStream fos = ctx.openFileOutput(FILENAME,
					Context.MODE_APPEND);

			PrintWriter pw = new PrintWriter(new OutputStreamWriter(fos));
			String newProfile = r + "," + g + "," + b + "," + d + "," + scent
					+ "," + name + "," + desc;

			pw.println(newProfile);
			pw.flush();

			pw.close();

		} catch (IOException e) {
			Log.d("ProfileMan", "IOException writing");
		}
	}

	public void deleteProfile(int line){
		StringBuilder updatedProfiles = new StringBuilder();
		
		try {
			// Read all except on a given line
			FileInputStream fin = ctx.openFileInput(FILENAME);
			BufferedReader bw = new BufferedReader(new InputStreamReader(fin));
			
			int i = 0;
			String row;
			for (row = bw.readLine(); row != null; row = bw.readLine()){
				if(line != i)
					updatedProfiles.append(row + "\n");
				
				i++;
			}
			
			bw.close();
			
			
			// rewrite profiles list
			FileOutputStream fos = ctx.openFileOutput(FILENAME,
					Context.MODE_PRIVATE);
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(fos));

			pw.println(updatedProfiles.toString());
			pw.flush();
			
			pw.close();
			
		}
		catch (FileNotFoundException e){
			Log.d("ProfileMan", "File not found");
		}catch (IOException e) { Log.d("ProfileMan","IOException deleting"); }
	}

}
