package ru.avdeev.resourceservice.entiti.callback;

import lombok.NonNull;
import org.reactivestreams.Publisher;
import org.springframework.data.r2dbc.mapping.OutboundRow;
import org.springframework.data.r2dbc.mapping.event.BeforeSaveCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.r2dbc.core.Parameter;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import ru.avdeev.resourceservice.entiti.Contact;

import java.util.UUID;

@Component
public class ContactBeforeSave implements BeforeSaveCallback<Contact> {
    @Override
    @NonNull
    public Publisher<Contact> onBeforeSave(Contact entity, @NonNull OutboundRow row, @NonNull SqlIdentifier table) {
        if (entity.id() == null) {
            row.put("id", Parameter.from(UUID.randomUUID()));
        }
        return Mono.just(entity);
    }
}
