package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Schedule {
	private LocalDate start;
	private LocalDate end;
	private String name;
	private List<Shift> listOfShifts;
	
	public Schedule(LocalDate start, LocalDate end, String name) {
		super();
		this.start = start;
		this.end = end;
		this.name = name;
		this.listOfShifts = new ArrayList<>();
	}

	public void addWokerToShift(Worker w){

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
