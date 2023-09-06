package ru.avdeev.resourceservice.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.avdeev.resourceservice.dto.StorageDto;
import ru.avdeev.resourceservice.entiti.Storage;

import java.util.UUID;

public interface StorageService {
    Mono<StorageDto> getById(UUID id);
    Mono<StorageDto> add(StorageDto storage);
    Mono<StorageDto> update(StorageDto storage);
    Flux<StorageDto> getAll();
}
