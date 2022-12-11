package cl.ufro.dci.kanpaiapi.controller.mangas;

import cl.ufro.dci.kanpaiapi.model.Chapter;
import cl.ufro.dci.kanpaiapi.model.Manga;
import cl.ufro.dci.kanpaiapi.service.ChapterService;
import cl.ufro.dci.kanpaiapi.service.MangaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("chapter")
public class ChapterController {
    @Autowired
    ChapterService service;

    @PostMapping()
    public ResponseEntity<Chapter> createChapter(@RequestBody Chapter chapter) {
        return ResponseEntity.status(200).body(service.createChapter(chapter));
    }

    @GetMapping()
    public ResponseEntity<List<Chapter>> getAllChapters() {
        return ResponseEntity.status(200).body(service.getAllChapters());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Chapter> getChapterbyId(@PathVariable long id) {
        return ResponseEntity.status(200).body(service.getChapterbyID(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteManga(@PathVariable long id) {
        return ResponseEntity.status(200).body(service.deleteChapter(id));
    }

}
