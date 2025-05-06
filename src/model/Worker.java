package model;

import java.util.ArrayList;

public class Worker {
	private String name;
	private String address;
	private String phoneNo;
	private String email;
	private String workerNumber;
	private boolean atWork;
	private ArrayList<Shift> listOfShifts;

	public Worker(String name, String address, String phoneNo, String email, String workerNumber) {
		this.name = name;
		this.address = address;
		this.phoneNo = phoneNo;
		this.email = email;
		this.workerNumber = workerNumber;
		atWork = false;
		listOfShifts = new ArrayList<>();
	}

	
	public void addShift(Shift s) {
		listOfShifts.add(s);
	}

	// Getters
	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public String getWorkerNumber() {
		return workerNumber;
	}

	public boolean isAtWork() {
		return atWork;
	}

	public ArrayList<Shift> getListOfShifts() {
		return listOfShifts;
	}

	// Setters
	public void setAddress(String address) {
		this.address = address;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setWorkerNumber(String workerNumber) {
		this.workerNumber = workerNumber;
	}

	public void setAtWork(boolean atWork) {
		this.atWork = atWork;
	}

	public void setListOfShifts(ArrayList<Shift> listOfShifts) {
		this.listOfShifts = listOfShifts;
	}

}
