package pl.wojak.domo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.wojak.domo.entity.WspolnotaEntity;

import java.util.List;

@Repository
public interface WspolnotaRepository extends CrudRepository<WspolnotaEntity, Long> {

    @Query("select w from WspolnotaEntity w where w.obslogiwana = true order by w.nrWspolnoty")
    List<WspolnotaEntity> wybierzWszystieObslugiwaneWspolnoty();
}
