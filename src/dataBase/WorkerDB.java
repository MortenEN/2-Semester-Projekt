package dataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Shift;
import model.Worker;


/**
 * WorkerDB handles the database access related to Worker objects.
 * 
 * This class performs various operations such as finding a worker by worker number
 * or name, changing sign-in status, retrieving active shifts, and listing all workers at work.
 * It utilizes prepared SQL statements for secure and efficient access to the database.
 * 
 * WorkerDB implements the WorkerDBIF interface.
 * 
 * @author gruppe 2
 * @version 23-05-2025
 */

public class WorkerDB implements WorkerDBIF {
	private Connection con;
	private static final String FIND_Worker_By_Worker_Number_SQL = "select * from worker where workerid = ?";
	private PreparedStatement findWorkerByWorkerNumber;

	private static final String FIND_City_From_Worker = "select *" + "from worker, city, country where worker.FK_city_ID = city.cityID and city.FK_country_ID = 1";
	private Statement findCityFromWorker;

	private static final String Change_Work_Status_To_SignedIn_SQL = "update worker set signedIn = 1 where workerid = ?";
	private PreparedStatement changeStatusIn;

	private static final String Change_Work_Status_To_SignedOut_SQL = "update worker set signedIn = 0 where workerid = ?";
	private PreparedStatement changeStatusOut;

	private static final String FIND_All_Workers_At_Work_SQL = "select * from worker where signedIn = 1";
	private PreparedStatement findAllWorkersAtWork;

	private static final String FIND_Active_Shift_SQL = "SELECT * \r\n" + "FROM worker \r\n" + "JOIN shift ON worker.workerid = shift.FK_worker_ID \r\n" + "WHERE worker.workerid = ? and shift.[end] is null;";
	private PreparedStatement findActiveShift;

	private static final String FIND_Worker_By_Name_SQL = "select * from Worker where name LIKE ?"; 
	private PreparedStatement findWorkerByName;

	/**
	 * Constructor for WorkerDB that initializes all SQL statements.
	 * 
	 * @throws SQLException if there is an error connecting or preparing statements.
	 */
	public WorkerDB() throws SQLException {
		con = DBConnection.getInstance().getConnection();
		findWorkerByWorkerNumber = con.prepareStatement(FIND_Worker_By_Worker_Number_SQL);
		findWorkerByName = con.prepareStatement(FIND_Worker_By_Name_SQL);
		changeStatusIn = con.prepareStatement(Change_Work_Status_To_SignedIn_SQL);
		changeStatusOut = con.prepareStatement(Change_Work_Status_To_SignedOut_SQL);
		findAllWorkersAtWork = con.prepareStatement(FIND_All_Workers_At_Work_SQL);
		findCityFromWorker = con.createStatement();
		findActiveShift = con.prepareStatement(FIND_Active_Shift_SQL);
	}


	/**
	 * Finds a worker by their worker number.
	 * If found, it also toggles their sign-in status.
	 * 
	 * @param workerNumber the unique identifier of the worker.
	 * @return Worker object with the information retrieved from the database.
	 * @throws SQLException if a database access error occurs.
	 */
	@Override
	public Worker findWorkerByWorkerNumber(String workerNumber) throws SQLException {
		Worker worker;
		ResultSet rs;

		findWorkerByWorkerNumber.setString(1, workerNumber);
		rs = findWorkerByWorkerNumber.executeQuery();
		if(rs.next()) {
			if(rs.getInt("signedIn") == 0) {
				changeStatusIn.setString(1, workerNumber);
				changeStatusIn.executeUpdate();
			} else {
				changeStatusOut.setString(1, workerNumber);
				changeStatusOut.executeUpdate();
			}
		}
		worker = buildObject(rs);
		return worker;
	}
	
	/**
	 * Finds a worker by their name (starting with).
	 * 
	 * @param name partial or full name of the worker.
	 * @return Worker object found, or null if none found.
	 * @throws SQLException if a database access error occurs.
	 */
	@Override
	public Worker findWorkerByName(String name) throws SQLException {
		Worker worker = null;
		ResultSet rs;

		findWorkerByName.setString(1, name + "%");
		rs = findWorkerByName.executeQuery();

		if(rs.next()) {
			worker = buildObject(rs);
		}
		return worker;
	}

	/**
	 * Builds a Worker object from the current row in the result set.
	 * 
	 * @param rs the result set positioned at a row.
	 * @return Worker object with values from the result set.
	 * @throws SQLException if a database access error occurs.
	 */
	public Worker buildObject(ResultSet rs) throws SQLException {
		String name = rs.getString("name");

		String streetName = rs.getString("streetName");
		int houseNumberInt = rs.getInt("houseNumber");
		String houseNumber = Integer.toString(houseNumberInt);
		String cityCountry = getCityAndCountryFromWorker();
		String address = streetName + " " + houseNumber + ", " + cityCountry;

		String phoneNo = rs.getString("phoneNo");
		String cpr = rs.getString("cpr");
		String email = rs.getString("email");
		String workerNumber = rs.getString("workerId");
		int atWorkInt = rs.getInt("signedIn");
		boolean atWork;
		if(atWorkInt == 1) {
			atWork = false;
		} else {
			atWork = true;
		}

		Worker foundWorker = new Worker(name, address, phoneNo, cpr, email, workerNumber, atWork);

		return foundWorker;
	}

	/**
	 * Builds a list of Worker objects from the result set.
	 * 
	 * @param rs the result set containing multiple rows.
	 * @return List of Worker objects.
	 * @throws SQLException if a database access error occurs.
	 */
	public List<Worker> buildObjectList(ResultSet rs) throws SQLException {
		List<Worker> res = new ArrayList<>();
		while(rs.next()) {
			res.add(buildObject(rs));
		}
		return res;
	}

	/**
	 * Retrieves the city and country for the current worker.
	 * 
	 * @return String representing city and country combined.
	 * @throws SQLException if a database access error occurs.
	 */
	public String getCityAndCountryFromWorker() throws SQLException {
		String city = "";
		String country = "";
		ResultSet rs;
		rs = findCityFromWorker.executeQuery(FIND_City_From_Worker);
		if(rs.next()) {
			city = rs.getString("city");
			country = rs.getString("country");
		}

		String cityCountry = city + " " + country;
		return cityCountry;
	} 

	/**
	 * Finds and returns all workers that are currently signed in (at work).
	 * 
	 * @return List of workers currently at work.
	 * @throws SQLException if a database access error occurs.
	 */
	@Override
	public List<Worker> findWorkersAtWork() throws SQLException {
		ResultSet rs = findAllWorkersAtWork.executeQuery();
		List<Worker> res = buildObjectList(rs);
		return res;
	}

	/**
	 * Finds the start time of the currently active shift for a worker.
	 * 
	 * @param login the worker's login or worker ID.
	 * @return String representing the start time of the shift, or empty if none active.
	 * @throws SQLException if a database access error occurs.
	 */
	@Override
	public String findActiveShift(String login) throws SQLException {
		String start = "";
		ResultSet rs;

		findActiveShift.setString(1, login);
		rs = findActiveShift.executeQuery();

		if(rs.next()) {
			start = rs.getString("start");
		}

		return start;
	}
}
