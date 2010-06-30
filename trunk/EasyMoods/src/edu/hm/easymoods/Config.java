package edu.hm.easymoods;

import edu.hm.easymoods.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class Config extends Activity{
	
	private EditText ipAddress;
	private RadioButton isSet;
	private RadioButton isFade;
	private String isSelected;
	private Button apply;
	private ConfigData configData;
	
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config);
        
     
        configData = ConfigData.getConfigDataInstance();
      
        
        
        ipAddress = (EditText)findViewById(R.id.IPAddress);
        ipAddress.setText(getString(R.string.ip_addr));
        
        String stellaType = getString(R.string.stellaSetType);
        
        isSet = (RadioButton)findViewById(R.id.IsSet);
        isSet.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				isSelected = "isSet";
				
			}
        });
        
        isFade = (RadioButton)findViewById(R.id.IsFade);
        isFade.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				isSelected = "isFade";
				
			}
        });
        
        
        if (stellaType.compareTo("immediate") == 0) {
        	isSet.performClick();
        } else {
        	isFade.performClick();
        }
        
        
        apply = (Button)findViewById(R.id.ApplyConfigButton);
        apply.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (ipAddress.getText().toString() != null)
					configData.setIpAddress(ipAddress.getText().toString());
				
				if (isSelected.compareTo("isSet") == 0) {
					configData.setType(StellaSetType.STELLA_SET_IMMEDIATELY);
				} else {
					configData.setType(StellaSetType.STELLA_SET_FADE);
				}
				
			}
        });
    }
}
