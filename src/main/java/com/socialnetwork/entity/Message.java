package com.socialnetwork.entity;

/**
 * Created by Vladislav on 9/12/2016.
 */
public class Message {
    private String message;
    // if - true: from user1 to user2, else from user2 to user1
    private boolean whoIsOwner;

    public Message(String message, boolean whoIsOwner) {
        this.message = message;
        this.whoIsOwner = whoIsOwner;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isWhoIsOwner() {
        return whoIsOwner;
    }

    public void setWhoIsOwner(boolean whoIsOwner) {
        this.whoIsOwner = whoIsOwner;
    }

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", whoIsOwner=" + whoIsOwner +
                '}';
    }
}
