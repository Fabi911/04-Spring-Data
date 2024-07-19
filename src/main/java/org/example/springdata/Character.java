package org.example.springdata;

import lombok.Data;
import lombok.With;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "characters")
@With
public record Character(
        String id,
        String name,
        int age,
        String profession
) {

}
