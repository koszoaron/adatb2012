package com.github.koszoaron.adatb2012.pojo;

public class Jegy {
    private long jegyId;
    private Jarat jarat;
    private Osztaly osztaly;
    private Biztositas biztositas;
    
    public Jegy() {
        super();
    }

    public Jegy(long jegyId, Jarat jarat, Osztaly osztaly, Biztositas biztositas) {
        super();
        this.jegyId = jegyId;
        this.jarat = jarat;
        this.osztaly = osztaly;
        this.biztositas = biztositas;
    }

    public long getJegyId() {
        return jegyId;
    }

    public void setJegyId(long jegyId) {
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
}
