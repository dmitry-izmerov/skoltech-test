package ru.skoltech.sensormeasurements.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import ru.skoltech.sensormeasurements.dto.SubjectDto;
import ru.skoltech.sensormeasurements.entity.Subject;
import ru.skoltech.sensormeasurements.service.SubjectService;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@RequestMapping("/subjects")
@RequiredArgsConstructor
@RestController
public class SubjectController {

    private final SubjectService subjectService;

    @PostMapping
    public ResponseEntity saveMeasurement(@RequestBody SubjectDto dto, HttpServletRequest request) {
        Subject subject = subjectService.save(dto);
        URI uri = UriComponentsBuilder.fromHttpRequest(new ServletServerHttpRequest(request))
            .path("/{id}")
            .buildAndExpand(subject.getId())
            .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public SubjectDto getById(@PathVariable Long id) {
        return subjectService.getById(id);
    }
}
