package cl.ufro.dci.kanpaiapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Manga {

    @Id
    @GeneratedValue
    private Long idManga;
    private String name;
}
