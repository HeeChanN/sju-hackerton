package hackerton.saver.dto.subject;


import hackerton.saver.entity.Subject;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SubjectGetListElementResDto {
    private Long subjectId;
    private String subjectName;

    public SubjectGetListElementResDto(Subject subject) {
        this.subjectId = subject.getId();
        this.subjectName = subject.getName();
    }
}
