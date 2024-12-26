package hackerton.saver.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subjec_id")
    private Subject subject;

    public Asset(String url, Subject subject) {
        this.url = url;
        this.subject = subject;
    }
}
