package cl.ufro.dci.kanpaiapi.model;

import cl.ufro.dci.kanpaiapi.dto.PublisherDto;
import cl.ufro.dci.kanpaiapi.dto.ReaderDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reaId;

    private String reaName;
    private String reaLastName;

    private String reaNickName;
    @Enumerated(EnumType.STRING)
    private Gender reaGender;

    private String reaBirthday;


    @OneToMany(targetEntity = Manga.class, mappedBy = "manReader", fetch = FetchType.EAGER,cascade = CascadeType.REFRESH)
    private List<Manga> reaMangas;

    public enum Gender {
        Male,
        Female,
        Other
    }
    public ReaderDto toDto(){
        //cambiar dto
        return new ReaderDto(
        this.reaId,
        this.reaName,
        this.reaLastName,
        this.reaNickName,
        this.reaGender,
        this.reaBirthday,
        this.reaMangas
        );
    }
}
