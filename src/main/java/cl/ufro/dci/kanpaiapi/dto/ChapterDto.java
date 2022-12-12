package cl.ufro.dci.kanpaiapi.dto;

import cl.ufro.dci.kanpaiapi.model.Chapter;
import cl.ufro.dci.kanpaiapi.model.Genre;
import cl.ufro.dci.kanpaiapi.model.Manga;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ChapterDto {

    private Long chaId;
    private String chaName;
    private String chaPath;
    private Manga chaManga;
}
