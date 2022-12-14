package cl.ufro.dci.kanpaiapi;

import cl.ufro.dci.kanpaiapi.dto.ChapterDto;
import cl.ufro.dci.kanpaiapi.dto.MangaDto;
import cl.ufro.dci.kanpaiapi.dto.PublisherDto;
import cl.ufro.dci.kanpaiapi.dto.ReaderDto;
import cl.ufro.dci.kanpaiapi.exception.ApiRequestException;
import cl.ufro.dci.kanpaiapi.service.ChapterService;
import cl.ufro.dci.kanpaiapi.service.MangaService;
import cl.ufro.dci.kanpaiapi.service.PublisherService;
import cl.ufro.dci.kanpaiapi.service.ReaderService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class Preload {
    private static final String[] ruta= {"src","main","resources"};
    private static final File publisherFile = Paths
            .get(ruta[0], ruta[1], ruta[2], "publisher.json").toFile();
    private static final File mangaFile = Paths
            .get(ruta[0], ruta[1], ruta[2], "manga.json").toFile();
    private static final File readerFile = Paths
            .get(ruta[0], ruta[1], ruta[2], "reader.json").toFile();
    private static final File chapterFile = Paths
            .get(ruta[0], ruta[1], ruta[2], "chapter.json").toFile();

    @Bean
    CommandLineRunner run(PublisherService publisherService, MangaService mangaService, ReaderService readerService,
    ChapterService chapterService) {
        return args -> {
            List<PublisherDto> publishers = new ArrayList<>();
            List<MangaDto> mangas = new ArrayList<>();
            List<ReaderDto> readers = new ArrayList<>();
            List<ChapterDto> chapters = new ArrayList<>();
            ObjectMapper mapper = new ObjectMapper();

            try {
                publishers = mapper.readValue(publisherFile, new TypeReference<List<PublisherDto>>() {
                });
            } catch (Exception e) {
                throw new ApiRequestException(e.getMessage());
            }
            for (PublisherDto publisher : publishers) {
                publisherService.createPublisher(publisher);
            }

            try {
                mangas = mapper.readValue(mangaFile, new TypeReference<List<MangaDto>>() {
                });
            } catch (Exception e) {
                throw new ApiRequestException(e.getMessage());
            }

            for (MangaDto manga : mangas) {
                mangaService.createManga(manga);
            }

            try {
                readers = mapper.readValue(readerFile, new TypeReference<List<ReaderDto>>() {
                });
            } catch (Exception e) {
                throw new ApiRequestException(e.getMessage());
            }

            for (ReaderDto readerDto : readers) {
                readerService.createReader(readerDto);
            }

            try {
                chapters = mapper.readValue(chapterFile, new TypeReference<List<ChapterDto>>() {
                });
            } catch (Exception e) {
                throw new ApiRequestException(e.getMessage());
            }

            for (ChapterDto chapterDto : chapters) {
                chapterService.createChapter(chapterDto);
            }

        };
    }
}
