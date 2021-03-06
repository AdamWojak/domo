package pl.wojak.domo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.wojak.domo.dto.DaneDTO;
import pl.wojak.domo.service.MainService;

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
    public String sendEmail(@ModelAttribute("dane") DaneDTO wybraneDane) {

        mainService.przygotujDaneDoWyslaniaWszystkichEmaili(wybraneDane);
        return "koniec";
    }

    @RequestMapping("/email")
    public String email(Model model) {
        return "email/wodaSzablon";
    }
}
