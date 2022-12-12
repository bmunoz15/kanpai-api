package cl.ufro.dci.kanpaiapi.controller.mangas;

import cl.ufro.dci.kanpaiapi.model.Manga;
import cl.ufro.dci.kanpaiapi.model.Publisher;
import cl.ufro.dci.kanpaiapi.service.MangaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("manga")
public class MangaController {
    @Autowired
    MangaService service;

    @GetMapping("/{id}")
    public ResponseEntity<Manga> getMangabyId(@PathVariable long id) {
        return ResponseEntity.status(200).body(service.getMangabyID(id));
    }
    @PostMapping()
    public ResponseEntity<Manga> createManga(@RequestBody Manga manga) {
        return ResponseEntity.status(200).body(service.createManga(manga));
    }
    @PutMapping()
    public ResponseEntity<Manga> updateManga(@RequestBody Manga manga) {
        return ResponseEntity.status(200).body(service.updateManga(manga));
    }
    @DeleteMapping()
    public ResponseEntity<String> deleteManga(@RequestBody long id) {
        return ResponseEntity.status(200).body(service.deleteManga(id));
    }

}
