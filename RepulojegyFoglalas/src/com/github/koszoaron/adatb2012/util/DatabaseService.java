package com.github.koszoaron.adatb2012.util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import com.github.koszoaron.adatb2012.pojo.Akcio;
import com.github.koszoaron.adatb2012.pojo.Biztositas;
import com.github.koszoaron.adatb2012.pojo.Felhasznalo;
import com.github.koszoaron.adatb2012.pojo.Foglalas;
import com.github.koszoaron.adatb2012.pojo.Jarat;
import com.github.koszoaron.adatb2012.pojo.Jegy;
import com.github.koszoaron.adatb2012.pojo.Menetrend;
import com.github.koszoaron.adatb2012.pojo.Nemzet;
import com.github.koszoaron.adatb2012.pojo.Osztaly;
import com.github.koszoaron.adatb2012.pojo.Repulo;
import com.github.koszoaron.adatb2012.pojo.Szalloda;
import com.github.koszoaron.adatb2012.pojo.Tarsasag;
import com.github.koszoaron.adatb2012.pojo.Varos;
import oracle.jdbc.pool.OracleDataSource;

public class DatabaseService {
    private static DatabaseService INSTANCE;
    
    private OracleDataSource dataSource;
    private Connection connection;
    private SimpleDateFormat dateFormat;
    
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
            
            dateFormat = new SimpleDateFormat(Constants.DATETIME_FORMAT);
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
            insert.setDate(5, new java.sql.Date(f.getSzuletett().getTime()));
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
            update.setDate(4, new java.sql.Date(f.getSzuletett().getTime()));
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
    
    public Nemzet getNemzetById(int id) {
        PreparedStatement selectById = null;
        Nemzet result = null;
        
        try {
            selectById = connection.prepareStatement("select nemzet_id, orszagnev from nemzet where nemzet_id=?");
            selectById.setInt(1, id);
            ResultSet queryResult = selectById.executeQuery();
            
            if (queryResult.next()) {
                result = new Nemzet();
                result.setNemzetId(queryResult.getInt(Constants.NEMZET_ID));
                result.setOrszagnev(queryResult.getString(Constants.ORSZAGNEV));
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
    
    public List<Nemzet> getAllNemzet() {
        PreparedStatement selectAll = null;
        List<Nemzet> result = new ArrayList<Nemzet>();
        
        try {
            selectAll = connection.prepareStatement("select nemzet_id, orszagnev from nemzet");
            ResultSet queryResult = selectAll.executeQuery();
            
            while (queryResult.next()) {
                Nemzet n = new Nemzet();
                n.setNemzetId(queryResult.getInt(Constants.NEMZET_ID));
                n.setOrszagnev(queryResult.getString(Constants.ORSZAGNEV));
                
                result.add(n);
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
    
    public boolean insertNemzet(Nemzet n) {
        PreparedStatement insert = null;
        int rowCount = 0;
        
        try {
            insert = connection.prepareStatement("insert into nemzet values (?, ?)");
            insert.setInt(1, n.getNemzetId());
            insert.setString(2, n.getOrszagnev());
            
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
    
    public boolean updateNemzet(Nemzet n) {
        PreparedStatement update = null;
        int rowCount = 0;
        
        try {
            update = connection.prepareStatement("update nemzet set orszagnev=? where nemzet_id=?");
            update.setString(1, n.getOrszagnev());
            update.setInt(2, n.getNemzetId());
                        
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
    
    public boolean deleteNemzet(Nemzet n) {
        PreparedStatement delete = null;
        int rowCount = 0;
        
        try {
            delete = connection.prepareStatement("delete from nemzet where nemzet_id=?");
            delete.setInt(1, n.getNemzetId());
            
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
    
    public Tarsasag getTarsasagById(int id) {
        PreparedStatement selectById = null;
        Tarsasag result = null;
        
        try {
            selectById = connection.prepareStatement("select tarsasag_id, tarsasag_nev, nemzet_id from tarsasag where tarsasag_id=?");
            selectById.setInt(1, id);
            ResultSet queryResult = selectById.executeQuery();
            
            if (queryResult.next()) {
                result = new Tarsasag();
                result.setTarsasagId(queryResult.getInt(Constants.TARSASAG_ID));
                result.setTarsasagNev(queryResult.getString(Constants.TARSASAG_NEV));
                result.setNemzet(getNemzetById(queryResult.getInt(Constants.NEMZET_ID)));                
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
    
    public List<Tarsasag> getAllTarsasag() {
        PreparedStatement selectAll = null;
        List<Tarsasag> result = new ArrayList<Tarsasag>();
        
        try {
            selectAll = connection.prepareStatement("select tarsasag_id, tarsasag_nev, nemzet_id from tarsasag");
            ResultSet queryResult = selectAll.executeQuery();
            
            while (queryResult.next()) {
                Tarsasag t = new Tarsasag();
                t.setTarsasagId(queryResult.getInt(Constants.TARSASAG_ID));
                t.setTarsasagNev(queryResult.getString(Constants.TARSASAG_NEV));
                t.setNemzet(getNemzetById(queryResult.getInt(Constants.NEMZET_ID)));
                
                result.add(t);
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
    
    public boolean insertTarsasag(Tarsasag t) {
        PreparedStatement insert = null;
        int rowCount = 0;
        
        try {
            insert = connection.prepareStatement("insert into tarsasag values (?, ?, ?)");
            insert.setInt(1, t.getTarsasagId());
            insert.setString(2, t.getTarsasagNev());
            insert.setInt(3, t.getNemzet().getNemzetId());
            
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
    
    public boolean updateTarsasag(Tarsasag t) {
        PreparedStatement update = null;
        int rowCount = 0;
        
        try {
            update = connection.prepareStatement("update tarsasag set tarsasag_nev=?, nemzet_id=? where tarsasag_id=?");
            update.setString(1, t.getTarsasagNev());
            update.setInt(2, t.getNemzet().getNemzetId());
            update.setInt(3, t.getTarsasagId());
                        
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
    
    public boolean deleteTarsasag(Tarsasag t) {
        PreparedStatement delete = null;
        int rowCount = 0;
        
        try {
            delete = connection.prepareStatement("delete from tarsasag where tarsasag_id=?");
            delete.setInt(1, t.getTarsasagId());
            
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
    
    public Varos getVarosById(int id) {
        PreparedStatement selectById = null;
        Varos result = null;
        
        try {
            selectById = connection.prepareStatement("select varos_id, varosnev, nemzet_id from varos where varos_id=?");
            selectById.setInt(1, id);
            ResultSet queryResult = selectById.executeQuery();
            
            if (queryResult.next()) {
                result = new Varos();
                result.setVarosId(queryResult.getInt(Constants.VAROS_ID));
                result.setVarosnev(queryResult.getString(Constants.VAROSNEV));
                result.setNemzet(getNemzetById(queryResult.getInt(Constants.NEMZET_ID)));                
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
    
    public List<Varos> getAllVaros() {
        PreparedStatement selectAll = null;
        List<Varos> result = new ArrayList<Varos>();
        
        try {
            selectAll = connection.prepareStatement("select varos_id, varosnev, nemzet_id from varos");
            ResultSet queryResult = selectAll.executeQuery();
            
            while (queryResult.next()) {
                Varos v = new Varos();
                v.setVarosId(queryResult.getInt(Constants.VAROS_ID));
                v.setVarosnev(queryResult.getString(Constants.VAROSNEV));
                v.setNemzet(getNemzetById(queryResult.getInt(Constants.NEMZET_ID)));
                
                result.add(v);
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
    
    public boolean insertVaros(Varos v) {
        PreparedStatement insert = null;
        int rowCount = 0;
        
        try {
            insert = connection.prepareStatement("insert into varos values (?, ?, ?)");
            insert.setInt(1, v.getVarosId());
            insert.setString(2, v.getVarosnev());
            insert.setInt(3, v.getNemzet().getNemzetId());
            
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
    
    public boolean updateVaros(Varos v) {
        PreparedStatement update = null;
        int rowCount = 0;
        
        try {
            update = connection.prepareStatement("update varos set varosnev=?, nemzet_id=? where varos_id=?");
            update.setString(1, v.getVarosnev());
            update.setInt(2, v.getNemzet().getNemzetId());
            update.setInt(3, v.getVarosId());
                        
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
    
    public boolean deleteVaros(Varos v) {
        PreparedStatement delete = null;
        int rowCount = 0;
        
        try {
            delete = connection.prepareStatement("delete from varos where varos_id=?");
            delete.setInt(1, v.getVarosId());
            
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
    
    public Repulo getRepuloById(int id) {
        PreparedStatement selectById = null;
        Repulo result = null;
        
        try {
            selectById = connection.prepareStatement("select repulo_id, tipus, ulohely, tarsasag_id from repulo where repulo_id=?");
            selectById.setInt(1, id);
            ResultSet queryResult = selectById.executeQuery();
            
            if (queryResult.next()) {
                result = new Repulo();
                result.setRepuloId(queryResult.getInt(Constants.REPULO_ID));
                result.setTipus(queryResult.getString(Constants.TIPUS));
                result.setUlohely(queryResult.getInt(Constants.ULOHELY));
                result.setTarsasag(getTarsasagById(queryResult.getInt(Constants.TARSASAG_ID)));
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
    
    public List<Repulo> getAllRepulo() {
        PreparedStatement selectAll = null;
        List<Repulo> result = new ArrayList<Repulo>();
        
        try {
            selectAll = connection.prepareStatement("select repulo_id, tipus, ulohely, tarsasag_id from repulo");
            ResultSet queryResult = selectAll.executeQuery();
            
            while (queryResult.next()) {
                Repulo r = new Repulo();
                r.setRepuloId(queryResult.getInt(Constants.REPULO_ID));
                r.setTipus(queryResult.getString(Constants.TIPUS));
                r.setUlohely(queryResult.getInt(Constants.ULOHELY));
                r.setTarsasag(getTarsasagById(queryResult.getInt(Constants.TARSASAG_ID)));
                
                result.add(r);
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
    
    public boolean insertRepulo(Repulo r) {
        PreparedStatement insert = null;
        int rowCount = 0;
        
        try {
            insert = connection.prepareStatement("insert into repulo values (?, ?, ?, ?)");
            insert.setInt(1, r.getRepuloId());
            insert.setString(2, r.getTipus());
            insert.setInt(3, r.getUlohely());
            insert.setInt(4, r.getTarsasag().getTarsasagId());
            
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
    
    public boolean updateRepulo(Repulo r) {
        PreparedStatement update = null;
        int rowCount = 0;
        
        try {
            update = connection.prepareStatement("update repulo set tipus=?, ulohely=?, tarsasag_id=? where repulo_id=?");
            update.setString(1, r.getTipus());
            update.setInt(2, r.getUlohely());
            update.setInt(3, r.getTarsasag().getTarsasagId());
            update.setInt(4, r.getRepuloId());
                        
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
    
    public boolean deleteRepulo(Repulo r) {
        PreparedStatement delete = null;
        int rowCount = 0;
        
        try {
            delete = connection.prepareStatement("delete from repulo where repulo_id=?");
            delete.setInt(1, r.getRepuloId());
            
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
    
    public Jarat getJaratById(int id) {
        PreparedStatement selectById = null;
        Jarat result = null;
        
        try {
            selectById = connection.prepareStatement("select jarat_id, honnan, hova, repulo_id from jarat where jarat_id=?");
            selectById.setInt(1, id);
            ResultSet queryResult = selectById.executeQuery();
            
            if (queryResult.next()) {
                result = new Jarat();
                result.setJaratId(queryResult.getInt(Constants.JARAT_ID));
                result.setHonnan(getVarosById(queryResult.getInt(Constants.HONNAN)));
                result.setHova(getVarosById(queryResult.getInt(Constants.HOVA)));
                result.setRepulo(getRepuloById(queryResult.getInt(Constants.REPULO_ID)));
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
    
    public List<Jarat> getAllJarat() {
        PreparedStatement selectAll = null;
        List<Jarat> result = new ArrayList<Jarat>();
        
        try {
            selectAll = connection.prepareStatement("select jarat_id, honnan, hova, repulo_id from jarat");
            ResultSet queryResult = selectAll.executeQuery();
            
            while (queryResult.next()) {
                Jarat j = new Jarat();
                j.setJaratId(queryResult.getInt(Constants.JARAT_ID));
                j.setHonnan(getVarosById(queryResult.getInt(Constants.HONNAN)));
                j.setHova(getVarosById(queryResult.getInt(Constants.HOVA)));
                j.setRepulo(getRepuloById(queryResult.getInt(Constants.REPULO_ID)));
                
                result.add(j);
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
    
    public boolean insertJarat(Jarat j) {
        PreparedStatement insert = null;
        int rowCount = 0;
        
        try {
            insert = connection.prepareStatement("insert into jarat values (?, ?, ?, ?)");
            insert.setInt(1, j.getJaratId());
            insert.setInt(2, j.getHonnan().getVarosId());
            insert.setInt(3, j.getHova().getVarosId());
            insert.setInt(4, j.getRepulo().getRepuloId());
            
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
    
    public boolean updateJarat(Jarat j) {
        PreparedStatement update = null;
        int rowCount = 0;
        
        try {
            update = connection.prepareStatement("update jarat set honnan=?, hova=?, repulo_id=? where jarat_id=?");
            update.setInt(1, j.getHonnan().getVarosId());
            update.setInt(2, j.getHova().getVarosId());
            update.setInt(3, j.getRepulo().getRepuloId());
            update.setInt(4, j.getJaratId());
                        
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
    
    public boolean deleteJarat(Jarat j) {
        PreparedStatement delete = null;
        int rowCount = 0;
        
        try {
            delete = connection.prepareStatement("delete from jarat where jarat_id=?");
            delete.setInt(1, j.getJaratId());
            
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
    
    public Menetrend getMenetrendByIdAndDate(int id, Date date) {
        PreparedStatement selectById = null;
        Menetrend result = null;
        
        try {
            selectById = connection.prepareStatement("select jarat_id, to_char(indul, 'yyyy-mm-dd hh24:mi') as indul, to_char(erkezik, 'yyyy-mm-dd hh24:mi') as erkezik from menetrend where jarat_id=? and indul=to_date(?, 'yyyy-mm-dd hh24:mi')");
            selectById.setInt(1, id);
            selectById.setString(2, dateFormat.format(date));
            ResultSet queryResult = selectById.executeQuery();
            
            if (queryResult.next()) {
                result = new Menetrend();
                result.setJarat(getJaratById(queryResult.getInt(Constants.JARAT_ID)));
                result.setIndul(dateFormat.parse(queryResult.getString(Constants.INDUL)));
                result.setErkezik(dateFormat.parse(queryResult.getString(Constants.ERKEZIK)));
            }
            
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
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
    
    public List<Menetrend> getAllMenetrend() {
        PreparedStatement selectAll = null;
        List<Menetrend> result = new ArrayList<Menetrend>();
        
        try {
            selectAll = connection.prepareStatement("select jarat_id, to_char(indul, 'yyyy-mm-dd hh24:mi') as indul, to_char(erkezik, 'yyyy-mm-dd hh24:mi') as erkezik from menetrend");
            ResultSet queryResult = selectAll.executeQuery();
            
            while (queryResult.next()) {
                Menetrend m = new Menetrend();
                m.setJarat(getJaratById(queryResult.getInt(Constants.JARAT_ID)));
                m.setIndul(dateFormat.parse(queryResult.getString(Constants.INDUL)));
                m.setErkezik(dateFormat.parse(queryResult.getString(Constants.ERKEZIK)));
                
                result.add(m);
            }
            
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
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
    
    public boolean insertMenetrend(Menetrend m) {
        PreparedStatement insert = null;
        int rowCount = 0;
        
        try {
            String sql = "insert into menetrend values (" + m.getJarat().getJaratId() +  ", to_date('" + dateFormat.format(m.getIndul()) + "', 'yyyy-mm-dd hh24:mi'), to_date('" + dateFormat.format(m.getErkezik()) + "', 'yyyy-mm-dd hh24:mi'))";
            System.out.println(sql);
            insert = connection.prepareStatement(sql);
            /*insert.setInt(1, m.getJarat().getJaratId());
            insert.setString(2, dateFormat.format(m.getIndul()));
            insert.setString(3, dateFormat.format(m.getErkezik()));*/
            
            
            
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
    
    public boolean updateMenetrend(Menetrend m) {
        PreparedStatement update = null;
        int rowCount = 0;
        
        try {
            update = connection.prepareStatement("update menetrend set erkezik=to_date(?, 'yyyy-mm-dd hh24:mi') where jarat_id=? and indul=to_date(?, 'yyyy-mm-dd hh24:mi')");
            update.setString(1, dateFormat.format(m.getErkezik()));
            update.setInt(2, m.getJarat().getJaratId());
            update.setString(3, dateFormat.format(m.getIndul()));
                        
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
    
    public boolean deleteMenetrend(Menetrend m) {
        PreparedStatement delete = null;
        int rowCount = 0;
        
        try {
            delete = connection.prepareStatement("delete from menetrend where jarat_id=? and indul=to_date(?, 'yyyy-mm-dd hh24:mi')");
            delete.setInt(1, m.getJarat().getJaratId());
            delete.setString(2, dateFormat.format(m.getIndul()));
            
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
    
    public Osztaly getOsztalyById(int id) {
        PreparedStatement selectById = null;
        Osztaly result = null;
        
        try {
            selectById = connection.prepareStatement("select szama, etkezes, relax, extra_borond, internet from osztaly where szama=?");
            selectById.setInt(1, id);
            ResultSet queryResult = selectById.executeQuery();
            
            if (queryResult.next()) {
                result = new Osztaly();
                result.setSzama(queryResult.getInt(Constants.SZAMA));
                result.setEtkezes(queryResult.getInt(Constants.ETKEZES));
                result.setRelax(queryResult.getInt(Constants.RELAX));
                result.setExtraBorond(queryResult.getInt(Constants.EXTRA_BOROND));
                result.setInternet(queryResult.getInt(Constants.INTERNET));
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
    
    public List<Osztaly> getAllOsztaly() {
        PreparedStatement selectAll = null;
        List<Osztaly> result = new ArrayList<Osztaly>();
        
        try {
            selectAll = connection.prepareStatement("select szama, etkezes, relax, extra_borond, internet from osztaly");
            ResultSet queryResult = selectAll.executeQuery();
            
            while (queryResult.next()) {
                Osztaly o = new Osztaly();
                o.setSzama(queryResult.getInt(Constants.SZAMA));
                o.setEtkezes(queryResult.getInt(Constants.ETKEZES));
                o.setRelax(queryResult.getInt(Constants.RELAX));
                o.setExtraBorond(queryResult.getInt(Constants.EXTRA_BOROND));
                o.setInternet(queryResult.getInt(Constants.INTERNET));
                
                result.add(o);
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
    
    public boolean insertOsztaly(Osztaly o) {
        PreparedStatement insert = null;
        int rowCount = 0;
        
        try {
            insert = connection.prepareStatement("insert into osztaly values (?, ?, ?, ?, ?)");
            insert.setInt(1, o.getSzama());
            insert.setInt(2, o.getEtkezes());
            insert.setInt(3, o.getRelax());
            insert.setInt(4, o.getExtraBorond());
            insert.setInt(5, o.getInternet());
            
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
    
    public boolean updateOsztaly(Osztaly o) {
        PreparedStatement update = null;
        int rowCount = 0;
        
        try {
            update = connection.prepareStatement("update osztaly set etkezes=?, relax=?, extra_borond=?, internet=? where szama=?");
            update.setInt(1, o.getEtkezes());
            update.setInt(2, o.getRelax());
            update.setInt(3, o.getExtraBorond());
            update.setInt(4, o.getInternet());
            update.setInt(5, o.getSzama());
                        
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
    
    public boolean deleteOsztaly(Osztaly o) {
        PreparedStatement delete = null;
        int rowCount = 0;
        
        try {
            delete = connection.prepareStatement("delete from osztaly where szama=?");
            delete.setInt(1, o.getSzama());
            
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
    
    public Szalloda getSzallodaByVarosAndNev(Varos varos, String nev) {
        PreparedStatement selectById = null;
        Szalloda result = null;
        
        try {
            selectById = connection.prepareStatement("select varos_id, neve, leiras from szalloda where varos_id=? and neve=?");
            selectById.setInt(1, varos.getVarosId());
            selectById.setString(2, nev);
            ResultSet queryResult = selectById.executeQuery();
            
            if (queryResult.next()) {
                result = new Szalloda();
                result.setVaros(getVarosById(queryResult.getInt(Constants.VAROS_ID)));
                result.setNev(queryResult.getString(Constants.NEVE));
                result.setLeiras(queryResult.getString(Constants.LEIRAS));
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
    
    public List<Szalloda> getAllSzalloda() {
        PreparedStatement selectAll = null;
        List<Szalloda> result = new ArrayList<Szalloda>();
        
        try {
            selectAll = connection.prepareStatement("select varos_id, neve, leiras from szalloda");
            ResultSet queryResult = selectAll.executeQuery();
            
            while (queryResult.next()) {
                Szalloda sz = new Szalloda();
                sz.setVaros(getVarosById(queryResult.getInt(Constants.VAROS_ID)));
                sz.setNev(queryResult.getString(Constants.NEVE));
                sz.setLeiras(queryResult.getString(Constants.LEIRAS));
                
                result.add(sz);
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
    
    public boolean insertSzalloda(Szalloda sz) {
        PreparedStatement insert = null;
        int rowCount = 0;
        
        try {
            insert = connection.prepareStatement("insert into szalloda values (?, ?, ?)");
            insert.setInt(1, sz.getVaros().getVarosId());
            insert.setString(2, sz.getNev());
            insert.setString(3, sz.getLeiras());
            
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
    
    public boolean updateSzalloda(Szalloda sz) {
        PreparedStatement update = null;
        int rowCount = 0;
        
        try {
            update = connection.prepareStatement("update szalloda set leiras=? where varos_id=? and neve=?");
            update.setString(1, sz.getLeiras());
            update.setInt(2, sz.getVaros().getVarosId());
            update.setString(3, sz.getNev());
                        
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
    
    public boolean deleteSzalloda(Szalloda sz) {
        PreparedStatement delete = null;
        int rowCount = 0;
        
        try {
            delete = connection.prepareStatement("delete from szalloda where varos_id=? and neve=?");
            delete.setInt(1, sz.getVaros().getVarosId());
            delete.setString(2, sz.getNev());
            
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
    
    public Biztositas getBiztositasById(int id) {
        PreparedStatement selectById = null;
        Biztositas result = null;
        
        try {
            selectById = connection.prepareStatement("select bizt_id, utlemondas, poggyasz, arvisszaad from biztositas where bizt_id=?");
            selectById.setInt(1, id);
            ResultSet queryResult = selectById.executeQuery();
            
            if (queryResult.next()) {
                result = new Biztositas();
                result.setBiztId(queryResult.getInt(Constants.BIZT_ID));
                result.setUtlemondas(queryResult.getInt(Constants.UTLEMONDAS));
                result.setPoggyasz(queryResult.getInt(Constants.POGGYASZ));
                result.setArvisszaad(queryResult.getInt(Constants.ARVISSZAAD));
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
    
    public List<Biztositas> getAllBiztositas() {
        PreparedStatement selectAll = null;
        List<Biztositas> result = new ArrayList<Biztositas>();
        
        try {
            selectAll = connection.prepareStatement("select bizt_id, utlemondas, poggyasz, arvisszaad from biztositas");
            ResultSet queryResult = selectAll.executeQuery();
            
            while (queryResult.next()) {
                Biztositas b = new Biztositas();
                b.setBiztId(queryResult.getInt(Constants.BIZT_ID));
                b.setUtlemondas(queryResult.getInt(Constants.UTLEMONDAS));
                b.setPoggyasz(queryResult.getInt(Constants.POGGYASZ));
                b.setArvisszaad(queryResult.getInt(Constants.ARVISSZAAD));
                
                result.add(b);
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
    
    public boolean insertBiztositas(Biztositas b) {
        PreparedStatement insert = null;
        int rowCount = 0;
        
        try {
            insert = connection.prepareStatement("insert into Biztositas values (?, ?, ?, ?)");
            insert.setInt(1, b.getBiztId());
            insert.setInt(2, b.getUtlemondas());
            insert.setInt(3, b.getPoggyasz());
            insert.setInt(4, b.getArvisszaad());
            
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
    
    public boolean updateBiztositas(Biztositas b) {
        PreparedStatement update = null;
        int rowCount = 0;
        
        try {
            update = connection.prepareStatement("update biztositas set utlemondas=?, poggyasz=?, arvisszaad=? where bizt_id=?");
            update.setInt(1, b.getUtlemondas());
            update.setInt(2, b.getPoggyasz());
            update.setInt(3, b.getArvisszaad());
            update.setInt(4, b.getBiztId());
                        
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
    
    public boolean deleteBiztositas(Biztositas b) {
        PreparedStatement delete = null;
        int rowCount = 0;
        
        try {
            delete = connection.prepareStatement("delete from biztositas where bizt_id=?");
            delete.setInt(1, b.getBiztId());
            
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
    
    public Akcio getAkcioByJaratAndUjar(Jarat j, int ujar) {
        PreparedStatement selectById = null;
        Akcio result = null;
        
        try {
            selectById = connection.prepareStatement("select jarat_id, ujar from akcio where jarat_id=? and ujar=?");
            selectById.setInt(1, j.getJaratId());
            selectById.setInt(2, ujar);
            ResultSet queryResult = selectById.executeQuery();
            
            if (queryResult.next()) {
                result = new Akcio();
                result.setJarat(getJaratById(queryResult.getInt(Constants.JARAT_ID)));
                result.setUjar(queryResult.getInt(Constants.UJAR));
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
    
    public List<Akcio> getAllAkcio() {
        PreparedStatement selectAll = null;
        List<Akcio> result = new ArrayList<Akcio>();
        
        try {
            selectAll = connection.prepareStatement("select jarat_id, ujar from akcio");
            ResultSet queryResult = selectAll.executeQuery();
            
            while (queryResult.next()) {
                Akcio a = new Akcio();
                a.setJarat(getJaratById(queryResult.getInt(Constants.JARAT_ID)));
                a.setUjar(queryResult.getInt(Constants.UJAR));
                
                result.add(a);
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
    
    public boolean insertAkcio(Akcio a) {
        PreparedStatement insert = null;
        int rowCount = 0;
        
        try {
            insert = connection.prepareStatement("insert into Akcio values (?, ?)");
            insert.setInt(1, a.getJarat().getJaratId());
            insert.setInt(2, a.getUjar());
            
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
    
    public boolean updateAkcio(Akcio a) {
        PreparedStatement update = null;
        int rowCount = 0;
        
        try {
            update = connection.prepareStatement("update akcio set ujar=? where jarat_id=? and ujar=?");
            update.setInt(1, a.getUjar());
            update.setInt(2, a.getJarat().getJaratId());
            update.setInt(3, a.getUjar());
                        
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
    
    public boolean deleteAkcio(Akcio a) {
        PreparedStatement delete = null;
        int rowCount = 0;
        
        try {
            delete = connection.prepareStatement("delete from Akcio where jarat_id=? and ujar=?");
            delete.setInt(1, a.getJarat().getJaratId());
            delete.setInt(2, a.getUjar());
            
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
    
    public Jegy getJegyById(int id) {
        PreparedStatement selectById = null;
        Jegy result = null;
        
        try {
            selectById = connection.prepareStatement("select jegy_id, jarat_id, osztaly_id as szama, bizt_id from jegy where jegy_id=?");
            selectById.setInt(1, id);
            ResultSet queryResult = selectById.executeQuery();
            
            if (queryResult.next()) {
                result = new Jegy();
                result.setJegyId(queryResult.getInt(Constants.JEGY_ID));
                result.setJarat(getJaratById(queryResult.getInt(Constants.JARAT_ID)));
                result.setOsztaly(getOsztalyById(queryResult.getInt(Constants.SZAMA)));
                result.setBiztositas(getBiztositasById(queryResult.getInt(Constants.BIZT_ID)));
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
    
    public List<Jegy> getAllJegy() {
        PreparedStatement selectAll = null;
        List<Jegy> result = new ArrayList<Jegy>();
        
        try {
            selectAll = connection.prepareStatement("select jegy_id, jarat_id, osztaly_id as szama, bizt_id from jegy");
            ResultSet queryResult = selectAll.executeQuery();
            
            while (queryResult.next()) {
                Jegy j = new Jegy();
                j.setJegyId(queryResult.getInt(Constants.JEGY_ID));
                j.setJarat(getJaratById(queryResult.getInt(Constants.JARAT_ID)));
                j.setOsztaly(getOsztalyById(queryResult.getInt(Constants.SZAMA)));
                j.setBiztositas(getBiztositasById(queryResult.getInt(Constants.BIZT_ID)));
                
                result.add(j);
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
    
    public boolean insertJegy(Jegy j) {
        PreparedStatement insert = null;
        int rowCount = 0;
        
        try {
            insert = connection.prepareStatement("insert into jegy values (?, ?, ?, ?)");
            insert.setInt(1, j.getJegyId());
            insert.setInt(2, j.getJarat().getJaratId());
            insert.setInt(3, j.getOsztaly().getSzama());
            insert.setInt(4, j.getBiztositas().getBiztId());
            
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
    
    public boolean updateJegy(Jegy j) {
        PreparedStatement update = null;
        int rowCount = 0;
        
        try {
            update = connection.prepareStatement("update jegy set jarat_id=?, osztaly_id=?, bizt_id=? where jegy_id=?");
            update.setInt(1, j.getJarat().getJaratId());
            update.setInt(2, j.getOsztaly().getSzama());
            update.setInt(3, j.getBiztositas().getBiztId());
            update.setInt(4, j.getJegyId());
            
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
    
    public boolean deleteJegy(Jegy j) {
        PreparedStatement delete = null;
        int rowCount = 0;
        
        try {
            delete = connection.prepareStatement("delete from jegy where jegy_id=?");
            delete.setInt(1, j.getJegyId());
            
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
    
    public List<Foglalas> getAllFoglalas() {
        PreparedStatement selectAll = null;
        List<Foglalas> result = new ArrayList<Foglalas>();
        
        try {
            selectAll = connection.prepareStatement("select username, jegy_id from foglalas");
            ResultSet queryResult = selectAll.executeQuery();
            
            while (queryResult.next()) {
                Foglalas f = new Foglalas();
                f.setUser(getFelhasznaloByUsername(queryResult.getString(Constants.USERNAME)));
                f.setJegy(getJegyById(queryResult.getInt(Constants.JEGY_ID)));
                
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
    
    public boolean insertFoglalas(Foglalas f) {
        PreparedStatement insert = null;
        int rowCount = 0;
        
        try {
            insert = connection.prepareStatement("insert into foglalas values (?, ?)");
            insert.setString(1, f.getUser().getUsername());
            insert.setInt(2, f.getJegy().getJegyId());
            
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
    
    public boolean updateFoglalas(Foglalas f) {
        PreparedStatement update = null;
        int rowCount = 0;
        
        try {
            update = connection.prepareStatement("update foglalas set username=?, jegy_id=?, where username=? and jegy_id=?");
            update.setString(1, f.getUser().getUsername());
            update.setInt(2, f.getJegy().getJegyId());
            update.setString(3, f.getUser().getUsername());
            update.setInt(4, f.getJegy().getJegyId());
            
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
    
    public boolean deleteFoglalas(Foglalas f) {
        PreparedStatement delete = null;
        int rowCount = 0;
        
        try {
            delete = connection.prepareStatement("delete from foglalas where username=? and jegy_id=?");
            delete.setString(1, f.getUser().getUsername());
            delete.setInt(2, f.getJegy().getJegyId());
            
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
