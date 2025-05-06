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
	
	public Shift createShift(LocalDateTime now) {
		Shift shift;
		shift = shiftDB.createShiftObject(now);
		return shift;
	}

}
