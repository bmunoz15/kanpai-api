package cl.ufro.dci.kanpaiapi.dto;

import cl.ufro.dci.kanpaiapi.model.Manga;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ChapterDto {
    public ChapterDto(int chaNum, String chaName, String chaPath, String chaMangaName) {
        this.chaNum = chaNum;
        this.chaName = chaName;
        this.chaPath = chaPath;
        this.chaMangaName = chaMangaName;
    }

    private Long chaId;
    private int chaNum;
    private String chaName;
    private String chaPath;
    private String chaMangaName;
}
