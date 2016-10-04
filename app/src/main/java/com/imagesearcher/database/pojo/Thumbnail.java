package com.imagesearcher.database.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * @author equa1s.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"width", "height", "src"})
@JsonRootName("cse_thumbnail")
public class Thumbnail {

    @JsonProperty("width") private String width;
    @JsonProperty("height") private String height;
    @JsonProperty("src") private String src;

    public Thumbnail() {
    }

    public Thumbnail(String width, String height, String src) {
        this.width = width;
        this.height = height;
        this.src = src;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    @Override
    public String toString() {
        return "Thumbnail{" +
                "width='" + width + '\'' +
                ", height='" + height + '\'' +
                ", src='" + src + '\'' +
                '}';
    }
}
