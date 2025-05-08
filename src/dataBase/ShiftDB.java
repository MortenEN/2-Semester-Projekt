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
	private static final String update_SQL =
			"update shift set [end] = ? where FK_worker_ID = ? and start = ?";
	private PreparedStatement update;
	
	public ShiftDB() throws SQLException {
		con = DBConnection.getInstance().getConnection();
		create = con.prepareStatement(create_SQL);
		update = con.prepareStatement(update_SQL);
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

	@Override
	public void updateShiftInDB(String start, LocalDateTime end, String workerNumber) throws SQLException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = end.format(formatter);
        
		update.setString(1, formattedDateTime);
		update.setString(2, workerNumber);
		update.setString(3, start);
		
		update.executeUpdate();
	}

}
