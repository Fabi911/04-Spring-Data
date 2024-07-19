package org.example.springdata;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CharactersRepo extends MongoRepository<Character, String> {

}

