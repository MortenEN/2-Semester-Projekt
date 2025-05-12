package test;

import model.Worker;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WorkerTest {

    private Worker worker;

    @BeforeEach
    public void setUp() {
        worker = new Worker("Mikkel Nielsen", "Gaden", "21786500", "050607-5467","mikkelnielsen@gmail.com", "010203", true);
    }

    @Test
    public void testConstructerForWorker() {
        assertEquals("Mikkel Nielsen",worker.getName());
        assertEquals("Gaden", worker.getAddress());
        assertEquals("050607-5467",worker.getCpr());
        assertEquals("21786500", worker.getPhoneNo());
        assertEquals("mikkelnielsen@gmail.com", worker.getEmail());
        assertEquals("010203", worker.getWorkerNumber());
        assertTrue(worker.isAtWork());
        assertNotNull(worker.getListOfShifts());
        assertEquals(0, worker.getListOfShifts().size());

    }

    @After
    public void tearDown() throws Exception {
    }