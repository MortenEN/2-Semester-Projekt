package model;

import java.time.LocalDateTime;

/**
 * The Shift class represents a work shift with associated time and date details,
 * as well as the worker assigned to the shift.
 * 
 * @author gruppe 2
 * @version 23-05-2025
 */

public class Shift {
	//Fields
	private LocalDateTime start;
	private LocalDateTime end;
	private Worker worker;
	private boolean pastOrFutureShift; //True means in the past and false mean in the future
	
	/**
	 * Constructor for shift
	 * 
	 * @param start, the starting time of the shift
	 * @param end, the ending time of the shift
	 * @param pastOrFutureShift, In the past or in the future
	 */
	
	public Shift(LocalDateTime start, LocalDateTime end, boolean pastOrFutureShift) {
		this.start = start;
		this.end = end;
		this.pastOrFutureShift = pastOrFutureShift;
	}
	
	//Getters

	public LocalDateTime getStart() {
		return start;
	}
	
	public LocalDateTime getEnd() {
		return end;
	}
	
	public Worker getWorker() {
		return worker;
	}
	
	public boolean getPastOrFutureShift() {
		return pastOrFutureShift;
	}
	
	//Setters

	public void setStart(LocalDateTime start) {
		this.start = start;
	}


	public void setEnd(LocalDateTime end) {
		this.end = end;
	}
	
	
	public void setWorker(Worker worker) {
		this.worker = worker;
	}
	
	
	public void setPastOrFutureShift(boolean newStatus) {
		this.pastOrFutureShift = newStatus;
	}

}
