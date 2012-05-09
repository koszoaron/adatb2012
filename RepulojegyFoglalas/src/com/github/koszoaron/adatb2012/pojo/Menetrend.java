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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((indul == null) ? 0 : indul.hashCode());
        result = prime * result + ((jarat == null) ? 0 : jarat.hashCode());
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
        Menetrend other = (Menetrend) obj;
        if (indul == null) {
            if (other.indul != null)
                return false;
        } else if (!indul.equals(other.indul))
            return false;
        if (jarat == null) {
            if (other.jarat != null)
                return false;
        } else if (!jarat.equals(other.jarat))
            return false;
        return true;
    }
}
