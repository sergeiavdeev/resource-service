package ru.avdeev.resourceservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.avdeev.resourceservice.dto.ResourceDto;
import ru.avdeev.resourceservice.service.ResourceService;

import java.util.UUID;

@RestController
@RequestMapping("v1/resource")
@RequiredArgsConstructor
public class ResourceController {

    private final ResourceService resourceService;

    @GetMapping("{id}")
    public Mono<ResourceDto> getResource(@PathVariable UUID id) {
        return resourceService.getById(id);
    }
}
