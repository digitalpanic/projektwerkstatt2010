package hm.edu.easymoods;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;

public class DuftButtonListener implements View.OnClickListener {
	public Context ctx;
	public String ipAddr;

	public void onClick(View v) {
		// TODO Signale an die Platine ebergeben
		
		AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
		builder.setTitle("Duft! Button is pressed!");
		
		builder.setPositiveButton("OK", null);
		AlertDialog ad = builder.create();
		ad.show();
	
	}

}
