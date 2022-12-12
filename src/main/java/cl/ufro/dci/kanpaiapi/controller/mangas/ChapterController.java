package cl.ufro.dci.kanpaiapi.controller.mangas;

import cl.ufro.dci.kanpaiapi.dto.ChapterDto;
import cl.ufro.dci.kanpaiapi.model.Chapter;
import cl.ufro.dci.kanpaiapi.model.Manga;
import cl.ufro.dci.kanpaiapi.service.ChapterService;
import cl.ufro.dci.kanpaiapi.service.MangaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("chapter")
public class ChapterController {
    @Autowired
    ChapterService service;

    @PostMapping()
    public ResponseEntity<ChapterDto> createChapter(@RequestBody ChapterDto chapterDto) {
        return ResponseEntity.status(200).body(service.createChapter(chapterDto).toDto());
    }

    @GetMapping()
    public ResponseEntity<List<ChapterDto>> getAllChapters() {
        return ResponseEntity.status(200).body(service.getAllChapters()
                .stream().map(Chapter::toDto)
                .collect(Collectors.toList()));
    }

    @PutMapping
    public ResponseEntity<ChapterDto> updateChapter(@RequestBody ChapterDto chapterDto) {
        return ResponseEntity.status(200).body(service.updateChapter(chapterDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChapterDto> getChapterbyId(@PathVariable long id) {
        return ResponseEntity.status(200).body(service.getChapterbyID(id).toDto());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteManga(@PathVariable long id) {
        return ResponseEntity.status(200).body(service.deleteChapter(id));
    }

}
