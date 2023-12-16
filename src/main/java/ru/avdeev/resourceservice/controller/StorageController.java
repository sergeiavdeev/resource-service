package ru.avdeev.resourceservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.avdeev.resourceservice.dto.StorageDto;
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
    public Mono<StorageDto> add(@RequestBody StorageDto storage, @AuthenticationPrincipal Jwt jwt) {
        UUID userId = UUID.fromString(jwt.getClaim("sub").toString());
        return service.add(storage, userId);
    }

    @PatchMapping("")
    public Mono<StorageDto> update(@RequestBody StorageDto storage,  @AuthenticationPrincipal Jwt jwt) {
        UUID userId = UUID.fromString(jwt.getClaim("sub").toString());
        return service.update(storage, userId);
    }
}
