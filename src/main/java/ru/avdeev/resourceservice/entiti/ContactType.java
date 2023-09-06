package ru.avdeev.resourceservice.entiti;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("contact_type")
public record ContactType (

    @Id
    @Column("id")
    UUID id,

    @Column("name")
    String name
){}
