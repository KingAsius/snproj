package com.socialnetwork.service.message;

import com.socialnetwork.entity.Dialogue;
import com.socialnetwork.entity.Message;
import com.socialnetwork.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladislav on 9/12/2016.
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    @Qualifier(value = "jdbcMessageRepository")
    private MessageRepository messageRepository;

    @Override
    public List<Message> getMessages(int amount, int user1Id, int user2Id) {
        Dialogue dialogue = messageRepository.getDialogueWithMessages(user1Id, user2Id);
        if (dialogue == null) dialogue = messageRepository.getDialogueWithMessages(user2Id, user1Id);
        if (dialogue == null) {
            messageRepository.addDialogue(new Dialogue(user1Id, user2Id));
            return new ArrayList<>();
        }
        if (amount > dialogue.getMessages().size()) return dialogue.getMessages();
        else return dialogue.getMessages().subList(dialogue.getMessages().size() - amount, dialogue.getMessages().size());
    }

    @Override
    public void addMessage(int user1Id, int user2Id, int userFromId, String message) {
        Dialogue dialogue = messageRepository.getDialogueWithMessages(user1Id, user2Id);
        if (dialogue == null) dialogue = messageRepository.getDialogueWithMessages(user2Id, user1Id);
        if (dialogue == null) {
            messageRepository.addDialogue(new Dialogue(user1Id, user2Id));
            dialogue = messageRepository.getDialogueWithMessages(user1Id, user2Id);
        }
        messageRepository.addMessage(dialogue, new Message(message, userFromId == dialogue.getUser1Id()));

    }

    public MessageRepository getMessageRepository() {
        return messageRepository;
    }

    public void setMessageRepository(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public List<Dialogue> getDialoguesForUser(int userId) {
        return messageRepository.getDialoguesForUser(userId);
    }

    public boolean checkWhichIsDialogue(int user1Id, int user2Id) {
        Dialogue dialogue = messageRepository.getDialogueWithMessages(user1Id, user2Id);
        if (dialogue == null) return false;
        return true;
    }

    @Override
    public Dialogue addDialogue(int user1Id, int user2Id) {
        messageRepository.addDialogue(new Dialogue(user1Id, user2Id));
        return messageRepository.getDialogueWithMessages(user1Id, user2Id);
    }
}
