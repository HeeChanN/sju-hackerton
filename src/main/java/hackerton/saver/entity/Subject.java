package hackerton.saver.entity;


import hackerton.saver.dto.subject.SubjectCreateReqDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String serviceId;

    private String text;

    @OneToMany(mappedBy = "subject",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Asset> assetList = new ArrayList<>();

    public Subject(SubjectCreateReqDto dto,String serviceId) {
        this.name = dto.getName();
        this.text = dto.getText();
        this.serviceId = serviceId;
        dto.getFileUrl().stream().map(o->new Asset(o,this)).forEach(assetList::add);
    }
}
