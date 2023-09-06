package ru.avdeev.resourceservice.entiti;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("resource")
public record Resource (

    @Id
    @Column("id")
    UUID id,

    @Column("name")
    String name,

    @Column("description")
    String description,

    @Column("storage")
    UUID storage
) {}
