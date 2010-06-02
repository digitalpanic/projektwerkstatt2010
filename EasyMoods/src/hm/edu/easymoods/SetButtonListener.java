package hm.edu.easymoods;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.EditText;

public class SetButtonListener implements View.OnClickListener {
	public Context ctx;
	public EditText redValue;
	public EditText greenValue;
	public EditText blueValue;
	public EditText dimValue;
	
	public void onClick(View v) {
	
		//TODO: Signale an der LED-Leiste uebergeben
		
		AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
		builder.setTitle("R = " + getRed() + " " +
						 "G = " + getGreen() + " " +
						 "B = " + getBlue() + " " +
						 "Dim = " + getDim());
		
		builder.setPositiveButton("OK", null);
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
