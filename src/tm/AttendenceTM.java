package tm;

public class AttendenceTM {
    private String employeeId;
    private String employeeName;
    private String status;

    public AttendenceTM(String employeeId,String employeeName, String status) {
        this.employeeId=employeeId;
        this.employeeName = employeeName;
        this.status = status;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
}
