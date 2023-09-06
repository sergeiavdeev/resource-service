package ru.avdeev.resourceservice.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.avdeev.resourceservice.dto.ContactDto;

import java.util.List;
import java.util.UUID;

public interface ContactService {

    Mono<ContactDto> getById(UUID id);
    Flux<ContactDto> getByOwner(UUID ownerId);
    Flux<ContactDto> add(List<ContactDto> contacts);

    Mono<ContactDto> add(ContactDto contact);

    Mono<Void> deleteByOwner(UUID owner);
}
