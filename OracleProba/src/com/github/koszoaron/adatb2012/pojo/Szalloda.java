package com.github.koszoaron.adatb2012.pojo;

public class Szalloda {
    private Varos varos;
    private String nev;
    private String leiras;
    
    public Szalloda() {
        super();
    }

    public Szalloda(Varos varos, String nev, String leiras) {
        super();
        this.varos = varos;
        this.nev = nev;
        this.leiras = leiras;
    }

    public Varos getVaros() {
        return varos;
    }

    public void setVaros(Varos varos) {
        this.varos = varos;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getLeiras() {
        return leiras;
    }

    public void setLeiras(String leiras) {
        this.leiras = leiras;
    }

    @Override
    public String toString() {
        return "Szalloda [varos=" + varos + ", nev=" + nev + ", leiras="
                + leiras + "]";
    }
}
