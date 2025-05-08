package controller;

import java.sql.SQLException;
import java.util.List;

import dataBase.WorkerDB;
import dataBase.WorkerDBIF;
import model.Shift;
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

	public List<Worker> findAll() throws SQLException {
		return workerDB.findWorkersAtWork();
	}
}
