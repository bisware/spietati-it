package com.gmail.superbisco.spietati.it.demo;


import com.gmail.superbisco.spietati.it.utils.Costanti;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JSoupDemo {

    public static void main(String... args) {

        //testConnection();

        testSchedaFilm();
    }

    private static void testSchedaFilm() {
        try {
            Document doc = Jsoup.connect(Costanti.URL_BASE_SPIETATI + Costanti.URL_SCHEDA_DETT + "4830").get();

            String urlLocandina = doc.select("div#schedaFilm img").attr("src");
            String titolo = doc.select(".titoloRecensione").text();
            String autore = doc.select(".regista").text();

            System.out.println("Titolo:" + titolo);
            System.out.println("Locandina:" + urlLocandina);
            System.out.println("Regista:" + autore);

            Elements paragrafi = doc.select("#schedaFilmContent p");

            for (Element p : paragrafi) {
                System.out.println(p.text());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private static void testConnection() {

        try {
            Document doc = Jsoup.connect(Costanti.URL_BASE_SPIETATI + Costanti.URL_RECENSIONI).timeout(0).get();

            Elements links = doc.select(".tableRecensioni a");
            for (Element link : links) {

                String titolo = link.text();
                String url = link.attr("href");
                String idfilm = url.substring(Costanti.URL_SCHEDA_DETT.length());

                System.out.println(titolo + ": "+ idfilm);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

}
