package dataBase;

import java.sql.SQLException;
import java.time.LocalDateTime;

import model.Shift;

public interface ShiftDBIF {
	void addShiftToDB(LocalDateTime now, String workerNumber) throws SQLException;
	Shift createShiftObject(LocalDateTime now);
	void updateShiftInDB(String start, LocalDateTime end, String workerNumber) throws SQLException;
}
