package com.github.koszoaron.adatb2012.pojo;

public class Jegy {
    private int jegyId;
    private Jarat jarat;
    private Osztaly osztaly;
    private Biztositas biztositas;
    
    public Jegy() {
        super();
    }

    public Jegy(int jegyId, Jarat jarat, Osztaly osztaly, Biztositas biztositas) {
        super();
        this.jegyId = jegyId;
        this.jarat = jarat;
        this.osztaly = osztaly;
        this.biztositas = biztositas;
    }

    public int getJegyId() {
        return jegyId;
    }

    public void setJegyId(int jegyId) {
        this.jegyId = jegyId;
    }

    public Jarat getJarat() {
        return jarat;
    }

    public void setJarat(Jarat jarat) {
        this.jarat = jarat;
    }

    public Osztaly getOsztaly() {
        return osztaly;
    }

    public void setOsztaly(Osztaly osztaly) {
        this.osztaly = osztaly;
    }

    public Biztositas getBiztositas() {
        return biztositas;
    }

    public void setBiztositas(Biztositas biztositas) {
        this.biztositas = biztositas;
    }

    @Override
    public String toString() {
        return "Jegy [jegyId=" + jegyId + ", jarat=" + jarat + ", osztaly="
                + osztaly + ", biztositas=" + biztositas + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + jegyId;
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
        Jegy other = (Jegy) obj;
        if (jegyId != other.jegyId)
            return false;
        return true;
    }
}
