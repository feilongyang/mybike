package com.mybike.lucenetest.domain;

import java.util.Date;

public class VideoInfo {

    private String videoName;
    private Date createTime;

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "VideoInfo{" +
                "videoName='" + videoName + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}

