package com.mybike.lucenetest.test;

import com.mybike.lucenetest.dao.VideoInfoDao;
import com.mybike.lucenetest.service.IndexService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBuildIndex {

    public static void main(String[] args) {

        ApplicationContext context=new ClassPathXmlApplicationContext("classpath:spring.xml");
        IndexService indexService = context.getBean("indexService", IndexService.class);
        VideoInfoDao videoInfoDao = context.getBean("videoInfoDao", VideoInfoDao.class);
        indexService.build(videoInfoDao.getVideoInfoList(1,100000));
        System.out.println("索引构建完毕！");
    }
}
