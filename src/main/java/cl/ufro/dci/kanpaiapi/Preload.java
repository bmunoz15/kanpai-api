package cl.ufro.dci.kanpaiapi;

import cl.ufro.dci.kanpaiapi.model.Manga;
import cl.ufro.dci.kanpaiapi.repository.ChapterRepository;
import cl.ufro.dci.kanpaiapi.repository.MangaRepository;
import cl.ufro.dci.kanpaiapi.service.ChapterService;
import cl.ufro.dci.kanpaiapi.service.MangaService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class Preload {
    private static final File mangasFile = Paths
            .get("src", "main", "resources", "manga.json").toFile();

    @Bean
    CommandLineRunner run(MangaService mangaService, MangaRepository mangaRepository, ChapterService chapterService,
                          ChapterRepository chapterRepository) {
        return args -> {
            List<Manga> mangas = new ArrayList<>();
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            try {
                mangas = mapper.readValue(mangasFile, new TypeReference<List<Manga>>() {
                });

            } catch (Exception e) {
                e.printStackTrace();
            }


            for (Manga manga : mangas) {
                mangaService.createManga(manga);
            }

        };
    }
}
