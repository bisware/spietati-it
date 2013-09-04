package com.gmail.superbisco.spietati.it.bean;

import java.util.List;

public class SchedaFilm {

    private String locandinaUrl;
    private String titolo;
    private String regista;
    private List<Recensioni> recensioni;

    public String getLocandinaUrl() {
        return locandinaUrl;
    }

    public void setLocandinaUrl(String locandinaUrl) {
        this.locandinaUrl = locandinaUrl;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getRegista() {
        return regista;
    }

    public void setRegista(String regista) {
        this.regista = regista;
    }

    public List<Recensioni> getRecensioni() {
        return recensioni;
    }

    public void setRecensioni(List<Recensioni> recensioni) {
        this.recensioni = recensioni;
    }
}
