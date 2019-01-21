package pl.wojak.domo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "wlasciciel", schema = "domo_schema")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WlascicielEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imie;

    private String nazwisko;

    private String email;

    private boolean aktualny;

    private boolean wspolwlasciciel;

    @Column(name = "osoba_kontaktowa")
    private boolean osobaKontaktowa;

    @ManyToMany(mappedBy = "wlasciciele")
    private List<LokalEntity> lokale = new ArrayList<>();
}
