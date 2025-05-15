package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import model.Schedule;
import model.Shift;
import model.Worker;

public class ScheduleDB implements ScheduleDBIF{
	private Connection con;
	private Schedule schedule;
	private DateTimeFormatter formatter;


	private PreparedStatement create;
	private static final String create_SQL = "Insert into [Schedule](start, [end], name)" + "  values (?, ?, ?)";
	private PreparedStatement update;
	private static final String update_SQL = "update [Schedule] set [end] = ? where name = ? and start = ?";

	public ScheduleDB() throws SQLException {
		con = DBConnection.getInstance().getConnection();
		formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		create = con.prepareStatement(create_SQL);
		update = con.prepareStatement(update_SQL);

	}
	public Schedule buildObject(ResultSet rs) throws SQLException {
		String startString = rs.getString("start");
		LocalDate start = LocalDate.parse(startString, formatter);

		String endString = rs.getString("end");
		LocalDate end = null;
		if (endString != null) {
			end = LocalDate.parse(endString, formatter);
		}

		String name = rs.getString("name");
		return new Schedule(start, end, name);
	}


	@Override
	public Schedule createScheduleObject(LocalDate start, LocalDate end, String name) throws SQLException {
		create.setString(1, start.format(formatter));
		create.setString(2, end.format(formatter));
		create.setString(3, name);
		
		create.executeUpdate();
		return new Schedule(start, end, name);
	}

	@Override
	public void setShift(Shift shift) {
		this.schedule.getListOfShifts().add(shift);

	}

	@Override
	public void addWorkerToShift(Worker worker) {
		for (Shift shift : schedule.getListOfShifts()) {
			worker.addShift(shift);
		}
	}

	@Override
	public void saveSchedule(Schedule schedule) {
		// TODO Auto-generated method stub

	}

}
