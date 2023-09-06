package ru.avdeev.resourceservice.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.avdeev.resourceservice.entiti.Contact;
import java.util.UUID;

public interface ContactRepository extends ReactiveCrudRepository<Contact, UUID> {

    Flux<Contact> findAllByOwner(UUID id);
    Mono<Void> deleteAllByOwner(UUID id);
}
