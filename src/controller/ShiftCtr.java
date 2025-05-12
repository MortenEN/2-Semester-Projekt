package controller;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import dataBase.ShiftDB;
import dataBase.ShiftDBIF;
import model.Shift;
import model.Worker;

public class ShiftCtr {
	private ShiftDBIF shiftDB;

	public ShiftCtr() throws SQLException {
		shiftDB = new ShiftDB();
	}

	public List<Shift> findShiftsByWorker(String workerNumber) throws SQLException {
		return shiftDB.createShiftsObject(workerNumber);
	}
	
	public void addShiftsToWorker(Worker worker) throws SQLException {
		List<Shift> res = findShiftsByWorker(worker.getWorkerNumber());
		for(int i = 0; i < res.size(); i++) {
			worker.addShift(res.get(i));
		}
	}
	
	public void addShiftToDB(LocalDateTime now, String workerNumber) throws SQLException {
		shiftDB.addShiftToDB(now, workerNumber);
	}

	public void updateShiftInDB(String start, LocalDateTime end, String workerNumber) throws SQLException {
		shiftDB.updateShiftInDB(start, end, workerNumber);
	}

}
