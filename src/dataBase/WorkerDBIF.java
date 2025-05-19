package dataBase;

import java.sql.SQLException;
import java.util.List;

import model.Shift;
import model.Worker;
/**
 * WorkerDBIF defines the methods required for interacting with the worker database.
 * 
 * This interface provides operations to find workers by worker number or name,
 * retrieve a list of currently working employees, and fetch an active shift's start time.
 * 
 * Any implementing class (e.g., WorkerDB) is responsible for providing the actual database logic.
 * 
 * @author gruppe 2
 * @version 23-05-2025
 */
public interface WorkerDBIF {
	Worker findWorkerByWorkerNumber(String workerNumber) throws SQLException;
	List<Worker> findWorkersAtWork() throws SQLException;
	String findActiveShift(String login) throws SQLException;
	Worker findWorkerByName(String name) throws SQLException;
}
