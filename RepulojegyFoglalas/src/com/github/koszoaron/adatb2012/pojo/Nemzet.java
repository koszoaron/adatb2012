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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + nemzetId;
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
        Nemzet other = (Nemzet) obj;
        if (nemzetId != other.nemzetId)
            return false;
        return true;
    }
    
}
