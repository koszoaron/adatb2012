package com.github.koszoaron.adatb2012.pojo;

public class Akcio {
    private Jarat jarat;
    private int ujar;
    
    public Akcio() {
        super();
    }

    public Akcio(Jarat jarat, int ujar) {
        super();
        this.jarat = jarat;
        this.ujar = ujar;
    }

    public Jarat getJarat() {
        return jarat;
    }

    public void setJarat(Jarat jarat) {
        this.jarat = jarat;
    }

    public int getUjar() {
        return ujar;
    }

    public void setUjar(int ujar) {
        this.ujar = ujar;
    }

    @Override
    public String toString() {
        return "Akcio [jarat=" + jarat + ", ujar=" + ujar + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((jarat == null) ? 0 : jarat.hashCode());
        result = prime * result + ujar;
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
        Akcio other = (Akcio) obj;
        if (jarat == null) {
            if (other.jarat != null)
                return false;
        } else if (!jarat.equals(other.jarat))
            return false;
        if (ujar != other.ujar)
            return false;
        return true;
    }
}
