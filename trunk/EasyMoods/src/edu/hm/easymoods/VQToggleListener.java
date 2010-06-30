package edu.hm.easymoods;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.util.Log;
import android.view.View;
import android.widget.ToggleButton;

public class VQToggleListener implements View.OnClickListener, Runnable {
	Thread VisualEq;
	int frequency = 8000;
	int channelConfiguration = AudioFormat.CHANNEL_CONFIGURATION_MONO;
	int audioEncoding = AudioFormat.ENCODING_PCM_16BIT;
	int bufferSize = 100000;
	AudioRecord audioRecord;
	boolean isRecording;
	
	
	public void onClick(View v) {
			
		if (((ToggleButton)v).isChecked()) {	// EIN
			isRecording = true;
            VisualEq = new Thread(this);
            VisualEq.start();	// start a new thread based on this class
            
        } else {	// AUS
        	isRecording = false;
        	audioRecord.stop();
            VisualEq.interrupt();
        }
	}

	@Override
	public void run(){
		// Init AudioRecord and buffer
		audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC,
	            frequency, channelConfiguration,
	            audioEncoding, bufferSize);
		
	    short[] buffer = new short[bufferSize];
	    audioRecord.startRecording();
		
		while (isRecording){	// log all audio-samples
			int bufferReadResult = audioRecord.read(buffer, 0, bufferSize);
			Log.e("VBUFF", "bufsize: " + bufferReadResult);
			for (int i = 0; i < bufferReadResult; i++)
		        Log.e("VEQ", "val: " + buffer[i]);
			
//			try {
//				Thread.sleep(500);
//			}catch (InterruptedException e) { }
			
		}
	}
}
