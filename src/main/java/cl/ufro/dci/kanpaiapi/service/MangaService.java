package cl.ufro.dci.kanpaiapi.service;

import cl.ufro.dci.kanpaiapi.model.Manga;
import cl.ufro.dci.kanpaiapi.repository.MangaRepository;
import cl.ufro.dci.kanpaiapi.utils.UtilSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MangaService {
    @Autowired
    MangaRepository repository;

    public Manga createManga(Manga manga) {
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

    public String deleteManga(long id) {
        Manga manga = getMangabyID(id);
        repository.delete(manga);
        return "si";
    }
}
