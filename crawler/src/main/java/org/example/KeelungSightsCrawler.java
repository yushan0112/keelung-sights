package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;

public class KeelungSightsCrawler {



    public Sight[] getItems(String zone){
        ArrayList<Sight> sights = new ArrayList<>();
        String url =  "https://www2.travelking.com.tw/tourguide/taiwan/keelungcity/"  + zone +  "/";
        try {
            Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(10000).get();
            Elements links = doc.select("#guide-point a");
            for (Element link : links){
                String name = link.text();
                String detailUrl = link.absUrl("href");
                Document detailDoc = Jsoup.connect(detailUrl).userAgent("Mozilla/5.0").timeout(10000).get();
                String address;
                String category;
                String photoURL;
                String description;
                address = getText(detailDoc, "[property=vcard:street-address]");
                category = getText(detailDoc, ".point_type strong");
                photoURL = getAttr(detailDoc, "#galleria img", "src");
                photoURL = photoURL.replace("../../","https://");
                description = getAttr(detailDoc, "meta[itemprop=description]", "content");

                Sight s1 = new Sight();
                s1.setSightName(name);
                s1.setZone(zoneName(zone));
                s1.setCategory(category);
                s1.setPhotoURL(photoURL);
                s1.setDescription(description);
                s1.setAddress(address);
                sights.add(s1);
            }
        }catch(Exception e) {
            System.out.println("連線失敗：" + e.getMessage());
        }
        return sights.toArray(new Sight[0]);
    }

    public static String zoneName(String code) {
        HashMap<String, String> zones = new HashMap<>();
        zones.put("qidu", "七堵區");
        zones.put("zhongshan", "中山區");
        zones.put("zhongzheng", "中正區");
        zones.put("renai", "仁愛區");
        zones.put("anle", "安樂區");
        zones.put("xinyi", "信義區");
        zones.put("nuannuan", "暖暖區");

        if (zones.containsKey(code)) {
            return zones.get(code);
        }
        return "找不到這個區";
    }

    // 讀標籤中間的字，找不到就回傳空字串
    public static String getText(Document doc, String css) {
        Element box = doc.select(css).first();
        if (box == null) {
            return "";
        }
        return box.text();
    }

    // 讀屬性，找不到就回傳空字串
    public static String getAttr(Document doc, String css, String attrName) {
        Element box = doc.select(css).first();
        if (box == null) {
            return "";
        }
        return box.attr(attrName);
    }


}
