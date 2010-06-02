package hm.edu.easymoods;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Moods extends Activity {
	public Button setColorButton;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // Listener fuer SetColorButton setzen
        setColorButton = (Button)findViewById(R.id.SetColorButton);
        SetButtonListener setButtonListener = new SetButtonListener();
        setButtonListener.ctx = this;
        setColorButton.setOnClickListener(setButtonListener);
        
        setButtonListener.redValue = (EditText)findViewById(R.id.redVal);     
        setButtonListener.greenValue = (EditText)findViewById(R.id.greenVal);        
        setButtonListener.blueValue = (EditText)findViewById(R.id.blueVal);     
        setButtonListener.dimValue = (EditText)findViewById(R.id.dimVal);
        
    }
    
}