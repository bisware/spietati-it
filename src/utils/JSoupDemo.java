package utils;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JSoupDemo {

    public static void main(String... args) {

        try {
            System.setProperty("http.proxyHost", "proxy-centro.risorse.enel");
            System.setProperty("http.proxyPort", "8080");

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
