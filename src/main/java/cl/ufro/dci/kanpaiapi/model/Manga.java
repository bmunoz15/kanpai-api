package cl.ufro.dci.kanpaiapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
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

    private State manStatus;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Genre> manGenre;

    private String manPath;

    @ManyToOne()
    private Publisher manPublisher;

    @OneToMany(targetEntity = Chapter.class, mappedBy = "chaManga", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
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
