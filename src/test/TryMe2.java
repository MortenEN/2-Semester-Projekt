package test;

import java.sql.SQLException;
import java.time.LocalDate;

import dataBase.ScheduleDB;
import model.Schedule;

public class TryMe2 {

    public static void main(String[] args) {
        try {
            // Opret forbindelse til ScheduleDB
            ScheduleDB scheduleDB = new ScheduleDB();

            // Opret start og slutdato
            LocalDate start = LocalDate.of(2025, 6, 1);
            LocalDate end = LocalDate.of(2025, 6, 30);
            String name = "Uge 21";

            // Lav en ny schedule-instans
            Schedule newSchedule = new Schedule(start, end, name);

            // Gem schedule (hvis den ikke allerede findes)
            scheduleDB.saveSchedule(newSchedule);
            System.out.println("Schedule forsøgt gemt.");

            // Tjek om den eksisterer
            boolean exists = scheduleDB.scheduleExists(newSchedule);
            System.out.println("Schedule eksisterer: " + exists);

            // Opdater slutdato (ændr fx til 28. juni)
            newSchedule.setEnd(LocalDate.of(2025, 6, 28));
            scheduleDB.updateSchedule(newSchedule);
            System.out.println("Schedule opdateret med ny slutdato.");

        } catch (SQLException e) {
            System.err.println("Der opstod en SQL-fejl:");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Der opstod en uventet fejl:");
            e.printStackTrace();
        }
    }
}
