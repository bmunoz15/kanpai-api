package cl.ufro.dci.kanpaiapi.controller;

import cl.ufro.dci.kanpaiapi.model.Manga;
import cl.ufro.dci.kanpaiapi.service.MangaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("mangas")
public class MangaController {
    @Autowired
    MangaService service;

    @PostMapping()
    public ResponseEntity<Manga> createManga(@RequestBody Manga manga){
        return ResponseEntity.status(200).body(service.createManga(manga));
    }
    @GetMapping()
    public ResponseEntity<List<Manga>> getAllManga(){
        return ResponseEntity.status(200).body(service.getAllMangas());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Manga> getMangabyId(@PathVariable long id){
        return ResponseEntity.status(200).body(service.getMangabyID(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteManga(@PathVariable long id){
        return ResponseEntity.status(200).body(service.deleteManga(id));
    }

}
