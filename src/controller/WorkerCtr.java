package controller;

import java.sql.SQLException;
import java.time.Duration;
import java.util.List;

import dataBase.WorkerDB;
import dataBase.WorkerDBIF;
import model.Shift;
import model.Worker;

/**
 * The WorkerCtr class handles logic and database communication related to workers.
 * It provides functionality to find workers by name or worker number, retrieve their active shift,
 * and calculate total work hours for the past month based on their shift history.
 * 
 * This class uses the WorkerDB interface to interact with the underlying database.
 * 
 * @author gruppe 2
 * @version 23-05-2025
 */

public class WorkerCtr {
	//Interface for worker related database operations
	private WorkerDBIF workerDB;
	
	/**
	 * Constructs a new worker controller and initializes the worker database interface.
	 * @throws SQLException
	 */

	public WorkerCtr() throws SQLException {
		workerDB = new WorkerDB();
	}
	
	/**
	 * Finds a worker in the system using their unique worker number.
	 * 
	 * @param workerNumber the unique ID of the worker
	 * @return returns the worker object if found
	 * @throws SQLException
	 */

	public Worker findWorkerByWorkerNumber(String workerNumber) throws SQLException {
		Worker worker;

		worker = workerDB.findWorkerByWorkerNumber(workerNumber);
		return worker;
	}
	
	/**
	 * Finds a worker using their name
	 * 
	 * @param name the name of the worker
	 * @return returns the worker object if found
	 * @throws SQLException
	 */
	
	public Worker findWorkerByName(String name) throws SQLException {
		Worker worker;
		
		worker = workerDB.findWorkerByName(name);
		return worker;
	}
	
	/**
	 * Retrieves all workers that are currently registered as being at work
	 * 
	 * @return returns a list of active worker objects
	 * @throws SQLException
	 */

	public List<Worker> findAll() throws SQLException {
		return workerDB.findWorkersAtWork();
	}
	
	/**
	 * Finds the start time of the currently active shift for a worker 
	 * identified by their login credentials
	 * 
	 * @param login the login identifier of the worker
	 * @return returns a string representing the start time of the active shift or null if non found
	 * @throws SQLException
	 */

	public String findActiveShift(String login) throws SQLException {
		String start = workerDB.findActiveShift(login);
		return start;
	}
	
	/**
	 * Calculates the total work hours for the last month based on the workers completed shifts
	 * 
	 * @param worker the worker whose hours are to be calculated
	 * @return returns a formatted string showing hours and minutes worked
	 */

	public String getHoursForLastMonth(Worker worker) {
		Duration totalHours = Duration.ZERO;
		List<Shift> res = worker.getListOfShifts();

		for (Shift shift : res) {
			if(shift.getEnd() != null){
			Duration workHours = Duration.between(shift.getStart(), shift.getEnd());
			totalHours = totalHours.plus(workHours);
			}
		}
		return String.format("%d timer og %d minutter", 
				totalHours.toHours(), 
				totalHours.toMinutes() % 60);
	}
	
}
