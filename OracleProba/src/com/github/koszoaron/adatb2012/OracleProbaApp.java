package com.github.koszoaron.adatb2012;

import com.github.koszoaron.adatb2012.db.DBService;
import com.github.koszoaron.adatb2012.pojo.Employee;
import com.github.koszoaron.adatb2012.pojo.Felhasznalo;
import com.github.koszoaron.adatb2012.util.DatabaseService;

public class OracleProbaApp {
    public static void main(String[] args) {
        //DBService service = DBService.getInstance();
        
        /*for (Employee e : service.getAllEmployees()) {
            System.out.println(e.toString());
        }*/
        
        DatabaseService service = DatabaseService.getInstance();
        Felhasznalo user = service.getFelhasznaloByUsername("ARON");
        System.out.println(user.toString());
    }
}
