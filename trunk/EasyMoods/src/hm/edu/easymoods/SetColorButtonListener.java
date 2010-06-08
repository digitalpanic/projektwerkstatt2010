package hm.edu.easymoods;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.View;
import android.widget.EditText;
import android.Manifest;

public class SetColorButtonListener implements View.OnClickListener {
	public Context ctx;
	public EditText redValue;
	public EditText greenValue;
	public EditText blueValue;
	public EditText dimValue;
	public String ipAddr;
	
	public void onClick(View v) {
		final SetAVR avr = new SetAVR("192.168.1.90");
		System.out.println("BBBBBBB");
		avr.setColor(getRed(), getGreen(), getBlue(), getDim());
		//TODO Signale an die Platine uebergeben
		
		AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
		builder.setTitle("R = " + getRed() + " " +
						 "G = " + getGreen() + " " +
						 "B = " + getBlue() + " " +
						 "Dim = " + getDim());
		
		
		builder.setPositiveButton("OK", new OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				System.out.println("AAAAA");
				avr.setColor(getRed(), getGreen(), getBlue(), getDim());
				
			}
		});

		AlertDialog ad = builder.create();
		ad.show();
	}
	
	
	public int getRed(){
		return Integer.parseInt( redValue.getText().toString() );
	}
	
	public int getGreen(){
		return Integer.parseInt( greenValue.getText().toString() );
	}
	
	public int getBlue(){
		return Integer.parseInt( blueValue.getText().toString() );
	}
	
	public int getDim(){
		return Integer.parseInt( dimValue.getText().toString() );
	}
}
