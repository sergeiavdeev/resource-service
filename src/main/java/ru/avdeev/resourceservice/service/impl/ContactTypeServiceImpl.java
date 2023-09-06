package ru.avdeev.resourceservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.avdeev.resourceservice.dto.ContactTypeDto;
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
    public Mono<ContactTypeDto> getById(UUID id) {
        return repository.findById(id)
                .map(mapper::toDto);
    }
}
