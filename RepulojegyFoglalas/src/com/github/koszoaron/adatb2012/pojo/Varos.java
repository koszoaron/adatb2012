package com.github.koszoaron.adatb2012.pojo;

public class Varos {
    private long varosId;
    private String varosnev;
    private Nemzet nemzet;
    
    public Varos() {
        super();
    }

    public Varos(long varosId, String varosnev, Nemzet nemzet) {
        super();
        this.varosId = varosId;
        this.varosnev = varosnev;
        this.nemzet = nemzet;
    }

    public long getVarosId() {
        return varosId;
    }

    public void setVarosId(long varosId) {
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
}
