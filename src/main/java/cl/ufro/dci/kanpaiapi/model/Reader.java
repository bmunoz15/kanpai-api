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
    private Gender reaGender;

    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date reaBirthday;


    @OneToMany(mappedBy = "manReader")
    @JsonManagedReference
    private List<Manga> reaMangas;

    private enum Gender {
        Male,
        Female,
        Other
    }
}
