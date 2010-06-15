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
		final SetAVR avr = new SetAVR(ctx.getString(R.string.ip_addr));
		avr.setColor(getRed(), getGreen(), getBlue(), getDim(), StellaSetType.STELLA_SET_FADE);
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
