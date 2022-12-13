package cl.ufro.dci.kanpaiapi.dto;

import cl.ufro.dci.kanpaiapi.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class MangaDto {
    public MangaDto(Long manId, String manName, String manSynopsis, String manThumbnail) {
        this.manId = manId;
        this.manName = manName;
        this.manSynopsis = manSynopsis;
        this.manThumbnail = manThumbnail;
    }

    private Long manId;
    private String manName;
    private String manSynopsis;
    private Manga.Demography manDemography;
    private String manRealease;
    private Manga.State manStatus;
    private List<Genre> manGenre;
    private String manThumbnail;
    private String manBanner;
    private Long manIdPublisher;
    private List<Chapter> manChapters;

}
