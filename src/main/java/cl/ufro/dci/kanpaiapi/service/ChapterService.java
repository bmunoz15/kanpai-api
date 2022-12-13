package cl.ufro.dci.kanpaiapi.service;

import cl.ufro.dci.kanpaiapi.dto.ChapterDto;
import cl.ufro.dci.kanpaiapi.dto.MangaDto;
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

    public Chapter createChapter(ChapterDto chapterDto) {
        Chapter chapter = new Chapter();
        buildFromDto(chapter,chapterDto);
        return repository.save(chapter);
    }
    public ChapterDto updateChapter(ChapterDto chapterDto){
        Chapter chapterStoraged = getChapterbyID(chapterDto.getChaId());
        buildFromDto(chapterStoraged,chapterDto);
        return repository.save(chapterStoraged).toDto();
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
    private void buildFromDto(Chapter chapter, ChapterDto chapterDto){
        chapter.setChaName(chapterDto.getChaName());
        chapter.setChaNum(chapterDto.getChaNum());
        chapter.setChaPath(chapterDto.getChaPath());
        chapter.setChaManga(chapterDto.getChaManga());
    }

}
