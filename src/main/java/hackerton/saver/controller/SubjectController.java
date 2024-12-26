package hackerton.saver.controller;


import hackerton.saver.dto.subject.SubjectCreateReqDto;
import hackerton.saver.dto.subject.SubjectGetListResDto;
import hackerton.saver.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    @GetMapping
    public SubjectGetListResDto getSubjectList(@RequestHeader("Service-Id") String serviceId){
        // 헤더로 전달된 서비스 ID 출력
        System.out.println("Service ID: " + serviceId);
        return new SubjectGetListResDto(subjectService.getAllSubjects(serviceId));
    }

    @PostMapping
    public void createSubject(@RequestHeader("Service-Id") String serviceId,
                              @RequestBody SubjectCreateReqDto reqDto){
        subjectService.createSubject(reqDto, serviceId);
    }
}
