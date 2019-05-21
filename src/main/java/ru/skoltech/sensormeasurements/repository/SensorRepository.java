package ru.skoltech.sensormeasurements.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skoltech.sensormeasurements.entity.Sensor;

public interface SensorRepository extends JpaRepository<Sensor, Long> {
}
