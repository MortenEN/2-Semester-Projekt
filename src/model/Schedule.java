package model;

import java.time.LocalDateTime;
import java.util.List;

public class Schedule {
	private LocalDateTime start;
	private LocalDateTime end;
	private String name;
	private List<Shift> listOfShift;
	
	
	public void setStart(LocalDateTime start) {
		this.start = start;
	}
	
	public void setEnd(LocalDateTime end) {
		this.end = end;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	public LocalDateTime getStart() {
		return start;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public String getName() {
		return name;
	}

	public List<Shift> getListOfShift() {
		return listOfShift;
	}

	public void setListOfShift(List<Shift> listOfShift) {
		this.listOfShift = listOfShift;
	}
	
	public void addWokerToShift(Worker w){
		
	}
}
