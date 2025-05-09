package test;

import model.Shift;
import model.Worker;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class WorkerTest {

	private Worker worker;

	@BeforeEach
	public void setUp() {
		worker = new Worker("Mikkel Nielsen", "Gaden", "21786500", "mikkelnielsen@gmail.com","010203", true);
	}

	@Test
	public void testConstructerForWorker() {
		assertEquals("Mikkel Nielsen",worker.getName());
		assertEquals("Gaden", worker.getAddress());
		assertEquals("21786500", worker.getPhoneNo());
		assertEquals("mikkelnielsem@gmail.com", worker.getEmail());
		assertEquals("010203", worker.getWorkerNumber());
		assertTrue(worker.isAtWork());
		assertNotNull(worker.getListOfShifts());
		assertEquals(0, worker.getListOfShifts().size());

	}
	
	
	
}


