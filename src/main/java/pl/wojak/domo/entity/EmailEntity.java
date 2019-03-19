package pl.wojak.domo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "email", schema = "domo_schema")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "wlasciciel_id")
    private WlascicielEntity wlasciciel;

    @Column(name = "data_utworzenia")
    private LocalDateTime dataUtworzenia;

    @Column(name = "data_wyslania")
    private LocalDateTime dataWyslania;

    private String temat;

    private String tresc;

    @Column(name = "sciezka_pliku")
    private String sciezkaPliku;

    public EmailEntity(WlascicielEntity wlasciciel, String temat, String tresc, String sciezkaPliku) {
        this.wlasciciel = wlasciciel;
        this.dataUtworzenia = LocalDateTime.now();
        this.temat = temat;
        this.tresc = tresc;
        this.sciezkaPliku = sciezkaPliku;
    }
}
