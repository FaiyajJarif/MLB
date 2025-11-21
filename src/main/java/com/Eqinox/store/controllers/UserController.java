package com.Eqinox.store.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Eqinox.store.dtos.UserDto;
import com.Eqinox.store.entities.User;
import com.Eqinox.store.mappers.UserMapper;
import com.Eqinox.store.repositories.UserRepository;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    
    @GetMapping
    public Iterable<UserDto> getAllUsers(
        @RequestParam(required = false) String sort
    ) {
        if (sort == null || !Set.of("name", "email").contains(sort)) {
            sort = "name";
        }        

        return userRepository.findAll(Sort.by(sort)).stream().map(userMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        var user = userRepository.findById(id);
        if(user == null || user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userMapper.toDto(user.get()));
    }
}
