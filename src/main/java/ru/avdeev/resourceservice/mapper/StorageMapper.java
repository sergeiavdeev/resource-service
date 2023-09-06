package ru.avdeev.resourceservice.mapper;

import org.mapstruct.Mapper;
import ru.avdeev.resourceservice.dto.StorageDto;
import ru.avdeev.resourceservice.entiti.*;

@Mapper(componentModel = "spring")
public interface StorageMapper {

    StorageDto toDto(Storage storage);
    Storage toEntity(StorageDto storageDto);
}
