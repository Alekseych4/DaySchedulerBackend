package com.example.dayschedulerbackend.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Set;
import java.util.UUID;

@Entity(name = "tasks")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Task {
    @Id
    @Column(name = "id")
    private UUID id;
    @Column(name = "title")
    private String title;
    @Column(name = "start_time")
    private long startTime;
    @Column(name = "end_time")
    private Long endTime;
    @Column(name = "is_reminder")
    private boolean isReminder;
    @Column(name = "is_anchor")
    private boolean isAnchor;
    @Column(name = "tag")
    private String tag;
    @Column(name = "description")
    private String description;
    @ManyToMany
    @JoinTable(
            name = "users_tasks_mtm",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public int hashCode() {
        return id.hashCode() + user.hashCode() + users.hashCode() + title.hashCode() + (int) startTime;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Task)) {
            return false;
        } else {
            Task other = (Task) o;
            return other.hashCode() == hashCode();
        }
    }
}
