package org.den.projectmvc.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "organization")
@Data
public class Organization {
    @Id
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "organizations")
     private Set<User> users = new HashSet<>();

}
