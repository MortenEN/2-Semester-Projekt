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

public class ScheduleDB implements ScheduleDBIF {
	private Connection con;
	private Schedule schedule;
	private DateTimeFormatter formatter;


	private PreparedStatement create;
	private static final String create_SQL = "Insert into [Schedule](start, [end], name)" + "  values (?, ?, ?)";
	private PreparedStatement update;
	private static final String update_SQL = "update [Schedule] set [end] = ? where name = ? and start = ?";
	private PreparedStatement checkExists;
	private static final String checkExists_SQL = "SELECT COUNT(*) AS total FROM [Schedule] WHERE name = ? AND start = ?";

	public ScheduleDB() throws SQLException {
		con = DBConnection.getInstance().getConnection();
		formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		create = con.prepareStatement(create_SQL);
		update = con.prepareStatement(update_SQL);
		checkExists = con.prepareStatement(checkExists_SQL);

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
	public void saveSchedule(Schedule schedule) { //Tror ikke den er lavet rigtigt
		try {
			if (!scheduleExists(schedule)) { //Tjekker om schedule findes med samme navn og start dato
				createScheduleObject(schedule.getStart(), schedule.getEnd(), schedule.getName()); //hvis den ikke gør, kalder den createScheduleObject og gemmer 
			} else {
				System.out.println("Schedule findes allerede – gemmer ikke igen.");
			}
		} catch (SQLException e) {
			e.printStackTrace(); 
		}
	}
	
	public boolean scheduleExists(Schedule schedule) throws SQLException {
		checkExists.setString(1, schedule.getName());
		checkExists.setString(2, schedule.getStart().format(formatter));
		
		try(ResultSet rs = checkExists.executeQuery()){
			if(rs.next()) {
				int count = rs.getInt("total");
				return count > 0;
			}
		}
		
		
		return false;
	}
	
	public void updateSchedule(Schedule schedule) { //???????????????
		try {
			update.setString(1, schedule.getEnd() != null ? schedule.getEnd().format(formatter) : null); //Sætter værdien til schedule.getEnd hvis det ikke er null ellers indsætter vi null. EKS hvis end = 2025-05-15 bliver det til 2025-05-15
			update.setString(2, schedule.getName()); //Den matcher "name" f.eks uge 20.
			update.setString(3, schedule.getStart().format(formatter)); //Matcher "AND start = ?" som i vores SQL
			
			update.executeUpdate(); //Kører SQL kommandoen
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	
	}

}


