package pl.wojak.domo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.wojak.domo.entity.LokalWlascicielView;

import java.util.List;

@Repository
public interface LokalWlascicielViewRepository extends CrudRepository<LokalWlascicielView, Long> {


//    @Query(value = "select * from domo_schema.lokalwlascicielview v " +
//            "where v.wspolnota_id = ?1 " +
//            "and v.email is not null " +
//            "and v.aktualny = true " +
//            "and v.osoba_kontaktowa = true " +
//            "order by v.lokal_id, v.nazwisko, v.imie", nativeQuery = true)

    @Query("select v from LokalWlascicielView v where v.wspolnotaId =?1 and v.email is not null and v.aktualny = true and v.osobaKontaktowa = true" +
            " order by v.lokalId, v.nazwisko, v.imie")
    List<LokalWlascicielView> wybierzLokaleIOsobyKontaktoweDlaWybranejWspolnoty(Long wspolnotaId);
}
