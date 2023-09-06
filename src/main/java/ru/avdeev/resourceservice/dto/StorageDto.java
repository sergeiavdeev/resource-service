package ru.avdeev.resourceservice.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class StorageDto {

    private UUID id;
    private UUID owner;
    private String name;
    private String address;
    private Double lat;
    private Double lng;
    private String description;

    private List<ContactDto> contacts;
    private List<ResourceDto> resources;
}
