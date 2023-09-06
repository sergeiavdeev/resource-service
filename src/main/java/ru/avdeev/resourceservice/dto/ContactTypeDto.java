package ru.avdeev.resourceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ContactTypeDto {

    private UUID id;
    private String name;
}
