package model;

import java.util.ArrayList;
import java.util.List;

/**
 * The Worker class represents an employee and contains their personal information,
 * contact details, and their associated list of shifts.
 * 
 * @author gruppe 2
 * @version 23-05-2025
 */

public class Worker {
	private String name;
	private String address;
	private String phoneNo;
	private String cpr;
	private String email;
	private String workerNumber;
	private boolean atWork;
	private List<Shift> listOfShifts;

	/**
	 * Constructor for Worker
	 * @param name of the worker
	 * @param address of the worker
	 * @param phoneNo of the worker
	 * @param cpr of the worker
	 * @param email of the worker
	 * @param workerNumber of the worker
	 * @param atWork true or false
	 */

	public Worker(String name, String address, String phoneNo, String cpr, String email, String workerNumber, boolean atWork) {
		this.name = name;
		this.address = address;
		this.phoneNo = phoneNo;
		this.setCpr(cpr); 
		this.email = email;
		this.workerNumber = workerNumber;
		this.atWork = atWork;
		listOfShifts = new ArrayList<>();
	}

	/**
	 * Adds a shift to the workers shift list
	 * @param s the shift to bed added
	 */

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

	public List<Shift> getListOfShifts() {
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

	public String getCpr() {
		return cpr;
	}

	public void setCpr(String cpr) {
		this.cpr = cpr;
	}
}
