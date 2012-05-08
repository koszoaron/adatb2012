package com.github.koszoaron.adatb2012.pojo;

public class Varos {
    private int varosId;
    private String varosnev;
    private Nemzet nemzet;
    
    public Varos() {
        super();
    }

    public Varos(int varosId, String varosnev, Nemzet nemzet) {
        super();
        this.varosId = varosId;
        this.varosnev = varosnev;
        this.nemzet = nemzet;
    }

    public int getVarosId() {
        return varosId;
    }

    public void setVarosId(int varosId) {
        this.varosId = varosId;
    }

    public String getVarosnev() {
        return varosnev;
    }

    public void setVarosnev(String varosnev) {
        this.varosnev = varosnev;
    }

    public Nemzet getNemzet() {
        return nemzet;
    }

    public void setNemzet(Nemzet nemzet) {
        this.nemzet = nemzet;
    }

    @Override
    public String toString() {
        return "Varos [varosId=" + varosId + ", varosnev=" + varosnev
                + ", nemzet=" + nemzet + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + varosId;
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
        Varos other = (Varos) obj;
        if (varosId != other.varosId)
            return false;
        return true;
    }
}
