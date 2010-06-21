package edu.hm.easymoods;

import edu.hm.easymoods.R;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;

public class EasyMoodsStart extends TabActivity {

	private static final String TAG = "EasyMoods";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate()");
		setContentView(R.layout.main);

		Resources res = getResources(); // Resource object to get Drawables
		TabHost tabHost = getTabHost(); // The activity TabHost
		TabHost.TabSpec spec; // Resusable TabSpec for each tab
		Intent intent = null; // Reusable Intent for each tab

		// Profile Tab
		intent = new Intent().setClass(this, EasyMoods.class);
		spec = tabHost.newTabSpec("profile").setIndicator(
				getApplicationContext().getString(R.string.tab_profile),
				res.getDrawable(R.drawable.smalltiles)).setContent(intent);
		tabHost.addTab(spec);

		// Manuell Tab
		intent = new Intent().setClass(this, Profile.class);
		spec = tabHost.newTabSpec("manualcolor").setIndicator(
				getApplicationContext().getString(R.string.tab_easy_moods),
				res.getDrawable(R.drawable.equalizer)).setContent(intent);
		tabHost.addTab(spec);

		// Config Tab
		intent = new Intent().setClass(this, Config.class);
		spec = tabHost.newTabSpec("config").setIndicator(
				getApplicationContext().getString(R.string.tab_config),
				res.getDrawable(R.drawable.tag)).setContent(intent);
		tabHost.addTab(spec);

		// About Tab
		intent = new Intent().setClass(this, About.class);
		spec = tabHost.newTabSpec("about").setIndicator(
				getApplicationContext().getString(R.string.tab_about),
				res.getDrawable(R.drawable.seal)).setContent(intent);
		tabHost.addTab(spec);
	}
}