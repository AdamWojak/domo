package pl.wojak.domo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.wojak.domo.entity.WlascicielEntity;

@Repository
public interface WlascicielRepository extends CrudRepository<WlascicielEntity, Long> {

}
