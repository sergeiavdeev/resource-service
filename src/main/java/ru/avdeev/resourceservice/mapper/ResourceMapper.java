package ru.avdeev.resourceservice.mapper;

import org.mapstruct.Mapper;
import ru.avdeev.resourceservice.dto.ResourceDto;
import ru.avdeev.resourceservice.entiti.Resource;

@Mapper(componentModel = "spring")
public interface ResourceMapper {

    ResourceDto toDto(Resource resource);
    Resource toEntity(ResourceDto resourceDto);
}
