package com.socialnetwork.service.message;

import com.socialnetwork.entity.Dialogue;
import com.socialnetwork.entity.Message;

import java.util.List;

/**
 * Created by Vladislav on 9/12/2016.
 */
public interface MessageService {
    List<Message> getMessages(int amount, int user1Id, int userId2);
    void addMessage(int user1Id, int user2Id, int userFromId, String message);
    List<Dialogue> getDialoguesForUser(int userId);
    boolean checkWhichIsDialogue(int user1Id, int user2Id);
    Dialogue addDialogue(int user1Id, int user2Id);
}
