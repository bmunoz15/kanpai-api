package cl.ufro.dci.kanpaiapi.model;

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

    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date reaBirthday;


    @OneToMany(targetEntity = Manga.class, mappedBy = "manReader", fetch = FetchType.EAGER,cascade = CascadeType.REFRESH)
    private List<Manga> reaMangas;

    private enum Gender {
        Male,
        Female,
        Other
    }
}
