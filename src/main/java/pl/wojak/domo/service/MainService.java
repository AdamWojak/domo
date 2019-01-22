package pl.wojak.domo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.wojak.domo.dto.DaneDTO;
import pl.wojak.domo.entity.LokalEntity;
import pl.wojak.domo.entity.WspolnotaEntity;
import pl.wojak.domo.repository.LokalRepository;
import pl.wojak.domo.repository.WspolnotaRepository;

import java.text.MessageFormat;
import java.util.List;
import java.util.ResourceBundle;

@Service
public class MainService {

    @Autowired
    WspolnotaRepository wspolnotaRepository;

    @Autowired
    LokalRepository lokalRepository;

    private final String SEPARATOR = "-";

    public void stronaGlowna(Model model) {
        DaneDTO dane = new DaneDTO();
        dane.setWspolnoty(wspolnotaRepository.wybierzWszystieObslugiwaneWspolnoty());
        model.addAttribute("dane", dane);
        model.addAttribute("wspolnoty", dane.getWspolnoty());
        model.addAttribute("dni", dane.getDni());
        model.addAttribute("miesiace", dane.getMiesiace());
        model.addAttribute("lata", dane.getLata());

    }

    public void przygotujDaneDoWyslaniaWszystkichEmaili(DaneDTO wybraneDane) {

        WspolnotaEntity wybranaWspolnota = wybraneDane.getWspolnoty().get(0);
        List<LokalEntity> lokaleWybranejWspolnoty = lokalRepository.pobierzListeLokaliDlaWspolnotyOId(wybranaWspolnota.getId());

        String data = ustawDate(wybraneDane);

        String temat = MessageFormat.format(ResourceBundle.getBundle("messages").getString("email.tytul"), data);
//
//        String tresc = ResourceBundle.getBundle("messages").getString("mail.content");
//        String adresat;
        System.out.println("test");
    }

    private String ustawDate(DaneDTO wybraneDane) {
        Integer dzien = wybraneDane.getDni().get(0);
        Integer miesiac = wybraneDane.getMiesiace().get(0);
        Integer rok = wybraneDane.getLata().get(0);

        StringBuilder data = new StringBuilder()
                .append(dzien)
                .append(SEPARATOR)
                .append(miesiac)
                .append(SEPARATOR)
                .append(rok);

        return data.toString();
    }

    private void wyslijEmail() {

//        String temat = MessageFormat.format(ResourceBundle.getBundle("messages").getString("email.tytul"), user.getUserName());
//
//        String tresc = ResourceBundle.getBundle("messages").getString("mail.content");
//        String adresat;
//        if (user.getUserName().equals(ANONYMOUS_NAME)) {
//            adresat = ResourceBundle.getBundle("messages").getString("mail.addressee");
//            emailService.sendSimpleEmail(temat, tresc, adresat);
//        } else {
//            adresat = user.getEmail();
//            emailService.sendExpandedEmail(temat, tresc, adresat);
//        }
    }
}
