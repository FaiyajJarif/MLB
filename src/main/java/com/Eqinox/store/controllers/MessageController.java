package com.Eqinox.store.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Eqinox.store.entities.Message;

@RestController
public class MessageController {
    @RequestMapping("/hello")
    public Message hello() {
        return new Message("Hello World");
    }
}
