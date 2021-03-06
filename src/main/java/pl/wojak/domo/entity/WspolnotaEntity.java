package pl.wojak.domo.entity;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "wspolnota", schema = "domo_schema")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WspolnotaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nr_wspolnoty")
    private Integer nrWspolnoty;

    private String symbol;

    private String miasto;

    private String ulica;

    private String nazwa;

    @Column(name = "kod_pocztowy")
    private String kodPocztowy;

    private boolean obslogiwana;

    private String gdzie;


}
