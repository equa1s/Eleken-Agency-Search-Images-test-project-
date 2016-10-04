package com.imagesearcher.database.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author equa1s.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"cse_thumbnail"})
public class Pagemap {

    @JsonProperty("cse_thumbnail") private List<Thumbnail> thumbnails = new ArrayList<>();

    public Pagemap() {
    }

    public Pagemap(List<Thumbnail> thumbnails) {
        this.thumbnails = thumbnails;
    }

    public List<Thumbnail> getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(List<Thumbnail> thumbnails) {
        this.thumbnails = thumbnails;
    }

    @Override
    public String toString() {
        return "Pagemap{" +
                "thumbnails=" + thumbnails +
                '}';
    }
}

