package com.afb.DocApp.controller;

import com.afb.DocApp.domain.dto.User.GetUserResource;
import com.afb.DocApp.domain.model.User;
import com.afb.DocApp.domain.dto.User.CreateUserResource;
import com.afb.DocApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping
    public ResponseEntity<CreateUserResource> registerUser(@Valid @RequestBody CreateUserResource userResource, UriComponentsBuilder uriComponentsBuilder){
        User user = userService.save(userResource);
        URI uri = uriComponentsBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new CreateUserResource(user));
    }

    @GetMapping
    public ResponseEntity<List<GetUserResource>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetUserResource> getUser(@PathVariable Long id){
        GetUserResource user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("getTotalOfMedicalHistoriesByUser/{userId}")
    public Integer getTotalOfMedicalHistoriesByUser(@PathVariable Long userId){
        return userService.getTotalOfMedicalHistoriesByUser(userId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser (@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
