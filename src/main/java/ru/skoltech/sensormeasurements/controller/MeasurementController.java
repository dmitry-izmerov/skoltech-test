package ru.skoltech.sensormeasurements.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import ru.skoltech.sensormeasurements.dto.MeasurementDto;
import ru.skoltech.sensormeasurements.entity.Measurement;
import ru.skoltech.sensormeasurements.service.MeasurementService;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/measurements")
@RequiredArgsConstructor
@RestController
public class MeasurementController {

    private final MeasurementService measurementService;

    @PostMapping
    public ResponseEntity saveMeasurement(@RequestBody MeasurementDto dto, HttpServletRequest request) {
        Measurement measurement = measurementService.saveMeasurement(dto);
        URI uri = UriComponentsBuilder.fromHttpRequest(new ServletServerHttpRequest(request))
            .path("/{id}")
            .buildAndExpand(measurement.getId())
            .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public MeasurementDto getById(@PathVariable Long id) {
        return measurementService.getById(id);
    }

    @GetMapping("/sensors/{id}")
    public List<MeasurementDto> getMeasurementsOfSensor(
        @PathVariable Long id,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to
    ) {
        return measurementService.getMeasurementsOfSensor(id, from, to);
    }

    @GetMapping("/subjects/{id}")
    public List<MeasurementDto> getMeasurementsOfSubject(@PathVariable Long id) {
        return measurementService.getMeasurementsOfSubject(id);
    }

    @GetMapping("/avgForSubjects")
    public List<MeasurementDto> getAvgMeasurementsForSubjects() {
        return measurementService.getAvgMeasurementsForSubjects();
    }
}
