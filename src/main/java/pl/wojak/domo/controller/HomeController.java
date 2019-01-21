package pl.wojak.domo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.wojak.domo.service.HomeService;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    HomeService homeService;

    @RequestMapping({"/", "/domo"})
    public String hello(Model model) {
        return "index";
    }
}
