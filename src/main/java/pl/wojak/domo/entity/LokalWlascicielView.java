package pl.wojak.domo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "lokalwlascicielview", schema = "domo_schema")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LokalWlascicielView {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "row_number")
    private Long id;

    @JoinColumn(name = "wspolnota_id")
    private Long wspolnotaId;

    @Column(name = "lokal_id")
    private Long lokalId;

    @Column(name = "kod_lokalu")
    private String kodLokalu;

    @Column(name = "nr_mieszkania")
    private Integer nrMieszkania;

    @Column(name = "wlasciciel_id")
    private Long wlascicielId;

    private String nazwisko;

    private String imie;

    @Column(name = "osoba_nazwa")
    private String nazwa;

    private String email;

    private Boolean aktualny;

    private Boolean wspolwlasciciel;

    @Column(name = "osoba_kontaktowa")
    private Boolean osobaKontaktowa;

}
