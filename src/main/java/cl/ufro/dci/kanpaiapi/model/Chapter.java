package cl.ufro.dci.kanpaiapi.model;

import cl.ufro.dci.kanpaiapi.dto.ChapterDto;
import cl.ufro.dci.kanpaiapi.dto.MangaDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chaId;

    private String chaName;

    private String chaPath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Manga chaManga;

    public ChapterDto toDto() {
        return new ChapterDto(
                this.chaId,
                this.chaName,
                this.chaPath,
                this.chaManga
        );
    }
}
