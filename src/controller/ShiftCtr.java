package controller;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import dataBase.DBConnection;
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
		DBConnection db = DBConnection.getInstance();
		
		try {
		db.startTransaction(); //Starter Transcation
		
		shiftDB.addShiftToDB(now, workerNumber);
		db.commitTransaction(); //Hvis Transcation lykkes committer den

		} catch (Exception e) {
			try {
				db.rollbackTransaction(); //Hvis noget skulle fejle laver den rollback call
			} catch (SQLException ex) {
				ex.printStackTrace(); //Udskriver fejl
			}
			e.printStackTrace();
		}
		
	}

	public void updateShiftInDB(String start, LocalDateTime end, String workerNumber) throws SQLException {
		DBConnection db = DBConnection.getInstance();
		
		try {
			db.startTransaction(); //Starter Transcation
			
			shiftDB.updateShiftInDB(start, end, workerNumber);
			db.commitTransaction(); //Hvis Transcation lykkes committer den

		} catch (Exception e) {
			try {
				db.rollbackTransaction(); //Hvis noget skulle fejle laver den et rollback call
			} catch (SQLException ex) {
				ex.printStackTrace(); //Udskriver fejl
			}
			e.printStackTrace();
		}
	}

}
