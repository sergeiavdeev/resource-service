package ru.avdeev.resourceservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.avdeev.resourceservice.dto.ContactDto;
import ru.avdeev.resourceservice.dto.ResourceDto;
import ru.avdeev.resourceservice.mapper.ResourceMapper;
import ru.avdeev.resourceservice.repository.ResourceRepository;
import ru.avdeev.resourceservice.service.ResourceService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceService {

    private final ResourceRepository repository;
    private final ResourceMapper mapper;
    @Override
    public Flux<ResourceDto> getByOwner(UUID storageId) {
        return repository.findAllByStorage(storageId)
                .map(mapper::toDto);
    }

    @Override
    public Flux<ResourceDto> add(List<ResourceDto> resources) {
        return Flux.fromIterable(resources)
                .flatMap(resourceDto -> repository.save(mapper.toEntity(resourceDto)))
                .map(mapper::toDto);
    }

    @Override
    public Mono<Void> deleteByStorage(UUID storageId) {
        return repository.deleteAllByStorage(storageId);
    }

}
