package com.socialnetwork.controller;

import com.socialnetwork.entity.Dialogue;
import com.socialnetwork.entity.Message;
import com.socialnetwork.entity.User;
import com.socialnetwork.repository.CrutchUserRepository;
import com.socialnetwork.service.UserService;
import com.socialnetwork.service.message.MessageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Vladislav on 9/13/2016.
 */
@Controller
@RequestMapping("/sn.com/messages")
public class MessageController {
    private static final Logger log = Logger.getLogger(CrutchUserRepository.class);
    @Autowired
    private UserService userService;
    @Autowired
    private MessageService messageService;

    @RequestMapping({"", "/"})
    public ModelAndView getPageWithDialogues() {
        int userId = getCurrentUser().getId();
        List<Dialogue> dialogues = this.messageService.getDialoguesForUser(userId);
        Map<Dialogue, User> dialoguesAndUsers = new HashMap<>();
        for (Dialogue dialogue : dialogues) {
            int otherUserId =
                    dialogue.getUser1Id() == userId ?
                            dialogue.getUser2Id() : dialogue.getUser1Id();
            dialoguesAndUsers.put(dialogue, userService.getUserById(otherUserId));
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("usersAndDialogues", dialoguesAndUsers);
        modelAndView.setViewName("dialoguePage");
        return modelAndView;
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET, params = "id")
    public ModelAndView getMessagesWithUser(@RequestParam("id") int id, Integer amount) {
        int currentUserId = getCurrentUser().getId();
        User otherUser = this.userService.getUserById(id);
        if (amount == null) amount = 20;
        List<Message> messages = messageService.getMessages(amount, currentUserId, id);
        boolean whichIsMessages = messageService.checkWhichIsDialogue(currentUserId, id);
        ModelAndView modelAndView = new ModelAndView("messagePage");
        List<String> messageList = new ArrayList<>();
        for (Message message : messages) {
            String mes = "";
            if (whichIsMessages == message.isWhoIsOwner())
                mes = "You : ";
            else mes = otherUser.getName() + " " + otherUser.getSurname() + " : ";
            mes += message.getMessage();
            messageList.add(mes);
        }
        modelAndView.addObject("messages", messageList);
        modelAndView.addObject("otherUserName", otherUser.getName() + " " + otherUser.getSurname());
        modelAndView.addObject("amount", amount);
        return modelAndView;
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST, params = {"amount", "id"})
    public ModelAndView getMoreMessages(@RequestParam("amount") int amount, @RequestParam("id") int id) {
        return getMessagesWithUser(id, amount + 20);
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST, params = "id")
    public ModelAndView sendMessage(@RequestParam("textarea") String message,
                                    @RequestParam("id") int id) {
        int userId = getCurrentUser().getId();
        this.messageService.addMessage(userId, id, userId, message);
        return getMessagesWithUser(id, null);
    }

    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = ((UserDetails) auth.getPrincipal()).getUsername();
        return this.userService.getUserByLogin(login);
    }
}
