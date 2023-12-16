package ru.avdeev.resourceservice.service.impl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.avdeev.resourceservice.dto.ContactTypeDto;
import ru.avdeev.resourceservice.exception.ResourceNotFoundException;
import ru.avdeev.resourceservice.mapper.ContactTypeMapper;
import ru.avdeev.resourceservice.repository.ContactTypeRepository;
import ru.avdeev.resourceservice.service.ContactTypeService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContactTypeServiceImpl implements ContactTypeService {

    private final ContactTypeRepository repository;
    private final ContactTypeMapper mapper;
    @Override
    @Cacheable(cacheNames = "contact-type")
    public Mono<ContactTypeDto> getById(UUID id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(
                        new ResourceNotFoundException("Contact type id: %s not found!", id))
                )
                .map(mapper::toDto)
                .cache();
    }

    @Override
    @Cacheable(cacheNames = "contact-type")
    @CircuitBreaker(name = "CONTACT_TYPE_SERVICE", fallbackMethod = "fallback")
    public Flux<ContactTypeDto> getAll() {
        return repository.findAll()
                .map(mapper::toDto)
                .cache()
                ;
    }

    public Flux<ContactTypeDto> fallback(Throwable e) {

        return Flux.empty();
    }
}
