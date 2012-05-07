package com.github.koszoaron.adatb2012.pojo;

import java.sql.Date;

public class Menetrend {
    private Jarat jarat;
    private Date indul;
    private Date erkezik;
    
    public Menetrend() {
        super();
    }

    public Menetrend(Jarat jarat, Date indul, Date erkezik) {
        super();
        this.jarat = jarat;
        this.indul = indul;
        this.erkezik = erkezik;
    }

    public Jarat getJarat() {
        return jarat;
    }

    public void setJarat(Jarat jarat) {
        this.jarat = jarat;
    }

    public Date getIndul() {
        return indul;
    }

    public void setIndul(Date indul) {
        this.indul = indul;
    }

    public Date getErkezik() {
        return erkezik;
    }

    public void setErkezik(Date erkezik) {
        this.erkezik = erkezik;
    }

    @Override
    public String toString() {
        return "Menetrend [jarat=" + jarat + ", indul=" + indul + ", erkezik="
                + erkezik + "]";
    }
}
