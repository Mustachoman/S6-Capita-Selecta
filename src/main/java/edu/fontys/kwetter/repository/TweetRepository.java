/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fontys.kwetter.repository;

import edu.fontys.kwetter.entitites.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Marijn
 */
@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {}
