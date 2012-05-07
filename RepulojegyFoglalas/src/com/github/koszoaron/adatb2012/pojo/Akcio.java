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
}
