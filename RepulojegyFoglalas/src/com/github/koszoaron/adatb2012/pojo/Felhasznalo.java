package com.github.koszoaron.adatb2012.pojo;

import java.io.Serializable;
import java.sql.Date;

public class Felhasznalo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String username;
    private String pass;
    private String okmanyszam;
    private String neve;
    private Date szuletett;
    private long bankkartyaszam;
    private String lakcim;
    private long telefonszam;
    
    public Felhasznalo() {
        super();
    }

    public Felhasznalo(String username, String pass, String okmanyszam,
            String neve, Date szuletett, long bankkartyaszam, String lakcim,
            long telefonszam) {
        super();
        this.username = username;
        this.pass = pass;
        this.okmanyszam = okmanyszam;
        this.neve = neve;
        this.szuletett = szuletett;
        this.bankkartyaszam = bankkartyaszam;
        this.lakcim = lakcim;
        this.telefonszam = telefonszam;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getOkmanyszam() {
        return okmanyszam;
    }

    public void setOkmanyszam(String okmanyszam) {
        this.okmanyszam = okmanyszam;
    }

    public String getNeve() {
        return neve;
    }

    public void setNeve(String neve) {
        this.neve = neve;
    }

    public Date getSzuletett() {
        return szuletett;
    }

    public void setSzuletett(Date szuletett) {
        this.szuletett = szuletett;
    }

    public long getBankkartyaszam() {
        return bankkartyaszam;
    }

    public void setBankkartyaszam(long bankkartyaszam) {
        this.bankkartyaszam = bankkartyaszam;
    }

    public String getLakcim() {
        return lakcim;
    }

    public void setLakcim(String lakcim) {
        this.lakcim = lakcim;
    }

    public long getTelefonszam() {
        return telefonszam;
    }

    public void setTelefonszam(long telefonszam) {
        this.telefonszam = telefonszam;
    }
    
    @Override
    public String toString() {
        return "Felhasznalo [username=" + username + ", pass=" + pass
                + ", okmanyszam=" + okmanyszam + ", neve=" + neve
                + ", szuletett=" + szuletett + ", bankkartyaszam="
                + bankkartyaszam + ", lakcim=" + lakcim + ", telefonszam="
                + telefonszam + "]";
    }
}
