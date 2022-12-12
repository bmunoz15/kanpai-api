package cl.ufro.dci.kanpaiapi.dto;

import cl.ufro.dci.kanpaiapi.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class MangaDto {

    private Long manId;
    private String manName;
    private String manSynopsis;
    private Manga.Demography manDemography;
    private String manRealease;
    private Manga.State manStatus;
    private List<Genre> manGenre;
    private String manPath;
    private Long manIdPublisher;
    private List<Chapter> manChapters;

    public enum Demography {
        Seinen,
        Shoujo,
        Shounen,
        Josei,
        Kodomo
    }

    public enum State {
        Emission,
        Finish,
        Hiatus
    }
}
