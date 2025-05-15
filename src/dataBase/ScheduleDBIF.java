package dataBase;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import model.Schedule;

public interface ScheduleDBIF {
	void addScheduleToDB(LocalDate start,LocalDate end, String name) throws SQLException;
	List<Schedule>createScheduleObject(String workerNumber) throws SQLException;
	void updateScheduleInDB(String start, LocalDateTime end, String name) throws SQLException;
}
