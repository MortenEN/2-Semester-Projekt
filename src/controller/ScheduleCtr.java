package controller;

import java.sql.SQLException;

import dataBase.ScheduleDBIF;
import dataBase.WorkerDBIF;
import model.Shift;
import model.Worker;

public class ScheduleCtr {
	private ScheduleDBIF scheduleDB;
	private ShiftCtr shiftCtr;
	private WorkerCtr workerCtr;
	private WorkerDBIF workerDB;
	
	public ScheduleCtr() throws SQLException {
		
	}
	
	public Worker findWorkerByName(String name) throws SQLException {
		Worker worker;
		
		worker = workerDB.findWorkerByName(name);
		return worker;
	}
	
	public void addWorkerToShift(Worker worker, Shift shift) throws SQLException {
		shift.setWorker(worker);
	}
	
	public void saveSchedule() {
		
	}
	

}
