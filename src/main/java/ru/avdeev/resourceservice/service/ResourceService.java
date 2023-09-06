package ru.avdeev.resourceservice.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.avdeev.resourceservice.dto.ContactDto;
import ru.avdeev.resourceservice.dto.ResourceDto;

import java.util.List;
import java.util.UUID;

public interface ResourceService {

    Flux<ResourceDto> getByOwner(UUID storageId);

    Flux<ResourceDto> add(List<ResourceDto> contacts);

    Mono<Void> deleteByStorage(UUID storageId);
}
