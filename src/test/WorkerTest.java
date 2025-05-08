package test;


import model.Shift;
import model.Worker;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class WorkerTest {

	@Test
	public void testCreateWorkerAndAddShift() {
		// Arrange
		Worker worker = new Worker("Mikkel Nielsen", "Gaden", "21786500", "mikkelnielsen@gmail.com", "010203", false);
		LocalDateTime start = LocalDateTime.of(2024, 5, 8, 8, 0);
		LocalDateTime end = LocalDateTime.of(2024, 5, 8, 16, 0);
		Shift shift = new Shift(start);
		shift.setEnd(end);

		// Act
		worker.addShift(shift);

		// Assert
		assertEquals("Mikkel Nielsen", worker.getName());
		assertEquals("010203", worker.getWorkerNumber());
		assertFalse(worker.isAtWork());
		assertEquals(1, worker.getListOfShifts().size());
		assertEquals(start, worker.getListOfShifts().get(0).getStart());
		assertEquals(end, worker.getListOfShifts().get(0).getEnd());
	}

	@Test
	public void testSetAndGetWorkerAttributes() {
		Worker worker = new Worker("Name", "Street", "Phone Number", "E-mail", "Worker Number", true);
		worker.setName("Mikkel Nielsen");
		worker.setAddress("Gaden");
		worker.setPhoneNo("21786500");
		worker.setEmail("mikkelnielsen@gmail.com");
		worker.setWorkerNumber("010203");
		worker.setAtWork(false);

		assertEquals("Mikkel Nielsen", worker.getName());
		assertEquals("Gaden", worker.getAddress());
		assertEquals("21786500", worker.getPhoneNo());
		assertEquals("mikkelnielsen@gmail.com", worker.getEmail());
		assertEquals("010203", worker.getWorkerNumber());
		assertFalse(worker.isAtWork());
	}

	@Test
	public void testCreateWorkerAndAddShift2() {
		// Arrange
		Worker worker = new Worker("Mikkel Nielsen", "Gaden", "21786500", "mikkelnielsen@gmail.com", "999999", false);
		LocalDateTime start = LocalDateTime.of(2024, 5, 8, 8, 0);
		LocalDateTime end = LocalDateTime.of(2024, 5, 8, 16, 0);
		Shift shift = new Shift(start);
		shift.setEnd(end);

		// Act
		worker.addShift(shift);

		// Assert
		assertEquals("Mikkel Nielsen", worker.getName());
		assertEquals("999999", worker.getWorkerNumber());
		assertFalse(worker.isAtWork());
		assertEquals(1, worker.getListOfShifts().size());
		assertEquals(start, worker.getListOfShifts().get(0).getStart());
		assertEquals(end, worker.getListOfShifts().get(0).getEnd());
	}

	@Test
	public void testSetWorkerWithWorkerNumberNotInTheSystem() {
		Worker worker = new Worker("Name", "Street", "Phone Number", "E-mail", "Worker Number", true);
		worker.setName("Mikkel Nielsen");
		worker.setAddress("Gaden");
		worker.setPhoneNo("21786500");
		worker.setEmail("mikkelnielsen@gmail.com");
		worker.setWorkerNumber("999999");
		worker.setAtWork(false);

		assertEquals("Mikkel Nielsen", worker.getName());
		assertEquals("Gaden", worker.getAddress());
		assertEquals("21786500", worker.getPhoneNo());
		assertEquals("mikkelnielsen@gmail.com", worker.getEmail());
		assertNotEquals("010203", worker.getWorkerNumber());
		assertFalse(worker.isAtWork());
	}

	@Test
	public void testWorkerNumberWithTooManyDigits() {
		// Arrange

		String tooLongWorkerNumber = "9999999999999999999999999999999";
		// Act + Assert
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,() -> {

			Worker worker = new Worker("Mikkel Nielsen", "Gaden", "21786500", "mikkelnielsen@gmail.com", tooLongWorkerNumber, false);
			LocalDateTime start = LocalDateTime.of(2024, 5, 8, 8, 0);
			LocalDateTime end = LocalDateTime.of(2024, 5, 8, 16, 0);
			Shift shift = new Shift(start);
			shift.setEnd(end);
			worker.addShift(shift);

			
		});
		
		String expectedMessage = "Worker number must be 30 digits or fewer";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));


	}
	
	@Test
	public void testWorkerNumberWithZeroDigits() {
		// Arrange

		String zeroDigitWorkerNumber = "";
		// Act + Assert
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,() -> {

			Worker worker = new Worker("Mikkel Nielsen", "Gaden", "21786500", "mikkelnielsen@gmail.com", zeroDigitWorkerNumber, false);
			LocalDateTime start = LocalDateTime.of(2024, 5, 8, 8, 0);
			LocalDateTime end = LocalDateTime.of(2024, 5, 8, 16, 0);
			Shift shift = new Shift(start);
			shift.setEnd(end);
			worker.addShift(shift);

			
		});
		
		String expectedMessage = "Worker number must be 1 digit or more";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));


	}
}
    