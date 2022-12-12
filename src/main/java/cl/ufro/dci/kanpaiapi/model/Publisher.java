package cl.ufro.dci.kanpaiapi.model;

import cl.ufro.dci.kanpaiapi.dto.MangaDto;
import cl.ufro.dci.kanpaiapi.dto.PublisherDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pubId;

    private String pubDescription;
    private String pubName;
    @Enumerated(EnumType.STRING)
    private Type pubType;

    @OneToMany(targetEntity = Manga.class, mappedBy = "manPublisher", fetch = FetchType.EAGER,cascade = CascadeType.REFRESH)
    @JsonIgnore
    private List<Manga> pubMangas;

    public enum Type {
        Editorial,
        Mangaka
    }
    public PublisherDto toDto(){
        return new PublisherDto(
        this.pubId,
        this.pubName,
        this.pubDescription,
        this.pubType,
        this.pubMangas
        );
    }
}
