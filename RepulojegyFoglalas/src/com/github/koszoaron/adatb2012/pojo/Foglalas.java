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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((jegy == null) ? 0 : jegy.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
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
        Foglalas other = (Foglalas) obj;
        if (jegy == null) {
            if (other.jegy != null)
                return false;
        } else if (!jegy.equals(other.jegy))
            return false;
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        return true;
    }
}
