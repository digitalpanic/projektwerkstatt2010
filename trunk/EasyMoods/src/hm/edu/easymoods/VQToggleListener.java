package hm.edu.easymoods;

import android.view.View;
import android.widget.ToggleButton;

public class VQToggleListener implements View.OnClickListener {
	public void onClick(View v) {
		// TODO Bearbeitung von Mikrofon Signale
		
		
		if (((ToggleButton)v).isChecked()) {
            // EIN
        } else {
            // AUS
        }
	}
}
