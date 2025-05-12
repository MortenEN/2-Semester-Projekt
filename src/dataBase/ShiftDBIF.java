package dataBase;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import model.Shift;

public interface ShiftDBIF {
	void addShiftToDB(LocalDateTime now, String workerNumber) throws SQLException;
	List<Shift> createShiftsObject(String workerNumber) throws SQLException;
	void updateShiftInDB(String start, LocalDateTime end, String workerNumber) throws SQLException;
}
