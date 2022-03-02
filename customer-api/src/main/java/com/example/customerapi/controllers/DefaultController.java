package com.example.customerapi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/")
public class DefaultController {
    @GetMapping
    public @ResponseBody String getDefault() {
        return "<div style=\"font-family: Verdana, Arial, Helvetica, sans-serif;\">" +
                "Welcome to the Customer API!<br><br>" +
                "Swagger UI: <a href=\"/swagger-ui/index.html\">/swagger-ui/index.html</a><br>" +
                "</div>";
    }
}
