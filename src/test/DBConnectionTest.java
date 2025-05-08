package test;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

import dataBase.DBConnection;

public class DBConnectionTest {
	static DBConnection con = null;

	@Before
	public void setUp() {
		con = DBConnection.getInstance();
	}
	
	@Test
	public void wasConnected() {
		con.disconnect();
		boolean wasNullified = DBConnection.instanceIsNull();
		assertTrue(wasNullified, "Disconnected - instance set to null");
		
		con = DBConnection.getInstance();
		boolean connectionIsOpen = DBConnection.getOpenStatus();
		assertTrue(connectionIsOpen);	
	}
}
