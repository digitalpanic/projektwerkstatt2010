package hm.edu.easymoods;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class Moods extends Activity {
	
	Button setColorButton;	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // Listener fuer SetColorButton setzen
        setColorButton = (Button)findViewById(R.id.SetColorButton);
        SetButtonListener listener = new SetButtonListener();
        listener.ctx = this;
        setColorButton.setOnClickListener(listener);
        
    }
    
}