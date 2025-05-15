package controller;

import java.sql.SQLException;

import dataBase.ScheduleDBIF;
import dataBase.WorkerDBIF;
import model.Worker;

public class ScheduleCtr {
	private ScheduleDBIF scheduleDB;
	private ShiftCtr shiftCtr;
	private WorkerCtr workerCtr;
	private WorkerDBIF workerDB;
	
	public ScheduleCtr() throws SQLException {
		
	}
	
	public void findWorkerByName(String name) throws SQLException {
		Worker worker;
		
		//worker = workerDB.findWorkerByName(name);
	}

}
