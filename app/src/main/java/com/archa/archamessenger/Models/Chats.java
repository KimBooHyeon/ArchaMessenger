package com.archa.archamessenger.Models;

/**
 * Created by Boo on 2016-10-13.
 */
public class Chats {
    private int profile;
    private String title;
    private String last_chat;
    private String date;
    private boolean is_read;

    public Chats(int profile ,String title, String last_chat, String date, boolean is_read){
        this.profile = profile;
        this.title = title;
        this.last_chat = last_chat;
        this.date = date;
        this.is_read = is_read;
    }

    public int getProfile() {
        return profile;
    }
    public String getTitle() {
        return title;
    }
    public String getLast_chat() {
        return last_chat;
    }
    public String getDate() {
        return date;
    }
    public boolean is_read() {
        return is_read;
    }
    public void setProfile(int profile) {
        this.profile = profile;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setLast_chat(String last_chat) {
        this.last_chat = last_chat;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setIs_read(boolean is_read) {
        this.is_read = is_read;
    }
}
