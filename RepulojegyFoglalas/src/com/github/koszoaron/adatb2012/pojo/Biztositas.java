package com.github.koszoaron.adatb2012.pojo;

public class Biztositas {
    private int biztId;
    private int utlemondas;
    private int poggyasz;
    private int arvisszaad;
    
    public Biztositas() {
        super();
    }

    public Biztositas(int biztId, int utlemondas, int poggyasz, int arvisszaad) {
        super();
        this.biztId = biztId;
        this.utlemondas = utlemondas;
        this.poggyasz = poggyasz;
        this.arvisszaad = arvisszaad;
    }

    public int getBiztId() {
        return biztId;
    }

    public void setBiztId(int biztId) {
        this.biztId = biztId;
    }

    public int getUtlemondas() {
        return utlemondas;
    }

    public void setUtlemondas(int utlemondas) {
        this.utlemondas = utlemondas;
    }

    public int getPoggyasz() {
        return poggyasz;
    }

    public void setPoggyasz(int poggyasz) {
        this.poggyasz = poggyasz;
    }

    public int getArvisszaad() {
        return arvisszaad;
    }

    public void setArvisszaad(int arvisszaad) {
        this.arvisszaad = arvisszaad;
    }

    @Override
    public String toString() {
        return "Biztositas [biztId=" + biztId + ", utlemondas=" + utlemondas
                + ", poggyasz=" + poggyasz + ", arvisszaad=" + arvisszaad + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + biztId;
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
        Biztositas other = (Biztositas) obj;
        if (biztId != other.biztId)
            return false;
        return true;
    }
}
