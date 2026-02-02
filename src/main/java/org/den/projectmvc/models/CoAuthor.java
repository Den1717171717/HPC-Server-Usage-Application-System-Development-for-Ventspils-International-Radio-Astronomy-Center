package org.den.projectmvc.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "co_author")
@Data
public class CoAuthor {
    @Id
    private Long id;
    private String name;
    private String email;


    @Column(name = "is_from_university")
    private Boolean isFromUniversity;

    @ManyToMany(mappedBy = "coAuthors")
    Set<User> users = new HashSet<>();
}