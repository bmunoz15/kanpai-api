package cl.ufro.dci.kanpaiapi.repository;

import cl.ufro.dci.kanpaiapi.model.Manga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MangaRepository extends JpaRepository<Manga, Long> {
    List<Manga> findAllByManNameContainingIgnoreCase(String name);

    List<Manga> findAllByManNameContainingIgnoreCaseAndManDemography(String name, Manga.Demography demography);

    Manga findMangasByManNameIgnoreCase(String name);
}