package com.socialnetwork.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladislav on 9/12/2016.
 */
public class Dialogue {
    private List<Message> messages = new ArrayList<>();
    int user1Id;
    int user2Id;

    public Dialogue(int user1Id, int user2Id) {
        this.user1Id = user1Id;
        this.user2Id = user2Id;
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public int getUser1Id() {
        return user1Id;
    }

    public int getUser2Id() {
        return user2Id;
    }


}
