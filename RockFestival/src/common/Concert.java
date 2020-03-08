package common;

import java.sql.Timestamp;

public class Concert {
	
	private Timestamp startTime;
	private Timestamp stopTime;
	private String stage;
	private Band band;
	
	public Concert(Band band, Timestamp start, Timestamp stop, String stage) {
		this.band = band;
		this.startTime = start;
		this.stopTime = stop;
		this.stage = stage;
	}
	
	public Band getBand() {
		return band;
	}

	public void setBand(Band band) {
		this.band = band;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getStopTime() {
		return stopTime;
	}

	public void setStopTime(Timestamp stopTime) {
		this.stopTime = stopTime;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}
	
	public String toString() {
		String txt = startTime + " " + band.getName() + " " + stage;
		return txt;	
	}
}
