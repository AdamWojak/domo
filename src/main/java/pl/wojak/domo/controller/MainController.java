package pl.wojak.domo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.wojak.domo.entity.WspolnotaEntity;
import pl.wojak.domo.service.MainService;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    MainService mainService;

    @GetMapping({"/"})
    public String mainPage(Model model) {

        mainService.stronaGlowna(model);
        return "index";
    }

    @PostMapping("/")
    public String sendEmail(@ModelAttribute("wspolnoty")List<WspolnotaEntity> wspolnoty, Model model){

        System.out.println("test");

        return "udało się!";
    }

    @RequestMapping("/email")
    public String email(Model model) {
        return "email/wodaTemplate";
    }
}