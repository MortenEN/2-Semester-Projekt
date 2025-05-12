package controller;

import java.sql.SQLException;
import java.time.Duration;
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

	public String findActiveShift(String login) throws SQLException {
		String start = workerDB.findActiveShift(login);
		return start;
	}

	public String getHoursForLastMonth(Worker worker) {
		Duration totalHours = Duration.ZERO;
		List<Shift> res = worker.getListOfShifts();

		for (Shift shift : res) {
			Duration workHours = Duration.between(shift.getStart(), shift.getEnd());
			totalHours = totalHours.plus(workHours);
		}

		return String.format("%d timer og %d minutter", 
				totalHours.toHours(), 
				totalHours.toMinutes() % 60);
	}
}
