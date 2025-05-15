package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import model.Schedule;

public class ScheduleDB implements ScheduleDBIF{
	private Connection con;
	private DateTimeFormatter formatter;


	private PreparedStatement create;
	private static final String create_SQL = "Insert into [Schedule](start, [end], name)"
			+ "  values (?, ?, ?)";
	private PreparedStatement update;
	private static final String update_SQL = "update [Schedule] set [end] = ? where name = ? and start = ?";

	public ScheduleDB() throws SQLException {
		con = DBConnection.getInstance().getConnection();
		create = con.prepareStatement(create_SQL);
		update = con.prepareStatement(update_SQL);
		formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	}

	@Override
	public void addScheduleToDB(LocalDate start, LocalDate end, String name) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Schedule> createScheduleObject(String workerNumber) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateScheduleInDB(String start, LocalDate end, String name) throws SQLException {
		// TODO Auto-generated method stub

	}

}
