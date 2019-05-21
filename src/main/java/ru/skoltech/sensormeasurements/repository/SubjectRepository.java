package ru.skoltech.sensormeasurements.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skoltech.sensormeasurements.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
