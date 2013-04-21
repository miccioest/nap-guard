package com.diw.snoreguard.ui;

import java.util.ArrayList;
import java.util.Iterator;

import com.diw.snoreguard.R;
import com.diw.snoreguard.provider.AlarmDTO;
import com.diw.snoreguard.provider.SnoreguardDatabaseHelper;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View; 
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.ToggleButton;
  

public class MainActivity extends Activity {

	private static final String TAG = "MainActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	}

	// Method to create each of the items of ActionBar
    public void CreaMenu(Menu menu) {
		MenuItem item1 = menu.add(0, 0, 0, "Item 1");
		{
			item1.setIcon(R.drawable.device_access_add_alarm);
			item1.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
			
		}
		
		//Activate the item Home to work as an additional item
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
		
	}
	
	@Override
	protected void onStart() {
	// TODO Auto-generated method stub
		super.onStart();
		
                //Hacemos una referencia a la tabla
				final TableLayout tl = (TableLayout)findViewById(R.id.tableLayoutAlarms);
				//tl.removeAllViews();
				// Traer la lista de alarmas y 
				SnoreguardDatabaseHelper db = SnoreguardDatabaseHelper.getInstance(MainActivity.this);
				ArrayList<AlarmDTO> alarms = db.getAlarms();
				
				
				Iterator<AlarmDTO> it = alarms.iterator();
				Log.v(TAG, "Recorriendo el array de Alarmas");
				while (it.hasNext()) {
					AlarmDTO alarm = it.next();
					Log.v(TAG,"id = "+alarm.GetId());
					
					//Creamos la nueva fila a insertar en la tabla
					final TableRow row = new TableRow(this);
			        int idAlarm = alarm.GetId();
			        
					//Creamos el Toogle Button correspondiente a la nueva fila
					ToggleButton tb = new ToggleButton(this);
			        tb.setId(idAlarm);
			        
					//Colocamos el nombre de la nueva fila
			        TextView tv = new TextView(this);
			        tv.setText("Alarma "+idAlarm);
			        tv.setTextSize(20);
			        
			        tv.setId(idAlarm);
			        
			        //Armamos la fila y luego la insertamos al final de la tabla
			        row.addView(tb);
			        row.addView(tv);
			        
			        row.setClickable(true);
			        row.setBackgroundColor(Color.TRANSPARENT);
			        
			        row.setOnClickListener(new View.OnClickListener() {
			               @Override
			                public void onClick(View v) {
			                    // TODO: do your logic here
			            	   row.setBackgroundColor(Color.GRAY);

			            	   Intent intent = 
			                           new Intent(MainActivity.this, ConfigActivity.class); 
			            	   startActivity(intent);
			            	   overridePendingTransition(R.anim.fade,R.anim.hold); //Animation
			                }   
			        });
			        
			        tl.addView(row);
			        
			        //Insertamos una linea divisoria
			        
			        View v = new View(this);
			        v.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, 4));
			        v.setBackgroundColor(Color.BLUE);
 
			        tl.addView(v);
				}
	

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
		//getMenuInflater().inflate(R.menu.main, menu);
		super.onCreateOptionsMenu(menu);
		CreaMenu(menu);
		return true;
	}
	
	// Method to know which item is clicked
			public boolean MenuSelecciona(MenuItem item) {
				switch (item.getItemId()) {
				
				case 0:
					//New Alarm				
					Intent intent = 
                    new Intent(MainActivity.this, ConfigActivity.class); 
					startActivity(intent);
					overridePendingTransition(R.anim.fade,R.anim.hold); //Animation
					
					return true;
				
				
				case android.R.id.home:
					//Cancel Operation
					finish();
		            System.exit(0);
					
					return true;	
				}
				return false;
				
			}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return MenuSelecciona(item);
	}

	

}
