package com.github.koszoaron.adatb2012.pojo;

public class Nemzet {
    private long nemzetId;
    private String orszagnev;
    
    public Nemzet() {
        super();
    }

    public Nemzet(long nemzetId, String orszagnev) {
        super();
        this.nemzetId = nemzetId;
        this.orszagnev = orszagnev;
    }

    public long getNemzetId() {
        return nemzetId;
    }

    public void setNemzetId(long nemzetId) {
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
