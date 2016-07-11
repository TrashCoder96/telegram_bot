package com.bot.logic.vo;

import java.util.List;

/**
 * Created by asus-pc on 19.06.2016.
 */
public class Post {

    private String text;

    private List<Attachment> attachments;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }



}
