/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fontys.kwetter.resource;

import edu.fontys.kwetter.entitites.Tweet;
import edu.fontys.kwetter.repository.TweetRepository;
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
 * @author roy_s
 */
@RestController
public class TweetResource {

    @Autowired
    private TweetRepository tweetRepository;

    @GetMapping("/tweets")
    public List<Tweet> retrieveAllTweets() {
        return tweetRepository.findAll();
    }

    @GetMapping("/tweets/{id}")
    public Tweet retrieveTweet(@PathVariable long id) {
        Optional<Tweet> tweet = tweetRepository.findById(id);
        return tweet.get();
    }

    @PostMapping("/tweets")
    public ResponseEntity<Object> createUser(@RequestBody Tweet tweet) {
        Tweet savedTweet = tweetRepository.save(tweet);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedTweet.getId()).toUri();

        return ResponseEntity.created(location).build();

    }
}
