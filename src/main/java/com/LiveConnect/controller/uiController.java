package com.LiveConnect.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class uiController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("message", "Welcome to the Video Streaming Platform");
        return "Home";
    }

    @GetMapping("/video")
    public String viewVideo( Model model) {
        model.addAttribute("message", "Welcome to the Video Streaming Platform");
        return "video";
    }
}