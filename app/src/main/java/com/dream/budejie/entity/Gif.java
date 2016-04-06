package com.dream.budejie.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by jerry on 2015/12/22.
 */
public class Gif {
    @JSONField(name = "images")
    private List<String> gif_images;
    @JSONField(name = "width")
    private String gif_widht;
    @JSONField(name = "height")
    private String gif_height;
    @JSONField(name = "gif_thumbnail")
    private List<String> gif_gif_thumbnail;

    public Gif() {
    }

    public List<String> getGif_images() {
        return gif_images;
    }

    public void setGif_images(List<String> gif_images) {
        this.gif_images = gif_images;
    }

    public String getGif_widht() {
        return gif_widht;
    }

    public void setGif_widht(String gif_widht) {
        this.gif_widht = gif_widht;
    }

    public String getGif_height() {
        return gif_height;
    }

    public void setGif_height(String gif_height) {
        this.gif_height = gif_height;
    }

    public List<String> getGif_gif_thumbnail() {
        return gif_gif_thumbnail;
    }

    public void setGif_gif_thumbnail(List<String> gif_gif_thumbnail) {
        this.gif_gif_thumbnail = gif_gif_thumbnail;
    }
}
