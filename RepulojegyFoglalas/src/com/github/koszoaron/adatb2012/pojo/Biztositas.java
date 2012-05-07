package com.github.koszoaron.adatb2012.pojo;

public class Biztositas {
    private long biztId;
    private int utlemondas;
    private int poggyasz;
    private int arvisszaad;
    
    public Biztositas() {
        super();
    }

    public Biztositas(long biztId, int utlemondas, int poggyasz, int arvisszaad) {
        super();
        this.biztId = biztId;
        this.utlemondas = utlemondas;
        this.poggyasz = poggyasz;
        this.arvisszaad = arvisszaad;
    }

    public long getBiztId() {
        return biztId;
    }

    public void setBiztId(long biztId) {
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
}
