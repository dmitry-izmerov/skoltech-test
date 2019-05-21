package ru.skoltech.sensormeasurements.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skoltech.sensormeasurements.dto.SensorDto;
import ru.skoltech.sensormeasurements.dto.SubjectDto;
import ru.skoltech.sensormeasurements.entity.Sensor;
import ru.skoltech.sensormeasurements.repository.SensorRepository;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class SensorService {
    private final SensorRepository sensorRepository;

    @Transactional
    public Sensor save(SubjectDto dto) {
        Sensor sensor = new Sensor(dto.getName());
        sensorRepository.save(sensor);
        return sensor;
    }

    public SensorDto getById(Long id) {
        Sensor sensor = sensorRepository.findById(id).orElseThrow(RuntimeException::new);
        return new SensorDto(sensor.getId(), sensor.getName());
    }
}
