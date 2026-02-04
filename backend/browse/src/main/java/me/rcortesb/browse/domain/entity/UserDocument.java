package me.rcortesb.browse.domain.entity;

import jakarta.persistence.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.util.List;

@Document(indexName = "users")
public class UserDocument {
    @Id
    private String id;

    @Field(type = FieldType.Integer)
    private int age;

    @Field(type = FieldType.Keyword)
    private String gender;

    @Field(type = FieldType.Keyword)
    private String sexualPreference;

    @Field
    private GeoPoint location;

    @Field(type = FieldType.Keyword)
    List<String> tags;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSexualPreference() {
        return sexualPreference;
    }

    public void setSexualPreference(String sexualPreference) {
        this.sexualPreference = sexualPreference;
    }

    public GeoPoint getLocation() {
        return location;
    }

    public void setLocation(GeoPoint location) {
        this.location = location;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "UserDocument{" +
                "id='" + id + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", sexualPreference='" + sexualPreference + '\'' +
                ", location=" + location +
                ", tags=" + tags +
                '}';
    }
}
/*
public record UserProfileUpdateDTO(String userId,
                                   int age,
                                   String gender,
                                   String genderPreference,
                                   double latitude,
                                   double longitude,
                                   List<String> tags) {}
 */