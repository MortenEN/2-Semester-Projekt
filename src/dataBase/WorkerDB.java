package dataBase;

import java.sql.*;

import model.Worker;

public class WorkerDB {
	private Connection con;
	private static final String FIND_Worker_By_Worker_Number_SQL = "select * from worker where workernumber = ?";
	private PreparedStatement findWorkerByWorkerNumber;
	private static final String FIND_City_From_Worker = "select *\r\n"
			+ "from worker, city, country where worker.FK_city_ID = city.cityID and city.FK_country_ID = 1";
	private Statement findCityFromWorker;
	
	public WorkerDB() throws SQLException {
		con = DBConnection.getInstance().getConnection();
		findWorkerByWorkerNumber = con.prepareStatement(FIND_Worker_By_Worker_Number_SQL);
		findCityFromWorker = con.prepareStatement(FIND_City_From_Worker);
	}
	
	public Worker findWorkerByWorkerNumber(String workerNumber) throws SQLException {
		Worker worker;
		ResultSet rs;
		
		findWorkerByWorkerNumber.setString(1, workerNumber);
		rs = findWorkerByWorkerNumber.executeQuery();
		
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
		String workerNumber = rs.getString("workerNumber");
		
		Worker foundWorker = new Worker(name, address, phoneNo, email, workerNumber);
		return foundWorker; //TODO Check names in strings 
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

}
