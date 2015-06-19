package com.mybike.lucenetest.controller;

import com.mybike.lucenetest.dao.VideoInfoDao;
import com.mybike.lucenetest.service.IndexService;
import com.mybike.lucenetest.service.SearchService;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Map;

@RequestMapping("/home")
public class HomeController {

    @Resource
    private SearchService searchService;
    @Resource
    private IndexService indexService;
    @Resource
    private VideoInfoDao videoInfoDao;

    @RequestMapping("/index")
    public String buildIndex(Map<String,Object> model){

        model.put("name","aaa");
        return "";
    }

    @RequestMapping({"/","/search"})
    public String search(Map<String,Object> model){

        System.out.println("search................");
        model.put("name","bbb");
        return "search";
    }

}
