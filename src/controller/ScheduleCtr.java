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

/**
 * ScheduleCtr class handles the scheduling operations related to workers and tasks.
 * It includes functionalities for assigning tasks, retrieving tasks by employee ID,
 * and checking if an employee is free at a specific time.
 * 
 * @author gruppe 2
 * @version 23-05-2025
 */

public class ScheduleCtr {
	//Fields
	private ScheduleDBIF scheduleDB;
	private WorkerCtr workerCtr;
	
	/**
	 * Constructor that initializes database interface for schedule and worker 
	 * 
	 * @throws SQLException
	 */
	
	public ScheduleCtr() throws SQLException {
		this.scheduleDB = new ScheduleDB();
		this.workerCtr = new WorkerCtr();
	}
	
	/**
	 * Creates a Schedule object
	 * 
	 * @param name of schedule
	 * @param start of schedule
	 * @param end of schedule
	 * @return returns a new schedule object
	 * @throws SQLException
	 */
	
	public Schedule SelectDate(String name, LocalDate start, LocalDate end) throws SQLException{
		Schedule schedule = new Schedule(start, end , name);
		return schedule;
	}
	
	/**
	 * Creates a new shift object with a start and end time
	 * 
	 * @param start the start time of the shift
	 * @param end the end time of the shift
	 * @return returns a new shift object
	 * @throws SQLException
	 */
	
	public Shift createShift(LocalDateTime start, LocalDateTime end) throws SQLException {
		Shift shift = new Shift(start, end, false);
		return shift;
	}
	
	/**
	 * Searches for and retrieves a worker obecjt by name
	 * 
	 * @param name of a worker within the system
	 * @return returns a worker
	 * @throws SQLException
	 */
	
	public Worker findWorkerByName(String name) throws SQLException {
		Worker worker;
		
		worker = workerCtr.findWorkerByName(name);
		return worker;
	}
	
	/**
	 * Assigns a worker to a shift by setting the worker on the shift object
	 * 
	 * @param worker the worker to assign
	 * @param shift the shift to which the worker is assigned
	 * @throws SQLException
	 */
	
	public void addWorkerToShift(Worker worker, Shift shift) throws SQLException {
		shift.setWorker(worker);
	}
	
	/**
	 * Saves a schedule to the database
	 * 
	 * @param schedule the schedule to save
	 * @throws SQLException
	 */
	
	public void saveSchedule(Schedule schedule) throws SQLException {
		if(schedule == null) {
			throw new IllegalStateException("No schedule to save");
		}
		
		scheduleDB.saveSchedule(schedule);
	}
	
	/**
	 * Checks whether a schedule already exists in the database
	 * 
	 * @param schedule the schedule to check
	 * @return reutrn true if the schedule exists, false otherwise
	 * @throws SQLException
	 */
	
	public boolean scheduleExists(Schedule schedule) throws SQLException {
	    return ((ScheduleDB) scheduleDB).scheduleExists(schedule);
	}
	

	

}
