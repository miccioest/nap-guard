package com.diw.snoreguard.ui;

import java.util.Calendar;

import com.diw.snoreguard.R;
import com.diw.snoreguard.provider.AlarmDTO;
import com.diw.snoreguard.provider.SnoreguardDatabaseHelper;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle; 
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class ConfigActivity extends Activity {
	    
		private static final String TAG = "ConfigActivity";
        
		//Variables del timePicker
		private TextView displayTime;
		private int pHour;
	    private int pMinute;
	    /** This integer will uniquely define the dialog to be used for displaying time picker.*/
	    static final int TIME_DIALOG_ID = 0;

	    // Métodos usados en el TimePicker -------------------------------------------------------
	    /** Callback received when the user "picks" a time in the dialog */
	    private TimePickerDialog.OnTimeSetListener mTimeSetListener =
	        new TimePickerDialog.OnTimeSetListener() {
	            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
	                pHour = hourOfDay;
	                pMinute = minute;
	                updateDisplay();
	                displayToast();
	            }
	        };
	     
	    /** Updates the time in the TextView */
	    private void updateDisplay() {
	        displayTime.setText(
	            new StringBuilder()
	                    .append(pad(pHour)).append(":")
	                    .append(pad(pMinute)));
	    }
	     
	    /** Displays a notification when the time is updated */
	    private void displayToast() {
	        Toast.makeText(this, new StringBuilder().append("Time choosen is ").append(displayTime.getText()),   Toast.LENGTH_SHORT).show();
	             
	    }
	     
	    /** Add padding to numbers less than ten */
	    private static String pad(int c) {
	        if (c >= 10)
	            return String.valueOf(c);
	        else
	            return "0" + String.valueOf(c);
	    }
	    // Fin Métodos TimePicker---------------------------------------------------------------
		
		@Override
	    protected void onCreate(Bundle savedInstanceState) { 
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_config);
     	   
	        //Activate the item Home to work as an additional item
	        ActionBar actionBar = getActionBar();
	        actionBar.setDisplayHomeAsUpEnabled(true);
	        
	       } 
	    
		// Method to create each of the items of ActionBar
	    public void CreaMenu(Menu menu) {
			MenuItem item1 = menu.add(0, 0, 0, "Item 1");
			{
				item1.setIcon(R.drawable.content_save);
				item1.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
			}
			
			MenuItem item2 = menu.add(0, 1, 1, "Item 2");
			{
				item2.setIcon(R.drawable.content_discard);
				item2.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
			}
			
		}
		
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			
			// Inflate the menu; this adds items to the action bar if it is present.
			//getMenuInflater().inflate(R.menu.main, menu);
			super.onCreateOptionsMenu(menu);
			CreaMenu(menu);
			return true;
		}
		
		// Method to know which item is clicked
		public boolean MenuSelecciona(MenuItem item) {
			switch (item.getItemId()) {
			
			case 0:
				//Save Alarm
				Toast.makeText(this, "You pressed the Item 1 of ActionBar", Toast.LENGTH_SHORT).show();
				
				AlarmDTO alarm = new AlarmDTO(0, true, "ringtone1", 10, 15, "10:25", true);
				SnoreguardDatabaseHelper db = SnoreguardDatabaseHelper.getInstance(ConfigActivity.this);
				db.addAlarm(alarm);
				
				return true;
			
			case 1:
				//Delete Alarm

				Toast.makeText(this, "You pressed the Item 2 of ActionBar", Toast.LENGTH_SHORT).show();

				return true;	
				
			case android.R.id.home:
				//Cancel Operation

				Intent intent = new Intent(this, MainActivity.class);            
		        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
		        startActivity(intent);

				Toast.makeText(this, "You pressed the Item 'Home' of ActionBar", Toast.LENGTH_SHORT).show();

				return true;	
			}
			return false;
			
		}
		
		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			return MenuSelecciona(item);
		}
		
		protected void onStart() {
			// TODO Auto-generated method stub
				super.onStart();
				
		        //Hacemos referencia al TimeDetail de la fila Time.
				displayTime = (TextView) findViewById(R.id.textViewTimeDetail);
				
				//Hacemos una referencia a la Fila de la tabla que contiene en Campo 'Time'
				final TableLayout tl = (TableLayout)findViewById(R.id.tableLayoutConfig);
				final TableRow trTime = (TableRow)tl.getChildAt(12);
		        
				trTime.setClickable(true);
		        Log.v(TAG, "Voy a la fila 'Time' ");
		        trTime.setBackgroundColor(Color.TRANSPARENT);
		        
		        trTime.setOnClickListener(new View.OnClickListener() {
		               @Override
		                public void onClick(View v) {
		                    // TODO: do your logic here
		            	   trTime.setBackgroundColor(Color.GRAY);
		            	   showDialog(TIME_DIALOG_ID);
		            	   overridePendingTransition(R.anim.fade,R.anim.hold); //Animation
		                }   
		        });
		        
		        /** Get the current time */
		        final Calendar cal = Calendar.getInstance();
		        pHour = cal.get(Calendar.HOUR_OF_DAY);
		        pMinute = cal.get(Calendar.MINUTE);
		        
		        /** Display the current time in the TextView */
		        updateDisplay();
						
		}	
		        
		@Override
	    protected Dialog onCreateDialog(int id) {
	        switch (id) {
	        case TIME_DIALOG_ID:
	            return new TimePickerDialog(this,
	                    mTimeSetListener, pHour, pMinute, false);
	        }
	        return null;
	    }

}
