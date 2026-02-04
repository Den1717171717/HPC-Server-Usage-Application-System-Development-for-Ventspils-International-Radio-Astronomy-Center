package org.den.projectmvc.controller.user;


import lombok.RequiredArgsConstructor;
import org.den.projectmvc.dto.UserRequest;
import org.den.projectmvc.entities.organization.Department;
import org.den.projectmvc.entities.organization.Organization;
import org.den.projectmvc.entities.user.User;
import org.den.projectmvc.services.department.DepartmentServiceImpl;
import org.den.projectmvc.services.organization.OrganizationServiceImpl;
import org.den.projectmvc.services.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final DepartmentServiceImpl departmentService;
    private final OrganizationServiceImpl organizationService;

    @GetMapping("/create")
    public String createUserForm(Model model) {
        List<Department> departments = departmentService.findAll();
        List<Organization> organizations = organizationService.findAll();

        UserRequest userRequest = new UserRequest();
        userRequest.setDepartmentIds(new ArrayList<>());
        userRequest.setOrganizationIds(new ArrayList<>());

        model.addAttribute("userRequest", userRequest);
        model.addAttribute("departments", departments);
        model.addAttribute("organizations", organizations);

        return "users/create";
    }

    @PostMapping
    public ResponseEntity<UserRequest> create(@ModelAttribute UserRequest userRequest) {
        List<Department> departments = userRequest.getDepartmentIds().stream().map(departmentService::findById).toList();
        List<Organization> organizations = userRequest.getOrganizationIds().stream().map(organizationService::findById).toList();

        User user = new User();
        user.setName(userRequest.getName());
        user.setSurname(userRequest.getSurname());
        user.setAddress(userRequest.getAddress());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setEmail(userRequest.getEmail());
        //user.setDeleted(userRequest.getIsDeleted());
        user.setDepartments(departments);
        user.setOrganizations(organizations);

        userService.create(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(user));
    }

    private UserRequest toDto(User u) {
        if (u == null) return null;
        UserRequest dto = new UserRequest();
        dto.setId(u.getId());
        dto.setName(u.getName());
        dto.setSurname(u.getSurname());
        dto.setAddress(u.getAddress());
        dto.setPhoneNumber(u.getPhoneNumber());
        dto.setEmail(u.getEmail());
        //dto.setIsDeleted(u.getDeleted());
        dto.setDepartmentIds(u.getDepartments().stream().map(Department::getId).toList());
        dto.setOrganizationIds(u.getOrganizations().stream().map(Organization::getId).toList());
        return dto;
    }
}

