package com.gmail.superbisco.spietati.it.bean;

public class ElencoFilmItem {

    private String nome;
    private String idFilm;

    public ElencoFilmItem(String nome, String idFilm) {
        this.nome = nome;
        this.idFilm = idFilm;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(String idFilm) {
        this.idFilm = idFilm;
    }

}
