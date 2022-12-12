package cl.ufro.dci.kanpaiapi.dto;

import cl.ufro.dci.kanpaiapi.model.Manga;
import cl.ufro.dci.kanpaiapi.model.Publisher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class PublisherDto {

    private Long pubId;
    private String pubName;
    private String pubDescription;
    private Publisher.Type pubType;
    private List<Manga> pubMangas;
}
