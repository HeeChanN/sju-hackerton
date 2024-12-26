package hackerton.saver.dto.subject;


import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class SubjectCreateReqDto {
    private String name;
    private String text;
    List<String> fileUrl;
}
