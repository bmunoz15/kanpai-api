package cl.ufro.dci.kanpaiapi.controller.mangas;

import cl.ufro.dci.kanpaiapi.model.Manga;
import cl.ufro.dci.kanpaiapi.service.MangaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping()
public class CatalogController {
    @Autowired
    MangaService mangaService;

    @GetMapping()
    public ResponseEntity<List<Manga>> getAllMangas() {
        return ResponseEntity.status(200).body(mangaService.getAllMangas());
    }

    @GetMapping("search-n/{name}")
    public  ResponseEntity<List<Manga>> getAllMangasByName(@PathVariable String name){
        return ResponseEntity.status(200).body(mangaService.searchMangabyName(name));
    }
    @GetMapping("search-g/{genre}")
    public  ResponseEntity<List<Manga>> getAllMangasByGenre(@PathVariable String genre){
        return ResponseEntity.status(200).body(mangaService.searchMangabyGenre(genre));
    }
    @GetMapping("search-d/{demography}")
    public  ResponseEntity<List<Manga>> getAllMangasByDemography(@PathVariable String demography){
        return ResponseEntity.status(200).body(mangaService.searchMangabyDemography(demography));
    }
    @GetMapping("search-nd/{name}/{demography}")
    public  ResponseEntity<List<Manga>> getAllMangasByNameandDemography(@PathVariable String name, @PathVariable Manga.Demography demography){
        return ResponseEntity.status(200).body(mangaService.searchMangabyNameandDemography(name,demography));
    }



}
