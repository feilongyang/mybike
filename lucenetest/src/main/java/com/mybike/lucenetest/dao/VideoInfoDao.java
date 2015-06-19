package com.mybike.lucenetest.dao;

import com.mybike.lucenetest.domain.VideoInfo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class VideoInfoDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {

        ApplicationContext context=new ClassPathXmlApplicationContext("classpath:spring.xml");
        VideoInfoDao videoInfoDao = context.getBean("videoInfoDao", VideoInfoDao.class);
        List<VideoInfo> list = videoInfoDao.getVideoInfoList(1, 500000);
        System.out.println(list);
    }


    public List<VideoInfo> getVideoInfoList(int startIndex, int endIndex) {

        final List<VideoInfo> list=new ArrayList<VideoInfo>();
        String sql = "select *  from (select videoinfo.*, rownum ro from videoinfo where rownum <= ?) where ro >= ?";
        jdbcTemplate.query(sql, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {

                VideoInfo vi = new VideoInfo();
                String videoName = rs.getString("filmname");
                Date createTime = rs.getTimestamp("createtime");
                vi.setVideoName(videoName);
                vi.setCreateTime(createTime);
                list.add(vi);
            }
        },endIndex,startIndex);

        return list;
    }
}
