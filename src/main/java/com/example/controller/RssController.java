package com.example.controller;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Hao on 13/12/2016.
 */
@RestController
public class RssController {

    @RequestMapping(value = "rss", method = RequestMethod.GET)
    public List<Map<String, String>> fetchRss(@RequestParam(value = "url") String url, @RequestParam(value = "keyword") String keyword){
        System.out.println(keyword);
        String weburl = url;
        List<Map<String, String>> result = new ArrayList<>();
        try {
            SyndFeedInput input = new SyndFeedInput();
            XmlReader reader = new XmlReader(new URL(weburl));
            SyndFeed feed = input.build(reader);
            //这里的entries就是item的集合,一般是20个
            List<SyndEntry> entries = feed.getEntries();
            for (SyndEntry entry : entries){
                String title = entry.getTitle();
                String description = entry.getDescription().getValue();
                Document document = Jsoup.parse(description);
                String descriptionStr = document.text();
                System.out.println(descriptionStr);
                if (title.contains(keyword) || descriptionStr.contains(keyword)){
                    Map<String, String> resp = new HashMap<>();
                    resp.put("title", title);
                    resp.put("description", descriptionStr);
                    result.add(resp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
