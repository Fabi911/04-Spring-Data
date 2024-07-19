package org.example.springdata;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CharactersRepo extends MongoRepository<Character, String> {

}

