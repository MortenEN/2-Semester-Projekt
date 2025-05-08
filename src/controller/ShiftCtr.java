package controller;

import java.sql.SQLException;
import java.time.LocalDateTime;

import dataBase.ShiftDB;
import dataBase.ShiftDBIF;
import model.Shift;

public class ShiftCtr {
	private ShiftDBIF shiftDB;

	public ShiftCtr() throws SQLException {
		shiftDB = new ShiftDB();
	}

	public Shift createShiftObject(LocalDateTime now) {
		Shift shift;
		shift = shiftDB.createShiftObject(now);
		return shift;
	}
	
	public void addShiftToDB(LocalDateTime now, String workerNumber) throws SQLException {
		Shift shift = createShiftObject(now);
		shiftDB.addShiftToDB(shift, workerNumber);
	}

	public void updateShiftInDB(String start, LocalDateTime end, String workerNumber) throws SQLException {
		shiftDB.updateShiftInDB(start, end, workerNumber);
	}

}
