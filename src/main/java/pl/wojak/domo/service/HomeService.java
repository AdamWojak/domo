package pl.wojak.domo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.wojak.domo.entity.WspolnotaEntity;
import pl.wojak.domo.repository.WspolnotaRepository;

import java.util.LinkedList;
import java.util.List;

@Service
public class HomeService {

    @Autowired
    WspolnotaRepository wspolnotaRepository;

    public void stronaGlowna(Model model) {
        List<WspolnotaEntity> wspolnoty =  wspolnotaRepository.wybierzWszystieObslugiwaneWspolnoty();
        model.addAttribute("wspolnoty", wspolnoty);
    }
}
