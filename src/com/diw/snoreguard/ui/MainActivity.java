package com.diw.snoreguard.ui;

import java.util.ArrayList;
import com.diw.snoreguard.R;
import com.diw.snoreguard.provider.AlarmDTO;
import com.diw.snoreguard.provider.SnoreguardDatabaseHelper;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View; 
import android.widget.TableRow;
  

public class MainActivity extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	}

	@Override
	protected void onStart() {
	// TODO Auto-generated method stub
		super.onStart();
		
				// Traer la lista de alarmas y 
				SnoreguardDatabaseHelper db = SnoreguardDatabaseHelper.getInstance(MainActivity.this);
				ArrayList<AlarmDTO> alarms = db.getAlarms();
				
		        //Obtenemos una referencia al Add Alarm y controlamos si se presiona. 

		        final TableRow firstRow = (TableRow) findViewById(R.id.tableRow1);
		        firstRow.setClickable(true);
		        firstRow.setBackgroundColor(Color.TRANSPARENT);
		        
		        firstRow.setOnClickListener(new View.OnClickListener() {
		               @Override
		                public void onClick(View v) {
		                    // TODO: do your logic here
		            	   firstRow.setBackgroundColor(Color.GREEN);

		            	   Intent intent = 
		                           new Intent(MainActivity.this, ConfigActivity.class); 
		            	   startActivity(intent);
		            	   overridePendingTransition(R.anim.fade,R.anim.hold); //Animation
		                }   
		        });
	

	}

	@Override
	protected void onRestart() {
	// TODO Auto-generated method stub
	super.onRestart();
	}
	
	@Override
	protected void onStop() {
	// TODO Auto-generated method stub
	super.onStop();

	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		super.onCreateOptionsMenu(menu);
		return true;
	}
	

}
