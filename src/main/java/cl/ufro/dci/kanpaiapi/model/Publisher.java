package cl.ufro.dci.kanpaiapi.model;

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

    @OneToMany(mappedBy = "manPublisher")
    @JsonManagedReference
    private List<Manga> pubMangas;

    private enum Type {
        Editorial,
        Mangaka
    }
}
