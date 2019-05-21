package ru.skoltech.sensormeasurements.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skoltech.sensormeasurements.dto.SubjectDto;
import ru.skoltech.sensormeasurements.entity.Subject;
import ru.skoltech.sensormeasurements.repository.SubjectRepository;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    @Transactional
    public Subject save(SubjectDto dto) {
        Subject subject = new Subject(dto.getName());
        subjectRepository.save(subject);
        return subject;
    }

    public SubjectDto getById(Long id) {
        Subject subject = subjectRepository.findById(id).orElseThrow(RuntimeException::new);
        return new SubjectDto(subject.getId(), subject.getName());
    }
}
