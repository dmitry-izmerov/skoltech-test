package ru.skoltech.sensormeasurements.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MeasurementDto {
    private Long id;
    private Long subjectId;
    private Long sensorId;
    private LocalDateTime time;
    private BigDecimal value;
}
