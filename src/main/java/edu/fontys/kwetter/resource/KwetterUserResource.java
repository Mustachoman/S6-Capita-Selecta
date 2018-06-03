/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fontys.kwetter.resource;

import edu.fontys.kwetter.entitites.KwetterUser;
import edu.fontys.kwetter.repository.KwetterUserRepository;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author Marijn
 */
@RestController
public class KwetterUserResource {

    @Autowired
    private KwetterUserRepository kwetterUserRepository;

    @GetMapping("/users")
    public List<KwetterUser> retrieveAllUsers() {
        return kwetterUserRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public KwetterUser retrieveStudent(@PathVariable long id) {
        Optional<KwetterUser> user = kwetterUserRepository.findById(id);
        return user.get();
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody KwetterUser user) {
        KwetterUser savedUser = kwetterUserRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();

    }
}
