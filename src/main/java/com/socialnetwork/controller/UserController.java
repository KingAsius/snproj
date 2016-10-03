package com.socialnetwork.controller;

import com.socialnetwork.entity.User;
import com.socialnetwork.service.UserService;
import com.socialnetwork.service.message.MessageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

@Controller
@RequestMapping("/sn.com")
public class UserController {
    private static final Logger log = Logger.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private MessageService messageService;

    @RequestMapping({"/", ""})
    public String getIndexPage(Model model){
        return "index";
    }

    @RequestMapping("/reg")
    public String getRegistrationPage() {
        return "registrationPage";
    }

    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public String getRegistrationPage(@RequestParam(value = "login") String login,
                                      @RequestParam(value = "password") String password,
                                      @RequestParam(value = "name") String name,
                                      @RequestParam(value = "surname") String surname,
                                      @RequestParam(value = "birthday") byte birthDay,
                                      @RequestParam(value = "birthmonth") String birthMonth,
                                      @RequestParam(value = "birthyear") short birthyear,
                                      @RequestParam(value = "city") String city,
                                      @RequestParam(value = "info") String info)
    {
        User user = new User(login, password);
        user.setName(name);
        user.setSurname(surname);
        user.setBirthDay(birthDay);
        user.setBirthMonth(birthMonth);
        user.setBirthYear(birthyear);
        user.setCity(city);
        user.setInfo(info);
        userService.registrateUser(user);
        return "registrationSuceed";
    }

    @RequestMapping(value = "/home")
    public ModelAndView getHomePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", getCurrentUser());
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @RequestMapping(value = {"/users/", "/users"}, method = RequestMethod.GET)
    public ModelAndView getPageWithUsers() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("users");
        User currentUser = getCurrentUser();
        Set<User> users = userService.getAllUsers();
        for (User user : users) if (user.getId() == currentUser.getId()) currentUser = user;
        users.remove(currentUser);
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @RequestMapping(value = "/user", params = "id")
    public ModelAndView getUserPage(@RequestParam int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userPage");
        User user = userService.getUserById(id);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public ModelAndView changePhoto(@RequestParam MultipartFile photo) {
        User user = getCurrentUser();
        log.info(user.toString());
        String photoName = user.getId() + photo.getOriginalFilename().substring(photo.getOriginalFilename().length() - 4);
        //need to rewrite
        log.info(photoName + " -----photoname");
        File file = new File("F:\\PROGRAMMING\\JAVA_PROJECTS\\Social Net\\src\\main\\webapp\\resources\\userPhotos\\" +
                photoName);
        if (file.exists()) file.delete();
        try {
            log.info(file.createNewFile() + "-----  creating");
            FileOutputStream out = new FileOutputStream(file);
            out.write(photo.getBytes());
            out.close();
            user.setPhotopath("/resources/userPhotos/" + photoName);
            userService.updateUser(user);
        }
        catch (Exception e) { log.info(e.getMessage()); }
        ModelAndView modelAndView = getHomePage();
        return modelAndView;
    }

    @RequestMapping("/update")
    public ModelAndView getUpdatePage() {
        ModelAndView modelAndView = new ModelAndView("updatePage");
        modelAndView.addObject("user", getCurrentUser());
        return modelAndView;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView postUpdatePage(@RequestParam(value = "name") String name,
                                       @RequestParam(value = "surname") String surname,
                                       @RequestParam(value = "birthday") byte birthDay,
                                       @RequestParam(value = "birthmonth") String birthMonth,
                                       @RequestParam(value = "birthyear") short birthyear,
                                       @RequestParam(value = "city") String city,
                                       @RequestParam(value = "info") String info) {
        User user = getCurrentUser();
        user.setName(name);
        user.setSurname(surname);
        user.setBirthDay(birthDay);
        user.setBirthMonth(birthMonth);
        user.setBirthYear(birthyear);
        user.setCity(city);
        user.setInfo(info);
        userService.updateUser(user);
        return getHomePage();
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = ((UserDetails) auth.getPrincipal()).getUsername();
        return this.userService.getUserByLogin(login);
    }
}
