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
import ru.skoltech.sensormeasurements.dto.SensorDto;
import ru.skoltech.sensormeasurements.dto.SubjectDto;
import ru.skoltech.sensormeasurements.entity.Sensor;
import ru.skoltech.sensormeasurements.service.SensorService;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@RequestMapping("/sensors")
@RequiredArgsConstructor
@RestController
public class SensorController {

    private final SensorService sensorService;

    @PostMapping
    public ResponseEntity saveMeasurement(@RequestBody SubjectDto dto, HttpServletRequest request) {
        Sensor sensor = sensorService.save(dto);
        URI uri = UriComponentsBuilder.fromHttpRequest(new ServletServerHttpRequest(request))
            .path("/{id}")
            .buildAndExpand(sensor.getId())
            .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public SensorDto getById(@PathVariable Long id) {
        return sensorService.getById(id);
    }
}
