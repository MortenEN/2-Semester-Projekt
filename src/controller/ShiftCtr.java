package controller;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import dataBase.DBConnection;
import dataBase.ShiftDB;
import dataBase.ShiftDBIF;
import model.Shift;
import model.Worker;

/**
 * The ShiftCtr class handles all operations related to worker shifts.
 * It connects to the database layer to fetch, create, and update shifts for workers.
 * 
 * @author gruppe 2
 * @version 23-05-2025
 */

public class ShiftCtr {
	//Interface for shift databse operations
	private ShiftDBIF shiftDB;
	
	/**
	 * Constructs a new shift controller instance and initializes the shiftDB interface
	 */

	public ShiftCtr() throws SQLException {
		shiftDB = new ShiftDB();
	}
	
	/**
	 * Retrieves a list of shift objects assigned to a worker by their worker number
	 * 
	 * @param workerNumber the unique identifier of the worker
	 * @return returns a list of shift assigned to the specified worker
	 * @throws SQLException
	 */

	public List<Shift> findShiftsByWorker(String workerNumber) throws SQLException {
		return shiftDB.createShiftsObject(workerNumber);
	}
	
	/**
	 * Assigns all retrieved shifts to the given worker object.
	 * 
	 * @param worker the worker object to assign shifts to
	 * @throws SQLException
	 */
	
	public void addShiftsToWorker(Worker worker) throws SQLException {
		List<Shift> res = findShiftsByWorker(worker.getWorkerNumber());
		for(int i = 0; i < res.size(); i++) {
			worker.addShift(res.get(i));
		}
	}
	
	/**
	 * Adds a new shift to the database for a given worker at the specified time
	 * 
	 * A transaction is used to ensure atomicity. If the operation fails
	 * the transcation is rolled back
	 * 
	 * @param now the start time of the shift
	 * @param workerNumber the workers unique ID
	 * @throws SQLException
	 */
	
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
	
	/**
	 * Updates an existing shift in the databse for a given worker
	 * 
	 * The shift is identified by its start time and updated with a new end time.
	 * A transcation ensures the operation is atomic and consistent.
	 * 
	 * @param start the start time of the shift as a string
	 * @param end the new end time of the shift
	 * @param workerNumber the id of the worker whose shift is being updated
	 * @throws SQLException
	 */

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
