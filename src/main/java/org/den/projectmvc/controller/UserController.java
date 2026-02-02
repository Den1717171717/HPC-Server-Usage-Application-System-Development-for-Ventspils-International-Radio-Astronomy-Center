package org.den.projectmvc.controller;


import org.den.projectmvc.DTO.UserDTO;
import org.den.projectmvc.models.User;
import org.den.projectmvc.services.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {

        return ResponseEntity.ok(
                userService.findAll().stream().map(user -> new UserDTO(user.getId() ,
                         user.getName() ,
                         user.getSurname() ,
                         user.getAddress() ,
                         user.getPhoneNumber() ,
                         user.getEmail() , user.getIsDeleted())).toList() // for demonstration purposes i added method isDeleted,but you should delete field "isDelete" in UserDTO
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(toDto(userService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody User user) { //idk if i should catch by UserDTO or just a User

       // User created = userService.create(toEntity(dto));
        userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(user));
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody User user) {
        User updated = userService.update(id, user);
        return ResponseEntity.ok(toDto(updated));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }



    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleBadRequest(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }


    private UserDTO toDto(User u) {
        if (u == null)
            return null;
        UserDTO dto = new UserDTO();
        dto.setId(u.getId());
        dto.setName(u.getName());
        dto.setSurname(u.getSurname());
        dto.setAddress(u.getAddress());
        dto.setPhoneNumber(u.getPhoneNumber());
        dto.setEmail(u.getEmail());
        return dto;
    }

    private User toEntity(UserDTO dto) {
        if (dto == null)
            return null;
        User u = new User();
        u.setId(dto.getId());
        u.setName(dto.getName());
        u.setSurname(dto.getSurname());
        u.setAddress(dto.getAddress());
        u.setPhoneNumber(dto.getPhoneNumber());
        u.setEmail(dto.getEmail());
        return u;
    }

}
