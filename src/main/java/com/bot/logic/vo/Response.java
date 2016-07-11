package com.bot.logic.vo;

import java.util.List;

/**
 * Created by asus-pc on 19.06.2016.
 */
public class Response {

    private Long count;

    private List<Post> items;

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<Post> getItems() {
        return items;
    }

    public void setItems(List<Post> items) {
        this.items = items;
    }


}
