package com.github.koszoaron.adatb2012.pojo;

public class Foglalas {
    private Felhasznalo user;
    private Jegy jegy;
    
    public Foglalas() {
        super();
    }

    public Foglalas(Felhasznalo user, Jegy jegy) {
        super();
        this.user = user;
        this.jegy = jegy;
    }

    public Felhasznalo getUser() {
        return user;
    }

    public void setUser(Felhasznalo user) {
        this.user = user;
    }

    public Jegy getJegy() {
        return jegy;
    }

    public void setJegy(Jegy jegy) {
        this.jegy = jegy;
    }

    @Override
    public String toString() {
        return "Foglalas [user=" + user + ", jegy=" + jegy + "]";
    }
}
