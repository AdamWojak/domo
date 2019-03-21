package pl.wojak.domo.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.wojak.domo.dto.DaneDTO;
import pl.wojak.domo.entity.EmailEntity;
import pl.wojak.domo.entity.LokalWlascicielView;
import pl.wojak.domo.entity.WlascicielEntity;
import pl.wojak.domo.entity.WspolnotaEntity;
import pl.wojak.domo.repository.*;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

@Service
public class MainService {

    private static final String EMAIL_TEMAT = "email.temat";
    private static final String EMAIL_TRESC = "email.tresc";

    private final String SEPARATOR = "-";



    private final WspolnotaRepository wspolnotaRepository;
    private final WlascicielRepository wlascicielRepository;
    private final EmailRepository emailRepository;
    private final LokalWlascicielViewRepository lokalWlascicielViewRepository;
    private final EmailService emailService;
    private final SciezkaPlikuService sciezkaPlikuService;


    public MainService(WspolnotaRepository wspolnotaRepository,
                       WlascicielRepository wlascicielRepository, EmailRepository emailRepository,
                       LokalWlascicielViewRepository lokalWlascicielViewRepository,
                       EmailService emailService, SciezkaPlikuService sciezkaPlikuService) {
        this.wspolnotaRepository = wspolnotaRepository;
        this.wlascicielRepository = wlascicielRepository;
        this.emailRepository = emailRepository;
        this.lokalWlascicielViewRepository = lokalWlascicielViewRepository;
        this.emailService = emailService;
        this.sciezkaPlikuService = sciezkaPlikuService;
    }

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
        List<LokalWlascicielView> lokaleIWlascicieleWspolnoty = lokalWlascicielViewRepository.wybierzLokaleIOsobyKontaktoweDlaWybranejWspolnoty(wybranaWspolnota.getId());

        String dataOdczytu = ustawDateOdczytu(wybraneDane);
        String dataOd = wybraneDane.getDataOd();
        String dataDo = wybraneDane.getDataDo();

        wyslijDzialajacegoMaila(wybranaWspolnota, lokaleIWlascicieleWspolnoty, dataOdczytu, dataOd, dataDo);

//        for (LokalWlascicielView osoba : lokaleIWlascicieleWspolnoty) {
//            WlascicielEntity wlasciciel = wlascicielRepository.findById(osoba.getWlascicielId()).orElseThrow(NullPointerException::new);
//            String temat = MessageFormat.format(ResourceBundle.getBundle("messages").getString(EMAIL_TEMAT), dataOdczytu, wybranaWspolnota.getNazwa(), osoba.getNrMieszkania());
//            String tresc = MessageFormat.format(ResourceBundle.getBundle("messages").getString(EMAIL_TRESC), dataOd, dataDo, wybranaWspolnota.getNazwa(), osoba.getNrMieszkania(), wybranaWspolnota.getGdzie());
//            String sciezka = sciezkaPlikuService.utworzSciezkeDoRozliczeniaWodyPdf(wybraneDane, wybranaWspolnota, osoba);
//
//            EmailEntity email = new EmailEntity(wlasciciel, temat, tresc, sciezka);
//            emailRepository.save(email);
//        }
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


    private void wyslijDzialajacegoMaila(WspolnotaEntity wybranaWspolnota, List<LokalWlascicielView> lokaleIWlascicieleWspolnoty, String dataOdczytu, String dataOd, String dataDo) {
        System.out.println("POCZATEK WYSYŁKI MAILI: " + LocalDateTime.now());
        try {
            for (LokalWlascicielView osoba : lokaleIWlascicieleWspolnoty) {
                String temat = MessageFormat.format(ResourceBundle.getBundle("messages").getString(EMAIL_TEMAT), dataOdczytu, wybranaWspolnota.getNazwa(), osoba.getNrMieszkania());
                String tresc = MessageFormat.format(ResourceBundle.getBundle("messages").getString(EMAIL_TRESC), dataOd, dataDo, wybranaWspolnota.getNazwa(), osoba.getNrMieszkania(), wybranaWspolnota.getGdzie());

                emailService.wyslijEmail(temat, tresc, osoba.getEmail(), osoba.getKodLokalu());
                System.out.println("TIMER POCZĄTEK: " + LocalDateTime.now());
                TimeUnit.SECONDS.sleep(30L);
                System.out.println("TIMER KONIEC: " + LocalDateTime.now());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("KONIEC WYSYŁKI MAILI: " + LocalDateTime.now());
    }



    private void wyslijEmail() {
//        String temat = MessageFormat.format(ResourceBundle.getBundle("messages").getString("email.temat"), user.getUserName());
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
