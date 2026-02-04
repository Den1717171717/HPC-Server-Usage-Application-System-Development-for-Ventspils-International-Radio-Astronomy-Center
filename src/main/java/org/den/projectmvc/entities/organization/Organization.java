package org.den.projectmvc.entities.organization;

import jakarta.persistence.*;
import lombok.Data;
import org.den.projectmvc.entities.user.User;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "organization")
@Data
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "organizations" , cascade = CascadeType.ALL)
     private Set<User> users = new HashSet<>();

}
