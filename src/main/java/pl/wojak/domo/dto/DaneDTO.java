package pl.wojak.domo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import pl.wojak.domo.entity.WspolnotaEntity;

import java.util.LinkedList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class DaneDTO {

    List<WspolnotaEntity> wspolnoty;

    List<Integer> dni;
    List<Integer> miesiace;
    List<Integer> lata;
    String dataOd;
    String dataDo;

    public DaneDTO() {
        this.wspolnoty = new LinkedList<>();

        this.dni = new LinkedList<>();
        this.miesiace = new LinkedList<>();
        this.lata = new LinkedList<>();

        for (int i = 1; i <= 31; i++) {
            dni.add(i);
            if (i <= 12) {
                miesiace.add(i);
            }
        }
        for (int j = 2018; j <= 2020; j++) {
            lata.add(j);
        }
    }
}
