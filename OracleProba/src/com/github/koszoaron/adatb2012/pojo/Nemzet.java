package com.github.koszoaron.adatb2012.pojo;

public class Nemzet {
    private int nemzetId;
    private String orszagnev;
    
    public Nemzet() {
        super();
    }

    public Nemzet(int nemzetId, String orszagnev) {
        super();
        this.nemzetId = nemzetId;
        this.orszagnev = orszagnev;
    }

    public int getNemzetId() {
        return nemzetId;
    }

    public void setNemzetId(int nemzetId) {
        this.nemzetId = nemzetId;
    }

    public String getOrszagnev() {
        return orszagnev;
    }

    public void setOrszagnev(String orszagnev) {
        this.orszagnev = orszagnev;
    }

    @Override
    public String toString() {
        return "Nemzet [nemzetId=" + nemzetId + ", orszagnev=" + orszagnev
                + "]";
    }
}
