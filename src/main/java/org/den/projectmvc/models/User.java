package org.den.projectmvc.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String address;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(unique = true)
    private String email;


    @Column(name = "is_deleted")
    private Boolean isDeleted; //IDK if I need to make a field transient


    @CreationTimestamp // added in order to make the default timestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToMany
    @JoinTable(
            name = "user_departments",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "department_id")
    )
    private Set<Department> departments =new HashSet<>();


    @ManyToMany
    @JoinTable(
            name="user_organizations",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "organization_id")
    )
    private Set<Organization> organizations =new HashSet<>();


    @ManyToMany
    @JoinTable(name = "user_co_authors",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "co_author_id")


    )
    private Set<CoAuthor> coAuthors =new HashSet<>();


    @OneToMany(mappedBy="user" , cascade = CascadeType.ALL)
    Set<Application> applications =new HashSet<>();



}
