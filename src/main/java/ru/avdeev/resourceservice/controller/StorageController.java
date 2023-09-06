package ru.avdeev.resourceservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.avdeev.resourceservice.dto.StorageDto;
import ru.avdeev.resourceservice.entiti.Storage;
import ru.avdeev.resourceservice.service.StorageService;

import java.util.UUID;

@RestController
@RequestMapping("v1/storage")
@RequiredArgsConstructor
public class StorageController {

    private final StorageService service;

    @GetMapping("")
    public Flux<StorageDto> getAll() {

        return service.getAll();
    }
    @GetMapping("{id}")
    public Mono<StorageDto> getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PostMapping("")
    public Mono<StorageDto> add(@RequestBody StorageDto storage) {
        return service.add(storage);
    }

    @PatchMapping("")
    public Mono<StorageDto> update(@RequestBody StorageDto storage) {
        return service.update(storage);
    }
}
