package com.github.koszoaron.adatb2012;

import java.sql.Date;
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
        
        user.setLakcim("egy harmadik cim");
        boolean res = service.updateFelhasznalo(user);
        if (res) {
            System.out.println("Insert: OK");
        } else {
            System.out.println("Insert: FAIL");
        }
    }
}
