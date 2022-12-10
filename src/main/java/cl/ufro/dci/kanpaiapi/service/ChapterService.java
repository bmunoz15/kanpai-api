package cl.ufro.dci.kanpaiapi.service;

import cl.ufro.dci.kanpaiapi.model.Chapter;
import cl.ufro.dci.kanpaiapi.model.Manga;
import cl.ufro.dci.kanpaiapi.repository.ChapterRepository;
import cl.ufro.dci.kanpaiapi.repository.MangaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChapterService {
    @Autowired
    ChapterRepository repository;

    public Chapter createChapter(Chapter chapter) {
        return repository.save(chapter);
    }

    public List<Chapter> getAllChapters() {
        return repository.findAll();
    }

    public Chapter getChapterbyID(long id) {
        return repository.findById(id).orElseThrow();
    }

    public String deleteChapter(long id) {
        Chapter chapter = getChapterbyID(id);
        repository.delete(chapter);
        return "si";
    }
}
