package cl.ufro.dci.kanpaiapi.repository;

import cl.ufro.dci.kanpaiapi.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher,Long> {

}
