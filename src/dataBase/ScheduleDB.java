package dataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import model.Schedule;
import model.Shift;

public class ScheduleDB implements ScheduleDBIF{
	private Connection con;
	private DateTimeFormatter formatter;
	
	public ScheduleDB() throws SQLException {
		con = DBConnection.getInstance().getConnection();
		create = con.prepareStatement(create_SQL);
		update = con.prepareStatement(update_SQL);
		formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
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
	public void updateScheduleInDB(String start, LocalDateTime end, String name) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
