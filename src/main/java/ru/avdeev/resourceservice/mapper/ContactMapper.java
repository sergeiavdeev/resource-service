package ru.avdeev.resourceservice.mapper;

import org.mapstruct.*;
import ru.avdeev.resourceservice.dto.ContactDto;
import ru.avdeev.resourceservice.entiti.Contact;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ContactMapper {

    //ContactTypeDto.ContactTypeDtoBuilder().UUID(contact.contactType()).build();
    @Mappings({
            @Mapping(target="type", expression="java(new ContactTypeDto(contact.contactType(), null))")
    })
    ContactDto toDto(Contact contact);

    @Mappings({
            @Mapping(target="contactType", source="contactDto.type.id")
    })
    Contact toEntity(ContactDto contactDto);
}
