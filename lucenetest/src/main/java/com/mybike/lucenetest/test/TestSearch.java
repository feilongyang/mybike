package com.mybike.lucenetest.test;

import com.mybike.lucenetest.domain.VideoInfo;
import com.mybike.lucenetest.service.SearchService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TestSearch {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        SearchService searchService = context.getBean("searchService", SearchService.class);
        List<VideoInfo> list = searchService.search("键盘");
        System.out.println(list);
    }
}
