package model;

import java.awt.*;

public class Employees {
    private String employees_id;
    private String employees_name;
    private String birth_date;
    private String address;
    private String email;
    private String phone_number;
    private String hire_date;
    private String job_position;
    private int salary;
    private String manager_id;
    private int departments_id;
    public Employees(){

    }

    public Employees(String employees_id, String employees_name, String birth_date, String address, String email, String phone_number, String hire_date, String job_position, int salary, String manager_id, int departments_id) {
        this.employees_id = employees_id;
        this.employees_name = employees_name;
        this.birth_date = birth_date;
        this.address = address;
        this.email = email;
        this.phone_number = phone_number;
        this.hire_date = hire_date;
        this.job_position = job_position;
        this.salary = salary;
        this.manager_id = manager_id;
        this.departments_id = departments_id;
    }

    public void setEmployees_id(String employees_id) {
        this.employees_id = employees_id;
    }

    public String getEmployees_id() {
        return employees_id;
    }

    public void setEmployees_name(String employees_name) {
        this.employees_name = employees_name;
    }

    public String getEmployees_name() {
        return employees_name;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getBirth_date() {
        return birth_date;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setHire_date(String hire_date) {
        this.hire_date = hire_date;
    }

    public String getHire_date() {
        return hire_date;
    }

    public void setJob_position(String job_position) {
        this.job_position = job_position;
    }

    public String getJob_position() {
        return job_position;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }

    public void setManager_id(String manager_id) {
        this.manager_id = manager_id;
    }

    public String getManager_id() {
        return manager_id;
    }
    public void setDepartments_id(int departments_id) {
        this.departments_id = departments_id;
    }

    public int getDepartments_id() {
        return departments_id;
    }


    @Override
    public String toString() {
        return "Employees{" +
                "employees_id='" + employees_id + '\'' +
                ", employees_name='" + employees_name + '\'' +
                ", birth_date='" + birth_date + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", hire_date='" + hire_date + '\'' +
                ", job_position='" + job_position + '\'' +
                ", salary=" + salary +
                ", manager_id='" + manager_id + '\'' +
                ", departments_id=" + departments_id +
                '}';
    }
}
