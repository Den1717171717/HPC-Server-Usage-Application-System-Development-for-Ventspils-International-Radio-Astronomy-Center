package org.den.projectmvc.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "hpc_server_usage")
@Data
public class HpcServerUsage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

     private Long id;


}
