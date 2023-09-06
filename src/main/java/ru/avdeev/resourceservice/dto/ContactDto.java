package ru.avdeev.resourceservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.UUID;

@Data
public class ContactDto {

    private UUID id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UUID owner;
    private ContactTypeDto type;
    private String value;
    private String valueExt;
}
