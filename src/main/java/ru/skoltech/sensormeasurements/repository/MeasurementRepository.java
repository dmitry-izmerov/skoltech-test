package ru.skoltech.sensormeasurements.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.skoltech.sensormeasurements.entity.AvgMeasurement;
import ru.skoltech.sensormeasurements.entity.Measurement;

import java.time.LocalDateTime;
import java.util.List;

public interface MeasurementRepository extends JpaRepository<Measurement, Long> {

    List<Measurement> findBySensorIdAndTimeBetween(Long sensorId, LocalDateTime from, LocalDateTime to);

    List<Measurement> findBySubjectId(Long subjectId);

    @Query(
        value = "select m.subject_id, m.sensor_id, avg(m.value) " +
            "from measurements m " +
            "group by m.subject_id, m.sensor_id",
        nativeQuery = true
    )
    List<AvgMeasurement> findAvgMeasurementsForSubjects();
}
