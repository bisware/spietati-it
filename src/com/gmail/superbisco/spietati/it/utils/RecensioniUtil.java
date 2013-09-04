package com.gmail.superbisco.spietati.it.utils;

import com.gmail.superbisco.spietati.it.bean.ElencoFilmItem;
import com.gmail.superbisco.spietati.it.bean.Recensioni;
import com.gmail.superbisco.spietati.it.bean.SchedaFilm;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.LinkedList;
import java.util.List;

public class RecensioniUtil {

    public static List<ElencoFilmItem> getElencoUltimeRecensioni() {

        List<ElencoFilmItem> listRece = new LinkedList<ElencoFilmItem>();
        listRece.add(new ElencoFilmItem("THE CANYONS", "4830"));

        try {

            //System.setProperty("http.proxyHost", "proxy-centro.risorse.enel");
            //System.setProperty("http.proxyPort", "8080");

            Document doc = Jsoup.connect(Costanti.URL_BASE_SPIETATI + Costanti.URL_RECENSIONI).get();

            Elements links = doc.select(".tableRecensioni a");

            for (Element link : links) {

                String titolo = link.text();
                String url = link.attr("href");
                String idfilm = url.substring(Costanti.URL_SCHEDA_DETT.length());

                listRece.add(new ElencoFilmItem(titolo, idfilm));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return listRece;
    }

    public static List<SchedaFilm> getSchedaFilm(String...idFilm) {
        List<SchedaFilm> films = new LinkedList<SchedaFilm>();

        try {
            //System.setProperty("http.proxyHost", "proxy-centro.risorse.enel");
            //System.setProperty("http.proxyPort", "8080");

            Document doc = Jsoup.connect(Costanti.URL_BASE_SPIETATI + Costanti.URL_SCHEDA_DETT + idFilm).get();

            String urlLocandina = doc.select("div#schedaFilm img").attr("src");
            String titolo = doc.select(".titoloRecensione").text();
            String regista = doc.select(".regista").text();
            String receAutore = doc.select("nomeVoto").text();
            String receVoto = doc.select("voto").text();
            String receDataVoto = doc.select("dataVoto").text();

            SchedaFilm film = new SchedaFilm();

            film.setLocandinaUrl(urlLocandina);
            film.setRegista(regista);
            film.setTitolo(titolo);

            List<Recensioni> recensioniList = new LinkedList<Recensioni>();

            Recensioni recensioni = new Recensioni();
            recensioni.setAutore(receAutore);
            recensioni.setData(receDataVoto);
            recensioni.setVoto(receVoto);

            Elements paragrafi = doc.select("#schedaFilmContent p");

            String testo = "";
            for (Element p : paragrafi) {
                testo += p.text() + "\n\n";
            }
            recensioni.setTesto(testo);


            film.setRecensioni(recensioniList);

            films.add(film);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }



        return films;
    }
}
