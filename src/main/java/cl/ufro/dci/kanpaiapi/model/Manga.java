package cl.ufro.dci.kanpaiapi.model;

import cl.ufro.dci.kanpaiapi.dto.MangaDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Manga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long manId;
    private String manName;
    @Column(columnDefinition = "LONGTEXT")
    private String manSynopsis;
    @Enumerated(EnumType.STRING)
    private Demography manDemography;
    private String manRealease;
    @Enumerated(EnumType.STRING)
    private State manStatus;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Genre> manGenre;

    private String manPath;

    @ManyToOne(fetch = FetchType.EAGER)
    @Nullable
    @JsonIgnore
    private Publisher manPublisher;

    @ManyToOne()
    @JsonIgnore
    private Reader manReader;

    @OneToMany(targetEntity = Chapter.class, mappedBy = "chaManga", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Chapter> manChapters;

    public MangaDto toDto() {
        return new MangaDto(
                this.manId,
                this.manName,
                this.manSynopsis,
                this.manDemography,
                this.manRealease,
                this.manStatus,
                this.manGenre,
                this.manPath,
                this.manPublisher.getPubId(),
                this.manChapters
        );
    }

    public MangaDto toDtoP() {
        return new MangaDto(
                this.manId,
                this.manName,
                this.manSynopsis
        );
    }

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
