package com.github.koszoaron.adatb2012.pojo;

public class Jarat {
    private int jaratId;
    private Varos honnan;
    private Varos hova;
    private Repulo repulo;
    
    public Jarat() {
        super();
    }

    public Jarat(int jaratId, Varos honnan, Varos hova, Repulo repulo) {
        super();
        this.jaratId = jaratId;
        this.honnan = honnan;
        this.hova = hova;
        this.repulo = repulo;
    }

    public int getJaratId() {
        return jaratId;
    }

    public void setJaratId(int jaratId) {
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + jaratId;
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
        Jarat other = (Jarat) obj;
        if (jaratId != other.jaratId)
            return false;
        return true;
    }
}
