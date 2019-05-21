package ru.skoltech.sensormeasurements.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SensorDto {
    private Long id;
    private String name;
}
