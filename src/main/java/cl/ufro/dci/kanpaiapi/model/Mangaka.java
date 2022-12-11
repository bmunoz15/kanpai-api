package cl.ufro.dci.kanpaiapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Mangaka {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mankId;


   //private List<Manga> mankMangas;
}
