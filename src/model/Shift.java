package model;

import java.time.LocalDateTime;

public class Shift {
	private LocalDateTime start;
	private LocalDateTime end;
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
	
	public boolean getPastOrFutureShift() {
		return pastOrFutureShift;
	}
	
	public void setPastOrFutureShift(boolean newStatus) {
		this.pastOrFutureShift = newStatus;
	}

}
