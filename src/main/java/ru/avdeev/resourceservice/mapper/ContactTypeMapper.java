package ru.avdeev.resourceservice.mapper;

import org.mapstruct.Mapper;
import ru.avdeev.resourceservice.dto.ContactTypeDto;
import ru.avdeev.resourceservice.entiti.ContactType;

@Mapper(componentModel = "spring")
public interface ContactTypeMapper {

    ContactTypeDto toDto(ContactType type);
    ContactType toEntity(ContactTypeDto contactTypeDto);
}
