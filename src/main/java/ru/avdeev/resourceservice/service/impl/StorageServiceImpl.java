package ru.avdeev.resourceservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.avdeev.resourceservice.dto.ContactDto;
import ru.avdeev.resourceservice.dto.StorageDto;
import ru.avdeev.resourceservice.exception.ResourceNotFoundException;
import ru.avdeev.resourceservice.mapper.StorageMapper;
import ru.avdeev.resourceservice.repository.StorageRepository;
import ru.avdeev.resourceservice.service.ContactService;
import ru.avdeev.resourceservice.service.ResourceService;
import ru.avdeev.resourceservice.service.StorageService;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {

    private final StorageRepository repository;
    private final StorageMapper storageMapper;
    private final ContactService contactService;
    private final ResourceService resourceService;

    @Override
    @Cacheable(cacheNames = "storage")
    public Mono<StorageDto> getById(UUID id) {
        return repository
                .findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Storage id: %s not found", id)))
                .map(storageMapper::toDto)
                .flatMap(this::setContacts)
                .flatMap(this::setResources)
                .cache(Duration.ofMinutes(10));
    }

    @Override
    @Transactional
    public Mono<StorageDto> add(StorageDto storage, UUID userId) {
        storage.setOwner(userId);
        return repository.save(storageMapper.toEntity(storage))
                .map(storageMapper::toDto)
                .map(storageDto -> {
                    storageDto.setContacts(storage.getContacts());
                    storageDto.setResources(storage.getResources());
                    return storageDto;
                })
                .flatMap(this::saveContacts)
                .flatMap(this::saveResources);
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = "storage", key = "#storage.getId()")
    public Mono<StorageDto> update(StorageDto storage, UUID userId) {
        return contactService.deleteByOwner(storage.getId())
                .then(resourceService.deleteByStorage(storage.getId()))
                .then(add(storage, userId));
    }

    @Override
    public Flux<StorageDto> getAll() {
        return repository.findAll()
                .map(storageMapper::toDto);
    }

    private Mono<StorageDto> setContacts(StorageDto storageDto) {
        return contactService.getByOwner(storageDto.getId())
                .collectList()
                .map(contacts -> {
                    storageDto.setContacts(contacts);
                    return storageDto;
                });
    }

    private Mono<StorageDto> setResources(StorageDto storageDto) {
        return resourceService.getByOwner(storageDto.getId())
                .collectList()
                .map(resources -> {
                    storageDto.setResources(resources);
                    return storageDto;
                });
    }

    private StorageDto setContactsOwner(StorageDto storage, UUID id) {
        storage.getContacts().forEach(contactDto -> contactDto.setOwner(id));
        return storage;
    }

    private StorageDto setResourceOwner(StorageDto storage, UUID id) {
        storage.getResources().forEach(resourceDto -> resourceDto.setStorage(id));
        return storage;
    }

    private Mono<StorageDto> saveContacts(StorageDto storage) {

        return Mono.just(setContactsOwner(storage, storage.getId()))
                .flatMap(storageDto -> contactService.add(storageDto.getContacts()).collectList())
                .map(contactList -> setContactTypes(storage, contactList));
    }

    private Mono<StorageDto> saveResources(StorageDto storage) {

        return Mono.just(setResourceOwner(storage, storage.getId()))
                .flatMap(storageDto -> resourceService.add(storageDto.getResources()).collectList())
                .map(resourceList -> {
                    storage.setResources(resourceList);
                    return storage;
                });
    }

    private StorageDto setContactTypes(StorageDto storage, List<ContactDto> contacts) {

        contacts.forEach(contactDto -> {
            Optional<ContactDto> contact = storage.getContacts().stream()
                    .filter(value -> value.getType().getId()
                            .equals(contactDto.getType().getId()))
                    .findFirst();
            contact.ifPresent(dto -> contactDto.setType(dto.getType()));
        });
        storage.setContacts(contacts);
        return storage;
    }
}
