package pl.wojak.domo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.wojak.domo.dto.DaneDTO;
import pl.wojak.domo.entity.LokalEntity;
import pl.wojak.domo.entity.WspolnotaEntity;
import pl.wojak.domo.repository.LokalRepository;
import pl.wojak.domo.repository.WlascicielRepository;
import pl.wojak.domo.repository.WspolnotaRepository;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

@Service
public class MainService {

    @Autowired
    WspolnotaRepository wspolnotaRepository;

    @Autowired
    LokalRepository lokalRepository;

    @Autowired
    WlascicielRepository wlascicielRepository;

    @Autowired
    EmailService emailService;

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

        String dataOdczytu = ustawDateOdczytu(wybraneDane);
        String dataOd = wybraneDane.getDataOd();
        String dataDo = wybraneDane.getDataDo();

        System.out.println("POCZATEK WYSYŁKI MAILI" + LocalDateTime.now());

        for (LokalEntity lokal : lokaleWybranejWspolnoty) {
            String temat = MessageFormat.format(ResourceBundle.getBundle("messages").getString("email.tytul"), dataOdczytu, wybranaWspolnota.getNazwa(), lokal.getNrMieszkania());
            String tresc = MessageFormat.format(ResourceBundle.getBundle("messages").getString("email.tresc"), dataOd, dataDo, wybranaWspolnota.getNazwa(), lokal.getNrMieszkania());
            List<String> adresaci = wlascicielRepository.pobierzEmaileOsobKontaktowychDlaKonkretnegoLokalu(lokal.getId());
            for (String adresat : adresaci) {
                emailService.wyslijEmail(temat, tresc, adresat);
            }
        }

        System.out.println("KONIEC WYSYŁKI MAILI" + LocalDateTime.now());
    }

    private String ustawDateOdczytu(DaneDTO wybraneDane) {
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
}
