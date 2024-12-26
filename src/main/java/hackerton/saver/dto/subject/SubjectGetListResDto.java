package hackerton.saver.dto.subject;

import hackerton.saver.entity.Subject;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class SubjectGetListResDto {
    List<SubjectGetListElementResDto> subjectList;

    public SubjectGetListResDto(List<Subject> subjectList) {
        this.subjectList = subjectList.stream().map(SubjectGetListElementResDto::new).toList();
    }
}
