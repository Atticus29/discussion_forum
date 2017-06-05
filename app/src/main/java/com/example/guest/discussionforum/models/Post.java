package com.example.guest.discussionforum.models;

import java.util.ArrayList;
import java.util.List;

public class Post {
    private String userID;
    private String title;
    private String content;
    private String imageURL;
    private int upCount;
    private int downCount;
    private List<String> commentIDs = new ArrayList<>();

    public Post() {}

    public Post(String title, String content, String imageURL) {
//        this.userID = userID;
        this.title = title;
        this.content = content;
        this.imageURL = imageURL;
        this.upCount = 0;
        this.downCount = 0;
//        this.commentIDs = commentIDs;
    }

    public String getUserID() {
        return userID;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getImageURL() {
        return imageURL;
    }

    public int getUpCount() {
        return upCount;
    }

    public int getDownCount() {
        return downCount;
    }

    public List<String> getCommentIDs() {
        return commentIDs;
    }
}
