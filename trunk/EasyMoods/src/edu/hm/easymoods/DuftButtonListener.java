package edu.hm.easymoods;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.View;

public class DuftButtonListener implements View.OnClickListener {
	public Context ctx;
	public String ipAddr;

	public void onClick(View v) {
		// TODO Signale an die Platine ebergeben
		final SetAVR avr = new SetAVR(ipAddr);
		AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
		builder.setTitle("Duft! Button is pressed!");
		
		builder.setPositiveButton("OK", new OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				avr.spray();
				
			}
		});
		AlertDialog ad = builder.create();
		ad.show();
	
	}

}
