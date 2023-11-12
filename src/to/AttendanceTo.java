package to;

import java.sql.Time;

public class AttendanceTo {
    private String employeeId;
    private String dayId;
    private Time time;
    private String status;

    public AttendanceTo(String employeeId, String dayId, Time time, String status) {
        this.employeeId = employeeId;
        this.dayId = dayId;
        this.time = time;
        this.status = status;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getDayId() {
        return dayId;
    }

    public void setDayId(String dayId) {
        this.dayId = dayId;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
