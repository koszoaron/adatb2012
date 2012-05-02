package com.github.koszoaron.adatb2012.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.github.koszoaron.adatb2012.pojo.Department;
import com.github.koszoaron.adatb2012.pojo.Employee;
import oracle.jdbc.pool.OracleDataSource;

public class DBService {
    private static DBService INSTANCE;
    
    private OracleDataSource dataSource;
    private Connection connection;
    
    public static DBService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DBService();
        }
        
        return INSTANCE; 
    }
    
    private DBService() {
        try {
            dataSource = new OracleDataSource();
            dataSource.setURL("jdbc:oracle:thin:@192.168.1.22:1521:XE");
            dataSource.setUser("hr");
            dataSource.setPassword("hr");
            
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Department getDepartmentById(int id) {
        PreparedStatement selectById = null;
        Department result = null;
        
        try {
            selectById = connection.prepareStatement("select deptno, dname, loc from dept where deptno=?");
            selectById.setInt(1, id);
            ResultSet queryResult = selectById.executeQuery();
            
            if (queryResult.next()) {
                result = new Department();
                result.setDeptNo(queryResult.getInt("deptno"));
                result.setName(queryResult.getString("dname"));
                result.setLocation(queryResult.getString("loc"));
            }
            
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (selectById != null) {
                    selectById.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return result;
    }
    
    public Employee getEmployeeById(int id) {
        PreparedStatement selectById = null;
        Employee result = null;
        try {
            selectById = connection.prepareStatement("select empno, ename, job, sal, emp.deptno, dname, loc from emp, dept where emp.deptno=dept.deptno and empno=?");
            selectById.setInt(1, id);
            ResultSet queryResult = selectById.executeQuery();
            
            if (queryResult.next()) {
                result = new Employee();
                result.setEmpNo(queryResult.getInt("empno"));
                result.setName(queryResult.getString("ename"));
                result.setJob(queryResult.getString("job"));
                result.setSalary(queryResult.getFloat("sal"));
                
                Department dept = new Department();
                dept.setDeptNo(queryResult.getInt("deptno"));
                dept.setName(queryResult.getString("dname"));
                dept.setLocation(queryResult.getString("loc"));
                result.setDepartment(dept);
            }
            
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (selectById != null) {
                    selectById.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return result;
    }
    
    public List<Employee> getAllEmployees() {
        PreparedStatement selectAll = null;
        List<Employee> result = new ArrayList<Employee>();
        try {
            selectAll = connection.prepareStatement("select empno, ename, job, sal, emp.deptno, dname, loc from emp, dept where emp.deptno=dept.deptno");
            ResultSet queryResult = selectAll.executeQuery();
            
            while (queryResult.next()) {
                Employee emp = new Employee();
                emp.setEmpNo(queryResult.getInt("empno"));
                emp.setName(queryResult.getString("ename"));
                emp.setJob(queryResult.getString("job"));
                emp.setSalary(queryResult.getFloat("sal"));
                
                Department dept = new Department();
                dept.setDeptNo(queryResult.getInt("deptno"));
                dept.setName(queryResult.getString("dname"));
                dept.setLocation(queryResult.getString("loc"));
                emp.setDepartment(dept);
                
                result.add(emp);
            }
            
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (selectAll != null) {
                    selectAll.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return result;
    }
}
