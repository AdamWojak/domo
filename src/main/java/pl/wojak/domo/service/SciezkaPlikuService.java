package pl.wojak.domo.service;

import org.springframework.stereotype.Service;
import pl.wojak.domo.dto.DaneDTO;
import pl.wojak.domo.entity.LokalWlascicielView;
import pl.wojak.domo.entity.WspolnotaEntity;

import java.io.File;

@Service
public class SciezkaPlikuService {


    private static final String K_R_DOM = "K.R.DOM";
    private static final String ROZLICZENIE_WODY = "rozliczenie_wody";

    //    todo przenieść do properties:     @Value("${app.lokalizacja_plikow}")
    private String zasob = "C:\\Users\\WAC\\Dysk Google";

//     private String przykladowa_sciezka = "C:\\Users\\WAC\\Dysk Google\\K.R.DOM\\rozliczenie_wody\\2019\\03\\W01";
//     private String przykladowa_nazwa = "W01_01";


    public String utworzSciezkeDoRozliczeniaWodyPdf(DaneDTO wybraneDane, WspolnotaEntity wybranaWspolnota, LokalWlascicielView osoba) {

        StringBuilder sb = new StringBuilder(zasob)
                .append(File.separator)
                .append(K_R_DOM)
                .append(File.separator)
                .append(ROZLICZENIE_WODY)
                .append(File.separator)
                .append(wybraneDane.getLata().get(0).toString())
                .append(File.separator)
                .append(wybraneDane.getMiesiace().get(0).toString())
                .append(File.separator)
                .append(wybranaWspolnota.getSymbol())
                .append(File.separator)
                .append(stworzNazwePlikuPdf(wybranaWspolnota.getSymbol(), osoba.getKodLokalu()));

        return sb.toString();
    }

    private String stworzNazwePlikuPdf(String symbolWspolnoty, String kodLokalu) {
        String[] lokal = kodLokalu.split("-");
        if (symbolWspolnoty.contains(lokal[0])) {
            StringBuilder sb = new StringBuilder(symbolWspolnoty)
                    .append("_")
                    .append(lokal[1]);
            return sb.toString();
        }
//        todo stworzyć odpowiedni błąd
        throw new NullPointerException();
    }
}
