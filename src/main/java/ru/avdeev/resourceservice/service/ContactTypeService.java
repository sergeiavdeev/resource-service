package ru.avdeev.resourceservice.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.avdeev.resourceservice.dto.ContactTypeDto;

import java.util.UUID;

public interface ContactTypeService {

    Mono<ContactTypeDto> getById(UUID id);
    Flux<ContactTypeDto> getAll();
}
