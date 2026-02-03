package me.rcortesb.browsing.domain.entity;

import jakarta.persistence.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.UUID;

@Document(indexName = "users")
public class UserDocument {
    @Id
    private UUID id;

    @Field(type = FieldType.Integer)
    private int age;
}
