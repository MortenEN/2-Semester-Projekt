package test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import controller.ScheduleCtr;
import model.Schedule;
import model.Shift;
import model.Worker;

public class TryMe {
	public static void main(String[] args) {
		try {



			ScheduleCtr scheduleCtr = new ScheduleCtr();


			LocalDate startDate = LocalDate.of(2025, 5, 19);
			LocalDate endDate = LocalDate.of(2025, 5, 25);
			String scheduleName = "Uge 31";
			Schedule schedule = scheduleCtr.SelectDate(scheduleName, startDate, endDate);



			LocalDateTime shiftStart = LocalDateTime.of(2025, 5, 20, 8, 0);
			LocalDateTime shiftEnd = LocalDateTime.of(2025, 5, 20, 16, 0);
			Shift shift = scheduleCtr.createShift(shiftStart, shiftEnd);


			Worker worker = scheduleCtr.findWorkerByName("Mikkel");
			if(worker == null) {
				System.out.println("Worker With the name Sofie was not found!");
				return;
			}


			scheduleCtr.addWorkerToShift(worker, shift);


			schedule.getListOfShifts().add(shift);


			scheduleCtr.saveSchedule(schedule);


		} catch (SQLException e) {
			System.out.println("Databasefejl: " + e.getMessage());
			e.printStackTrace();
		} catch (IllegalStateException e) {
			System.out.println("Logisk fejl: " + e.getMessage());
		}
	}
}