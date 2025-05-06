package dataBase;

import java.sql.SQLException;
import java.time.LocalDateTime;

import model.Shift;

public interface ShiftDBIF {
	void addShiftToDB(Shift shift) throws SQLException;
	Shift createShiftObject(LocalDateTime now);
}
