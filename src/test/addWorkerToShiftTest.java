package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import model.Worker;
import model.Shift;

public class addWorkerToShiftTest {
	
	private Worker worker;
	private Shift shift;
	
	@Before
	public void setUp() throws SQLException {
		worker = new Worker ("name", "address", "PhoneNo", "CPR", "Email", "WorkerNumber", true);		
	}
	
	@Test
	public void AddWorkerToShift() throws SQLException {
		//Arrange
		shift = new Shift(null, null, false); //Creates a new instance of shift

		//Act
		shift.setWorker(worker); //Sets the worker to a shift object
		worker.addShift(shift); //Adds the shift to the workers list of shifts
		//Assert
		
		assertEquals(worker, shift.getWorker()); //Test if the worker is the same worker we initialized earlier
		assertTrue(worker.getListOfShifts().contains(shift)); //Test if the worker's list of shifts contains the shift we added
		System.out.println("Worker added to shift.");

	}

}
