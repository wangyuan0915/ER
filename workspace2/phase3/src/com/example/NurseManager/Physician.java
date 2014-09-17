package com.example.NurseManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.example.patient_information.Patient;
import com.example.patient_information.VitalSign;

/** A Physician. */
public class Physician implements Serializable {


	private static final long serialVersionUID = 9147678430885141688L;

	/** This is Physician's name */ 
	private String username;

	/** This is Physician's password */
	private String password;

	/** This is all of the patients */
	private Map<String, Patient> patients;

	/** Constructs a Physician with username and password. 
	 * @param username username of new Physician.
	 * @param password password of new Physician.
	 */
	public Physician(String username, String password) {
		this.username = username;
		this.password = password;
	}

	/** Constructs a Physician with username, password and manages Physicians information.
	 * @param username username of new Physician.
	 * @param password password of new Physician.
	 * @param dir the directory in which the data file is stored.
	 * @param fileName the data file containing Person information.
	 * @throws IOException An instance of the type being managed by this RecordManager.
	 */
	public Physician(String username, String password, File dir, String fileName) 
			throws IOException {

		this.username = username;
		this.password = password;
		this.patients = new HashMap<String, Patient>();

		File file = new File(dir, fileName);
		if (file.exists()) {
			this.readFromFile(file.getPath());
		}
		else {
			file.createNewFile();
		}		
	}
	
	/** Returns the map of the patient information.
	 * @param cardnum The card number of the patient
	 * @return the map of the patient information.
	 */
	public Map<String, ArrayList<String>> getPatientInformation(String cardnum) {
		
		ArrayList<String> patient_information = new ArrayList<String>();
		ArrayList<String> vitalsign = new ArrayList<String>();
		Map<String, ArrayList<String>>  patient_map = new HashMap<String, ArrayList<String>>();
		
		Patient patient = patients.get(cardnum);
		String patientinformation = "personal information: " + patient.getName() + ", " + 
		patient.getDob() + ", " + patient.getArriveTime() + ", " + patient.getSeen_physician() + 
		", " + patient.getDescription() + ", " + "urgency score: " + patient.getUrgencyScore();
		patient_information.add(patientinformation);
		patient_map.put("patient_information", patient_information);
		
		for (VitalSign v: patient.getVitalsigns().values()) {
			String item = "VitalSign: " + v.toString();
			vitalsign.add(item);
		}
		
		patient_map.put("vitalsign", vitalsign);
		return patient_map;
	}

	/** Returns the Physician's username.
	 * @return the Physician's username.
	 */
	public String getUsername() {
		return username;
	}

	/** Returns the Physician's password.
	 * @return the Physician's password.
	 */
	public String getPassword() {
		return password;
	}
	
	/** Returns a Map of patients.
	 * @return a Map of all the patients.
	 */
	public Map<String, Patient> getPatients() {
		return patients;
	}
	
	/** Returns true iff patients already exists.
	 * @param cardnumber The card number of the patient
	 * @return true iff patients already exists.
	 */
	public boolean cotainsPatient(String cardnumber) {
		return patients.containsKey(cardnumber);
	}
	
	/** 
	 * Returns a String representation of this Physician.
	 * @return a String representation of this Physician.
	 */
	@Override
	public String toString() {
		return username + "," + password;
	}
	
	/**
	 * Add prescription to the patient's information.
	 * @param patient The patient who need the prescription.
	 * @param prescription The prescription given by the physician.
	 * @param filePath The filepath of the data file.
	 * @throws FileNotFoundException.
	 */
	public void addPrescription(Patient patient , String prescription, String filePath) 
			throws FileNotFoundException {
		
		this.patients.clear();
		this.readFromFile(filePath);
		
		if (patients.get(patient.getID()) != null) {
			patients.get(patient.getID()).setDescription(prescription);
			try {
				FileWriter writer = new FileWriter(filePath, false);
				
				for (Patient p: patients.values()) {
					writer.write(p.toString() + "\n");		     
				}
				writer.close();
				this.patients.clear();
				this.readFromFile(filePath);
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
	}

	/**
	 * Populates the list of Patients using the file at path filePath.
	 * @param filePath The filepath of the data file.
	 * @throws FileNotFoundException if the file does not exist.
	 */
	public void readFromFile(String filePath) throws FileNotFoundException {

		Scanner scanner = new Scanner(new FileInputStream(filePath));
		String [] record;

		while (scanner.hasNextLine()) {
			record = scanner.nextLine().split(",");
			int i = record.length;
			String[] name = record[0].split(" ");
			String[] dob = record[1].split("/");
			String[] cardnum = record[2].split(" ");
			String[] arrivedate = record[3].split("/");
			String[] arrivetime = record[4].split(":");

			Patient patient = new Patient(name[0], Integer.parseInt(dob[0]), 
					Integer.parseInt(dob[1]), Integer.parseInt(dob[2]),
					cardnum[0], Integer.parseInt(arrivetime[0]),
					Integer.parseInt(arrivetime[1]), Integer.parseInt(arrivedate[0]),
					Integer.parseInt(arrivedate[1]), Integer.parseInt(arrivedate[2]));
			
			if (!record[5].equals("Haven't seen physician yet")) {
				patient.setSeen_physician(record[5]);
			}
			
			if (!record[6].equals("No Description")) {
				patient.setDescription(record[6]);
			}

			if (!record[7].equals("0")) {
				if (patient.getAge() < 2) {
					patient.setUrgencyScore(Integer.valueOf(record[7]).intValue() - 1);
				} else {
					patient.setUrgencyScore(Integer.valueOf(record[7]).intValue());
				}
			}


			patients.put(cardnum[0], patient);

			int n = 8;
			while (i - 8 > 0) {
				String[] record1 = record[n].split("-");
				String[] date = record1[0].split("/");
				String[] time = record1[1].split(":");
				String[] temperature = record1[2].split(" ");
				String[] systolic = record1[3].split(" ");
				String[] diastolic = record1[4].split(" ");
				String[] heartrate = record1[5].split(" ");
				
				VitalSign vitalsigns = new VitalSign(Double.valueOf(temperature[0]), 
						Integer.parseInt(systolic[0]), Integer.parseInt(diastolic[0]),
						Integer.parseInt(heartrate[0]), Integer.parseInt(time[0]),
						Integer.parseInt(time[1]), Integer.parseInt(date[0]), Integer.parseInt(date[1]),
						Integer.parseInt(date[2]));


				i = i - 1;
				n = n + 1;

				patient.addVitalsigns(vitalsigns);               	                	 
			}

		}
		scanner.close();
	}
	
}
