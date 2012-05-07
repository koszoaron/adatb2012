package com.github.koszoaron.adatb2012.pojo;

public class Jarat {
    private long jaratId;
    private Varos honnan;
    private Varos hova;
    private Repulo repulo;
    
    public Jarat() {
        super();
    }

    public Jarat(long jaratId, Varos honnan, Varos hova, Repulo repulo) {
        super();
        this.jaratId = jaratId;
        this.honnan = honnan;
        this.hova = hova;
        this.repulo = repulo;
    }

    public long getJaratId() {
        return jaratId;
    }

    public void setJaratId(long jaratId) {
        this.jaratId = jaratId;
    }

    public Varos getHonnan() {
        return honnan;
    }

    public void setHonnan(Varos honnan) {
        this.honnan = honnan;
    }

    public Varos getHova() {
        return hova;
    }

    public void setHova(Varos hova) {
        this.hova = hova;
    }

    public Repulo getRepulo() {
        return repulo;
    }

    public void setRepulo(Repulo repulo) {
        this.repulo = repulo;
    }

    @Override
    public String toString() {
        return "Jarat [jaratId=" + jaratId + ", honnan=" + honnan + ", hova="
                + hova + ", repulo=" + repulo + "]";
    }
}
