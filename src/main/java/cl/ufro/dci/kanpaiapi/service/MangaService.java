package cl.ufro.dci.kanpaiapi.service;

import cl.ufro.dci.kanpaiapi.dto.MangaDto;
import cl.ufro.dci.kanpaiapi.model.Genre;
import cl.ufro.dci.kanpaiapi.model.Manga;
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

    @Autowired
    PublisherService publisherService;

    public Manga createManga(MangaDto mangaDto) {
        Manga manga = new Manga();
        buildFromDto(manga,mangaDto);

        return repository.save(manga);
    }

    public MangaDto updateManga(MangaDto mangaDto) {

        Manga mangaStored = repository.findById(mangaDto.getManId()).orElseThrow();

        buildFromDto(mangaStored,mangaDto);
        return repository.save(mangaStored).toDto();
    }

    public List<Manga> getAllMangas() {
        return repository.findAll();
    }

    public Manga getMangabyID(long id) {
        return repository.findById(id).orElseThrow();
    }
    public Manga getFirstMangabyName(String name){
        return repository.findMangaByManNameContainingIgnoreCase(name);
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

    private void buildFromDto(Manga manga, MangaDto mangaDto){
        manga.setManName(mangaDto.getManName());
        manga.setManSynopsis(mangaDto.getManSynopsis());
        manga.setManDemography(mangaDto.getManDemography());
        manga.setManRealease(mangaDto.getManRealease());
        manga.setManStatus(mangaDto.getManStatus());
        manga.setManGenre(mangaDto.getManGenre());
        manga.setManPath(mangaDto.getManPath());
        manga.setManPublisher(publisherService.getPublisherbyID(mangaDto.getManIdPublisher()));
        manga.setManChapters(mangaDto.getManChapters());
    }
}
