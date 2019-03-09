package pl.wojak.domo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.wojak.domo.entity.LokalEntity;

import java.util.List;

@Repository
public interface LokalRepository extends CrudRepository<LokalEntity, Long> {

    @Query(value = "SELECT DISTINCT l.id, l.wspolnota_id, l.kod_lokalu, l.nr_mieszkania, l.klatka, l.blok, l.nr_kontrolny " +
            "FROM domo_schema.lokal l JOIN domo_schema.lokal_wlasciciel los ON (l.id = los.lokal_id) " +
            "JOIN domo_schema.wlasciciel os ON (los.wlasciciel_id = os.id) WHERE l.wspolnota_id =?1 AND os.aktualny = true " +
            "AND os.osoba_kontaktowa = true AND os.email is not null", nativeQuery = true)
    List<LokalEntity> pobierzListeLokaliDlaWspolnotyOId(Long idWspolnoty);

    @Query(value = "SELECT * FROM domo_schema.lokal l " +
            "JOIN domo_schema.lokal_wlasciciel los ON (l.id = los.lokal_id) " +
            "JOIN domo_schema.wlasciciel os ON (los.wlasciciel_id = os.id) " +
            "WHERE l.wspolnota_id =?1 " +
            "AND l.id =?2 " +
            "AND os.aktualny = true " +
            "AND os.osoba_kontaktowa = true " +
            "AND os.email is not null", nativeQuery = true)
    List<LokalEntity> pobierzWybranyLokalDlaWspolnotyOId(Long idWspolnoty, Long idLokalu);
}
