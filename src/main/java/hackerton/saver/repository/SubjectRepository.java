package hackerton.saver.repository;

import hackerton.saver.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    List<Subject> findAllByServiceId(String serviceId);
    Optional<Subject> findByName(String name);
}
