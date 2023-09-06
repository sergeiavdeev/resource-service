package ru.avdeev.resourceservice.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.avdeev.resourceservice.entiti.Resource;

import java.util.UUID;

@Repository
public interface ResourceRepository extends ReactiveCrudRepository<Resource, UUID> {
    Flux<Resource> findAllByStorage(UUID storageId);
    Mono<Void> deleteAllByStorage(UUID storageId);
}
