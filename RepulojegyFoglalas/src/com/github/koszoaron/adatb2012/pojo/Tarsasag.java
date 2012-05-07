package com.github.koszoaron.adatb2012.pojo;

public class Tarsasag {
    private int tarsasagId;
    private String tarsasagNev;
    private Nemzet nemzet;
    
    public Tarsasag() {
        super();
    }

    public Tarsasag(int tarsasagId, String tarsasagNev, Nemzet nemzet) {
        super();
        this.tarsasagId = tarsasagId;
        this.tarsasagNev = tarsasagNev;
        this.nemzet = nemzet;
    }

    public int getTarsasagId() {
        return tarsasagId;
    }

    public void setTarsasagId(int tarsasagId) {
        this.tarsasagId = tarsasagId;
    }

    public String getTarsasagNev() {
        return tarsasagNev;
    }

    public void setTarsasagNev(String tarsasagNev) {
        this.tarsasagNev = tarsasagNev;
    }

    public Nemzet getNemzet() {
        return nemzet;
    }

    public void setNemzet(Nemzet nemzet) {
        this.nemzet = nemzet;
    }

    @Override
    public String toString() {
        return "Tarsasag [tarsasagId=" + tarsasagId + ", tarsasagNev="
                + tarsasagNev + ", nemzet=" + nemzet + "]";
    }
}
