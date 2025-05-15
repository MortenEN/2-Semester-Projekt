package dataBase;

import java.sql.SQLException;
import java.time.LocalDate;
import model.Schedule;
import model.Shift;
import model.Worker;

public interface ScheduleDBIF {
	
	Schedule createScheduleObject(LocalDate start,LocalDate end, String name) throws SQLException;
	void setShift(Shift shift);
	void addWorkerToShift(Worker worker);
	void saveSchedule(Schedule schedule);
	void updateSchedule(Schedule schedule);

}

