package com.socialnetwork.repository;

import com.socialnetwork.entity.Dialogue;
import com.socialnetwork.entity.Message;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Vladislav on 9/12/2016.
 */
@Repository
public class CrutchMessageRepository implements MessageRepository {
    private Set<Dialogue> dialogues = new HashSet<>();

    public CrutchMessageRepository() {
        Dialogue dialogue = new Dialogue(1, 2);
        dialogue.addMessage(new Message("hello message world!!!", true));
        dialogue.addMessage(new Message("hello Vlad, how are you??", false));
        dialogue.addMessage(new Message("im ok!", true));
        dialogues.add(dialogue);
    }

    @Override
    public void addMessage(Dialogue dialogue, Message message) {
    }



    public Set<Dialogue> getDialogues() {
        return dialogues;
    }

    @Override
    public Dialogue getDialogueWithoutMessages(int user1Id, int user2Id) {
        return null;
    }

    @Override
    public Dialogue getDialogueWithMessages(int user1Id, int user2Id) {
        return null;
    }

    @Override
    public void addDialogue(Dialogue dialogue) {

    }

    @Override
    public List<Dialogue> getDialoguesForUser(int userId) {
        return null;
    }
}
