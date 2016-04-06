package com.dream.budejie.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by jerry on 2015/12/22.
 */
public class Image {
    @JSONField(name = "width")
    private String image_width;
    @JSONField(name = "height")
    private String image_height;
    @JSONField(name = "small")
    private List<String> image_small;
    @JSONField(name = "big")
    private List<String> image_big;
    @JSONField(name = "medium")
    private List<String> image_medium;

    public Image() {
    }

    public String getImage_width() {
        return image_width;
    }

    public void setImage_width(String image_width) {
        this.image_width = image_width;
    }

    public String getImage_height() {
        return image_height;
    }

    public void setImage_height(String image_height) {
        this.image_height = image_height;
    }

    public List<String> getImage_small() {
        return image_small;
    }

    public void setImage_small(List<String> image_small) {
        this.image_small = image_small;
    }

    public List<String> getImage_big() {
        return image_big;
    }

    public void setImage_big(List<String> image_big) {
        this.image_big = image_big;
    }

    public List<String> getImage_medium() {
        return image_medium;
    }

    public void setImage_medium(List<String> image_medium) {
        this.image_medium = image_medium;
    }
}
