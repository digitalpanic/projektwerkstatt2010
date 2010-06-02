package hm.edu.easymoods;

import android.app.AlertDialog;
import android.content.Context;
import android.view.*;

public class SetButtonListener implements View.OnClickListener {
	public Context ctx;
		
	public void onClick(View v) {
	
		//TODO: Signale an der LED-Leiste uebergeben
		
		AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
		builder.setTitle("R= " + getRed() + " " +
						 "G = " + getGreen() + " " +
						 "B = " + getBlue());
		
		builder.setPositiveButton("OK", null);
		AlertDialog ad = builder.create();
		ad.show();
	}
	
	
	// Die RGB-Werte werden vom SeekBars ausgelesen
	public int getRed(){
		return 100;
	}
	
	public int getGreen(){
		return 150;
	}
	
	public int getBlue(){
		return 200;
	}
	
	public int getDim(){
		return 100;
	}
}
