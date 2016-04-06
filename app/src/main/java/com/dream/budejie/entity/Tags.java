package com.dream.budejie.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by jerry on 2015/12/20.
 */
public class Tags {
    private String id;
    @JSONField(name="name")
    private String tagsName;

    public Tags() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTagsName() {
        return tagsName;
    }

    public void setTagsName(String tagsName) {
        this.tagsName = tagsName;
    }


}
