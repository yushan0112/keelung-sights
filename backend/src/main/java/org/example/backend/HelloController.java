package org.example.backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, 基隆！";
    }

    @GetMapping("/about")
    public String name() {
        return "這是基隆景點專題，作者：你的名字";
    }

    @GetMapping("/hello/{name}")
    public String helloName(@PathVariable String name) {
        return "Hello, " + name + "！";
    }

    @GetMapping("/zone/{code}")
    public String getZoneName(@PathVariable String code) {
        return zoneName(code) ;
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

}