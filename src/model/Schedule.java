package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The Schedule class is responsible for maintaining and managing a collection of shifts.
 * 
 * @author gruppe 2
 * @version 23-05-2025
 */

public class Schedule {
	//Fields
	private LocalDate start;
	private LocalDate end;
	private String name;
	private List<Shift> listOfShifts;
	
	/**
	 * Constructor for schedule
	 * @param start, the start date of the schedule
	 * @param end, the end date of the schedule
	 * @param name, the name of the schedule
	 */
	
	public Schedule(LocalDate start, LocalDate end, String name) {
		super();
		this.start = start;
		this.end = end;
		this.name = name;
		this.listOfShifts = new ArrayList<>();
	}
	
	/**
	 * Adds a Shift to the schedule's list of shifts
	 * @param s the shift to be added
	 */

	public void addShiftToSchedule(Shift s){
		listOfShifts.add(s);
	}

	//Setters
	public void setStart(LocalDate start) {
		this.start = start;
	}

	public void setEnd(LocalDate end) {
		this.end = end;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setListOfShifts(List<Shift> listOfShifts) {
		this.listOfShifts = listOfShifts;
	}


	//Getters
	public LocalDate getStart() {
		return start;
	}

	public LocalDate getEnd() {
		return end;
	}

	public String getName() {
		return name;
	}

	public List<Shift> getListOfShifts() {
		return listOfShifts;
	}

}
