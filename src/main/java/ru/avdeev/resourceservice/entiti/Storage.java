package ru.avdeev.resourceservice.entiti;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("storage")
public record Storage(
        @Id
        @Column("id")
        UUID id,

        @Column("owner")
        UUID owner,

        @Column("name")
        String name,

        @Column("address")
        String address,

        @Column("lat")
        Double lat,

        @Column("lng")
        Double lng,

        @Column("description")
        String description
){}