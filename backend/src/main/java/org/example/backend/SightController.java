package org.example.backend;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class SightController {

    @GetMapping("/api/sights/{zone}")
    public Sight[] getSights(@PathVariable String zone) {
        KeelungSightsCrawler crawler = new KeelungSightsCrawler();
        Sight[] sight = crawler.getItems(zone);
        return sight;
    }
}
