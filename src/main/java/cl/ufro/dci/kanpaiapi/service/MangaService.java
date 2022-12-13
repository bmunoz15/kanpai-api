package cl.ufro.dci.kanpaiapi.service;

import cl.ufro.dci.kanpaiapi.dto.MangaDto;
import cl.ufro.dci.kanpaiapi.exception.ApiForbiddenException;
import cl.ufro.dci.kanpaiapi.exception.ApiRequestException;
import cl.ufro.dci.kanpaiapi.model.Genre;
import cl.ufro.dci.kanpaiapi.model.Manga;
import cl.ufro.dci.kanpaiapi.repository.MangaRepository;
import cl.ufro.dci.kanpaiapi.utils.UtilSearch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class MangaService {
    @Autowired
    MangaRepository repository;

    @Autowired
    PublisherService publisherService;

    public Manga createManga(MangaDto mangaDto) {
        log.info("Creating new manga");
        try {
            Manga manga = new Manga();
            buildFromDto(manga, mangaDto);
            return repository.save(manga);
        } catch (Exception e) {
            log.error("Error creating new manga {}", e.toString());
            throw new ApiRequestException("Error creating new manga");
        }
    }

    public MangaDto updateManga(MangaDto mangaDto) {
        log.info("Updating manga");
        try {
            Manga mangaStored = repository.findById(mangaDto.getManId()).orElseThrow();
            buildFromDto(mangaStored, mangaDto);
            return repository.save(mangaStored).toDto();
        } catch (ApiForbiddenException e) {
            log.error(e.toString());
            throw new ApiForbiddenException(e.getMessage());
        } catch (Exception e) {
            log.error("Error updating manga with id " + mangaDto.getManId() + " " + e.toString());
            throw new ApiRequestException("Error updating manga");
        }
    }

    public List<Manga> getAllMangas() {
        return repository.findAll();
    }

    public Manga getMangabyID(long id) {
        log.info("Fetching manga");
        try {
            return repository.findById(id).orElseThrow();
        } catch (NoSuchElementException e) {
            log.error("No manga with Id " + id + " found");
            throw new ApiRequestException("No manga with Id " + id + " found");
        } catch (ApiForbiddenException e) {
            log.error(e.getMessage());
            throw new ApiForbiddenException(e.getMessage());
        } catch (Exception e) {
            log.error("error fetching manga" + " " + e.toString());
            throw new ApiRequestException("Error fetching manga");
        }
    }

    public Manga getFirstMangabyName(String name) {
        log.info("Fetching manga by name");
        try {
            return repository.findMangasByManNameIgnoreCase(name.replaceAll("-", " "));
        } catch (Exception e) {
            log.error("error fetching manga by name " + name + " " + e.toString());
            throw new ApiRequestException("Error fetching manga");
        }

    }

    public List<Manga> searchMangabyName(String name) {
        log.info("Fetching manga by name");
        try {
            return repository.findAllByManNameContainingIgnoreCase(UtilSearch.formatearTexto(name));
        } catch (NullPointerException e) {
            log.error("No manga with name " + name + " found");
            throw new ApiRequestException("No manga with name " + name + " found");
        } catch (Exception e) {
            log.error("error fetching manga by name " + name + " " + e.toString());
            throw new ApiRequestException("Error fetching manga");
        }
    }

    public List<Manga> searchMangabyNameandDemography(String name, Manga.Demography demography) {
        log.info("Fetching manga by name and demography");
        try {
            return repository.findAllByManNameContainingIgnoreCaseAndManDemography(name, demography);
        } catch (NullPointerException e) {
            log.error("No manga with name " + name + " found");
            throw new ApiRequestException("No manga with name " + name + " found");
        } catch (Exception e) {
            log.error("error fetching manga by name " + name + " and demography " + demography + " " + e.toString());
            throw new ApiRequestException("Error fetching manga by name and demography");
        }

    }

    public List<Manga> searchMangabyGenre(String genre) {
        log.info("Fetching manga by genre");
        try {
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
        } catch (Exception e) {
            log.error("error fetching manga by genre" + " " + e.toString());
            throw new ApiRequestException("Error fetching manga by genre");
        }

    }

    public List<Manga> searchMangabyDemography(String dmgphy) {
        log.info("Fetching manga by demography");
        try {
            List<Manga> mangas = getAllMangas();
            List<Manga> mangasDemography = new ArrayList<>();
            for (Manga manga : mangas) {
                if (manga.getManDemography() == Manga.Demography.valueOf(dmgphy)) {
                    mangasDemography.add(manga);
                }
            }
            return mangasDemography;
        } catch (Exception e) {
            log.error("error fetching manga by demography" + " " + e.toString());
            throw new ApiRequestException("Error fetching manga by demography");
        }

    }

    public String deleteManga(long id) {
        log.info("deleting chapter");
        try {
            Manga manga = getMangabyID(id);
            repository.delete(manga);
            return "si";
        } catch (ApiForbiddenException e) {
            log.error(e.getMessage());
            throw new ApiForbiddenException(e.getMessage());
        } catch (Exception e) {
            log.error("error deleting manga" + " " + e.toString());
            throw new ApiRequestException("Error deleting manga");
        }
    }

    private void buildFromDto(Manga manga, MangaDto mangaDto) {
        manga.setManName(mangaDto.getManName());
        manga.setManSynopsis(mangaDto.getManSynopsis());
        manga.setManDemography(mangaDto.getManDemography());
        manga.setManThumbnail(mangaDto.getManThumbnail());
        manga.setManBanner(mangaDto.getManBanner());
        manga.setManRealease(mangaDto.getManRealease());
        manga.setManStatus(mangaDto.getManStatus());
        manga.setManGenre(mangaDto.getManGenre());
        manga.setManPath(mangaDto.getManPath());
        manga.setManPublisher(publisherService.getPublisherbyID(mangaDto.getManIdPublisher()));
        manga.setManChapters(mangaDto.getManChapters());
    }
}
