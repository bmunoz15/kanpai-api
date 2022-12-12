package cl.ufro.dci.kanpaiapi.service;

import cl.ufro.dci.kanpaiapi.model.Genre;
import cl.ufro.dci.kanpaiapi.model.Manga;
import cl.ufro.dci.kanpaiapi.model.Publisher;
import cl.ufro.dci.kanpaiapi.repository.MangaRepository;
import cl.ufro.dci.kanpaiapi.utils.UtilSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MangaService {
    @Autowired
    MangaRepository repository;

    public Manga createManga(Manga manga) {
        return repository.save(manga);
    }

    public Manga updateManga(Manga manga) {

        Manga mangaStored = repository.findById(manga.getManId()).orElseThrow();

        return repository.save(manga);
    }

    public List<Manga> getAllMangas() {
        return repository.findAll();
    }

    public Manga getMangabyID(long id) {
        return repository.findById(id).orElseThrow();
    }

    public List<Manga> searchMangabyName(String name) {
        return repository.findAllByManNameContainingIgnoreCase(UtilSearch.formatearTexto(name));
    }

    public List<Manga> searchMangabyNameandDemography(String name, Manga.Demography demography){

        return repository.findAllByManNameContainingIgnoreCaseAndManDemography(name,demography);
    }

    public List<Manga> searchMangabyGenre(String genre) {

        List<Manga> mangas = getAllMangas();
        List<Manga> mangasGenre = new ArrayList<>();
        for (Manga manga : mangas) {
            for (Genre genres : manga.getManGenre()) {
                if (genres == Genre.valueOf(genre)) {
                    mangasGenre.add(manga);
                }
            }
        }
        return mangasGenre;
    }

    public List<Manga> searchMangabyDemography(String dmgphy) {
        List<Manga> mangas = getAllMangas();
        List<Manga> mangasDemography = new ArrayList<>();
        for (Manga manga : mangas) {
            if (manga.getManDemography() == Manga.Demography.valueOf(dmgphy)) {
                mangasDemography.add(manga);
            }
        }
        return mangasDemography;
    }

    public String deleteManga(long id) {
        Manga manga = getMangabyID(id);
        repository.delete(manga);
        return "si";
    }
}
