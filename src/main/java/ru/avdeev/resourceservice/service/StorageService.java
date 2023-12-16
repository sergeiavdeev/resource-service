package ru.avdeev.resourceservice.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.avdeev.resourceservice.dto.StorageDto;

import java.util.UUID;

public interface StorageService {
    Mono<StorageDto> getById(UUID id);
    Mono<StorageDto> add(StorageDto storage, UUID userId);
    Mono<StorageDto> update(StorageDto storage, UUID userId);
    Flux<StorageDto> getAll();
}
