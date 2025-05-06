package dataBase;

import model.Worker;

public interface WorkerDBIF {
	Worker findWorkerByWorker(String workerNumber);
}
