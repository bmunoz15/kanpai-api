package cl.ufro.dci.kanpaiapi.repository;

import cl.ufro.dci.kanpaiapi.model.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, Long> {

}
