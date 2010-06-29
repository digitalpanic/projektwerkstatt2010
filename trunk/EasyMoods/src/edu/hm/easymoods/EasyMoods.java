package edu.hm.easymoods;

import edu.hm.easymoods.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * 
 * @author Jevgeni, Franziska
 * 
 */
public class EasyMoods extends Activity {
	private ToggleButton vqToggle;
	private Button setColorButton;
	private Button duft1Button;
	private Button duft2Button;
	private SeekBar seekBar;
	private EditText redValue;
	private EditText greenValue;
	private EditText blueValue;
	private EditText dimValue;
	private TextView previewHex;
	private View preview;
	private SetAVR avr;
	private ConfigData config;

	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		config = ConfigData.getConfigDataInstance();
		config.setIpAddress(getString(R.string.ip_addr));

		avr = SetAVR.getAVRInstance();
		avr.setIPAddress(config.getIpAddress());

		setContentView(R.layout.manuelcolor);

		setStellaSetType(getString(R.string.stellaSetType));

		// Preview und PreviewHex auswaehlen
		preview = (View) findViewById(R.id.preview);
		previewHex = (TextView) findViewById(R.id.previewHex);

		// Listener fuer vqToggle setzen
		// vqToggle = (ToggleButton) findViewById(R.id.VisualEqToggle);
		//VQToggleListener vtl = new VQToggleListener();
		//vqToggle.setOnClickListener(vtl);

		// Listener fuer duftButton setzen und Context und ip-adresse uebergeben
		duft1Button = (Button) findViewById(R.id.DuftButton1);
		duft1Button.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				avr.spray(1);

			}

		});

		duft2Button = (Button) findViewById(R.id.DuftButton2);
		duft2Button.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				avr.spray(2);

			}

		});

		// EditTexts auswaehlen
		redValue = (EditText)findViewById(R.id.redVal);
		 greenValue = (EditText) findViewById(R.id.greenVal);
		 blueValue = (EditText) findViewById(R.id.blueVal);
		 dimValue = (EditText) findViewById(R.id.dimVal);

		// rote SeekBar auswahlen und ein onChange-Listener fuer ihm erzeugen
		seekBar = (SeekBar) findViewById(R.id.redSeekbar);
		seekBar
				.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
					public void onProgressChanged(SeekBar seekBar,
							int progress, boolean fromUser) {
						redValue.setText(Integer.toString(progress));
						updatePreview();
					}

					public void onStartTrackingTouch(SeekBar seekBar) {
					}

					public void onStopTrackingTouch(SeekBar seekBar) {
					}
				});

		// gruene SeekBar auswahlen und ein onChange-Listener fuer ihm erzeugen
		seekBar = (SeekBar) findViewById(R.id.greenSeekbar);
		seekBar
				.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
					public void onProgressChanged(SeekBar seekBar,
							int progress, boolean fromUser) {
						greenValue.setText(Integer.toString(progress));
						updatePreview();
					}

					public void onStartTrackingTouch(SeekBar seekBar) {
					}

					public void onStopTrackingTouch(SeekBar seekBar) {
					}
				});

		// blaue SeekBar auswahlen und ein onChange-Listener fuer ihm erzeugen
		seekBar = (SeekBar) findViewById(R.id.blueSeekbar);
		seekBar
				.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
					public void onProgressChanged(SeekBar seekBar,
							int progress, boolean fromUser) {
						blueValue.setText(Integer.toString(progress));
						updatePreview();
					}

					public void onStartTrackingTouch(SeekBar seekBar) {
					}

					public void onStopTrackingTouch(SeekBar seekBar) {
					}
				});

		// dim SeekBar auswahlen und ein onChange-Listener fuer ihm erzeugen
		seekBar = (SeekBar) findViewById(R.id.dimSeekbar);
		seekBar
				.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
					public void onProgressChanged(SeekBar seekBar,
							int progress, boolean fromUser) {
						dimValue.setText(Integer.toString(progress));
						updatePreview();
					}

					public void onStartTrackingTouch(SeekBar seekBar) {
					}

					public void onStopTrackingTouch(SeekBar seekBar) {
					}
				});
	}

	private void setStellaSetType(String stellaSetType) {

		if (stellaSetType.compareTo("immediate") == 0) {
			config.setType(StellaSetType.STELLA_SET_IMMEDIATELY);
		} else if (stellaSetType.compareTo("fade") == 0) {
			config.setType(StellaSetType.STELLA_SET_FADE);
		}

	}

	// preview Farbe berechnen
	public void updatePreview() {
		int redVal = Integer.parseInt(redValue.getText().toString());
		int greenVal = Integer.parseInt(greenValue.getText().toString());
		int blueVal = Integer.parseInt(blueValue.getText().toString());
		int dimVal = Integer.parseInt(dimValue.getText().toString());

		// es gibt keinen unsigned int, aber man muss unbedigt alle 4-int bytes
		// setzen.
		// das ist das beste was ich gefunden hab, um zB. 0xffffffff zu
		// speichern
		long buffer = ((long) (dimVal << 24 | redVal << 16 | greenVal << 8 | blueVal)) & 0xFFFFFFFFL;

		int previewColor = (int) buffer;

		preview.setBackgroundColor(previewColor); // Farbe uebernehmen
		previewHex.setText(Integer.toHexString(previewColor)); // Farbe-Hex
																// ausgeben

		// Farbeaenderung im real-time
		avr.setIPAddress(config.getIpAddress());
		avr.setColor(redVal, greenVal, blueVal, dimVal, config.getType());
	}
}