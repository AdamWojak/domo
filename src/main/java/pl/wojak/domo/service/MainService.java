package pl.wojak.domo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.wojak.domo.dto.DaneDTO;
import pl.wojak.domo.entity.WspolnotaEntity;
import pl.wojak.domo.repository.WspolnotaRepository;

@Service
public class MainService {

    @Autowired
    WspolnotaRepository wspolnotaRepository;

    public void stronaGlowna(Model model) {
        DaneDTO dane = new DaneDTO();
        dane.setWspolnoty(wspolnotaRepository.wybierzWszystieObslugiwaneWspolnoty());
        model.addAttribute("dane", dane);
        model.addAttribute("wspolnoty", dane.getWspolnoty());
        model.addAttribute("dni", dane.getDni());
        model.addAttribute("miesiace", dane.getMiesiace());
        model.addAttribute("lata", dane.getLata());

    }

    public void przygotujDaneDoWyslaniaWszystkichEmaili(WspolnotaEntity wybranaWspolnota, DaneDTO wybranaData) {

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
