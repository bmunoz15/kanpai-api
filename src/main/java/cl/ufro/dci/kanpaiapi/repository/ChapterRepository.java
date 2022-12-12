package cl.ufro.dci.kanpaiapi.repository;

import cl.ufro.dci.kanpaiapi.model.Chapter;
import cl.ufro.dci.kanpaiapi.model.Manga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter,Long> {
}
