package com.github.koszoaron.adatb2012.pojo;

public class Osztaly {
    private int szama;
    private int etkezes;
    private int relax;
    private int extraBorond;
    private int internet;
    
    public Osztaly() {
        super();
    }

    public Osztaly(int szama, int etkezes, int relax, int extraBorond,
            int internet) {
        super();
        this.szama = szama;
        this.etkezes = etkezes;
        this.relax = relax;
        this.extraBorond = extraBorond;
        this.internet = internet;
    }

    public int getSzama() {
        return szama;
    }

    public void setSzama(int szama) {
        this.szama = szama;
    }

    public int getEtkezes() {
        return etkezes;
    }

    public void setEtkezes(int etkezes) {
        this.etkezes = etkezes;
    }

    public int getRelax() {
        return relax;
    }

    public void setRelax(int relax) {
        this.relax = relax;
    }

    public int getExtraBorond() {
        return extraBorond;
    }

    public void setExtraBorond(int extraBorond) {
        this.extraBorond = extraBorond;
    }

    public int getInternet() {
        return internet;
    }

    public void setInternet(int internet) {
        this.internet = internet;
    }

    @Override
    public String toString() {
        return "Osztaly [szama=" + szama + ", etkezes=" + etkezes + ", relax="
                + relax + ", extraBorond=" + extraBorond + ", internet="
                + internet + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + szama;
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
        Osztaly other = (Osztaly) obj;
        if (szama != other.szama)
            return false;
        return true;
    }
}
