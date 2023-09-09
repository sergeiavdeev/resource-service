package ru.avdeev.resourceservice.service.impl;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import ru.avdeev.resourceservice.configuration.ServicesTestConfiguration;
import ru.avdeev.resourceservice.entiti.ContactType;
import ru.avdeev.resourceservice.exception.ResourceNotFoundException;
import ru.avdeev.resourceservice.repository.ContactTypeRepository;
import ru.avdeev.resourceservice.service.ContactTypeService;
import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
@Import(ServicesTestConfiguration.class)
class ContactTypeServiceImplTest {

    @Autowired
    private ContactTypeRepository repository;

    @Autowired
    private ContactTypeService service;
    @Test
    void getById() {

        UUID id = UUID.randomUUID();
        UUID failId = UUID.randomUUID();

        Mockito.when(repository.findById(id))
                .thenReturn(Mono.just(new ContactType(id, "Test")));

        StepVerifier.create(service.getById(id))
                .expectSubscription()
                .assertNext(contactType -> Assertions.assertEquals(contactType.getId(), id))
                .verifyComplete();

        Mockito.when(repository.findById(failId))
                .thenReturn(Mono.empty());

        StepVerifier.create(service.getById(failId))
                .expectErrorMatches(throwable -> throwable instanceof ResourceNotFoundException)
                .verify();
    }

    @Test
    void getAll() {

        Mockito.when(repository.findAll())
                .thenReturn(Flux.just(
                        new ContactType(UUID.randomUUID(), "Test 1"),
                        new ContactType(UUID.randomUUID(), "Test 2")));

        StepVerifier.create(service.getAll())
                .expectSubscription()
                .assertNext(type -> Assertions.assertNotNull(type.getId()))
                .assertNext(type -> Assertions.assertNotNull(type.getId()))
                .verifyComplete();
    }
}