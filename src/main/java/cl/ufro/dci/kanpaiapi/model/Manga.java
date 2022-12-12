package cl.ufro.dci.kanpaiapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date manRealease;

    private State manStatus;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Genre> manGenre;

    private String manPath;

    @ManyToOne(fetch = FetchType.EAGER)
    private Publisher manPublisher;

    @ManyToOne()
    @JsonIgnore
    private Reader manReader;

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
