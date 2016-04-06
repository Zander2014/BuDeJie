package com.dream.budejie.entity;

import com.alibaba.fastjson.annotation.JSONField;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by Administrator on 2015/12/27.
 */
@Table(name = "free")
public class FreeBean {

    @Column(name = "time")
    private String time;
    @Column(name = "text")
    private String text;

    public FreeBean() {
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public FreeBean(String time, String text) {
        this.time = time;
        this.text = text;
    }
}
