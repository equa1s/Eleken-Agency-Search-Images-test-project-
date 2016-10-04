package com.imagesearcher.database.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author equa1s.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"title", "pagemap"})
public class SearchItem {

    @JsonProperty("title")
    private String title;
    @JsonProperty("pagemap")
    private Pagemap pagemap;

    public SearchItem() {
    }

    public SearchItem(String title, Pagemap pagemap) {
        this.title = title;
        this.pagemap = pagemap;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Pagemap getPagemap() {
        return pagemap;
    }

    public void setPagemap(Pagemap pagemap) {
        this.pagemap = pagemap;
    }

    @Override
    public String toString() {
        return "SearchItem{" +
                "title='" + title + '\'' +
                ", pagemap=" + pagemap +
                '}';
    }
}
