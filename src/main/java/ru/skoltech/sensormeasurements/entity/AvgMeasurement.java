package ru.skoltech.sensormeasurements.entity;

import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;

public interface AvgMeasurement {
    @Value("#{target.subject_id}")
    Long getSubjectId();

    @Value("#{target.sensor_id}")
    Long getSensorId();

    BigDecimal getAvg();
}
