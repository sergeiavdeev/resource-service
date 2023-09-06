package ru.avdeev.resourceservice.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ru.avdeev.resourceservice.entiti.ContactType;

import java.util.UUID;

public interface ContactTypeRepository extends ReactiveCrudRepository<ContactType, UUID> {
}
