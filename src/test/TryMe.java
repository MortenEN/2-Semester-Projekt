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
        	
        	
            // 🔧 1. Lav en controller
            ScheduleCtr scheduleCtr = new ScheduleCtr();

            // 📅 2. Opret et schedule
            LocalDate startDate = LocalDate.of(2025, 5, 19);
            LocalDate endDate = LocalDate.of(2025, 5, 25);
            String scheduleName = "Uge 21";
            Schedule schedule = scheduleCtr.SelectDate(scheduleName, startDate, endDate);

            // 🕒 3. Opret et shift (vagt)
            LocalDateTime shiftStart = LocalDateTime.of(2025, 5, 20, 8, 0);
            LocalDateTime shiftEnd = LocalDateTime.of(2025, 5, 20, 16, 0);
            Shift shift = scheduleCtr.createShift(shiftStart, shiftEnd);

            // 👤 4. Find en worker (forudsætter at navnet findes i databasen)
            Worker worker = scheduleCtr.findWorkerByName("Mikkel");

            // 🔗 5. Tilknyt worker til shift
            scheduleCtr.addWorkerToShift(worker, shift);

            // 📋 6. Tilføj shift til schedule (hvis du har setShift-metoden)
            schedule.getListOfShifts().add(shift); // direkte adgang, alternativt scheduleDB.setShift(shift);

            // 💾 7. Gem schedule i databasen
            scheduleCtr.saveSchedule(schedule);

            System.out.println("Alt lykkedes! Schedule gemt med shift og worker.");

        } catch (SQLException e) {
            System.out.println("Databasefejl: " + e.getMessage());
            e.printStackTrace();
        } catch (IllegalStateException e) {
            System.out.println("Logisk fejl: " + e.getMessage());
        }
    }
}
