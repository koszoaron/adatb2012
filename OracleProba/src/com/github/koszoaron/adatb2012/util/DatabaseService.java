package com.github.koszoaron.adatb2012.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import com.github.koszoaron.adatb2012.pojo.Felhasznalo;
import oracle.jdbc.pool.OracleDataSource;

public class DatabaseService {
    private static DatabaseService INSTANCE;
    
    private OracleDataSource dataSource;
    private Connection connection;
    
    public static DatabaseService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DatabaseService();
        }
        
        return INSTANCE;
    }
    
    private DatabaseService() {
        try {
            /*Properties prop = new Properties();
            prop.load(new FileInputStream(Constants.PROPERTIES_FILENAME));*/
            
            dataSource = new OracleDataSource();
            /*dataSource.setURL(prop.getProperty(Constants.PROPERTIES_URL));
            dataSource.setUser(prop.getProperty(Constants.PROPERTIES_USER));
            dataSource.setPassword(prop.getProperty(Constants.PROPERTIES_PASS));*/
            
            dataSource.setURL("jdbc:oracle:thin:@192.168.1.22:1521:XE");
            dataSource.setUser("hr");
            dataSource.setPassword("hr");
            
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        /*} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();*/
        }
    }
    
    public Felhasznalo getFelhasznaloByUsername(String username) {
        PreparedStatement selectById = null;
        Felhasznalo result = null;
        
        try {
            selectById = connection.prepareStatement("select username, pass, okmanyszam, neve, szuletett, bankkartyaszam, lakcim, telefonszam from felhasznalo where username=?");
            selectById.setString(1, username);
            ResultSet queryResult = selectById.executeQuery();
            
            if (queryResult.next()) {
                result = new Felhasznalo();
                result.setUsername(queryResult.getString(Constants.USERNAME));
                result.setPass(queryResult.getString(Constants.PASS));
                result.setOkmanyszam(queryResult.getString(Constants.OKMANYSZAM));
                result.setNev(queryResult.getString(Constants.NEVE));
                result.setSzuletett(queryResult.getDate(Constants.SZULETETT));
                result.setBankkartyaszam(queryResult.getString(Constants.BANKKARTYASZAM));
                result.setLakcim(queryResult.getString(Constants.LAKCIM));
                result.setTelefonszam(queryResult.getString(Constants.TELEFONSZAM));
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
}
