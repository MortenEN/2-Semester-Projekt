package dataBase;

import java.sql.SQLException;
import java.util.List;

import model.Shift;
import model.Worker;

public interface WorkerDBIF {
	Worker findWorkerByWorkerNumber(String workerNumber) throws SQLException;
	List<Worker> findWorkersAtWork() throws SQLException;
	String findActiveShift(String login) throws SQLException;
	Worker findWorkerByName(String name) throws SQLException;
}
