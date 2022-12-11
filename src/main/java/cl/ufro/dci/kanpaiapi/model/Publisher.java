package cl.ufro.dci.kanpaiapi.model;

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

    @OneToMany(mappedBy = "manPublisher")
    private List<Manga> pubMangas;
}
