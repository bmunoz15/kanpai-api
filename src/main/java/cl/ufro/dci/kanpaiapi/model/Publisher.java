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
public class Editorial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ediId;

    @OneToMany(mappedBy = "manEditorial")
    private List<Manga> ediManga;

    @OneToMany(mappedBy = "mankEditorial")
    private List<Mangaka> ediMangaka;
}
