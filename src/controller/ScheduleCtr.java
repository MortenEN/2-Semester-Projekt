package controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import dataBase.ScheduleDB;
import dataBase.ScheduleDBIF;
import dataBase.WorkerDBIF;
import dataBase.WorkerDB;
import model.Shift;
import model.Worker;
import model.Schedule;

public class ScheduleCtr {
	private ScheduleDBIF scheduleDB;
	private WorkerDBIF workerDB;
	
	public ScheduleCtr() throws SQLException {
		this.scheduleDB = new ScheduleDB();
		this.workerDB = new WorkerDB();
	}
	
	public Schedule SelectDate(String name, LocalDate start, LocalDate end) throws SQLException{
		Schedule schedule = new Schedule(start, end , name);
		return schedule;
	}
	
	public Shift createShift(LocalDateTime start, LocalDateTime end) throws SQLException {
		Shift shift = new Shift(start, end, false);
		return shift;
	}
	
	public Worker findWorkerByName(String name) throws SQLException {
		Worker worker;
		
		worker = workerDB.findWorkerByName(name);
		return worker;
	}
	
	public void addWorkerToShift(Worker worker, Shift shift) throws SQLException {
		shift.setWorker(worker);
	}
	
	public void saveSchedule(Schedule schedule) throws SQLException {
		if(schedule == null) {
			throw new IllegalStateException("No schedule to save");
		}
		
		scheduleDB.saveSchedule(schedule);
	}
	
	public boolean scheduleExists(Schedule schedule) throws SQLException {
	    return ((ScheduleDB) scheduleDB).scheduleExists(schedule);
	}
	

}
