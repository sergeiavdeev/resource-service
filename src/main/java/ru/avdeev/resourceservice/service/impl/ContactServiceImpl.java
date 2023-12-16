package ru.avdeev.resourceservice.service.impl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.avdeev.resourceservice.dto.ContactDto;
import ru.avdeev.resourceservice.entiti.Contact;
import ru.avdeev.resourceservice.mapper.ContactMapper;
import ru.avdeev.resourceservice.repository.ContactRepository;
import ru.avdeev.resourceservice.service.ContactService;
import ru.avdeev.resourceservice.service.ContactTypeService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository repository;
    private final ContactMapper mapper;
    private final ContactTypeService contactTypeService;
    @Override
    public Mono<ContactDto> getById(UUID id) {
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    @CircuitBreaker(name = "CONTACT_SERVICE")
    public Flux<ContactDto> getByOwner(UUID ownerId) {
        return repository.findAllByOwner(ownerId)
                .flatMap(this::setType);
    }

    @Override
    public Flux<ContactDto> add(List<ContactDto> contacts) {

        return Flux.fromIterable(contacts)
                .flatMap(contactDto -> repository.save(mapper.toEntity(contactDto)))
                .map(mapper::toDto);
    }

    @Override
    public Mono<ContactDto> add(ContactDto contact) {
        return repository.save(mapper.toEntity(contact))
                .map(mapper::toDto);
    }

    @Override
    public Mono<Void> deleteByOwner(UUID owner) {
        return repository.deleteAllByOwner(owner);
    }

    private Mono<ContactDto> setType(Contact contact) {
        return contactTypeService.getById(contact.contactType())
                .map(contactTypeDto -> {
                    ContactDto contactDto = mapper.toDto(contact);
                    contactDto.setType(contactTypeDto);
                    return contactDto;
                });
    }
}
