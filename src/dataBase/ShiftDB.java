package dataBase;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import model.Shift;

public class ShiftDB implements ShiftDBIF {
	private Connection con;
	private Shift shift;
	private static final String create_SQL =
			"Insert into [Shift](start, [end], FK_worker_ID)"
			+ "  values (?, ?, ?)";
	private PreparedStatement create;
	
	public ShiftDB() throws SQLException {
		con = DBConnection.getInstance().getConnection();
		create = con.prepareStatement(create_SQL);
	}

	@Override
	public void addShiftToDB(Shift shift, String workerNumber) throws SQLException {
		LocalDateTime date = shift.getStart();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = date.format(formatter);
		
		create.setString(1, formattedDateTime);
		create.setString(2, null);
		create.setString(3, workerNumber);
		
		create.executeUpdate();
	}

	@Override
	public Shift createShiftObject(LocalDateTime now) {
		shift = new Shift(now);
		return shift;
	}

}
