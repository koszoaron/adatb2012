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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + repuloId;
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
        Repulo other = (Repulo) obj;
        if (repuloId != other.repuloId)
            return false;
        return true;
    }
}
