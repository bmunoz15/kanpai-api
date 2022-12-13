package cl.ufro.dci.kanpaiapi.model;

import cl.ufro.dci.kanpaiapi.dto.ChapterDto;
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
    private int chaNum;
    private String chaName;
    private String chaPath;
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private Manga chaManga;
    public ChapterDto toDto() {
        return new ChapterDto(
                this.chaId,
                this.chaNum,
                this.chaName,
                this.chaPath,
                this.chaManga
        );
    }
}
