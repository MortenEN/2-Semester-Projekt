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
/**
 * The ScheduleDB class is responsible for interacting with the database to handle operations 
 * related to the Schedule entity. This includes creating schedule records, updating them, and 
 * managing associated shifts and workers.
 * 
 * The ScheduleDB class communicates with the database using SQL queries via JDBC. It formats 
 * dates appropriately and ensures that schedules are not duplicated based on their name and start date.
 * 
 * The class also allows setting shifts and assigning workers to all shifts within a schedule.
 * 
 * @author gruppe 2
 * @version 23-05-2025
 */
public class ScheduleDB implements ScheduleDBIF {
	// Fields
	private Connection con;
	private Schedule schedule;
	private DateTimeFormatter formatter;


	private PreparedStatement create;
	private static final String create_SQL = "Insert into [Schedule](start, [end], name)" + "  values (?, ?, ?)";
	private PreparedStatement update;
	private static final String update_SQL = "update [Schedule] set [end] = ? where name = ? and start = ?";
	private PreparedStatement checkExists;
	private static final String checkExists_SQL = "SELECT COUNT(*) AS total FROM [Schedule] WHERE name = ? AND start = ?";
	/**
	 * Constructor for ScheduleDB that initializes the database connection,
	 * date formatter, and prepares SQL statements for various operations.
	 * 
	 * @throws SQLException if a database access error occurs.
	 */
	public ScheduleDB() throws SQLException {
		con = DBConnection.getInstance().getConnection();
		formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		create = con.prepareStatement(create_SQL);
		update = con.prepareStatement(update_SQL);
		checkExists = con.prepareStatement(checkExists_SQL);

	}
	/**
	 * Builds a Schedule object from a ResultSet retrieved from the database.
	 * 
	 * @param rs the ResultSet containing schedule data.
	 * @return a Schedule object initialized with data from the ResultSet.
	 * @throws SQLException if an error occurs during data extraction.
	 */
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

	/**
	 * Creates and saves a new Schedule object in the database.
	 * 
	 * @param start the start date of the schedule.
	 * @param end the end date of the schedule.
	 * @param name the name of the schedule.
	 * @return the newly created Schedule object.
	 * @throws SQLException if an error occurs during the database operation.
	 */
	@Override
	public Schedule createScheduleObject(LocalDate start, LocalDate end, String name) throws SQLException {
		create.setString(1, start.format(formatter));
		create.setString(2, end.format(formatter));
		create.setString(3, name);

		create.executeUpdate();
		return new Schedule(start, end, name);
	}
	/**
	 * Adds a shift to the current schedule's list of shifts.
	 * 
	 * @param shift the Shift object to be added.
	 */
	@Override
	public void setShift(Shift shift) {
		this.schedule.getListOfShifts().add(shift);

	}
	/**
	 * Adds a worker to all shifts in the current schedule.
	 * 
	 * @param worker the Worker to be assigned to each shift.
	 */
	@Override
	public void addWorkerToShift(Worker worker) {
		for (Shift shift : schedule.getListOfShifts()) {
			worker.addShift(shift);
		}
	}
	/**
	 * Saves the given schedule to the database only if it doesn't already exist.
	 * If the schedule is new, it is saved along with its shifts and workers.
	 * 
	 * @param schedule the Schedule object to be saved.
	 */
	@Override
	public void saveSchedule(Schedule schedule) { //Tror ikke den er lavet rigtigt
		try {
			if (!scheduleExists(schedule)) { //Tjekker om schedule findes med samme navn og start dato
				createScheduleObject(schedule.getStart(), schedule.getEnd(), schedule.getName()); //hvis den ikke gør, kalder den createScheduleObject og gemmer 
				System.out.println("Alt lykkedes! Schedule gemt med shift og worker.");
			} else {
				System.out.println("Schedule findes allerede – Gemmer ikke igen.");
			}
		} catch (SQLException e) {
			e.printStackTrace(); 
		}
	}
	/**
	 * Checks whether a schedule already exists in the database based on its name and start date.
	 * 
	 * @param schedule the Schedule object to be checked.
	 * @return {@code true} if a matching schedule exists; {@code false} otherwise.
	 * @throws SQLException if an error occurs during the query.
	 */
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
	/**
	 * Updates the end date of an existing schedule in the database.
	 * 
	 * @param schedule the Schedule object containing the updated end date.
	 */
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


