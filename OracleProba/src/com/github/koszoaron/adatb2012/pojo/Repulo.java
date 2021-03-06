package com.github.koszoaron.adatb2012.pojo;

public class Repulo {
    private int repuloId;
    private String tipus;
    private String ulohely;
    private Tarsasag tarsasag;
    
    public Repulo() {
        super();
    }

    public Repulo(int repuloId, String tipus, String ulohely, Tarsasag tarsasag) {
        super();
        this.repuloId = repuloId;
        this.tipus = tipus;
        this.ulohely = ulohely;
        this.tarsasag = tarsasag;
    }

    public int getRepuloId() {
        return repuloId;
    }

    public void setRepuloId(int repuloId) {
        this.repuloId = repuloId;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public String getUlohely() {
        return ulohely;
    }

    public void setUlohely(String ulohely) {
        this.ulohely = ulohely;
    }

    public Tarsasag getTarsasag() {
        return tarsasag;
    }

    public void setTarsasag(Tarsasag tarsasag) {
        this.tarsasag = tarsasag;
    }

    @Override
    public String toString() {
        return "Repulo [repuloId=" + repuloId + ", tipus=" + tipus
                + ", ulohely=" + ulohely + ", tarsasag=" + tarsasag + "]";
    }
}
