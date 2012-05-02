package com.github.koszoaron.adatb2012.pojo;

public class Department {
    private int deptNo;
    private String name;
    private String location;
    
    public Department() {
        super();
    }

    public Department(int deptNo, String name, String location) {
        super();
        this.deptNo = deptNo;
        this.name = name;
        this.location = location;
    }

    public int getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(int deptNo) {
        this.deptNo = deptNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Department [deptNo=" + deptNo + ", name=" + name + ", location=" + location + "]";
    }
    
}
