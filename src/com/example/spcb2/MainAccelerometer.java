package com.example.spcb2;

import android.R.string;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainAccelerometer extends Activity implements AccelerometerListener {
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accelerometer_example_main);
        TextView score = (TextView) findViewById(R.id.jumlahPukulan);
        score.setVisibility(View.GONE);
        
        TextView angkaScore = (TextView) findViewById(R.id.angkaScore);
        angkaScore.setVisibility(View.GONE);
        // Check onResume Method to start accelerometer listener
    }
    
    public void onAccelerationChanged(float x, float y, float z) {
        // TODO Auto-generated method stub
         
    }
 
    public void onShake(float force) {
         
        // Do your stuff here
         TextView scoreVisible = (TextView) findViewById(R.id.jumlahPukulan);
         scoreVisible.setVisibility(View.VISIBLE);
         
         TextView angkaScoreVisible = (TextView) findViewById(R.id.angkaScore);
         angkaScoreVisible.setVisibility(View.VISIBLE);
         
         String stringForce = Float.toString(force);
         angkaScoreVisible.setText(stringForce);
    	
        // Called when Motion Detected
        //Toast.makeText(getBaseContext(), "Motion detected", 
          //      Toast.LENGTH_SHORT).show();
         
    }
 
    @Override
    public void onResume() {
            super.onResume();
            Toast.makeText(getBaseContext(), "onResume Accelerometer Started", 
                    Toast.LENGTH_SHORT).show();
             
            //Check device supported Accelerometer sensor or not
            if (AccelerometerManager.isSupported(this)) {
                 
                //Start Accelerometer Listening
                AccelerometerManager.startListening(this);
            }
    }
     
    @Override
    public void onStop() {
            super.onStop();
             
            //Check device supported Accelerometer sensor or not
            if (AccelerometerManager.isListening()) {
                 
                //Start Accelerometer Listening
                AccelerometerManager.stopListening();
                 
                Toast.makeText(getBaseContext(), "onStop Accelerometer Stoped", 
                         Toast.LENGTH_SHORT).show();
            }
            
    }
     
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("Sensor", "Service  distroy");
         
        //Check device supported Accelerometer sensor or not
        if (AccelerometerManager.isListening()) {
             
            //Start Accelerometer Listening
            AccelerometerManager.stopListening();
             
            Toast.makeText(getBaseContext(), "onDestroy Accelerometer Stoped", 
                   Toast.LENGTH_SHORT).show();
        }
             
    }
}
