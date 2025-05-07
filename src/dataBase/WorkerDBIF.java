package dataBase;

import java.sql.SQLException;
import java.util.List;

import model.Worker;

public interface WorkerDBIF {
	Worker findWorkerByWorkerNumber(String workerNumber) throws SQLException;
	List<Worker> findWorkersAtWork() throws SQLException;
}
