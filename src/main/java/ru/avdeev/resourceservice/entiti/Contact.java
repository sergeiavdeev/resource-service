package ru.avdeev.resourceservice.entiti;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("contacts")
public record Contact (

    @Id
    @Column("id")
    UUID id,

    @Column("owner")
    UUID owner,

    @Column("contact_type")
    UUID contactType,

    @Column("contact_value")
    String value,

    @Column("contact_value_ext")
    String valueExt
){}
