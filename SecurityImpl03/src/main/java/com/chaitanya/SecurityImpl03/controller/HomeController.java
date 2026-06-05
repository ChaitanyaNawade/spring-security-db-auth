package com.chaitanya.SecurityImpl03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController
{
    @GetMapping("/home")
    public String home()
    {
        return "home";
    }

    @GetMapping("/admin/home")
    public String handleadmin()
    {
        return "admin-home";
    }

    @GetMapping("/user/home")
    public String handleuser()
    {
        return "user-home";
    }

    @GetMapping("/403")
    public String accessDenied()
    {
        return "403";
    }

    @RequestMapping("/404")
    public String notFound()
    {
        return "404";
    }

    @GetMapping("/login")
    public  String login()
    {
        return "login";
    }
}
