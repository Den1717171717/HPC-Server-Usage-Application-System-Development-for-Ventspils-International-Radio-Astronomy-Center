package org.den.projectmvc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private Long id;
    private String name;
    private String surname;
    private String address;
    private String phoneNumber;
    private String email;
    private Boolean isDeleted;
    private List<Long> departmentIds;
    private List<Long> organizationIds;


}
