package com.dream.budejie.entity;

import com.alibaba.fastjson.annotation.JSONField;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by Administrator on 2015/12/19.
 */
@Table(name = "game")
public class MineBean {
    @Column(name = "id", isId = true)
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "pic")
    private String pic;
    @Column(name = "url")
    private String url;



    public MineBean() {
        super();
    }

    public int getId() {
        return id;
    }

    @JSONField(name = "id")
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    @JSONField(name = "name")
    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    @JSONField(name = "icon")
    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getUrl() {
        return url;
    }

    @JSONField(name = "url")
    public void setUrl(String url) {
        this.url = url;
    }

}
