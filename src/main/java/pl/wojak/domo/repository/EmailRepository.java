package pl.wojak.domo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.wojak.domo.entity.EmailEntity;

@Repository
public interface EmailRepository extends CrudRepository<EmailEntity, Long> {
}
