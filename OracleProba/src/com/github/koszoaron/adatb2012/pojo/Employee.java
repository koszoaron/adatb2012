package com.github.koszoaron.adatb2012.pojo;

public class Employee {
    private int empNo;
    private String name;
    private String job;
    private float salary;
    private Department department;
    
    public Employee() {
        super();
    }

    public Employee(int empNo, String name, String job, float salary, Department department) {
        super();
        this.empNo = empNo;
        this.name = name;
        this.job = job;
        this.salary = salary;
        this.department = department;
    }

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee [empNo=" + empNo + ", name=" + name + ", job=" + job + ", salary=" + salary + ", department=" + department + "]";
    }
}
