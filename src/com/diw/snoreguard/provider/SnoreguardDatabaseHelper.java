package com.diw.snoreguard.provider;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SnoreguardDatabaseHelper extends SQLiteOpenHelper {

	private static SnoreguardDatabaseHelper mInstance = null;
	
	private static final String DATABASE_NAME = "snoreguard.db";
	private static final int DATABASE_VERSION = 1;
	
	private interface Tables {
		String ALARMS = "alarms";
	}
	
    private interface AlarmsColumns {
        String ALARM_ID = "alarm_id";
        String ALARM_ACTIVE = "alarm_active";
        String ALARM_RINGTONE = "alarm_ringtone";
        String ALARM_SLEEPTIME = "alarm_sleep_time";
        String ALARM_MAXTIME = "alarm_max_time";
        String ALARM_TIME = "alarm_time";
        String ALARM_VIBRATE = "alarm_vibrate";
    }	
	
    private String[] fields = { AlarmsColumns.ALARM_ID, AlarmsColumns.ALARM_ACTIVE, AlarmsColumns.ALARM_MAXTIME, AlarmsColumns.ALARM_RINGTONE,
    							AlarmsColumns.ALARM_SLEEPTIME, AlarmsColumns.ALARM_TIME, AlarmsColumns.ALARM_TIME, AlarmsColumns.ALARM_VIBRATE };
    
	public static SnoreguardDatabaseHelper getInstance(Context ctx) {
		if (mInstance == null) {
			mInstance = new SnoreguardDatabaseHelper(ctx.getApplicationContext());
		}
		return mInstance;
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Tables.ALARMS + " ("
                + AlarmsColumns.ALARM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + AlarmsColumns.ALARM_ACTIVE + " INTEGER NOT NULL,"
                + AlarmsColumns.ALARM_RINGTONE + " TEXT NOT NULL,"
                + AlarmsColumns.ALARM_SLEEPTIME + " INTEGER NOT NULL,"
                + AlarmsColumns.ALARM_MAXTIME + " INTEGER NOT NULL,"
                + AlarmsColumns.ALARM_TIME + " TEXT,"
                + AlarmsColumns.ALARM_VIBRATE + " INTEGER NOT NULL)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	}
	
	public AlarmDTO getAlarm(int id) {
		String[] conditionValues = { Integer.toString(id) };
		
		SQLiteDatabase db = mInstance.getReadableDatabase();
		Cursor cursor = db.query(Tables.ALARMS, fields, AlarmsColumns.ALARM_ID + "=?", conditionValues, null, null, null);

		AlarmDTO alarm = null;
		if (cursor.moveToFirst()) {
			alarm = getAlarmDTOFromCursor(cursor);
		}
		return alarm;
	}
	
	public ArrayList<AlarmDTO> getAlarms() {
		ArrayList<AlarmDTO> alarms = new ArrayList<AlarmDTO>();

		SQLiteDatabase db = mInstance.getReadableDatabase();
		Cursor cursor = db.query(Tables.ALARMS, fields, null, null, null, null, null);

		if (cursor.moveToFirst()) {
			do {
				AlarmDTO alarm = getAlarmDTOFromCursor(cursor);
				alarms.add(alarm);
			} while (cursor.moveToNext());
		}		
		
		return alarms;
	}
	
	public long addAlarm(AlarmDTO alarm) {
		ContentValues values = getContentValuesFromAlarmDTO(alarm);
		
		SQLiteDatabase db = mInstance.getReadableDatabase();
		return db.insert(Tables.ALARMS, null, values);
	}
	
	public int updateAlarm(AlarmDTO alarm) {
		// TODO update row
		return 1;
	}
	
	public int deleteAlarm(int id) {
		// TODO delete row
		return 1;
	}
	
	private SnoreguardDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	private AlarmDTO getAlarmDTOFromCursor(Cursor cursor) {
		AlarmDTO alarm = new AlarmDTO(cursor.getInt(0),
				(cursor.getInt(1) == 1) ? true : false,
				cursor.getString(2),
				cursor.getInt(3),
				cursor.getInt(4),
				cursor.getString(5),
				(cursor.getInt(6) == 1) ? true : false);
		return alarm;
	}
	
	private ContentValues getContentValuesFromAlarmDTO(AlarmDTO alarm) {
		ContentValues values = new ContentValues();
		values.put(AlarmsColumns.ALARM_ACTIVE, (alarm.GetActive() == true) ? 1 : 0);
		values.put(AlarmsColumns.ALARM_RINGTONE, alarm.GetRingTone());
		values.put(AlarmsColumns.ALARM_SLEEPTIME, alarm.GetSleepTime());
		values.put(AlarmsColumns.ALARM_MAXTIME, alarm.GetMaxTime());
		values.put(AlarmsColumns.ALARM_TIME, alarm.GetTime());
		values.put(AlarmsColumns.ALARM_VIBRATE, (alarm.GetVibrate() == true) ? 1 : 0);
		return values;
	}
}
