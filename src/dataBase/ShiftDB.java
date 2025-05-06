package dataBase;

import java.sql.*;
import java.time.LocalDateTime;

import model.Shift;

public class ShiftDB implements ShiftDBIF {
	private Connection con;
	private Shift shift;
	private static final String create_SQL =
			"Insert into [Shift](start, end)"
			+ "values (?, ?)";
	private PreparedStatement create;
	
	public ShiftDB() throws SQLException {
		create = con.prepareStatement(create_SQL);
	}

	@Override
	public void addShiftToDB(Shift shift) throws SQLException {
		LocalDateTime date = shift.getStart();
		String stringDateStart = date.toString();
		
		//shift.setEnd(LocalDateTime.now()); FIND UD AF HVOR DET GIVER MENING
		
		LocalDateTime date1 = shift.getEnd();
		String stringDateEnd = date1.toString();
		
		create.setString(1, stringDateStart);
		create.setString(2, stringDateEnd);
		
		create.executeUpdate();
	}

	@Override
	public Shift createShiftObject(LocalDateTime now) {
		shift = new Shift(now);
		return shift;
	}

}
