package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * The DBConnection class manages the database connection to a Microsoft SQL Server.
 * 
 * This class is implemented as a singleton, ensuring only one connection instance is active 
 * during the application’s lifecycle. It supports connection initialization, transaction 
 * management, and execution of SQL operations including inserts with auto-generated keys.
 * 
 * It provides helper methods to execute SQL statements and retrieve a connection object.
 * 
 * @author gruppe 2
 * @version 23-05-2025
 */
public class DBConnection {
	// Static fields
	private static Connection connection = null;
	private static DBConnection dbConnection;
	// Database configuration
	private static final String driverClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String dbName = "DMA-CSD-S241_10434745";
	private static final String serverAddress = "hildur.ucn.dk";
	//private static final String serverAddress = "192.168.56.2";
	private static final int    serverPort = 1433;
	private static final String userName = "DMA-CSD-S241_10434745";
	private static final String password = "Password1!";

	/**
	 * Private constructor for initializing the database connection.
	 * Sets up the connection string, loads the JDBC driver, and connects to the database.
	 */

	private DBConnection() {
		String connectionString = String.format("jdbc:sqlserver://%s:%d;databaseName=%s;user=%s;password=%s;encrypt=false", 
				serverAddress, serverPort, dbName, userName, password);
		try {
			Class.forName(driverClass);
			connection = DriverManager.getConnection(connectionString);
		} catch (ClassNotFoundException e) {
			System.err.println("Could not load JDBC driver");
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Could not connect to database " + dbName + "@" + serverAddress + ":" + serverPort + " as user " + userName + " using password ******");
			System.out.println("Connection string was: " + connectionString.substring(0, connectionString.length() - password.length()) + "....");
			e.printStackTrace();
		}
	}
	/**
	 * Returns the single instance of the DBConnection class.
	 * Initializes the connection if it does not exist.
	 * 
	 * @return the singleton instance of DBConnection.
	 */
	public static DBConnection getInstance() {
		if(dbConnection == null) {
			dbConnection = new DBConnection();
		}
		return dbConnection;
	}
	/**
	 * Begins a new database transaction.
	 * 
	 * @throws SQLException if an error occurs while starting the transaction.
	 */
	public void startTransaction() throws SQLException {
		connection.setAutoCommit(false);
	}
	/**
	 * Commits the current transaction and resets auto-commit mode.
	 * 
	 * @throws SQLException if an error occurs while committing.
	 */
	public void commitTransaction() throws SQLException {
		connection.commit();
		connection.setAutoCommit(true);
	}
	/**
	 * Rolls back the current transaction and resets auto-commit mode.
	 * 
	 * @throws SQLException if an error occurs while rolling back.
	 */
	public void rollbackTransaction() throws SQLException {
		connection.rollback();
		connection.setAutoCommit(true);
	}
	/**
	 * Executes a prepared insert statement and returns the generated identity (primary key).
	 * 
	 * @param ps the prepared statement to execute.
	 * @return the generated ID if successful; -1 otherwise.
	 * @throws SQLException if an error occurs during execution.
	 */
	public int executeInsertWithIdentity(PreparedStatement ps) throws SQLException  {
		int res = -1;
		try {
			res = ps.executeUpdate();
			if(res > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				rs.next();
				res = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return res;
	}
	/**
	 * Executes an insert SQL statement and returns the generated identity (primary key).
	 * 
	 * @param sql the SQL insert query to execute.
	 * @return the generated ID if successful; -1 otherwise.
	 * @throws SQLException if an error occurs during execution.
	 */
	public int executeInsertWithIdentity(String sql) throws SQLException  {
		System.out.println("DBConnection, Inserting: " + sql);
		int res = -1;
		try (Statement s = connection.createStatement()) {
			res = s.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			if(res > 0) {
				ResultSet rs = s.getGeneratedKeys();
				rs.next();
				res = rs.getInt(1);
			}
			//s.close(); -- the try block does this for us now

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return res;
	}
	/**
	 * Executes a generic SQL update statement.
	 * 
	 * @param sql the SQL update query to execute.
	 * @return the number of affected rows.
	 * @throws SQLException if an error occurs during execution.
	 */
	public int executeUpdate(String sql) throws SQLException {
		System.out.println("DBConnection, Updating: " + sql);
		int res = -1;
		try (Statement s = connection.createStatement()){
			res = s.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return res;
	}

	/**
	 * Gets the current database connection.
	 * 
	 * @return the active Connection object.
	 */
	public Connection getConnection() {
		return connection;
	}
	/**
	 * Closes the database connection and resets the singleton instance.
	 */
	public void disconnect() {
		try {
			connection.close();
			dbConnection = null;
			System.out.println("The connection is closed");
		} catch (SQLException e) {
			System.out.println("Error trying to close the database " + e.getMessage());
		}
	}
	/**
	 * Checks whether the current database connection is open.
	 * 
	 * @return {@code true} if the connection is open; {@code false} otherwise.
	 */
	public static boolean getOpenStatus() {
		boolean isOpen = false;
		try {
			isOpen = (!connection.isClosed());
		} catch (Exception sclExc) {
			isOpen = false;
		}
		return isOpen;
	}
	/**
	 * Checks if the singleton instance is currently null.
	 * 
	 * @return {@code true} if no instance exists; {@code false} otherwise.
	 */
	public static boolean instanceIsNull() {
		return (dbConnection == null);
	}

}