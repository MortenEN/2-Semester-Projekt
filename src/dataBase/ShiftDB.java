package dataBase;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import model.Shift;
import model.Worker;

public class ShiftDB implements ShiftDBIF {
	private Connection con;
	private DateTimeFormatter formatter;
	private static final String create_SQL =
			"Insert into [Shift](start, [end], FK_worker_ID)"
			+ "  values (?, ?, ?)";
	private PreparedStatement create;
	private static final String update_SQL =
			"update shift set [end] = ? where FK_worker_ID = ? and start = ?";
	private PreparedStatement update;
	private static final String FIND_Last_Month_Shifts_By_Worker_Number_SQL = "SELECT * \r\n"
			+ "FROM shift \r\n"
			+ "WHERE start BETWEEN FORMAT(GETDATE(), 'yyyy-MM-01') \r\n"
			+ "AND EOMONTH(GETDATE()) AND FK_worker_ID = ?;";
	private PreparedStatement findLastMonthShiftsByWorkerNumber;
	
	public ShiftDB() throws SQLException {
		con = DBConnection.getInstance().getConnection();
		create = con.prepareStatement(create_SQL);
		update = con.prepareStatement(update_SQL);
		findLastMonthShiftsByWorkerNumber = con.prepareStatement(FIND_Last_Month_Shifts_By_Worker_Number_SQL);
		formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	}

	@Override
	public void addShiftToDB(LocalDateTime now, String workerNumber) throws SQLException {
        String formattedDateTime = now.format(formatter);
		
		create.setString(1, formattedDateTime);
		create.setString(2, null);
		create.setString(3, workerNumber);
		
		create.executeUpdate();
	}

	@Override
	public List<Shift> createShiftsObject(String workerNumber) throws SQLException {
		findLastMonthShiftsByWorkerNumber.setString(1, workerNumber);
		
		ResultSet rs = findLastMonthShiftsByWorkerNumber.executeQuery();
		List<Shift> res = buildObjectList(rs);
		return res;
	}

	private List<Shift> buildObjectList(ResultSet rs) throws SQLException {
		List<Shift> res = new ArrayList<>();
		while(rs.next()) {
			res.add(buildObject(rs));
		}
		return res;
	}

	private Shift buildObject(ResultSet rs) throws SQLException {
		String startString = rs.getString("start");
		LocalDateTime start = LocalDateTime.parse(startString, formatter);
		
		String endString = rs.getString("end");
		LocalDateTime end = LocalDateTime.parse(endString, formatter);
		
		Shift foundShift = new Shift(start, end);
		return foundShift;
	}

	@Override
	public void updateShiftInDB(String start, LocalDateTime end, String workerNumber) throws SQLException {
        String formattedDateTime = end.format(formatter);
        
		update.setString(1, formattedDateTime);
		update.setString(2, workerNumber);
		update.setString(3, start);
		
		update.executeUpdate();
	}

}
