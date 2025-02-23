package com.chat.app.controller;

import com.chat.app.model.ChatMessage;
import com.chat.app.repositories.ChatMessageRepository;
import com.chat.app.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class ChatController {
    @Autowired
    private ChatMessageService chatMessageService;


    // Endpoint to retrieve all chat messages
    @GetMapping("/messages")
    public List<ChatMessage> getAllMessages() {
        return chatMessageService.getAllMessages();
    }

    // Endpoint to post a new chat message
    @PostMapping("/messages")
    public ChatMessage postMessage(@RequestBody ChatMessage message) {
        return chatMessageService.saveMessage(message);
    }

    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public ChatMessage sendMessage (ChatMessage message){
        return message;
    }

    @GetMapping("/chat")
    public String chat() {
        return "chat";
    }
}
