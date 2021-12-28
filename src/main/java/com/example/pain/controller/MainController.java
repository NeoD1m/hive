package com.example.pain.controller;

import com.example.pain.domain.Message;
import com.example.pain.domain.User;
import com.example.pain.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.OnMessage;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
public class MainController{
    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/")
    public String main(
            @AuthenticationPrincipal User user,
            @RequestParam(required = false, defaultValue = "") String filter,
            Map<String,Object> model) throws InterruptedException {
        Iterable<Message> messages = messageRepo.findAll();
        Collections.reverse((List<?>) messages);

        model.put("messages",messages);
        model.put("filter",filter);
        return "main";
    }

    @GetMapping("/error")
    public String error(){
        return "main";
    }

    @PostMapping("/")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String comment,
             Map<String, Object> model
    ) {


        Message message = new Message(user, comment);
        messageRepo.save(message);
        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);

        return "redirect:/";
    }
}