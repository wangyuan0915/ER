package com.example.patient_information;

import java.io.Serializable;

/** A VitalSign of patient. */
public class VitalSign implements Serializable {
	

	private static final long serialVersionUID = -674056368344178490L;
	
	/** This is patient's temperature */ 
	private double temperature;
	
	/** This is patient's systolic */ 
	private int systolic;
	
	/** This is patient's diastolic */ 
	private int diastolic;
	
	/** This is patient's heartrate */ 
	private int heartrate;
	
	/** This is patient's arrival time */ 
	private String time;
	
	/** This is patient's arrival date */ 
	private String date;
	
	/** This is patient's urgencyscore */ 
	private int urgencyscore;
	
	/** Constructs a vitalsign with temperature, systolic, diastolic, heartrate and patients'arrival time.
	 * @param temperature temperature of patient.
	 * @param systolic systolic of patient.
	 * @param diastolic diastolic of patient.
	 * @param heartrate heartrate of patient.
	 * @param hour hour of patient's arrival time.
	 * @param minute minute of patient's arrival time.
	 * @param day day of patient's arrival time.
	 * @param month month of patient's arrival time.
	 * @param year year of patient's arrival time.
	 */
	public VitalSign(double temperature, int systolic, int diastolic,int heartrate, int hour,
			int minute, int day, int month, int year) {
		this.temperature = temperature;
		this.systolic = systolic;
		this.diastolic = diastolic;
		this.heartrate = heartrate;
		this.time = String.valueOf(hour) + ":" + String.valueOf(minute);
		this.date = String.valueOf(day) + "/" +  String.valueOf(month) + "/"
                + String.valueOf(year);
		this.urgencyscore = 0;
		calUrgencyScore();
	}
	
	/**
     * Sets This patient's urgencyscore.
     * @param temperature The temperature of patient.
     * @param systolic The systolic of patient.
	 * @param diastolic The diastolic of patient.
	 * @param heartrate The heartrate of patient.
     */
	public void calUrgencyScore() {
		if (temperature >= 39.0) {
			this.urgencyscore += 1;
		} 
		if ((systolic >= 140) || (diastolic >= 90)) {
			this.urgencyscore += 1;
		} 
		if ((heartrate >= 100) || (heartrate <= 50)) {
			this.urgencyscore += 1;
		}
	}
	
	/** Returns the patients' temperature.
	 * @return the patients' temperature.
	 */
	public double getTemperature(){
		return this.temperature;
	}
	
	/** Returns the patients' systolic.
	 * @return the patients' systolic.
	 */
	public int getSystolic(){
		return this.systolic;
	}
	
	/** Returns the patients' diastolic.
	 * @return the patients' diastolic.
	 */
	public int getDiastolic(){
		return this.diastolic;
	}
	
	/** Returns the patients' heartrate.
	 * @return the patients' heartrate.
	 */
	public int getHeartrate(){
		return this.heartrate;
	}
	
	/** Returns the patients' urgencyscore.
	 * @return the patients' urgencyscore.
	 */
	public int getUrgencyScore() {
		return this.urgencyscore;
	}
	
		
	/** Returns the patients' arrival time.
	 * @return the patients' arrival time.
	 */
	public String getTime(){
		return this.date + "," + this.time;
	}
	
	/** Returns the patients' information in type of string.
	 * @return the patients' information in type of string.
	 */
	public String toString(){
		return this.date + "-" + this.time + "-" + String.valueOf(temperature) + "-" + 
				String.valueOf(systolic) + "-" + String.valueOf(diastolic) + 
				"-" + String.valueOf(heartrate);		
	}		
}
