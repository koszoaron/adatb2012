package com.github.koszoaron.adatb2012.pojo;

import java.sql.Date;

public class Felhasznalo {
    private String username;
    private String pass;
    private String okmanyszam;
    private String nev;
    private Date szuletett;
    private String bankkartyaszam;
    private String lakcim;
    private String telefonszam;
    
    public Felhasznalo() {
        super();
    }

    public Felhasznalo(String username, String pass, String okmanyszam,
            String nev, Date szuletett, String bankkartyaszam, String lakcim,
            String telefonszam) {
        super();
        this.username = username;
        this.pass = pass;
        this.okmanyszam = okmanyszam;
        this.nev = nev;
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

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public Date getSzuletett() {
        return szuletett;
    }

    public void setSzuletett(Date szuletett) {
        this.szuletett = szuletett;
    }

    public String getBankkartyaszam() {
        return bankkartyaszam;
    }

    public void setBankkartyaszam(String bankkartyaszam) {
        this.bankkartyaszam = bankkartyaszam;
    }

    public String getLakcim() {
        return lakcim;
    }

    public void setLakcim(String lakcim) {
        this.lakcim = lakcim;
    }

    public String getTelefonszam() {
        return telefonszam;
    }

    public void setTelefonszam(String telefonszam) {
        this.telefonszam = telefonszam;
    }
    
    @Override
    public String toString() {
        return "Felhasznalo [username=" + username + ", pass=" + pass
                + ", okmanyszam=" + okmanyszam + ", nev=" + nev
                + ", szuletett=" + szuletett + ", bankkartyaszam="
                + bankkartyaszam + ", lakcim=" + lakcim + ", telefonszam="
                + telefonszam + "]";
    }
}
