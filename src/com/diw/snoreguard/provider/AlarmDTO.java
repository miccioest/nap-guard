package com.diw.snoreguard.provider;

public class AlarmDTO {
	private int id;
	private boolean active;
	private String ringTone;
	private int sleepTime;
	private int maxTime;
	private String time; 
	private boolean vibrate;
	
	public AlarmDTO() {	
	}
	
	public AlarmDTO(int id, boolean active, String ringTone, int sleepTime, int maxTime, String time, boolean vibrate) {
		this.id = id;
		this.active = active;
		this.ringTone = ringTone;
		this.sleepTime = sleepTime;
		this.maxTime = maxTime;
		this.time = time;
		this.vibrate = vibrate;
	}
	
	public int GetId() { return this.id; }
	public void SetId(int value) { this.id = value; }

	public boolean GetActive() { return this.active; }
	public void SetActive(boolean value) { this.active = value; }

	public String GetRingTone() { return this.ringTone; }
	public void SetRingTone(String value) { this.ringTone = value; }

	public int GetSleepTime() { return this.sleepTime; }
	public void SetSleepTime(int value) { this.sleepTime = value; }

	public int GetMaxTime() { return this.maxTime; }
	public void SetMaxTime(int value) { this.maxTime = value; }

	public String GetTime() { return this.time; }
	public void SetTime(String value) { this.time = value; }

	public boolean GetVibrate() { return this.vibrate; }
	public void SetVibrate(boolean value) { this.vibrate = value; }
}
