package com.example.spcb2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Survival_Mode extends Activity implements AccelerometerListener {
	public static int jmlPukulan=0;
	public static float jmlKalori=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_survival__mode);
		
		TextView angkaPukulan = (TextView) findViewById(R.id.angkaPukulan);
		String stringPukulan = Integer.toString(jmlPukulan);
		angkaPukulan.setText(stringPukulan);
		
		TextView angkaKalori = (TextView) findViewById(R.id.angkaKalori);
		String stringKalori = Float.toString(jmlKalori);
		angkaPukulan.setText(stringKalori);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.survival__mode, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void onAccelerationChanged(float x, float y, float z) {
        // TODO Auto-generated method stub
         
    }
 
    public void onShake(float force) {
        jmlPukulan = jmlPukulan+1;
        float kaloriPerPukulan = force*30;
        jmlKalori = jmlKalori + kaloriPerPukulan;
        
        TextView angkaPukulan = (TextView) findViewById(R.id.angkaPukulan);
		String stringPukulan = Integer.toString(jmlPukulan);
		angkaPukulan.setText(stringPukulan);
		
		TextView angkaKalori = (TextView) findViewById(R.id.angkaKalori);
		String stringKalori = Float.toString(jmlKalori);
		angkaKalori.setText(stringKalori);
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
