package utils;

import bean.Recensione;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.LinkedList;
import java.util.List;

public class RecensioniUtil {

    public static List<Recensione> getElencoUltimeRecensioni() {

        List<Recensione> listRece = new LinkedList<Recensione>();

        try {
            //System.setProperty("http.proxyHost", "proxy-centro.risorse.enel");
            //System.setProperty("http.proxyPort", "8080");

            Document doc = Jsoup.connect(Costanti.URL_BASE_SPIETATI + Costanti.URL_RECENSIONI).timeout(0).get();

            Elements links = doc.select(".tableRecensioni a");

            for (Element link : links) {

                String titolo = link.text();
                String url = link.attr("href");
                String idfilm = url.substring(Costanti.URL_SCHEDA_DETT.length());

                listRece.add(new Recensione(titolo, idfilm));
            }

        } catch (Exception e) {

        }


//        listRece.add(new Recensione("L'EVOCAZIONE", "4831"));
//        listRece.add(new Recensione("THE CANYONS", "4830"));
//        listRece.add(new Recensione("APACHE", "4829"));
//        listRece.add(new Recensione("TURBO", "4828"));
//        listRece.add(new Recensione("COMPLICES", "4827"));
//        listRece.add(new Recensione("MONSTERS UNIVERSITY", "4826"));
//        listRece.add(new Recensione("LES ADIEUX A LA REINE", "4825"));
//
//        listRece.add(new Recensione("L'EVOCAZIONE", "4831"));
//        listRece.add(new Recensione("THE CANYONS", "4830"));
//        listRece.add(new Recensione("APACHE", "4829"));
//        listRece.add(new Recensione("TURBO", "4828"));
//        listRece.add(new Recensione("COMPLICES", "4827"));
//        listRece.add(new Recensione("MONSTERS UNIVERSITY", "4826"));
//        listRece.add(new Recensione("LES ADIEUX A LA REINE", "4825"));

        return listRece;
    }
}
