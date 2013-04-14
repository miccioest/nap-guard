package com.diw.snoreguard.ui;

import com.diw.snoreguard.R;
import com.diw.snoreguard.provider.AlarmDTO;
import com.diw.snoreguard.provider.SnoreguardDatabaseHelper;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle; 
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class ConfigActivity extends Activity {
	   
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
				item2.setIcon(R.drawable.content_remove);
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

}
