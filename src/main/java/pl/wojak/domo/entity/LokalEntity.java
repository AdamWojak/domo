package pl.wojak.domo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lokal", schema = "domo_schema")
@Data
@ToString(exclude = "wlasciciele")
@NoArgsConstructor
@AllArgsConstructor
public class LokalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "wspolnota_id")
    private WspolnotaEntity wspolnota;

    @Column(name = "kod_lokalu")
    private String kodLokalu;

    @Column(name = "nr_mieszkania")
    private Integer nrMieszkania;

    private Integer klatka;

    private String blok;

    @Column(name = "nr_kontrolny")
    private Integer nrKontrolny;

    @ManyToMany(fetch = FetchType.LAZY,
                cascade = { CascadeType.PERSIST,
                            CascadeType.MERGE})
    @JoinTable(name = "lokal_wlasciciel",
            joinColumns = { @JoinColumn(name = "lokal_id") },
            inverseJoinColumns = { @JoinColumn(name = "wlasciciel_id") })
    private List<WlascicielEntity> wlasciciele = new ArrayList<>();
}
