package ru.skoltech.sensormeasurements.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skoltech.sensormeasurements.dto.MeasurementDto;
import ru.skoltech.sensormeasurements.entity.Measurement;

import java.util.List;

@Mapper
public interface MeasurementMapper {
    MeasurementMapper INSTANCE = Mappers.getMapper(MeasurementMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "subject.id", target = "subjectId")
    @Mapping(source = "sensor.id", target = "sensorId")
    @Mapping(source = "time", target = "time")
    @Mapping(source = "value", target = "value")
    MeasurementDto toDto(Measurement entity);

    List<MeasurementDto> toDtoList(List<Measurement> entities);
}
