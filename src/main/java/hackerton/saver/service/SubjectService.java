package hackerton.saver.service;


import hackerton.saver.dto.subject.SubjectCreateReqDto;
import hackerton.saver.entity.Subject;
import hackerton.saver.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public List<Subject> getAllSubjects(String serviceId) {
        return subjectRepository.findAllByServiceId(serviceId);
    }

    public void createSubject(SubjectCreateReqDto dto, String serviceId){
        if(subjectRepository.findByName(dto.getName()).isPresent()){
            throw new RuntimeException("해당 과목 이름이 이미 존재합니다.");
        }

        Subject subject = new Subject(dto,serviceId);
        subjectRepository.save(subject);
    }
}
