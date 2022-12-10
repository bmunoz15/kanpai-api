package cl.ufro.dci.kanpaiapi.model;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Manga chaManga;
}
