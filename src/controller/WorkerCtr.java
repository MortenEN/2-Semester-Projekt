package controller;

import java.sql.SQLException;

import dataBase.WorkerDB;
import dataBase.WorkerDBIF;
import model.Worker;

public class WorkerCtr {
private WorkerDBIF workerDB;
	
	public WorkerCtr() throws SQLException {
		workerDB = new WorkerDB();
	}
	
	public Worker findWorker(String workerNumber) throws SQLException {
		Worker worker;
		
		worker = workerDB.findWorkerByWorkerNumber(workerNumber);
		return worker;
	}
}
