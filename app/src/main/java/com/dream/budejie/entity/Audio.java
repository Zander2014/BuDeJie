package com.dream.budejie.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Administrator on 2015/12/25.
 */
public class Audio {
    private int duration;
    private int playcount;
    @JSONField(name="dudio")
    private String audios;
    private String thumbnail;

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPlaycount() {
        return playcount;
    }

    public void setPlaycount(int playcount) {
        this.playcount = playcount;
    }

    public String getAudios() {
        return audios;
    }

    public void setAudios(String audios) {
        this.audios = audios;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
