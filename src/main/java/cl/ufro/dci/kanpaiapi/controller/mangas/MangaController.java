package cl.ufro.dci.kanpaiapi.controller.mangas;

import cl.ufro.dci.kanpaiapi.dto.MangaDto;
import cl.ufro.dci.kanpaiapi.service.MangaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("manga")
@CrossOrigin(origins = "http://localhost:5173")
public class MangaController {
    @Autowired
    MangaService service;

    @GetMapping("/{name}")
    public ResponseEntity<MangaDto> getFirstMangabyName(@PathVariable String name) {
        return ResponseEntity.status(200).body(service.getFirstMangabyName(name).toDto());
    }

    @PostMapping()
    public ResponseEntity<MangaDto> createManga(@RequestBody MangaDto mangaDto) {
        return ResponseEntity.status(200).body(service.createManga(mangaDto).toDto());
    }

    @PutMapping()
    public ResponseEntity<MangaDto> updateManga(@RequestBody MangaDto mangaDto) {
        return ResponseEntity.status(200).body(service.updateManga(mangaDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteManga(@PathVariable long id) {
        return ResponseEntity.status(200).body(service.deleteManga(id));
    }

}
