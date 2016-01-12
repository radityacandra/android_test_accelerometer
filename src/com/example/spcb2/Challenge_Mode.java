package com.example.spcb2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class Challenge_Mode extends Activity implements AccelerometerListener {
	
	public static int jmlPukulan;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_challenge__mode);
		boolean status = WaktuPukul();
		if (status == true){
			//Intent intent = new Intent(this, Main_Menu.class);
			//startActivity(intent);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.challenge__mode, menu);
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
	
	public boolean WaktuPukul(){
		float jmlWaktu = 60;
		float waktuSekarang = SystemClock.currentThreadTimeMillis()/100;
		float masihJalan = jmlWaktu - waktuSekarang;
		masihJalan = 2;
		int sisaPukulan=200;
		while(sisaPukulan>=0){
			//Check device supported Accelerometer sensor or not
            if (AccelerometerManager.isSupported(this)) {
                 
                //Start Accelerometer Listening
                AccelerometerManager.startListening(this);
            }
            
            sisaPukulan = sisaPukulan-jmlPukulan;
            
            TextView sisaPukulanUI = (TextView) findViewById(R.id.sisaPukulan);
    		String stringPukulan = Integer.toString(sisaPukulan);
    		sisaPukulanUI.setText(stringPukulan);
			
    		TextView sisaWaktu = (TextView) findViewById(R.id.sisaWaktu);
    		String stringWaktu = Float.toString(masihJalan);
    		sisaWaktu.setText(stringWaktu);
    		
			//masihJalan = jmlWaktu - waktuSekarang;
    		masihJalan--;
		}
		return true;
	}
	
	public void onAccelerationChanged(float x, float y, float z) {
        // TODO Auto-generated method stub
         
    }
 
    public void onShake(float force) {
        jmlPukulan = jmlPukulan+1;
        
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
