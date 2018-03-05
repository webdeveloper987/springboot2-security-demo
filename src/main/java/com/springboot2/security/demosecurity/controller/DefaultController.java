package com.springboot2.security.demosecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DefaultController
{

    @GetMapping ("/")
    public String home1()
    {
        return "/home";
    }

    @GetMapping ("/home")
    public String home()
    {
        return "/home";
    }

    @GetMapping ("/admin")
    public String admin()
    {
        return "/admin";
    }

    @GetMapping ("/user")
    public String user()
    {
        return "/user";
    }

    @GetMapping ("/about")
    public String about()
    {
        return "/about";
    }

    @GetMapping ("/login")
    public String login(HttpServletRequest request)
    {
       /* String referrer = request.getHeader("referer");
        if(referrer!=null)
        {
            request.getSession().setAttribute("url_prior_login", referrer);
        }*/
        return "/login";
    }

    @GetMapping ("/403")
    public String error403()
    {
        return "/error/403";
    }

}
