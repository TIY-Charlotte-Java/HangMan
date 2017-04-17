package com.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Ben on 4/14/17.
 */
@Controller
public class HomeController {
    @RequestMapping(method = RequestMethod.GET, path = "/")
    public String home() {
        return "home";
    }
}
