package org.example;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        // ===== 準備資料：兩個景點 =====
        Sight s1 = new Sight();
        s1.setSightName("和平島公園");
        s1.setZone("中正區");
        s1.setCategory("公園");
        s1.setPhotoURL("https://example.com/1.jpg");
        s1.setDescription("基隆北邊的海岸公園");
        s1.setAddress("基隆市中正區平一路360號");

        Sight s2 = new Sight();
        s2.setSightName("情人湖公園");
        s2.setZone("安樂區");
        s2.setCategory("湖泊");
        s2.setPhotoURL("https://example.com/2.jpg");
        s2.setDescription("有吊橋和步道的湖");
        s2.setAddress("基隆市安樂區");


        // ===== 第 1 題：ArrayList<String> =====
        System.out.println("===== 第 1 題 =====");
        ArrayList<String> names = new ArrayList<>();
        names.add("和平島公園");
        names.add("正濱漁港");
        names.add("基隆廟口");
        names.add("八斗子漁港");
        names.add("情人湖公園");

        System.out.println("共有 " + names.size() + " 個景點");
        for (String name : names) {
            System.out.println(name);
        }


        // ===== 第 2 題：ArrayList<Sight> 轉成 Sight[] =====
        System.out.println("===== 第 2 題 =====");
        ArrayList<Sight> list = new ArrayList<>();
        list.add(s1);
        list.add(s2);

        Sight[] results = list.toArray(new Sight[0]);

        for (Sight sight : results ){
            System.out.println(sight);
        }
        // 👉 你來寫：用 toArray 把 list 轉成 Sight[]，取名 result
        // 👉 你來寫：用 for-each 印出 result 裡的每一個


        // ===== 第 3 題：HashMap 查區名 =====
        System.out.println("===== 第 3 題 =====");
        System.out.println(zoneName("renai"));
        System.out.println(zoneName("abc"));


        // ===== 第 4 題：try/catch =====
        System.out.println("===== 第 4 題 =====");
        // 👉 你來寫：try 裡面拿 names 的第 11 個，catch 裡印出「找不到第 11 個景點」

        try{
            System.out.println(names.get(10));
        }catch(Exception e){
            System.out.println("找不到第11個景點");
        }
        System.out.println("程式繼續跑");
    }


    // ===== 方法：代碼轉區名 =====
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
}