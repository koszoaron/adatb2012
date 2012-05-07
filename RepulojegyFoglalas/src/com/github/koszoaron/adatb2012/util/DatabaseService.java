package com.github.koszoaron.adatb2012.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
                result.setNeve(queryResult.getString(Constants.NEVE));
                result.setSzuletett(queryResult.getDate(Constants.SZULETETT));
                result.setBankkartyaszam(queryResult.getLong(Constants.BANKKARTYASZAM));
                result.setLakcim(queryResult.getString(Constants.LAKCIM));
                result.setTelefonszam(queryResult.getLong(Constants.TELEFONSZAM));
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
    
    public List<Felhasznalo> getAllFelhasznalo() {
        PreparedStatement selectAll = null;
        List<Felhasznalo> result = new ArrayList<Felhasznalo>();
        
        try {
            selectAll = connection.prepareStatement("select username, pass, okmanyszam, neve, szuletett, bankkartyaszam, lakcim, telefonszam from felhasznalo");
            ResultSet queryResult = selectAll.executeQuery();
            
            while (queryResult.next()) {
                Felhasznalo f = new Felhasznalo();
                f.setUsername(queryResult.getString(Constants.USERNAME));
                f.setPass(queryResult.getString(Constants.PASS));
                f.setOkmanyszam(queryResult.getString(Constants.OKMANYSZAM));
                f.setNeve(queryResult.getString(Constants.NEVE));
                f.setSzuletett(queryResult.getDate(Constants.SZULETETT));
                f.setBankkartyaszam(queryResult.getLong(Constants.BANKKARTYASZAM));
                f.setLakcim(queryResult.getString(Constants.LAKCIM));
                f.setTelefonszam(queryResult.getLong(Constants.TELEFONSZAM));
                
                result.add(f);
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
    
    public boolean insertFelhasznalo(Felhasznalo f) {
        PreparedStatement insert = null;
        int rowCount = 0;
        
        try {
            insert = connection.prepareStatement("insert into felhasznalo values (?, ?, ?, ?, ?, ?, ?, ?)");
            insert.setString(1, f.getUsername());
            insert.setString(2, f.getPass());
            insert.setString(3, f.getOkmanyszam());
            insert.setString(4, f.getNeve());
            insert.setDate(5, f.getSzuletett());
            insert.setLong(6, f.getBankkartyaszam());
            insert.setString(7, f.getLakcim());
            insert.setLong(8, f.getTelefonszam());
            
            rowCount = insert.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (insert != null) {
                    insert.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return rowCount > 0;
    }
    
    public boolean updateFelhasznalo(Felhasznalo f) {
        PreparedStatement update = null;
        int rowCount = 0;
        
        try {
            update = connection.prepareStatement("update felhasznalo set pass=?, okmanyszam=?, neve=?, szuletett=?, bankkartyaszam=?, lakcim=?, telefonszam=? where username=?");
            update.setString(1, f.getPass());
            update.setString(2, f.getOkmanyszam());
            update.setString(3, f.getNeve());
            update.setDate(4, f.getSzuletett());
            update.setLong(5, f.getBankkartyaszam());
            update.setString(6, f.getLakcim());
            update.setLong(7, f.getTelefonszam());
            update.setString(8, f.getUsername());
            
            rowCount = update.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (update != null) {
                    update.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return rowCount > 0;
    }
    
    public boolean deleteFelhasznalo(Felhasznalo f) {
        PreparedStatement delete = null;
        int rowCount = 0;
        
        try {
            delete = connection.prepareStatement("delete from felhasznalo where username=?");
            delete.setString(1, f.getUsername());
            
            rowCount = delete.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (delete != null) {
                    delete.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return rowCount > 0;
    }
}
