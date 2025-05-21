package dataBase;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import model.Shift;
import model.Worker;

/**
 * The ShiftDB class handles the database operations related to shifts.
 * 
 * It provides methods for inserting new shift records, updating shift end times,
 * and retrieving recent shifts for a specific worker. It also transforms 
 * database records into Shift objects.
 * 
 * This class implements the ShiftDBIF interface.
 * 
 * The class uses prepared SQL statements for efficient and secure interaction
 * with the underlying database.
 * 
 * @author gruppe 2
 * @version 23-05-2025
 */
public class ShiftDB implements ShiftDBIF {
	private Connection con;
	private DateTimeFormatter formatter;
	private static final String create_SQL =
			"Insert into [Shift](start, [end], FK_worker_ID, past_or_future_shift)"
					+ "  values (?, ?, ?, ?)";
	private PreparedStatement create;
	private static final String update_SQL =
			"update shift set [end] = ? where FK_worker_ID = ? and start = ?";
	private PreparedStatement update;
	private static final String FIND_Last_Month_Shifts_By_Worker_Number_SQL = "SELECT * FROM shift WHERE start BETWEEN FORMAT(GETDATE(), \r\n"
			+ "  'yyyy-MM-01') AND EOMONTH(GETDATE()) \r\n"
			+ "  AND FK_worker_ID = ? and past_or_future_shift = 0;";
	private PreparedStatement findLastMonthShiftsByWorkerNumber;

	/**
	 * Constructs a ShiftDB instance and initializes prepared SQL statements and the formatter.
	 * 
	 * @throws SQLException if database connection or statement preparation fails.
	 */
	public ShiftDB() throws SQLException {
		con = DBConnection.getInstance().getConnection();
		create = con.prepareStatement(create_SQL);
		update = con.prepareStatement(update_SQL);
		findLastMonthShiftsByWorkerNumber = con.prepareStatement(FIND_Last_Month_Shifts_By_Worker_Number_SQL);
		formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * Adds a new shift entry to the database.
	 * 
	 * The new shift will have a start time and will initially have no end time.
	 * It is marked as a future shift
	 * 
	 * @param now the start time of the shift.
	 * @param workerNumber the worker's unique identifier.
	 * @throws SQLException if an error occurs during insertion.
	 */
	@Override
	public void addShiftToDB(LocalDateTime now, String workerNumber) throws SQLException {
		String formattedDateTime = now.format(formatter);

		create.setString(1, formattedDateTime);
		create.setString(2, null);
		create.setString(3, workerNumber);
		create.setInt(4, 0);

		create.executeUpdate();
	}

	/**
	 * Retrieves a list of Shift objects for a worker from the current month.
	 * 
	 * Only shifts marked as "past" are included.
	 * 
	 * @param workerNumber the worker's unique identifier.
	 * @return a list of Shift objects for the current month.
	 * @throws SQLException if an error occurs during the query or object building.
	 */
	@Override
	public List<Shift> createShiftsObject(String workerNumber) throws SQLException {
		findLastMonthShiftsByWorkerNumber.setString(1, workerNumber);

		ResultSet rs = findLastMonthShiftsByWorkerNumber.executeQuery();
		List<Shift> res = buildObjectList(rs);
		return res;
	}

	/**
	 * Converts a ResultSet of shift rows into a list of Shift objects.
	 * 
	 * @param rs the ResultSet from the database.
	 * @return a list of Shift objects.
	 * @throws SQLException if an error occurs while reading from the ResultSet.
	 */
	private List<Shift> buildObjectList(ResultSet rs) throws SQLException {
		List<Shift> res = new ArrayList<>();
		while(rs.next()) {
			res.add(buildObject(rs));
		}
		return res;
	}

	/**
	 * Builds a single Shift object from a ResultSet row.
	 * 
	 * @param rs the ResultSet currently pointing to the row to convert.
	 * @return a Shift object populated from the current row.
	 * @throws SQLException if an error occurs while reading values.
	 */
	private Shift buildObject(ResultSet rs) throws SQLException {
		String startString = rs.getString("start");
		startString = startString.split("\\.")[0];
		LocalDateTime start = LocalDateTime.parse(startString, formatter);

		String endString = rs.getString("end");
		LocalDateTime end = null;
		if(endString != null) {
			endString = endString.split("\\.")[0];
			end = LocalDateTime.parse(endString, formatter);
		}

		boolean pastOrFutureShift;
		int pastFuture = rs.getInt("past_or_future_shift");
		if(pastFuture == 0) {
			pastOrFutureShift = true;
		} else {
			pastOrFutureShift = false;
		}

		Shift foundShift = new Shift(start, end, pastOrFutureShift);
		return foundShift;
	}

	/**
	 * Updates the end time of a shift in the database for a specific worker and start time.
	 * 
	 * @param start the start time of the shift (as a string).
	 * @param end the end time to be set.
	 * @param workerNumber the worker's unique identifier.
	 * @throws SQLException if an error occurs during update.
	 */
	@Override
	public void updateShiftInDB(String start, LocalDateTime end, String workerNumber) throws SQLException {
		String formattedDateTime = end.format(formatter);

		update.setString(1, formattedDateTime);
		update.setString(2, workerNumber);
		update.setString(3, start);

		update.executeUpdate();
	}

}