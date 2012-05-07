package com.github.koszoaron.adatb2012.pojo;

public class Tarsasag {
    private long tarsasagId;
    private String tarsasagNev;
    private Nemzet nemzet;
    
    public Tarsasag() {
        super();
    }

    public Tarsasag(long tarsasagId, String tarsasagNev, Nemzet nemzet) {
        super();
        this.tarsasagId = tarsasagId;
        this.tarsasagNev = tarsasagNev;
        this.nemzet = nemzet;
    }

    public long getTarsasagId() {
        return tarsasagId;
    }

    public void setTarsasagId(long tarsasagId) {
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
