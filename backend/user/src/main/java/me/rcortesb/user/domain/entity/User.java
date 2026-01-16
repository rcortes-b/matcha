package me.rcortesb.user.domain.entity;

import jakarta.persistence.*;
import me.rcortesb.user.domain.common.EGender;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "profile_user")
public class User {
    public User() {}

    public User(UUID id, String email, String firstName, String lastName) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.createdAt = Instant.now();
    }

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "email")
    private String email;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private EGender gender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sexual_preference_id")
    private SexualPreference sexualPreference;

    @Column(name = "biography")
    private String biography;

    @Column(name = "created_at")
    private Instant createdAt;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public EGender getGender() {
        return gender;
    }

    public void setGender(EGender gender) {
        this.gender = gender;
    }

    public SexualPreference getSexualPreference() {
        return sexualPreference;
    }

    public void setSexualPreference(SexualPreference sexualPreference) {
        this.sexualPreference = sexualPreference;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", gender=" + gender +
                ", sexualPreference=" + sexualPreference +
                ", biography='" + biography + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    /*
    * Process date, SexualPreference, Gender methods...
    * */
}
