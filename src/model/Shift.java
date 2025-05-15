package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Shift {
	@Override
	public String toString() {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
	    return "Shift [start " + start.format(formatter) + ", end " + end.format(formatter) + "]";
	}

	private LocalDateTime start;
	private LocalDateTime end;
	private Worker worker;
	private boolean pastOrFutureShift; //True means in the past and false mean in the future
	
	public Shift(LocalDateTime start, LocalDateTime end, boolean pastOrFutureShift) {
		this.start = start;
		this.end = end;
		this.pastOrFutureShift = pastOrFutureShift;
	}

	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}
	
	public Worker getWorker() {
		return worker;
	}
	
	public void setWorker(Worker worker) {
		this.worker = worker;
	}
	
	public boolean getPastOrFutureShift() {
		return pastOrFutureShift;
	}
	
	public void setPastOrFutureShift(boolean newStatus) {
		this.pastOrFutureShift = newStatus;
	}

}
