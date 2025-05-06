package dataBase;

import java.sql.SQLException;

import model.Worker;

public interface WorkerDBIF {
	Worker findWorkerByWorkerNumber(String workerNumber) throws SQLException;
}
