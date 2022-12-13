package cl.ufro.dci.kanpaiapi.service;

import cl.ufro.dci.kanpaiapi.dto.ChapterDto;
import cl.ufro.dci.kanpaiapi.exception.ApiForbiddenException;
import cl.ufro.dci.kanpaiapi.exception.ApiRequestException;
import cl.ufro.dci.kanpaiapi.model.Chapter;
import cl.ufro.dci.kanpaiapi.repository.ChapterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class ChapterService {
    @Autowired
    ChapterRepository repository;

    public Chapter createChapter(ChapterDto chapterDto) {
        log.info("Creating new chapter");
        try {
            Chapter chapter = new Chapter();
            buildFromDto(chapter, chapterDto);
            return repository.save(chapter);
        } catch (Exception e) {
            log.error("Error creating new Chapter {}", e.toString());
            throw new ApiRequestException("Error creating new chapter");
        }
    }

    public ChapterDto updateChapter(ChapterDto chapterDto) {
        log.info("Updating chapter");
        try {
            Chapter chapterStoraged = getChapterbyID(chapterDto.getChaId());
            buildFromDto(chapterStoraged, chapterDto);
            return repository.save(chapterStoraged).toDto();
        } catch (ApiForbiddenException e) {
            log.error(e.toString());
            throw new ApiForbiddenException(e.getMessage());
        } catch (Exception e) {
            log.error("Error updating chapter with id " + chapterDto.getChaId() + " " + e.toString());
            throw new ApiRequestException("Error updating chapter");
        }
    }

    public List<Chapter> getAllChapters() {
        return repository.findAll();
    }

    public Chapter getChapterbyID(long id) {
        log.info("Fetching chapter");
        try {
            return repository.findById(id).orElseThrow();
        } catch (NoSuchElementException e) {
            log.error("No chapter with Id " + id + " found");
            throw new ApiRequestException("No chapter with Id " + id + " found");
        } catch (ApiForbiddenException e) {
            log.error(e.getMessage());
            throw new ApiForbiddenException(e.getMessage());
        } catch (Exception e) {
            log.error("error fetching chapter" + " " + e.toString());
            throw new ApiRequestException("Error fetching chapter");
        }
    }

    public String deleteChapter(long id) {
        log.info("deleting chapter");
        try {
            Chapter chapter = getChapterbyID(id);
            repository.delete(chapter);
            return "si";
        } catch (ApiForbiddenException e) {
            log.error(e.getMessage());
            throw new ApiForbiddenException(e.getMessage());
        } catch (Exception e) {
            log.error("error deleting chapter" + " " + e.toString());
            throw new ApiRequestException("Error deleting chapter");
        }
    }

    private void buildFromDto(Chapter chapter, ChapterDto chapterDto) {
        chapter.setChaName(chapterDto.getChaName());
        chapter.setChaNum(chapterDto.getChaNum());
        chapter.setChaPath(chapterDto.getChaPath());
        chapter.setChaManga(chapterDto.getChaManga());
    }

}
