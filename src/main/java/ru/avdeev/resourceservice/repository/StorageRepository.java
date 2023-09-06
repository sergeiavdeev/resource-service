package ru.avdeev.resourceservice.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import ru.avdeev.resourceservice.entiti.Resource;
import ru.avdeev.resourceservice.entiti.Storage;

import java.util.UUID;

@Repository
public interface StorageRepository extends ReactiveCrudRepository<Storage, UUID> {
}
