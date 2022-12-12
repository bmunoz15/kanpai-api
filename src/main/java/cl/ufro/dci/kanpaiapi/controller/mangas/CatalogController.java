package cl.ufro.dci.kanpaiapi.controller.mangas;

import cl.ufro.dci.kanpaiapi.dto.MangaDto;
import cl.ufro.dci.kanpaiapi.model.Manga;
import cl.ufro.dci.kanpaiapi.service.MangaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping()
public class CatalogController {
    @Autowired
    MangaService mangaService;

    @GetMapping()
    public ResponseEntity<List<MangaDto>> getAllMangas() {
        return ResponseEntity.status(200).body(mangaService.getAllMangas()
                .stream().map(Manga::toDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("search-n/{name}")
    public  ResponseEntity<List<MangaDto>> getAllMangasByName(@PathVariable String name){
        return ResponseEntity.status(200).body(mangaService.searchMangabyName(name)
                .stream().map(Manga::toDto)
                .collect(Collectors.toList()));
    }
    @GetMapping("search-g/{genre}")
    public  ResponseEntity<List<MangaDto>> getAllMangasByGenre(@PathVariable String genre){
        return ResponseEntity.status(200).body(mangaService.searchMangabyGenre(genre)
                .stream().map(Manga::toDto)
                .collect(Collectors.toList()));
    }
    @GetMapping("search-d/{demography}")
    public  ResponseEntity<List<MangaDto>> getAllMangasByDemography(@PathVariable String demography){
        return ResponseEntity.status(200).body(mangaService.searchMangabyDemography(demography)
                .stream().map(Manga::toDto)
                .collect(Collectors.toList()));
    }
    @GetMapping("search-nd/{name}/{demography}")
    public  ResponseEntity<List<MangaDto>> getAllMangasByNameandDemography(@PathVariable String name, @PathVariable Manga.Demography demography){
        return ResponseEntity.status(200).body(mangaService.searchMangabyNameandDemography(name,demography)
                .stream().map(Manga::toDto)
                .collect(Collectors.toList()));
    }



}
