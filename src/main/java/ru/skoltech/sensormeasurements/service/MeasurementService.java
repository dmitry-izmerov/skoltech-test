package ru.skoltech.sensormeasurements.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skoltech.sensormeasurements.dto.MeasurementDto;
import ru.skoltech.sensormeasurements.entity.AvgMeasurement;
import ru.skoltech.sensormeasurements.entity.Measurement;
import ru.skoltech.sensormeasurements.entity.Sensor;
import ru.skoltech.sensormeasurements.entity.Subject;
import ru.skoltech.sensormeasurements.mapper.MeasurementMapper;
import ru.skoltech.sensormeasurements.repository.MeasurementRepository;
import ru.skoltech.sensormeasurements.repository.SensorRepository;
import ru.skoltech.sensormeasurements.repository.SubjectRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final SensorRepository sensorRepository;
    private final SubjectRepository subjectRepository;

    @Transactional
    public Measurement saveMeasurement(MeasurementDto dto) {
        Subject subject = subjectRepository.getOne(dto.getSubjectId());
        Sensor sensor = sensorRepository.getOne(dto.getSensorId());
        Measurement measurement = new Measurement(subject, sensor, dto.getTime(), dto.getValue());
        measurementRepository.save(measurement);
        return measurement;
    }

    public MeasurementDto getById(Long id) {
        Measurement measurement = measurementRepository.findById(id).orElseThrow(RuntimeException::new);
        return MeasurementMapper.INSTANCE.toDto(measurement);
    }

    public List<MeasurementDto> getMeasurementsOfSensor(Long id, LocalDateTime from, LocalDateTime to) {
        List<Measurement> measurements = measurementRepository.findBySensorIdAndTimeBetween(id, from, to);
        return MeasurementMapper.INSTANCE.toDtoList(measurements);
    }

    public List<MeasurementDto> getMeasurementsOfSubject(Long id) {
        List<Measurement> measurements = measurementRepository.findBySubjectId(id);
        return MeasurementMapper.INSTANCE.toDtoList(measurements);
    }

    public List<MeasurementDto> getAvgMeasurementsForSubjects() {
        List<AvgMeasurement> measurements = measurementRepository.findAvgMeasurementsForSubjects();
        return measurements.stream()
            .map(item -> new MeasurementDto(null, item.getSubjectId(), item.getSensorId(), null, item.getAvg()))
            .collect(Collectors.toList());
    }
}
