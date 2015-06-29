package com.mybike.lucenetest.controller;

import com.mybike.lucenetest.dao.VideoInfoDao;
import com.mybike.lucenetest.domain.VideoInfo;
import com.mybike.lucenetest.service.IndexService;
import com.mybike.lucenetest.service.SearchService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Resource
    private SearchService searchService;
    @Resource
    private IndexService indexService;
    @Resource
    private VideoInfoDao videoInfoDao;

    @RequestMapping("/buildindex")
    public String buildIndex(Map<String, Object> model) {

        System.out.println("buildindex....");
        model.put("name", "aaa");
        return "buildindex";
    }

    @RequestMapping(value = "/search", method = {RequestMethod.POST, RequestMethod.GET})
    public String search(@RequestParam(value = "keyword",required = false) String keyword, Model model) {

        if (StringUtils.isNoneBlank(keyword)) {
            System.out.println(keyword);
            List<VideoInfo> list = searchService.search(keyword);
            System.out.println(list);
            model.addAttribute("list", list);
        }
        return "search";
    }

}
