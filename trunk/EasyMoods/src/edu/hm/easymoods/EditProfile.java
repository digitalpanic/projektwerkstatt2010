package edu.hm.easymoods;

import java.io.*;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class EditProfile extends Activity{
	private ListView preview;
	private TextView redValue;
	private TextView greenValue;
	private TextView blueValue;
	private TextView dimValue;
	private EditText title;
	private EditText desc;
	private Button button;
	private SeekBar seekBar;
	private String FILENAME = "profiles.txt";
	
	
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.modifyprofile);
	        
	        TextView caption = (TextView)findViewById(R.id.profileCaption);
	        caption.setText("EDIT");
	 }


}
