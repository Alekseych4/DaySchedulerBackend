package com.example.dayschedulerbackend.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@Entity(name = "users")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Column(name = "id")
    private UUID id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "id_in_service")
    private String idInService;
    @Column(name = "email")
    private String email;
    @Column(name = "profile_image_url")
    private String profileImageUrl;
    @ManyToMany(mappedBy = "users")
    private Set<Task> tasks;
    @OneToMany(mappedBy = "user")
    private Collection<Task> createdByUserTasks;

    @Override
    public int hashCode() {
        return id.hashCode() + email.hashCode() + idInService.hashCode() + name.hashCode() + surname.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof User)) {
            return false;
        } else {
            User other = (User) o;
            return other.hashCode() == hashCode();
        }
    }
}
