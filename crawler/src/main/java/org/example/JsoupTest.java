package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupTest {

    public static void main(String[] args) {
        printDetail("https://www2.travelking.com.tw/tourguide/scenery307.html");
    }

    public static void printZone(String zone) {
        String url =  "https://www2.travelking.com.tw/tourguide/taiwan/keelungcity/"  + zone +  "/";
        System.out.println(url);
        try {
            Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(10000).get();
            System.out.println(doc.title());

            Elements links = doc.select("#guide-point a");
            System.out.println("共" + links.size() + "個景點") ;
            for (Element link : links){
                System.out.println(link.text() + " → " + link.absUrl("href"));
            }
        } catch (Exception e) {
            System.out.println("連線失敗：" + e.getMessage());
        }

    }

    public static void printDetail(String url) {
        try {
            Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(10000).get();

            String address;
            String category;
            String photoURL;
            String description;
            address = doc.select("[property=vcard:street-address]").first().text();
            System.out.println("Address: "+ address);
            category = doc.select(".point_type strong").first().text();
            System.out.println("Category: "+ category);
            photoURL = doc.select("#galleria img").first().attr("src");
            photoURL = photoURL.replace("../../","https://");
            System.out.println("PhotoURL: "+ photoURL);
            description = doc.select("[itemprop=description]").first().attr("content");
            System.out.println("Description: "+ description);

        } catch (Exception e) {
            System.out.println("連線失敗：" + e.getMessage());
        }
    }
}