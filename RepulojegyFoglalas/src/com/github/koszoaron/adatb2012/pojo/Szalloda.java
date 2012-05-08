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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nev == null) ? 0 : nev.hashCode());
        result = prime * result + ((varos == null) ? 0 : varos.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Szalloda other = (Szalloda) obj;
        if (nev == null) {
            if (other.nev != null)
                return false;
        } else if (!nev.equals(other.nev))
            return false;
        if (varos == null) {
            if (other.varos != null)
                return false;
        } else if (!varos.equals(other.varos))
            return false;
        return true;
    }
}
