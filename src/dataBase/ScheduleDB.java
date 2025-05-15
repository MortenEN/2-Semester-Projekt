package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import model.Schedule;
import model.Shift;
import model.Worker;

public class ScheduleDB implements ScheduleDBIF{
	private Connection con;
	private Schedule schedule;
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
	public Schedule buildObject(ResultSet rs) {
		rs=null;
		
		return rs;
	}

	@Override
	public void createScheduleObject(LocalDate start, LocalDate end, String name) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setShift(Shift shift) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addWorkerToShift(Worker worker) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveSchedule(Schedule schedule) {
		// TODO Auto-generated method stub
		
	}

}
