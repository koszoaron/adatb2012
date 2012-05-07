package com.github.koszoaron.adatb2012.pojo;

public class Osztaly {
    private long szama;
    private int etkezes;
    private int relax;
    private int extraBorond;
    private int internet;
    
    public Osztaly() {
        super();
    }

    public Osztaly(long szama, int etkezes, int relax, int extraBorond,
            int internet) {
        super();
        this.szama = szama;
        this.etkezes = etkezes;
        this.relax = relax;
        this.extraBorond = extraBorond;
        this.internet = internet;
    }

    public long getSzama() {
        return szama;
    }

    public void setSzama(long szama) {
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
}
