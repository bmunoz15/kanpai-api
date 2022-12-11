package cl.ufro.dci.kanpaiapi.controller;

import cl.ufro.dci.kanpaiapi.model.Manga;
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

}
