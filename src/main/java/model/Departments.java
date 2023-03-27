package model;

public class Departments {
    private int department_id;
    private String department_name;
    private String location;
    public  Departments(){

    }

    public Departments(int department_id, String department_name, String location) {
        this.department_id = department_id;
        this.department_name = department_name;
        this.location = location;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Departments{" +
                "department_id=" + department_id +
                ", department_name='" + department_name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
