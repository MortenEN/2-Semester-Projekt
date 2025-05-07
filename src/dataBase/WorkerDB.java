package dataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Worker;

public class WorkerDB implements WorkerDBIF {
	private Connection con;
	private static final String FIND_Worker_By_Worker_Number_SQL = "select * from worker where workerid = ?";
	private PreparedStatement findWorkerByWorkerNumber;
	private static final String FIND_City_From_Worker = "select *"
			+ "from worker, city, country where worker.FK_city_ID = city.cityID and city.FK_country_ID = 1";
	private Statement findCityFromWorker;
	private static final String Change_Work_Status_To_SignedIn_SQL = "update worker set signedIn = 1 where workerid = ?";
	private PreparedStatement changeStatusIn;
	private static final String Change_Work_Status_To_SignedOut_SQL = "update worker set signedIn = 0 where workerid = ?";
	private PreparedStatement changeStatusOut;
	private static final String FIND_All_Workers_At_Work_SQL = "select * from worker where signedIn = 1";
	private PreparedStatement findAllWorkersAtWork;

	public WorkerDB() throws SQLException {
		con = DBConnection.getInstance().getConnection();
		findWorkerByWorkerNumber = con.prepareStatement(FIND_Worker_By_Worker_Number_SQL);
		changeStatusIn = con.prepareStatement(Change_Work_Status_To_SignedIn_SQL);
		changeStatusOut = con.prepareStatement(Change_Work_Status_To_SignedOut_SQL);
		findAllWorkersAtWork = con.prepareStatement(FIND_All_Workers_At_Work_SQL);
		findCityFromWorker = con.createStatement();
	}

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

	public Worker buildObject(ResultSet rs) throws SQLException {
		String name = rs.getString("name");

		String streetName = rs.getString("streetName");
		int houseNumberInt = rs.getInt("houseNumber");
		String houseNumber = Integer.toString(houseNumberInt);
		String cityCountry = getCityAndCountryFromWorker();
		String address = streetName + " " + houseNumber + ", " + cityCountry;

		String phoneNo = rs.getString("phoneNo");
		String email = rs.getString("email");
		String workerNumber = rs.getString("workerId");
		int atWorkInt = rs.getInt("signedIn");
		boolean atWork;
		if(atWorkInt == 1) {
			atWork = false;
		} else {
			atWork = true;
		}

		Worker foundWorker = new Worker(name, address, phoneNo, email, workerNumber, atWork);
		return foundWorker;
	}

	public List<Worker> buildObjectList(ResultSet rs) throws SQLException {
		List<Worker> res = new ArrayList<>();
		while(rs.next()) {
			res.add(buildObject(rs));
		}
		return res;
	}

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

	@Override
	public List<Worker> findWorkersAtWork() throws SQLException {
		ResultSet rs = findAllWorkersAtWork.executeQuery();
		List<Worker> res = buildObjectList(rs);
		return res;
	}

}
