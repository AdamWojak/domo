package pl.wojak.domo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.wojak.domo.entity.WlascicielEntity;

import java.util.List;

@Repository
public interface WlascicielRepository extends CrudRepository<WlascicielEntity, Long> {


    @Query(value = "select os.email from domo_schema.wlasciciel os left join domo_schema.lokal_wlasciciel los on os.id = los.wlasciciel_id where los.lokal_id = 7 and os.osoba_kontaktowa = true", nativeQuery = true)
    List<String> pobierzEmaileOsobKontaktowychDlaKonkretnegoLokalu(Long idLokalu);
}
