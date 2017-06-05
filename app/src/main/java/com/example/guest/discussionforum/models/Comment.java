package com.example.guest.discussionforum.models;


public class Comment {
    private String userID;
    private String postID;
    private String content;
    private int upCount;
    private int downCount;

    public Comment() {}

    public Comment(String userID, String postID, String content, int upCount, int downCount) {
        this.userID = userID;
        this.postID = postID;
        this.content = content;
        this.upCount = upCount;
        this.downCount = downCount;
    }

    public String getUserID() {
        return userID;
    }

    public String getPostID() {
        return postID;
    }

    public String getContent() {
        return content;
    }

    public int getUpCount() {
        return upCount;
    }

    public int getDownCount() {
        return downCount;
    }
}
