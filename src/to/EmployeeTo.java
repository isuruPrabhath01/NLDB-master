package to;

public class EmployeeTo {
    private String id;
    private String name;
    private double salary;
    private String contact;
    private String address;
    private String nic;

    public EmployeeTo(String id, String name, double salary, String contact, String address, String nic) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.contact = contact;
        this.address = address;
        this.nic = nic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }
}
