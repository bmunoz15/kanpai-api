package cl.ufro.dci.kanpaiapi.dto;

import cl.ufro.dci.kanpaiapi.model.Manga;
import cl.ufro.dci.kanpaiapi.model.Reader;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ReaderDto {

    private Long reaId;
    private String reaName;
    private String reaLastName;
    private String reaNickName;
    private Reader.Gender reaGender;
    private String reaBirthday;
    private List<Manga> reaMangas;

}
