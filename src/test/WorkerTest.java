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
        Worker worker = new Worker("Test Navn", "Testvej 1", "12345678", "test@mail.com", "W001", false);
        LocalDateTime start = LocalDateTime.of(2024, 5, 8, 8, 0);
        LocalDateTime end = LocalDateTime.of(2024, 5, 8, 16, 0);
        Shift shift = new Shift(start);
        shift.setEnd(end);

        // Act
        worker.addShift(shift);

        // Assert
        assertEquals("Test Navn", worker.getName());
        assertEquals("W001", worker.getWorkerNumber());
        assertFalse(worker.isAtWork());
        assertEquals(1, worker.getListOfShifts().size());
        assertEquals(start, worker.getListOfShifts().get(0).getStart());
        assertEquals(end, worker.getListOfShifts().get(0).getEnd());
    }

    @Test
    public void testSetAndGetWorkerAttributes() {
        Worker worker = new Worker("A", "B", "C", "D", "E", true);
        worker.setName("Test Name");
        worker.setAddress("New Street");
        worker.setPhoneNo("88888888");
        worker.setEmail("new@mail.com");
        worker.setWorkerNumber("W999");
        worker.setAtWork(false);

        assertEquals("Test Name", worker.getName());
        assertEquals("New Street", worker.getAddress());
        assertEquals("88888888", worker.getPhoneNo());
        assertEquals("new@mail.com", worker.getEmail());
        assertEquals("W999", worker.getWorkerNumber());
        assertFalse(worker.isAtWork());
    }
}