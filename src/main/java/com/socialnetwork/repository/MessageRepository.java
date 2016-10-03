package com.socialnetwork.repository;

import com.socialnetwork.entity.Dialogue;
import com.socialnetwork.entity.Message;

import java.util.List;
import java.util.Set;

/**
 * Created by Vladislav on 9/12/2016.
 */
public interface MessageRepository {
    Dialogue getDialogueWithoutMessages(int user1Id, int user2Id);
    List<Dialogue> getDialoguesForUser(int userId);
    void addMessage(Dialogue dialogue, Message message);
    Dialogue getDialogueWithMessages(int user1Id, int user2Id);
    void addDialogue(Dialogue dialogue);
}
