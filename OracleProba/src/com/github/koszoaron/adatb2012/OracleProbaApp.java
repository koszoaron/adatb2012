package com.github.koszoaron.adatb2012;

import com.github.koszoaron.adatb2012.db.DBService;
import com.github.koszoaron.adatb2012.pojo.Employee;

public class OracleProbaApp {
    public static void main(String[] args) {
        DBService service = DBService.getInstance();
        
        for (Employee e : service.getAllEmployees()) {
            System.out.println(e.toString());
        }
    }
}
