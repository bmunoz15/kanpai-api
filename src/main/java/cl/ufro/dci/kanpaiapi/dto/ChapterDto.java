package cl.ufro.dci.kanpaiapi.dto;

import cl.ufro.dci.kanpaiapi.model.Manga;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ChapterDto {

    private Long chaId;
    private int chaNum;
    private String chaName;
    private String chaPath;
    private Manga chaManga;
}
