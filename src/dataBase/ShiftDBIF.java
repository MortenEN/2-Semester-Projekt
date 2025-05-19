package dataBase;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import model.Shift;

/**
 * The {@code ShiftDBIF} interface defines the contract for interacting with the Shift table in the database.
 * 
 * Implementing classes must provide functionality to:
 * <ul>
 *   <li>Add a shift for a worker</li>
 *   <li>Retrieve a list of shifts for a given worker</li>
 *   <li>Update an existing shift with an end time</li>
 * </ul>
 * 
 * This interface is used to abstract database logic from business logic for better separation of concerns.
 * 
 * @author gruppe 2
 * @version 23-05-2025
 */
public interface ShiftDBIF {
	void addShiftToDB(LocalDateTime now, String workerNumber) throws SQLException;
	List<Shift> createShiftsObject(String workerNumber) throws SQLException;
	void updateShiftInDB(String start, LocalDateTime end, String workerNumber) throws SQLException;
}